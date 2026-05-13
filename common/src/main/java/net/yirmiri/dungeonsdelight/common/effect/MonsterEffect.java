package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MonsterEffect extends PublicMobEffect {
    private final MobEffect normalVariant;

    public MonsterEffect(MobEffect normalVariant, MobEffectCategory category, int color) {
        super(category, color);
        this.normalVariant = normalVariant;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getDescriptionId()).withStyle(style -> style.withColor(0xc875c2));
    }
}
//todo make it change normal when monsterized, check livingentitymixin