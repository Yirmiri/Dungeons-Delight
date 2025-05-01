package net.yirmiri.dungeonsdelight.common.block;

import net.azurune.runiclib.common.publicized.PublicButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class WormwoodButtonBlock extends PublicButtonBlock {
    public WormwoodButtonBlock(Properties properties, BlockSetType type) {
        super(properties, type, 20, true);
    }

    //TODO: ticks pressed based on light level? (idea)
}
