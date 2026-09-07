package net.yirmiri.dungeonsdelight.common.entity.misc.leftovers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class LeftoversEntityRenderer extends EntityRenderer<LeftoversEntity> {
    public LeftoversEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.25F;
        this.shadowStrength = 0.75F;
    }

    @Override
    public void render(LeftoversEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.getAge() >= entity.getMaxAge() - 80 && (entity.tickCount / 4) % 2 == 0) return;

        poseStack.pushPose();

        float f = (float) (16) / 16.0F;
        float f1 = (float) (16 + 16) / 16;
        float f2 = (float) (0) / 16;
        float f3 = (float) (16) / 16;

        poseStack.translate(0.0F, 0.2F, 0.0F);

        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.scale(0.66F, 0.66F, 0.66F);

        ResourceLocation texture = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/leftovers/" + entity.getLeftoversType().getFolder() + "/" + entity.getVariant() + ".png");
        RenderType renderType = RenderType.entityTranslucent(texture);
        VertexConsumer vertexconsumer = buffer.getBuffer(renderType);
        PoseStack.Pose posestack$pose = poseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();

        vertex(vertexconsumer, matrix4f, matrix3f, -0.5F, -0.25F, 255, 255, 255, f, f3, packedLight);
        vertex(vertexconsumer, matrix4f, matrix3f, 0.5F, -0.25F, 255, 255, 255, f1, f3, packedLight);
        vertex(vertexconsumer, matrix4f, matrix3f, 0.5F, 0.75F, 255, 255, 255, f1, f2, packedLight);
        vertex(vertexconsumer, matrix4f, matrix3f, -0.5F, 0.75F, 255, 255, 255, f, f2, packedLight);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f matrix, Matrix3f matrixNormal, float x, float y, int red, int green, int blue, float texU, float texV, int packedLight) {
        consumer.vertex(matrix, x, y, 0.0F).color(red, green, blue, 200).uv(texU, texV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrixNormal, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(LeftoversEntity entity) {
        return RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/leftovers/" + entity.getLeftoversType().getFolder() + "/" + entity.getVariant() + ".png");
    }
}
