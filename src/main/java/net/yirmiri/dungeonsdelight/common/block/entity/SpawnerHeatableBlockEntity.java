package net.yirmiri.dungeonsdelight.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import vectorwing.farmersdelight.common.tag.ModTags;

public interface SpawnerHeatableBlockEntity {
    default boolean isHeated(Level level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        if (stateBelow.is(DDTags.BlockT.MONSTER_HEAT_SOURCES)) {
            return stateBelow.hasProperty(BlockStateProperties.LIT) ? stateBelow.getValue(BlockStateProperties.LIT) : true;
        } else {
            if (!this.requiresDirectHeat() && stateBelow.is(ModTags.HEAT_CONDUCTORS)) {
                BlockState stateFurtherBelow = level.getBlockState(pos.below(2));
                if (stateFurtherBelow.is(DDTags.BlockT.MONSTER_HEAT_SOURCES)) {
                    if (stateFurtherBelow.hasProperty(BlockStateProperties.LIT)) {
                        return stateFurtherBelow.getValue(BlockStateProperties.LIT);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    default boolean requiresDirectHeat() {
        return false;
    }
}
