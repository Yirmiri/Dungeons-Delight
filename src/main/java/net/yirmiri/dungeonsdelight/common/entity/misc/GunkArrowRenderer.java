package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class GunkArrowRenderer extends ArrowRenderer<GunkArrowEntity> {
    public static final ResourceLocation TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/gunk_arrow.png");

    public GunkArrowRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(GunkArrowEntity arrow) {
        return TEXTURE;
    }
}
