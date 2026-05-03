//
//Based on the original version from Farmer's Delight here:
//https://github.com/vectorwing/FarmersDelight/blob/e2b72feac591ebdd827c729f46ca45d52d5a36c7/src/main/java/vectorwing/farmersdelight/common/block/entity/StoveBlockEntity.java
//

package net.yirmiri.dungeonsdelight.common.block.entity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.yirmiri.dungeonsdelight.common.block.DungeonStoveBlock;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import vectorwing.farmersdelight.common.block.AbstractStoveBlock;
import vectorwing.farmersdelight.common.block.entity.AbstractStoveBlockEntity;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.utility.ItemUtils;

import java.util.Optional;

public class DungeonStoveBlockEntity extends AbstractStoveBlockEntity {
    private static final int MAX_STORED_EXP = 1395;
    private int storedExperience;

    public DungeonStoveBlockEntity(BlockPos pos, BlockState state) {
        super(DDBlockEntities.DUNGEON_STOVE.get(), pos, state, RecipeType.CAMPFIRE_COOKING);
        storedExperience = 0;
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("StoredExperience", 3)) {
            storedExperience = tag.getInt("StoredExperience");
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        writeItems(tag, registries);
        tag.putInt("StoredExperience", storedExperience);
    }

    private CompoundTag writeItems(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        return tag;
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

    public static void particleTick(Level level, BlockPos pos, BlockState state, DungeonStoveBlockEntity stoveEntity) {
        if (!stoveEntity.isEmpty()) {
            stoveEntity.addSmokeParticles();
        }
    }

    public void addSmokeParticles() {
        assert level != null;
        ItemStackHandler items = getItems();

        for(int i = 0; i < items.getSlots(); ++i) {
            if (!items.getStackInSlot(i).isEmpty() && !(level.random.nextFloat() >= 0.2F)) {
                Vec2 itemOffset = getStoveItemOffset(i);
                Direction direction = getBlockState().getValue(AbstractStoveBlock.FACING);
                if (direction.get2DDataValue() % 2 != 0) {
                    itemOffset = new Vec2(itemOffset.y, itemOffset.x);
                }

                double x = (double) worldPosition.getX() + (double) 0.5F - (double)((float)direction.getStepX() * itemOffset.x) + (double)((float)direction.getClockWise().getStepX() * itemOffset.x);
                double y = (double) worldPosition.getY() + (double) 1.0F;
                double z = (double) worldPosition.getZ() + (double) 0.5F - (double)((float)direction.getStepZ() * itemOffset.y) + (double)((float)direction.getClockWise().getStepZ() * itemOffset.y);

                for(int k = 0; k < 3; ++k) {
                    level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0F, 5.0E-4, 0.0F);
                }
            }
        }
    }

    protected int getInventorySlotCount() {
        return 6;
    }

    public Vec2 getStoveItemOffset(int index) {
        Vec2[] OFFSETS = new Vec2[]{
                new Vec2(0.3F, 0.2F), new Vec2(0.0F, 0.2F), new Vec2(-0.3F, 0.2F), 
                new Vec2(0.3F, -0.2F), new Vec2(0.0F, -0.2F), new Vec2(-0.3F, -0.2F)
        };
        return OFFSETS[index];
    }
}
