package net.yirmiri.dungeonsdelight.common.block.entity.cleaving_board;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;

public class CleavingBoardRenderer implements BlockEntityRenderer<CleavingBoardBlockEntity> {
    private static final float SIZE = 0.5F;
    private final ItemRenderer itemRenderer;

    public CleavingBoardRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(CleavingBoardBlockEntity cleavingBoardBlockEntity, float v, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Direction direction = cleavingBoardBlockEntity.getBlockState().getValue(CleavingBoardBlock.FACING);
        ItemStack itemstack = cleavingBoardBlockEntity.getItem(0);
        int i = (int)cleavingBoardBlockEntity.getBlockPos().asLong();
        boolean upOrDown = (direction == Direction.UP || direction == Direction.DOWN);

        if (itemstack != ItemStack.EMPTY) {
            int stackSize = itemstack.getCount();

            poseStack.pushPose();
            Quaternionf joml = direction.getRotation();

            poseStack.translate(0.5F, 0.5F, 0.5F);
            poseStack.rotateAround(joml, 0, 0, 0);
            poseStack.scale(SIZE, SIZE, SIZE);

            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            if (!upOrDown) {
                poseStack.mulPose(Axis.ZN.rotationDegrees(180.0F));
            }
            poseStack.translate(0.0F, 0.0F, 0.85F);

            this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, cleavingBoardBlockEntity.getLevel(), i);

            if (stackSize > 1) {
                poseStack.translate(-0.08F, 0.1F, -0.08F);
                this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, cleavingBoardBlockEntity.getLevel(), i);
                if (stackSize >= 32) {
                    poseStack.translate(0.2F, -0.2F, -0.08F);
                    this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, cleavingBoardBlockEntity.getLevel(), i);
                }
            }

            poseStack.popPose();
        }
    }
}