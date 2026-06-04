package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

public class RavenousRushEffect extends PublicMobEffect {
    public RavenousRushEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getDescriptionId()).withStyle(style -> style.withColor(DDUtil.MONSTER_COLOR));
    }
}
