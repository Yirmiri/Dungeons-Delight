package net.yirmiri.dungeonsdelight.common.util.data;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public interface HomewardData {
    void setHomewardPos(BlockPos pos);

    BlockPos getHomewardPos();

    void setHomewardDimension(ResourceKey<Level> level);

    ResourceKey<Level> getHomewardDimension();
}