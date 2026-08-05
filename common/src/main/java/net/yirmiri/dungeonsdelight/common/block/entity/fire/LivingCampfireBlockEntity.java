package net.yirmiri.dungeonsdelight.common.block.entity.fire;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

public class LivingCampfireBlockEntity extends CampfireBlockEntity {
    private static final int MAX_STORED_EXP = 1395;
    private int storedExperience;

    public LivingCampfireBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        storedExperience = 0;
    }

    @Override
    public BlockEntityType<?> getType() {
        return DDBlockEntities.LIVING_CAMPFIRE.get();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("StoredExperience", 3)) {
            storedExperience = tag.getInt("StoredExperience");
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putInt("StoredExperience", storedExperience);
    }

    public boolean canStoreExperience() {
        return storedExperience < MAX_STORED_EXP;
    }

    public int getStoredExperience() {
        return storedExperience;
    }

    public void setStoredExperience(int newValue) {
        storedExperience = newValue;
    }

    public void addExperience(int amount) {
        if (amount <= 0) return;
        storedExperience = Math.min(MAX_STORED_EXP, storedExperience + amount);
        setChanged();
    }
}