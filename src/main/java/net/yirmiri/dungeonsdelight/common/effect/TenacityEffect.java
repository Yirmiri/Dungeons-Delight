package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

public class TenacityEffect extends MobEffect {
    private final Holder<MobEffect> normalVariant;
    int applyInterval = 20;

    public TenacityEffect(Holder<MobEffect> normalVariant, MobEffectCategory category, int color, ParticleOptions particle) {
        super(category, color, particle);
        this.normalVariant = normalVariant;
    }

    public TenacityEffect(Holder<MobEffect> normalVariant, MobEffectCategory category, int color) {
        super(category, color);
        this.normalVariant = normalVariant;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getDescriptionId()).withStyle(style -> style.withColor(0xc875c2));
    }

    @Override
    public void onEffectAdded(LivingEntity living, int amplifier) {
        if (!living.level().isClientSide && living instanceof Player player) {
            applyInterval = getInterval(player);
        }
        super.onEffectAdded(living, amplifier);
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        if (!living.level().isClientSide && living instanceof Player player) {
            player.heal(1.0F);
            player.getFoodData().tick(player);
            applyInterval = getInterval(player);
        }
        return true; //switch to false,,, but i have no clue why this is a boolean?
    }

    public static int getInterval(Player player) {
        if (player.getFoodData().getFoodLevel() != 0) {
            return (player.getFoodData().getFoodLevel() * 3);
        } else {
            return (player.getFoodData().getFoodLevel() * 3) + 1;
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % (applyInterval - (amplifier * 2)) == 0;
    }
}
