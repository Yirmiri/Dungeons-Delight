package net.yirmiri.dungeonsdelight.common.util.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class SpikedFoodData {
    public static final String SPIKES_TAG = "Spikes";

    public static boolean hasSpike(ItemStack stack, SpikeType type) {
        CompoundTag tag = stack.getTag();
        return tag != null && tag.contains(SPIKES_TAG, Tag.TAG_COMPOUND) && tag.getCompound(SPIKES_TAG).contains(type.name(), Tag.TAG_LIST);
    }

    public static Set<SpikeType> getSpikeTypes(ItemStack stack) {
        Set<SpikeType> types = EnumSet.noneOf(SpikeType.class);
        CompoundTag tag = stack.getTag();

        if (tag == null || !tag.contains(SPIKES_TAG, Tag.TAG_COMPOUND)) {
            return types;
        }
        CompoundTag spikes = tag.getCompound(SPIKES_TAG);

        for (SpikeType type : SpikeType.values()) {
            if (spikes.contains(type.name(), Tag.TAG_LIST)) {
                types.add(type);
            }
        }
        return types;
    }

    public static void addEffect(SoundEvent sound, Player player, ItemStack stack, SpikeType type, MobEffectInstance effect) {
        CompoundTag spikes = stack.getOrCreateTagElement(SPIKES_TAG);
        ListTag effects = spikes.getList(type.name(), Tag.TAG_COMPOUND);
        effects.add(effect.save(new CompoundTag()));
        spikes.put(type.name(), effects);
        player.level().playSound(player, player.blockPosition(), sound, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public static void copySpike(ItemStack from, ItemStack to, SpikeType type) {
        CompoundTag spikes = to.getOrCreateTagElement(SPIKES_TAG);
        ListTag effects = spikes.getList(type.name(), Tag.TAG_COMPOUND);

        for (MobEffectInstance effect : getEffects(from, type)) {
            effects.add(effect.save(new CompoundTag()));
        }
        spikes.put(type.name(), effects);
    }

    public static List<MobEffectInstance> getEffects(ItemStack stack, SpikeType type) {
        List<MobEffectInstance> list = new ArrayList<>();

        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains(SPIKES_TAG, Tag.TAG_COMPOUND)) {
            return list;
        }

        ListTag effects = tag.getCompound(SPIKES_TAG).getList(type.name(), Tag.TAG_COMPOUND);

        for (int i = 0; i < effects.size(); i++) {
            MobEffectInstance effect = MobEffectInstance.load(effects.getCompound(i));
            if (effect != null) {
                list.add(effect);
            }
        }
        return list;
    }

    public static void applyEffects(ItemStack stack, LivingEntity living) {
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains(SPIKES_TAG, Tag.TAG_COMPOUND)) {
            return;
        }

        for (SpikeType type : getSpikeTypes(stack)) {
            for (MobEffectInstance effect : getEffects(stack, type)) {
                living.addEffect(effect);
            }
        }
    }

    public enum SpikeType {
        SPIDER
    }
}