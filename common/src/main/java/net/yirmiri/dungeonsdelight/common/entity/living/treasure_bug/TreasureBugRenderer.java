package net.yirmiri.dungeonsdelight.common.entity.living.treasure_bug;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;

public class TreasureBugRenderer extends MobRenderer<TreasureBugEntity, TreasureBugModel<TreasureBugEntity>> {
    public TreasureBugRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new TreasureBugModel<>(ctx.bakeLayer(DDModelLayers.TREASURE_BUG)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(TreasureBugEntity entity) {
        return RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/treasure_bug_generic.png");
    }

    @Override
    protected void setupRotations(TreasureBugEntity entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
        if (!entity.onClimbable()) return;
        Vec3 normal = Vec3.ZERO;
        double range = 0.3D;

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!entity.level().getBlockState(BlockPos.containing(entity.position().add(direction.getStepX() * range, 0, direction.getStepZ() * range))).isAir()) {
                normal = normal.add(direction.getStepX(), 0, direction.getStepZ());
            }
        }

        if (normal.lengthSqr() > 0) {
            normal = normal.normalize();
            poseStack.mulPose(Axis.YP.rotationDegrees((float) -Math.toDegrees(Math.atan2(normal.x, normal.z)) - rotationYaw));
        }
        poseStack.mulPose(Axis.XP.rotationDegrees(90F * Math.min(1F, (float) Math.sqrt(normal.lengthSqr()))));
    }
}
