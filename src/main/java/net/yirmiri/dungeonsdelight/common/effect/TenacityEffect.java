package net.yirmiri.dungeonsdelight.common.effect;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

public class TenacityEffect extends MonsterEffect {
    private final Holder<MobEffect> normalVariant;
    int applyInterval = 20;

    public TenacityEffect(Holder<MobEffect> normalVariant, MobEffectCategory category, int color) {
        super(normalVariant, category, color);
        this.normalVariant = normalVariant;
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
        Holder<MobEffect> thisHolder = Holder.direct(this);
        if (living.hasEffect(normalVariant)) {
            DDUtil.applyEffectSwap(living, normalVariant, thisHolder);
            living.removeEffect(normalVariant);
        }
        return true;
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
