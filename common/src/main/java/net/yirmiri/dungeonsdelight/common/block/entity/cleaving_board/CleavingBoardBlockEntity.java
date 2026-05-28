package net.yirmiri.dungeonsdelight.common.block.entity.cleaving_board;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

public class CleavingBoardBlockEntity extends BlockEntity {
    protected NonNullList<ItemStack> items;
    private ResourceLocation outputTable; //loot table so we can include multiple items with weights and chances easily

    public CleavingBoardBlockEntity(BlockPos pos, BlockState blockState) {
        super(DDBlockEntities.CLEAVING_BOARD.get(), pos, blockState);
    }
}
