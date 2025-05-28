package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.common.block.entity.DungeonStoveBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.LivingCampfireBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.MonsterPotBlockEntity;

import java.util.function.Supplier;

public class DDBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BE_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "dungeonsdelight");

    public static final Supplier<BlockEntityType<MonsterPotBlockEntity>> MONSTER_COOKING_POT = BE_TYPES.register("monster_cooking_pot",
            () -> BlockEntityType.Builder.of(MonsterPotBlockEntity::new, new Block[]{DDBlocks.MONSTER_POT.get()}).build(null));

    public static final Supplier<BlockEntityType<DungeonStoveBlockEntity>> DUNGEON_STOVE = BE_TYPES.register("dungeon_stove",
            () -> BlockEntityType.Builder.of(DungeonStoveBlockEntity::new, new Block[]{DDBlocks.DUNGEON_STOVE.get()}).build(null));

    public static final Supplier<BlockEntityType<LivingCampfireBlockEntity>> LIVING_CAMPFIRE = BE_TYPES.register("living_campfire",
            () -> BlockEntityType.Builder.of(LivingCampfireBlockEntity::new, new Block[]{DDBlocks.LIVING_CAMPFIRE.get()}).build(null));
}
