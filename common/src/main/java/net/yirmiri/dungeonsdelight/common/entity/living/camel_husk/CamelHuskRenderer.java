package net.yirmiri.dungeonsdelight.common.entity.living.camel_husk;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;

public class CamelHuskRenderer extends MobRenderer<CamelHuskEntity, CamelHuskModel<CamelHuskEntity>> {
    public CamelHuskRenderer(EntityRendererProvider.Context context) {
        super(context, new CamelHuskModel(context.bakeLayer(DDModelLayers.CAMEL_HUSK)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(CamelHuskEntity entity) {
        return RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/camel_husk.png");
    }
}