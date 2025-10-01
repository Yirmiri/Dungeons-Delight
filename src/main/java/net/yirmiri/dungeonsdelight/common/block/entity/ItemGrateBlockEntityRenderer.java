package net.yirmiri.dungeonsdelight.common.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;

public class ItemGrateBlockEntityRenderer implements BlockEntityRenderer<ItemGrateBlockEntity> {
    public ItemGrateBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(ItemGrateBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        ItemStack stack = blockEntity.getStack();
        if (!stack.isEmpty()) {
            poseStack.pushPose();

            poseStack.translate(0.5, 0.385, 0.5);

            if (Minecraft.renderNames() && stack.has(DataComponents.CUSTOM_NAME)) {
                if (Minecraft.getInstance().getEntityRenderDispatcher().distanceToSqr(blockEntity.getBlockPos().getX() + 0.5, blockEntity.getBlockPos().getY() + 0.5, blockEntity.getBlockPos().getZ() + 0.5) < 18 * 18) {
                    poseStack.pushPose();

                    poseStack.translate(0, 0.875F, 0);
                    poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
                    poseStack.scale(0.025F, -0.025F, -0.025F);

                    Minecraft.getInstance().font.drawInBatch(blockEntity.getStack().getHoverName(), -Minecraft.getInstance().font.width(
                            blockEntity.getStack().getHoverName()) / 2F, 0, probablysomethingbettericando(stack), false, poseStack.last().pose(),
                            buffer, Font.DisplayMode.NORMAL, (int) Minecraft.getInstance().options.getBackgroundOpacity(0.25F), packedLight);

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

    private Integer probablysomethingbettericando(ItemStack stack) {
        if (stack.getRarity().ordinal() <= 3) {
            return stack.getRarity().color().getColor();
        } else if (stack.getRarity() == DDProperties.MONSTER) {
            return 0xC875C2;
        } else return -1;
    }
}
