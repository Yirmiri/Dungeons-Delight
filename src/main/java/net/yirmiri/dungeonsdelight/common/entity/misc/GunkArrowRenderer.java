package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

@OnlyIn(Dist.CLIENT)
public class GunkArrowRenderer extends ArrowRenderer<GunkArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/entity/gunk_arrow.png");

    public GunkArrowRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(GunkArrowEntity arrow) {
        return TEXTURE;
    }
}
