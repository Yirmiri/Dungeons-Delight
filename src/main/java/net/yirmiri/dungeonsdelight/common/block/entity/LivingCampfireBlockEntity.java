package net.yirmiri.dungeonsdelight.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import org.jetbrains.annotations.NotNull;

public class LivingCampfireBlockEntity extends CampfireBlockEntity {
    public LivingCampfireBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return DDBlockEntities.LIVING_CAMPFIRE.get();
    }
}
