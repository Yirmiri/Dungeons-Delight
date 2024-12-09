package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.DungeonPotBlock;
import net.yirmiri.dungeonsdelight.block.DungeonStoveBlock;
import net.yirmiri.dungeonsdelight.util.DDProperties;

public class DDBlocks {
    //MISC
    public static final Block DUNGEON_POT = register("dungeon_pot", new DungeonPotBlock(DDProperties.BlockP.DUNGEON_POT), true);
    public static final Block DUNGEON_STOVE = register("dungeon_stove", new DungeonStoveBlock(DDProperties.BlockP.DUNGEON_STOVE), true);

    private static Block register(String id, Block block, boolean registerItem) {
        if (registerItem) {
            registerBlockItem(id, block);
        }
        return Registry.register(Registries.BLOCK, Identifier.of(DungeonsDelight.MOD_ID, id), block);
    }

    private static Item registerBlockItem(String id, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(DungeonsDelight.MOD_ID, id), new BlockItem(block, new Item.Settings()));
    }

    public static void loadBlocks() {
    }
}
