package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.cleaving_board.CleavingBoardBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.fire.LivingCampfireBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.fire.LivingFireBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.item_grate.ItemGrateBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.wavy_block.WavyBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.wormouth.WormouthBlockEntity;

import java.util.function.Supplier;

public class DDBlockEntities {
    public static final Supplier<BlockEntityType<WormouthBlockEntity>> WORMOUTH = registerBlockEntity("wormouth",
            () -> Services.REGISTRY.createBlockEntity(WormouthBlockEntity::new,
                    DDBlocks.WORMOUTH.get()
            ));

    public static final Supplier<BlockEntityType<ItemGrateBlockEntity>> ITEM_GRATE = registerBlockEntity("item_grate",
            () -> Services.REGISTRY.createBlockEntity(ItemGrateBlockEntity::new,
                    DDBlocks.STAINED_SCRAP_GRATE.get()
            ));

    public static final Supplier<BlockEntityType<CleavingBoardBlockEntity>> CLEAVING_BOARD = registerBlockEntity("cleaving_board",
            () -> Services.REGISTRY.createBlockEntity(CleavingBoardBlockEntity::new,
                    DDBlocks.BAMBOO_CLEAVING_BOARD.get(), DDBlocks.WORMWOOD_CLEAVING_BOARD.get()
            ));

    public static final Supplier<BlockEntityType<MonsterPotBlockEntity>> MONSTER_POT = registerBlockEntity("monster_pot",
            () -> Services.REGISTRY.createBlockEntity(MonsterPotBlockEntity::new,
                    DDBlocks.MONSTER_POT.get()
            ));

    public static final Supplier<BlockEntityType<WavyBlockEntity>> WAVY_BLOCK = registerBlockEntity("wavy_block",
            () -> Services.REGISTRY.createBlockEntity(WavyBlockEntity::new,
                    DDBlocks.ROTTEN_FLESH_BLOCK.get(), DDBlocks.SCULK_MAYONNAISE_BLOCK.get(), DDBlocks.GUNK_BLOCK.get()
            ));

    public static final Supplier<BlockEntityType<LivingFireBlockEntity>> LIVING_FIRE = registerBlockEntity("living_fire",
            () -> Services.REGISTRY.createBlockEntity(LivingFireBlockEntity::new,
                    DDBlocks.LIVING_FIRE.get(), DDBlocks.SPIRIT_FIRE.get()
            ));

    public static final Supplier<BlockEntityType<LivingCampfireBlockEntity>> LIVING_CAMPFIRE = registerBlockEntity("living_campfire",
            () -> Services.REGISTRY.createBlockEntity(LivingCampfireBlockEntity::new,
                    DDBlocks.LIVING_CAMPFIRE.get()
            ));

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> type) {
        return Services.REGISTRY.registerBlockEntityType(DungeonsDelight.MOD_ID, id, type);
    }

    public static void load() {};
}
