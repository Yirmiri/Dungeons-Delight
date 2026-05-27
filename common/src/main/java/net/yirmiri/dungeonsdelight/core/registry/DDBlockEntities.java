package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.CleavingBoardBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.ItemGrateBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.WormouthBlockEntity;

import java.util.function.Supplier;

public class DDBlockEntities {
    public static final Supplier<BlockEntityType<WormouthBlockEntity>> WORMOUTH = registerBlockEntity("wormouth",
            () -> Services.REGISTRY.createBlockEntity(WormouthBlockEntity::new, DDBlocks.WORMOUTH.get()));

    public static final Supplier<BlockEntityType<ItemGrateBlockEntity>> ITEM_GRATE = registerBlockEntity("item_grate",
            () -> Services.REGISTRY.createBlockEntity(ItemGrateBlockEntity::new, DDBlocks.STAINED_SCRAP_GRATE.get()));

    public static final Supplier<BlockEntityType<CleavingBoardBlockEntity>> CLEAVING_BOARD = registerBlockEntity("cleaving_board",
            () -> Services.REGISTRY.createBlockEntity(CleavingBoardBlockEntity::new,
                    DDBlocks.BAMBOO_CLEAVING_BOARD.get(), DDBlocks.WORMWOOD_CLEAVING_BOARD.get())
    );

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> type) {
        return Services.REGISTRY.registerBlockEntityType(DungeonsDelight.MOD_ID, id, type);
    }

    public static void load() {};
}
