package net.yirmiri.dungeonsdelight.common.entity.zombified_dryad;

import com.mojang.blaze3d.vertex.PoseStack;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;

public class ZombifiedDryadOuterLayer<T extends ZombifiedDryadEntity> extends RenderLayer<T, DrownedModel<T>> {
    private static final ResourceLocation TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/zombified_dryad_outer_layer.png");
    private final DrownedModel<T> model;

    public ZombifiedDryadOuterLayer(RenderLayerParent<T, DrownedModel<T>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new DrownedModel<>(modelSet.bakeLayer(DDModelLayers.ZOMBIFIED_DRYAD_OUTER_LAYER));
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, TEXTURE, poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, -1);
    }
}
