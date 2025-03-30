package net.yirmiri.dungeonsdelight.common.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
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

        if (!cleaverEntity.isInGround()) {
            stack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(ticks, cleaverEntity.xRotO, cleaverEntity.getXRot()) + 90.0F));
        } else {
            stack.mulPose(Axis.ZP.rotationDegrees(- 165.0F));
        }

        itemRenderer.render(cleaverEntity.getItem(), ItemDisplayContext.FIXED, false, stack, bufferSource, i, OverlayTexture.NO_OVERLAY,
                itemRenderer.getModel(cleaverEntity.getItem().copy(), cleaverEntity.level(), null, cleaverEntity.getId()));

        stack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(CleaverEntity entity) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
