package net.yirmiri.dungeonsdelight.common.block.entity.wavy_block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

public class WavyBlockEntity extends BlockEntity {
    public WavyBlockEntity(BlockPos pos, BlockState blockState) {
        super(DDBlockEntities.WAVY_BLOCK.get(), pos, blockState);
    }
}
