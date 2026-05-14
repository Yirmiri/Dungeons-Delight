package net.yirmiri.dungeonsdelight.common.block.crops;

import net.minecraft.world.level.ItemLike;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

public class ManalliumCropBlock extends RottenCropBlock {
    public ManalliumCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxAge() {
        return 7;
    }

    @Override
    public ItemLike getBaseSeedId() {
        return DDItems.MANALLIUM.get();
    }
}
