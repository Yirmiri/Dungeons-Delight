package net.yirmiri.dungeonsdelight.common.block.banquets;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BanquetBlock extends Block { //todo finish
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 4);

    public BanquetBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(SERVINGS, 4)
        );
    }

    public static boolean isEmpty(BlockState state) {
        return state.getValue(SERVINGS) == 0;
    }
}
