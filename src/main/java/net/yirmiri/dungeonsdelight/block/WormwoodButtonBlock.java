package net.yirmiri.dungeonsdelight.block;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class WormwoodButtonBlock extends ButtonBlock {
    public WormwoodButtonBlock(Properties properties, BlockSetType type) {
        super(properties, type, 20, true);
    }

    //TODO: ticks pressed based on light level
}
