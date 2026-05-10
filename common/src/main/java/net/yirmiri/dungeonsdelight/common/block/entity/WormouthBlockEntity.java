package net.yirmiri.dungeonsdelight.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

public class WormouthBlockEntity extends BlockEntity {
    public WormouthBlockEntity(BlockPos pos, BlockState blockState) {
        super(DDBlockEntities.WORMOUTH.get(), pos, blockState);
    }
}