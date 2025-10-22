package net.yirmiri.dungeonsdelight.common.util.misc;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

import java.util.WeakHashMap;

public class RottenHeartManager {
    private static final WeakHashMap<LivingEntity, RottenHeartData> DATA = new WeakHashMap<>();

    public static RottenHeartData get(LivingEntity player) {
        return DATA.computeIfAbsent(player, RottenHeartManager::load);
    }

    private static RottenHeartData load(LivingEntity living) {
        RottenHeartData data = new RottenHeartData();
        if (living.getPersistentData().contains("RottenHearts")) {
            data.load(living.getPersistentData().getCompound("RottenHearts"));
        }
        return data;
    }

    public static void save(LivingEntity living) {
        RottenHeartData data = DATA.get(living);
        if (data != null) {
            CompoundTag tag = new CompoundTag();
            data.save(tag);
            living.getPersistentData().put("RottenHearts", tag);
        }
    }
}
