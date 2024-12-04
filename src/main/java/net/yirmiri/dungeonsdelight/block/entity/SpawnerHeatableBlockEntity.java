package net.yirmiri.dungeonsdelight.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.util.DDTags;

public interface SpawnerHeatableBlockEntity {
    default boolean isHeated(World level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.down());
        return stateBelow.isIn(DDTags.BlockT.MONSTER_HEAT_SOURCES);
    }
}
