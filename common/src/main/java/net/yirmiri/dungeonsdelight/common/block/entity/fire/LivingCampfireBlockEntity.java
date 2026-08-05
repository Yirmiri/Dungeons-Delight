package net.yirmiri.dungeonsdelight.common.block.entity.fire;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
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

    public static void particleTick(Level level, BlockPos pos, BlockState state, CampfireBlockEntity blockEntity) {
        RandomSource randomsource = level.random;
        if (level.getBlockState(pos.above()).isAir()) {
            if (randomsource.nextFloat() < 0.11F) {
                for (int i = 0; i < randomsource.nextInt(2) + 2; ++i) {
                    CampfireBlock.makeParticles(level, pos, state.getValue(CampfireBlock.SIGNAL_FIRE), false);
                }
            }

            int l = state.getValue(CampfireBlock.FACING).get2DDataValue();

            for (int j = 0; j < blockEntity.getItems().size(); ++j) {
                if (!blockEntity.getItems().get(j).isEmpty() && randomsource.nextFloat() < 0.2F) {
                    Direction direction = Direction.from2DDataValue(Math.floorMod(j + l, 4));
                    float f = 0.3125F;
                    double d0 = (double) pos.getX() + (double) 0.5F - (double) ((float) direction.getStepX() * 0.3125F) + (double) ((float) direction.getClockWise().getStepX() * 0.3125F);
                    double d1 = (double) pos.getY() + (double) 0.5F;
                    double d2 = (double) pos.getZ() + (double) 0.5F - (double) ((float) direction.getStepZ() * 0.3125F) + (double) ((float) direction.getClockWise().getStepZ() * 0.3125F);

                    for (int k = 0; k < 4; ++k) {
                        level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0F, 5.0E-4, 0.0F);
                    }
                }
            }
        }
    }
}