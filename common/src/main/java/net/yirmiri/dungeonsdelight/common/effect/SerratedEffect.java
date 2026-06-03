package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;

public class SerratedEffect extends PublicMobEffect {
    public SerratedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        DamageSource source = new DamageSource(living.level().registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DDDamageTypes.SERRATED));
        if (!(DungeonsDelight.CONFIG.getCleaverSerratedEffectDamage() == 0)) {
            living.hurt(source, DungeonsDelight.CONFIG.getCleaverSerratedEffectDamage());
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % (30 / (amplifier + 1)) == 0;
    }
}