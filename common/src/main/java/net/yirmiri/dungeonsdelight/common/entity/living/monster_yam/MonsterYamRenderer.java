package net.yirmiri.dungeonsdelight.common.entity.living.monster_yam;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;

public class MonsterYamRenderer extends MobRenderer<MonsterYamEntity, MonsterYamModel<MonsterYamEntity>> {
    public MonsterYamRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new MonsterYamModel<>(ctx.bakeLayer(DDModelLayers.MONSTER_YAM)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(MonsterYamEntity entity) {
        return RunicLib.customid(DungeonsDelight.MOD_ID, "textures/entity/monster_yam.png");
    }
}