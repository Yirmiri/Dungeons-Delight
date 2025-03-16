package net.yirmiri.dungeonsdelight.common.entity.monster_yam;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class MonsterYamEntityRenderer extends MobRenderer<MonsterYamEntity, MonsterYamEntityModel<MonsterYamEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/entity/monster_yam.png");

    public MonsterYamEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new MonsterYamEntityModel<>(ctx.bakeLayer(MonsterYamEntityModel.LAYER_LOC)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(MonsterYamEntity entity) {
        return TEXTURE;
    }
}
