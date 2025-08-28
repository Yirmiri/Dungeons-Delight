package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import org.jetbrains.annotations.NotNull;

public class PureMonsterEffect extends PublicMobEffect {
    public PureMonsterEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable(this.getDescriptionId()).withStyle(style -> style.withColor(0xc875c2));
    }
}
