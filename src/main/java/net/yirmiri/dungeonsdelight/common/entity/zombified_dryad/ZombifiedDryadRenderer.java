package net.yirmiri.dungeonsdelight.common.entity.zombified_dryad;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Zombie;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;

public class ZombifiedDryadRenderer extends AbstractZombieRenderer<ZombifiedDryadEntity, DrownedModel<ZombifiedDryadEntity>> {
    private static final ResourceLocation TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/zombified_dryad.png");

    public ZombifiedDryadRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new DrownedModel(ctx.bakeLayer(DDModelLayers.ZOMBIFIED_DRYAD)), new DrownedModel(ctx.bakeLayer(DDModelLayers.ZOMBIFIED_DRYAD_INNER_ARMOR)), new DrownedModel(ctx.bakeLayer(DDModelLayers.ZOMBIFIED_DRYAD_OUTER_ARMOR)));
        this.addLayer(new ZombifiedDryadOuterLayer(this, ctx.getModelSet()));
    }

    public ResourceLocation getTextureLocation(Zombie entity) {
        return TEXTURE;
    }

    protected void setupRotations(ZombifiedDryadEntity entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        super.setupRotations(entity, poseStack, bob, yBodyRot, partialTick, scale);
        float f = entity.getSwimAmount(partialTick);
        if (f > 0.0F) {
            float f1 = -10.0F - entity.getXRot();
            float f2 = Mth.lerp(f, 0.0F, f1);
            poseStack.rotateAround(Axis.XP.rotationDegrees(f2), 0.0F, entity.getBbHeight() / 2.0F / scale, 0.0F);
        }

    }
}