package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.DungeonStoveBlock;
import net.yirmiri.dungeonsdelight.block.MonsterPotBlock;
import net.yirmiri.dungeonsdelight.util.DDProperties;

import java.util.function.Supplier;

public class DDBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DungeonsDelight.MOD_ID);

    //STATIONS
    public static final RegistryObject<Block> DUNGEON_STOVE = registerBlock("dungeon_stove", () -> new DungeonStoveBlock(DDProperties.BlockP.DUNGEON_STOVE));
    public static final RegistryObject<Block> MONSTER_POT = registerBlock("dungeon_pot", () -> new MonsterPotBlock(DDProperties.BlockP.MONSTER_POT));

    private static <T extends Block> RegistryObject<T> registerBlock(String id, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(id, block);
        registerBlockItem(id, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWOItem(String id, Supplier<T> block) {
        return BLOCKS.register(id, block);
    }

    private static <T extends Block> void registerBlockItem(String id, RegistryObject<T> block) {
        DDItems.ITEMS.register(id, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
