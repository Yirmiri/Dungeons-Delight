package net.yirmiri.dungeonsdelight.core.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    @Final
    private RenderBuffers renderBuffers;

    @Shadow
    private ClientLevel level;

    @Redirect(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 4))
    private void dungeonsdelight$levelEvent(ClientLevel instance, ParticleOptions p_104706_, double x, double y, double z, double dx, double dy, double dz) {
        if (DungeonsDelight.CONFIG.getSpawnersEmitLivingFlames()) {
            instance.addParticle(DDParticles.LIVING_FLAME.get(), x, y, z, dx, dy, dz);
        }
    }

    @Shadow
    private static void renderShape(PoseStack poseStack, VertexConsumer consumer, VoxelShape shape, double x, double y, double z, float red, float green, float blue, float alpha) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Inject(method = "renderLevel", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", ordinal = 3))
    private void dungeonsdelight$renderLevel(PoseStack poseStack, float partialTick, long finishNanoTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f projectionMatrix, CallbackInfo ci) {
        HitResult hitresult = this.minecraft.hitResult;

        if (renderBlockOutline && hitresult != null && hitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = ((BlockHitResult) hitresult).getBlockPos();
            if (this.level.getBlockState(pos).is(DDBlocks.MONSTER_POT.get()) || this.level.getBlockState(pos).is(DDBlocks.TELEPOTAGE_BLOCK.get())) {

                RenderSystem.enableDepthTest();
                RenderSystem.depthMask(false);

                renderShape(poseStack, this.renderBuffers.bufferSource().getBuffer(
                                RenderType.lines()), this.level.getBlockState(pos).getShape(this.level, pos, CollisionContext.of(camera.getEntity())), (double)
                                pos.getX() - camera.getPosition().x, (double) pos.getY() - camera.getPosition().y, (double) pos.getZ() - camera.getPosition().z,
                        0.0F, 0.0F, 0.0F, 0.4F);

                this.renderBuffers.bufferSource().endBatch(RenderType.lines());
            }
        }
    }

    @Inject(method = "renderHitOutline", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$renderHitOutline(PoseStack poseStack, VertexConsumer consumer, Entity entity, double camX, double camY, double camZ, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (state.is(DDBlocks.MONSTER_POT.get()) || state.is(DDBlocks.TELEPOTAGE_BLOCK.get())) {
            ci.cancel();
        }
    }
}