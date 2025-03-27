package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class RancidReductionItem extends DrinkableItem {
    public RancidReductionItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos blockpos = ctx.getClickedPos();
        BlockState state = level.getBlockState(blockpos);

        //TODO FINISH(imtired)

        return super.useOn(ctx);
    }
}
