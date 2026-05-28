package net.yirmiri.dungeonsdelight.common.block.entity.monster_pot;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

public class MonsterPotBlockEntity extends BlockEntity {
    public MonsterPotBlockEntity(BlockPos pos, BlockState blockState) {
        super(DDBlockEntities.MONSTER_POT.get(), pos, blockState);
    }
}
