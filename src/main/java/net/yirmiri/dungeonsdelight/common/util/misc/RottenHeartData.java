package net.yirmiri.dungeonsdelight.common.util.misc;

import net.minecraft.nbt.CompoundTag;

public class RottenHeartData {
    private int rottenHearts = 0;

    public int getRottenHearts() {
        return rottenHearts;
    }

    public void setRottenHearts(int value) {
        rottenHearts = Math.max(0, value);
    }

    public void addRottenHearts(int amount, int max) {
        rottenHearts = Math.min(rottenHearts + amount, max);
    }

    public void removeRottenHearts(int amount) {
        rottenHearts = Math.max(0, rottenHearts - amount);
    }

    public void save(CompoundTag tag) {
        tag.putInt("RottenHearts", rottenHearts);
    }

    public void load(CompoundTag tag) {
        if (tag.contains("RottenHearts")) {
            rottenHearts = tag.getInt("RottenHearts");
        }
    }
}
