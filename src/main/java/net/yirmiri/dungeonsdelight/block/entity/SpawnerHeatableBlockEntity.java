package net.yirmiri.dungeonsdelight.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.util.DDTags;

public interface SpawnerHeatableBlockEntity {
    /**
     * Checks for heat sources below the block. If it can, it also checks for conducted heat.
     */
    default boolean isHeated(World level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.down());

        if (stateBelow.isIn(DDTags.BlockT.MONSTER_HEAT_SOURCES)) {
            if (stateBelow.contains(Properties.LIT))
                return stateBelow.get(Properties.LIT);
            return true;
        }

        if (!this.requiresDirectHeat() && stateBelow.isIn(DDTags.BlockT.MONSTER_HEAT_CONDUCTORS)) {
            BlockState stateFurtherBelow = level.getBlockState(pos.down(2));
            if (stateFurtherBelow.isIn(DDTags.BlockT.MONSTER_HEAT_SOURCES)) {
                if (stateFurtherBelow.contains(Properties.LIT))
                    return stateFurtherBelow.get(Properties.LIT);
                return true;
            }
        }

        return false;
    }

    /**
     * Determines if this block can only be heated directly, excluding conductors.
     */
    default boolean requiresDirectHeat() {
        return false;
    }
}
