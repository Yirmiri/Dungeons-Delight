package net.yirmiri.dungeonsdelight.common.entity.rotten_zombie;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;

public class RottenZombieRenderer extends ZombieRenderer {
    private static final ResourceLocation ROTTEN_ZOMBIE = RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/rotten_zombie.png");

    public RottenZombieRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, DDModelLayers.ROTTEN_ZOMBIE, DDModelLayers.ROTTEN_ZOMBIE_INNER_ARMOR, DDModelLayers.ROTTEN_ZOMBIE_OUTER_ARMOR);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie zombie) {
        return ROTTEN_ZOMBIE;
    }
}
