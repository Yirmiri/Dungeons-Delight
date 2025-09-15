package net.yirmiri.dungeonsdelight.common.block;

import net.azurune.runiclib.common.publicized.PublicButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class WormwoodButtonBlock extends PublicButtonBlock {
    public WormwoodButtonBlock(BlockSetType type, int ticksToStayPressed, Properties properties) {
        super(type, ticksToStayPressed, properties);
    }

    //TODO: ticks pressed based on light level? (idea)
}
