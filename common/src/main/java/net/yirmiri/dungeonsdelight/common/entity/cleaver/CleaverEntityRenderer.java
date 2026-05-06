package net.yirmiri.dungeonsdelight.common.entity.cleaver;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;

public class CleaverEntityRenderer extends EntityRenderer<CleaverEntity> {
    private final ItemRenderer itemRenderer;

    public CleaverEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(CleaverEntity cleaverEntity, float yaw, float ticks, PoseStack stack, MultiBufferSource bufferSource, int i) {
        super.render(cleaverEntity, yaw, ticks, stack, bufferSource, i);
        stack.pushPose();

        stack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(ticks, cleaverEntity.yRotO, cleaverEntity.getYRot()) - 90.0F));
        stack.translate(0, 0.2, 0);
        if (cleaverEntity.isInGround()) {
            float rotation = -100;
            if (cleaverEntity.blockSide != null) {
                Direction direction = cleaverEntity.blockSide;
                if (direction == Direction.UP) {
                    rotation = -190;
                } else if (direction == Direction.DOWN) {
                    rotation = -10;
                }
            }
            stack.mulPose(Axis.ZP.rotationDegrees(rotation + cleaverEntity.embeddedRotOffset));
            if (cleaverEntity.blockSide != null) {
                if (cleaverEntity.blockSide == Direction.DOWN) {
                    stack.translate(0, -0.5, 0);
                } else if (cleaverEntity.blockSide != Direction.UP) {
                    stack.translate(0, -0.2, 0);
                }
            }
            cleaverEntity.spinning = true;
        } else if (cleaverEntity.spinning) {
            float spin = ((cleaverEntity.tickCount + ticks) * (Math.min(-60F + ((cleaverEntity.tickCount + ticks) / 4), -32) >= -32 ? 0 : Math.min(-60F + ((cleaverEntity.tickCount + ticks) / 4), -32)));
            stack.mulPose(Axis.ZP.rotationDegrees(spin));
        }

        if (cleaverEntity.isInGround() && cleaverEntity.ricochetsLeft == 0) {
            float shakeTime = (float) cleaverEntity.shakeTime - ticks;
            if (shakeTime > 0.0F) {
                float f10 = -Mth.sin(shakeTime * 1.5f) * shakeTime;
                stack.translate(0, 0.2, 0);
                stack.mulPose(Axis.XN.rotationDegrees(f10));
                stack.translate(0, -0.2, 0);
            }
        }

        itemRenderer.render(cleaverEntity.getCleaverStack(), ItemDisplayContext.FIXED, false, stack, bufferSource, i, OverlayTexture.NO_OVERLAY,
                itemRenderer.getModel(cleaverEntity.getCleaverStack().copy(), cleaverEntity.level(), null, cleaverEntity.getId()));

        stack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(CleaverEntity entity) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}