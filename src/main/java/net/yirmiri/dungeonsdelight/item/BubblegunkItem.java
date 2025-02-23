package net.yirmiri.dungeonsdelight.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BubblegunkItem extends BiteableItem {
    public BubblegunkItem(Properties properties, TagKey<Item> repairItem, boolean hasPotionEffectTooltip) {
        super(properties, repairItem, hasPotionEffectTooltip);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 20;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_BLOCK_STEP;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_BLOCK_STEP;
    }
}
