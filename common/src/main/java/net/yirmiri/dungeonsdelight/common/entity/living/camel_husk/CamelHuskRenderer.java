package net.yirmiri.dungeonsdelight.common.entity.living.camel_husk;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.renderer.entity.CamelRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.camel.Camel;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;

public class CamelHuskRenderer extends CamelRenderer {
    public CamelHuskRenderer(EntityRendererProvider.Context context) {
        super(context, DDModelLayers.CAMEL_HUSK);
    }

    @Override
    public ResourceLocation getTextureLocation(Camel entity) {
        return RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/camel_husk.png");
    }
}