package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;

import java.util.ArrayList;
import java.util.Set;

public class DDBlockLootGen extends BlockLootSubProvider {
    public DDBlockLootGen() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(DDBlocks.DUNGEON_STOVE);
        dropSelf(DDBlocks.MONSTER_POT);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return new ArrayList<>() {{
            addAll(DDBlocks.BLOCKS.getEntries().stream().filter((r) -> r.get().getLootTable() != BuiltInLootTables.EMPTY).map(RegistryObject::get).toList());
        }};
    }


    private void dropSelf(RegistryObject<Block> block) {
        dropSelf(block.get());
    }

    private void add(RegistryObject<Block> block, LootItemCondition.Builder builder) {
        add(block, builder);
    }
}
