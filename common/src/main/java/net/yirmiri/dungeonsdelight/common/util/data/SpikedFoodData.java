package net.yirmiri.dungeonsdelight.common.util.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDStats;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class SpikedFoodData {
    public static final String SPIKES_TAG = "Spikes";

    public static boolean hasSpike(ItemStack stack, SpikeType type) {
        return getSpikeType(stack) == type;
    }

    public static SpikeType getSpikeType(ItemStack stack) {
        CompoundTag tag = stack.getTag();

        if (tag == null || !tag.contains(SPIKES_TAG, Tag.TAG_COMPOUND)) {
            return null;
        }

        CompoundTag spikes = tag.getCompound(SPIKES_TAG);

        for (SpikeType type : SpikeType.values()) {
            if (spikes.contains(type.name(), Tag.TAG_LIST)) {
                return type;
            }
        }
        return null;
    }

    public static boolean isSpikeItem(ItemStack stack, SpikeType type) {
        return type.getItem() != null && stack.is(type.getItem());
    }

    public static boolean isSameSpike(ItemStack stack, SpikeType type) {
        return getSpikeType(stack) == type;
    }

    public static void spike(ItemStack stack, ItemStack spikeIngredient, SpikeType type, Player player) {
        if (stack.getItem() != type.blacklistedItems) return;

        clearSpike(stack);
        type.addEffect(stack, player);
        spikeIngredient.shrink(1);
        player.awardStat(DDStats.ITEMS_SPIKED.get());

        if (spikeIngredient.getItem().getCraftingRemainingItem() != null) {
            ItemStack container = new ItemStack(spikeIngredient.getItem().getCraftingRemainingItem());

            if (!player.getInventory().add(container)) {
                player.drop(container, false);
            }
        }
    }

    public static void addEffect(SoundEvent sound, Player player, ItemStack stack, SpikeType type, MobEffectInstance effect) {
        clearSpike(stack);

        CompoundTag spikes = stack.getOrCreateTagElement(SPIKES_TAG);
        ListTag effects = spikes.getList(type.name(), Tag.TAG_COMPOUND);

        effects.add(effect.save(new CompoundTag()));
        spikes.put(type.name(), effects);

        player.level().playSound(player, player.blockPosition(), sound, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public static void copySpike(ItemStack originalStack, ItemStack spikedStack, SpikeType type) {
        SpikeType spikeType = getSpikeType(spikedStack);

        if (spikeType != null && spikeType != type) return;
        if (originalStack.getTag() == null || !originalStack.getTag().contains(SPIKES_TAG, Tag.TAG_COMPOUND)) return;
        if (!originalStack.getTag().getCompound(SPIKES_TAG).contains(type.name(), Tag.TAG_LIST)) return;

        ListTag effects = new ListTag();
        ListTag fromTagEffects = originalStack.getTag().getCompound(SPIKES_TAG).getList(type.name(), Tag.TAG_COMPOUND);

        for (int i = 0; i < fromTagEffects.size(); i++) {
            effects.add(fromTagEffects.getCompound(i).copy());
        }
        spikedStack.getOrCreateTagElement(SPIKES_TAG).put(type.name(), effects);
    }

    public static void clearSpike(ItemStack stack) {
        if (stack.getTag() == null || !stack.getTag().contains(SPIKES_TAG, Tag.TAG_COMPOUND)) return;
        stack.getTag().remove(SPIKES_TAG);

        if (stack.getTag().isEmpty()) {
            stack.setTag(null);
        }
    }

    public static List<MobEffectInstance> getEffects(ItemStack stack, SpikeType type) {
        List<MobEffectInstance> list = new ArrayList<>();

        if (stack.getTag() == null || !stack.getTag().contains(SPIKES_TAG, Tag.TAG_COMPOUND)) return list;
        CompoundTag spikes = stack.getTag().getCompound(SPIKES_TAG);

        if (!spikes.contains(type.name(), Tag.TAG_LIST)) return list;
        ListTag effects = spikes.getList(type.name(), Tag.TAG_COMPOUND);

        for (int i = 0; i < effects.size(); i++) {
            MobEffectInstance effect = MobEffectInstance.load(effects.getCompound(i));
            if (effect != null) {
                list.add(effect);
            }
        }
        return list;
    }

    public static void applyEffects(ItemStack stack, LivingEntity living) {
        SpikeType spikeType = getSpikeType(stack);
        if (spikeType == null) return;

        for (MobEffectInstance effect : getEffects(stack, spikeType)) {
            living.addEffect(effect);
        }
        spikeType.onApply(living);
    }

    public enum SpikeType {
        POISON(DDItems.SPIDER_EXTRACT.get(), SoundEvents.BREWING_STAND_BREW, new MobEffectInstance(MobEffects.POISON, 240, 1), null),
        EXPLOSION(DDItems.CREEPERILLA_SQUIB.get(), SoundEvents.GRASS_PLACE, null, List.of(DDItems.CREEPERILLA.get(), DDItems.CREEPERILLA_SQUIB.get())),;

        private final Item item;
        private final SoundEvent sound;
        private final MobEffectInstance effect;
        private final List<Item> blacklistedItems;

        SpikeType(Item item, SoundEvent sound, MobEffectInstance effect, List<Item> blacklistedItems) {
            this.item = item;
            this.sound = sound;
            this.effect = effect;
            this.blacklistedItems = blacklistedItems;
        }

        public Item getItem() {
            return item;
        }

        public void addEffect(ItemStack food, Player player) {
            if (effect != null) {
                SpikedFoodData.addEffect(sound, player, food, this, new MobEffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible(), effect.showIcon()));
                return;
            }
            SpikedFoodData.clearSpike(food);
            CompoundTag spikes = food.getOrCreateTagElement(SPIKES_TAG);
            spikes.put(name(), new ListTag());
            player.level().playSound(player, player.blockPosition(), sound, SoundSource.BLOCKS, 1.0F, 1.0F);
        }

        public void onApply(LivingEntity living) {
            if (this == EXPLOSION) {
                if (!living.level().isClientSide) {
                    living.level().explode(living, living.getX(), living.getY(), living.getZ(), 2.0F, Level.ExplosionInteraction.MOB);
                }
            }
        }
    }
}