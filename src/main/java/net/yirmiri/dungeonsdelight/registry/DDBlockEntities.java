package net.yirmiri.dungeonsdelight.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.entity.DungeonPotBlockEntity;

public class DDBlockEntities {
    public static final BlockEntityType<DungeonPotBlockEntity> DUNGEON_POT = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(DungeonsDelight.MOD_ID, "dungeon_pot"), FabricBlockEntityTypeBuilder.create(DungeonPotBlockEntity::new,
                    DDBlocks.DUNGEON_POT
            ).build());

    public static void loadBlockEntities() {
    }
}
