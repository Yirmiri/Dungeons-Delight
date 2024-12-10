package net.yirmiri.dungeonsdelight.item;

import io.github.fabricators_of_create.porting_lib.entity.EffectCures;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.ArrayList;
import java.util.Iterator;

public class TrialFreakshakeItem extends DrinkableItem {
    public TrialFreakshakeItem(Settings settings) {
        super(settings, true, true);
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity entity) {
        return 48;
    }

    //Code below is sourced from: https://github.com/MehVahdJukaar/FarmersDelightRefabricated/blob/fabric/1.21/src/main/java/vectorwing/farmersdelight/common/item/HotCocoaItem.java
    @Override
    public void affectConsumer(ItemStack stack, World level, LivingEntity consumer) {
        Iterator<StatusEffectInstance> itr = consumer.getStatusEffects().iterator();
        ArrayList<RegistryEntry<StatusEffect>> compatibleEffects = new ArrayList<>();

        while (itr.hasNext()) {
            StatusEffectInstance effect = itr.next();
            if (effect.getEffectType().value().getCategory().equals(StatusEffectCategory.HARMFUL) && effect.getCures().contains(EffectCures.MILK)) {
                compatibleEffects.add(effect.getEffectType());
            }
        }

        if (!compatibleEffects.isEmpty()) {
            StatusEffectInstance selectedEffect = consumer.getStatusEffect(compatibleEffects.get(level.random.nextInt(compatibleEffects.size())));
            // There is no equivalent for MobEffectEvent, people are expected to mixin with instances like this on Fabric, so we don't bother.
            if (selectedEffect != null) {
                consumer.removeStatusEffect(selectedEffect.getEffectType());
            }
        }
    }
}
