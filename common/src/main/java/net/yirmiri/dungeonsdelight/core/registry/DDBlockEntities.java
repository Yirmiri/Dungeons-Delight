package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.ItemGrateBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.WormouthBlockEntity;

import java.util.function.Supplier;

public class DDBlockEntities {
    public static final Supplier<BlockEntityType<WormouthBlockEntity>> WORMOUTH = registerBlockEntity("wormouth",
            () -> RLServices.REGISTRY.createBlockEntity(WormouthBlockEntity::new, DDBlocks.WORMOUTH.get()));

    public static final Supplier<BlockEntityType<ItemGrateBlockEntity>> ITEM_GRATE = registerBlockEntity("item_grate",
            () -> RLServices.REGISTRY.createBlockEntity(ItemGrateBlockEntity::new, DDBlocks.STAINED_SCRAP_GRATE.get()));

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> type) {
        return RLServices.REGISTRY.registerBlockEntityType(DungeonsDelight.MOD_ID, id, type);
    }

    public static void load() {};
}
