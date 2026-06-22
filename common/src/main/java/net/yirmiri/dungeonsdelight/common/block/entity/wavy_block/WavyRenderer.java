package net.yirmiri.dungeonsdelight.common.block.entity.wavy_block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class WavyRenderer implements BlockEntityRenderer<WavyBlockEntity> {
    private final BlockRenderDispatcher dispatcher;

    public WavyRenderer(BlockEntityRendererProvider.Context ctx) {
        this.dispatcher = Minecraft.getInstance().getBlockRenderer();
    }

    @Override
    public void render(WavyBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        if (level == null) return;
        BlockState state = blockEntity.getBlockState();

        poseStack.pushPose();

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().tesselateBlock(level, dispatcher.getBlockModel(state), state,
                blockEntity.getBlockPos(), poseStack,
                new WavyVertexConsumer(buffer.getBuffer(RenderType.translucent()), level.getGameTime() + partialTick, getStrength(blockEntity)),
                true, RandomSource.create(42L), state.getSeed(blockEntity.getBlockPos()), packedOverlay
        );
        poseStack.popPose();
    }

    private float getStrength(WavyBlockEntity blockEntity) {
        if (blockEntity.getBlockState().getValue(WavyBlock.WAVY)) {
            return 0.03F;
        } else return 0.0F;
    }
}