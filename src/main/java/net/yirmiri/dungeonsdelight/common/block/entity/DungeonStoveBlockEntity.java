//
//Based on the original version from Farmer's Delight here:
//https://github.com/vectorwing/FarmersDelight/blob/e2b72feac591ebdd827c729f46ca45d52d5a36c7/src/main/java/vectorwing/farmersdelight/common/block/entity/StoveBlockEntity.java
//

package net.yirmiri.dungeonsdelight.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;
import net.yirmiri.dungeonsdelight.common.block.DungeonStoveBlock;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import vectorwing.farmersdelight.common.block.AbstractStoveBlock;
import vectorwing.farmersdelight.common.block.entity.AbstractStoveBlockEntity;

public class DungeonStoveBlockEntity extends AbstractStoveBlockEntity {
    public DungeonStoveBlockEntity(BlockPos pos, BlockState state) {
        super(DDBlockEntities.DUNGEON_STOVE.get(), pos, state, RecipeType.CAMPFIRE_COOKING);
    }

    public static void particleTick(Level level, BlockPos pos, BlockState state, DungeonStoveBlockEntity stoveEntity) {
        if (!stoveEntity.isEmpty()) {
            stoveEntity.addSmokeParticles();
        }
    }

    public void addSmokeParticles() {
        assert this.level != null;

        ItemStackHandler items = this.getItems();

        for(int i = 0; i < items.getSlots(); ++i) {
            if (!items.getStackInSlot(i).isEmpty() && !(this.level.random.nextFloat() >= 0.2F)) {
                Vec2 itemOffset = this.getStoveItemOffset(i);
                Direction direction = this.getBlockState().getValue(AbstractStoveBlock.FACING);
                if (direction.get2DDataValue() % 2 != 0) {
                    itemOffset = new Vec2(itemOffset.y, itemOffset.x);
                }

                double x = (double)this.worldPosition.getX() + (double)0.5F - (double)((float)direction.getStepX() * itemOffset.x) + (double)((float)direction.getClockWise().getStepX() * itemOffset.x);
                double y = (double)this.worldPosition.getY() + (double)1.0F;
                double z = (double)this.worldPosition.getZ() + (double)0.5F - (double)((float)direction.getStepZ() * itemOffset.y) + (double)((float)direction.getClockWise().getStepZ() * itemOffset.y);

                for(int k = 0; k < 3; ++k) {
                    this.level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0F, 5.0E-4, 0.0F);
                }
            }
        }

    }

    protected int getInventorySlotCount() {
        return 6;
    }

    public Vec2 getStoveItemOffset(int index) {
        float X_OFFSET = 0.3F;
        float Y_OFFSET = 0.2F;
        Vec2[] OFFSETS = new Vec2[]{new Vec2(0.3F, 0.2F), new Vec2(0.0F, 0.2F), new Vec2(-0.3F, 0.2F), new Vec2(0.3F, -0.2F), new Vec2(0.0F, -0.2F), new Vec2(-0.3F, -0.2F)};
        return OFFSETS[index];
    }
}

