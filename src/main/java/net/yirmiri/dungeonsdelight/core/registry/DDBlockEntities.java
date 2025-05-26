package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.common.block.entity.DungeonStoveBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.LivingCampfireBlockEntity;
import net.yirmiri.dungeonsdelight.common.block.entity.MonsterPotBlockEntity;

public class DDBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BE_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "dungeonsdelight");

    public static final RegistryObject<BlockEntityType<MonsterPotBlockEntity>> MONSTER_COOKING_POT = BE_TYPES.register("monster_cooking_pot",
            () -> BlockEntityType.Builder.of(MonsterPotBlockEntity::new, new Block[]{DDBlocks.MONSTER_POT.get()}).build(null));

    public static final RegistryObject<BlockEntityType<DungeonStoveBlockEntity>> DUNGEON_STOVE = BE_TYPES.register("dungeon_stove",
            () -> BlockEntityType.Builder.of(DungeonStoveBlockEntity::new, new Block[]{DDBlocks.DUNGEON_STOVE.get()}).build(null));

    public static final RegistryObject<BlockEntityType<LivingCampfireBlockEntity>> LIVING_CAMPFIRE = BE_TYPES.register("living_campfire",
            () -> BlockEntityType.Builder.of(LivingCampfireBlockEntity::new, new Block[]{DDBlocks.LIVING_CAMPFIRE.get()}).build(null));
}
