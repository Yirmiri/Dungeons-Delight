package net.yirmiri.dungeonsdelight.common.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Style;
import net.minecraft.util.CommonColors;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import org.joml.Matrix4f;

public class ItemGrateBlockEntityRenderer implements BlockEntityRenderer<ItemGrateBlockEntity> {
    public ItemGrateBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(ItemGrateBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        ItemStack stack = blockEntity.getStack();
        if (!stack.isEmpty()) {
            poseStack.pushPose();

            poseStack.translate(0.5, 0.385, 0.5);

            if (Minecraft.renderNames() && stack.hasCustomHoverName()) {
                if (Minecraft.getInstance().getEntityRenderDispatcher().distanceToSqr(blockEntity.getBlockPos().getX() + 0.5, blockEntity.getBlockPos().getY() + 0.5, blockEntity.getBlockPos().getZ() + 0.5) < 18 * 18) {
                    poseStack.pushPose();

                    poseStack.translate(0, 1.0F, 0);
                    poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
                    poseStack.scale(-0.025F, -0.025F, 0.025F);

                    Font font = Minecraft.getInstance().font;
                    float batf = (-font.width(stack.getHoverName()) / 2F);
                    Matrix4f lastPose = poseStack.last().pose();
                    float opa = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
                    int opaTrue = (int)(opa * 255.0F) << 24;
                    font.drawInBatch(stack.getHoverName(), batf, 0, lazyColorPick(stack), false, lastPose, buffer, Font.DisplayMode.NORMAL, opaTrue, packedLight);

                    poseStack.popPose();
                }
            }

            poseStack.scale(blockEntity.getRenderScale(), blockEntity.getRenderScale(), blockEntity.getRenderScale());

            BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(stack, blockEntity.getLevel(), null, 0);
            if (!blockEntity.isWaxed()) {
                poseStack.mulPose(Axis.YP.rotationDegrees(blockEntity.getRotationSpeed(partialTick)));
            } else {
                poseStack.mulPose(Axis.YP.rotationDegrees(blockEntity.getRotation()));
            }

            Minecraft.getInstance().getItemRenderer().render(stack, ItemDisplayContext.GROUND, false, poseStack, buffer, packedLight, packedOverlay, model);

            poseStack.popPose();
        }
    }

    private Integer lazyColorPick(ItemStack stack) {
        Style meStyle = stack.getDisplayName().getStyle();
        return FastColor.ABGR32.color(255, (meStyle.getColor() != null) ? meStyle.getColor().getValue() : CommonColors.WHITE);
    }
}
