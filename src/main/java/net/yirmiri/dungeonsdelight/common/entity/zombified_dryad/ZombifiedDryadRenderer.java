package net.yirmiri.dungeonsdelight.common.entity.zombified_dryad;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;

public class ZombifiedDryadRenderer extends ZombieRenderer {
    private static final ResourceLocation ZOMBIFIED_DRYAD = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/zombified_dryad.png");

    public ZombifiedDryadRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, DDModelLayers.ZOMBIFIED_DRYAD, DDModelLayers.ZOMBIFIED_DRYAD_INNER_ARMOR, DDModelLayers.ZOMBIFIED_DRYAD_OUTER_ARMOR);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie zombie) {
        return ZOMBIFIED_DRYAD;
    }
}
