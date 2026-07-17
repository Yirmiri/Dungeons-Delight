package net.yirmiri.dungeonsdelight.common.entity.misc.vexing_fangs;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;

public class VexingFangsRenderer extends EntityRenderer<VexingFangsEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/vexing_fangs.png");
    private final VexingFangsModel<VexingFangsEntity> model;

    public VexingFangsRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new VexingFangsModel<>(context.bakeLayer(DDModelLayers.VEXING_FANGS));
    }

    @Override
    public void render(VexingFangsEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        float f = entity.getAnimationProgress(partialTicks);
        if (f != 0.0F) {
            float f1 = 2.0F;
            if (f > 0.9F) {
                f1 *= (1.0F - f) / 0.1F;
            }

            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotationDegrees(90.0F - entity.getYRot()));
            poseStack.scale(-f1, -f1, f1);
            float f2 = 0.03125F;
            poseStack.translate((double)0.0F, -0.626, (double)0.0F);
            poseStack.scale(0.5F, 0.5F, 0.5F);
            this.model.setupAnim(entity, f, 0.0F, 0.0F, entity.getYRot(), entity.getXRot());
            VertexConsumer vertexconsumer = buffer.getBuffer(this.model.renderType(TEXTURE_LOCATION));
            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();
            super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        }

    }

    @Override
    public ResourceLocation getTextureLocation(VexingFangsEntity entity) {
        return TEXTURE_LOCATION;
    }
}
