package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.block.entity.MonsterPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;

public class DDBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BE_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "dungeonsdelight");

    public static final RegistryObject<BlockEntityType<MonsterPotBlockEntity>> MONSTER_COOKING_POT = BE_TYPES.register("monster_cooking_pot",
            () -> BlockEntityType.Builder.of(MonsterPotBlockEntity::new, new Block[]{DDBlocks.MONSTER_POT.get()}).build(null));

    public static final RegistryObject<BlockEntityType<StoveBlockEntity>> DUNGEON_STOVE = BE_TYPES.register("dungeon_stove",
            () -> BlockEntityType.Builder.of(StoveBlockEntity::new, new Block[]{DDBlocks.DUNGEON_STOVE.get()}).build(null));
}
