package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.*;
import net.yirmiri.dungeonsdelight.util.DDBlockSetTypes;
import net.yirmiri.dungeonsdelight.util.DDProperties;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.Supplier;

public class DDBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DungeonsDelight.MOD_ID);

    //MISC
    public static final RegistryObject<Block> ROTBULB_CRATE = registerBlock("rotbulb_crate", () -> new Block(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> SCULK_MAYO_BLOCK = registerBlock("sculk_mayo_block", () -> new Block(DDProperties.BlockP.SCULK_MAYO));

    //STATIONS
    public static final RegistryObject<Block> DUNGEON_STOVE = registerBlock("dungeon_stove", () -> new DungeonStoveBlock(DDProperties.BlockP.DUNGEON_STOVE));
    public static final RegistryObject<Block> MONSTER_POT = registerBlock("monster_pot", () -> new MonsterPotBlock(DDProperties.BlockP.MONSTER_POT));

    //FEASTS
    public static final RegistryObject<Block> GLOWBERRY_GELATIN_BLOCK = registerFeastBlock("glowberry_gelatin_block", () -> new GlowberryGelatinBlock(DDProperties.BlockP.GLOWBERRY_GELATIN, DDItems.GLOWBERRY_GELATIN, true));

    //WORMWOOD
    public static final RegistryObject<Block> WORMROOTS = registerBlock("wormroots", () -> new WormrootsBlock(DDProperties.BlockP.WORMROOTS));
    public static final RegistryObject<Block> WORMROOTS_BLOCK = registerBlock("wormroots_block", () -> new Block(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_PLANKS = registerBlock("wormwood_planks", () -> new Block(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_STAIRS = registerBlock("wormwood_stairs", () -> new StairBlock(WORMWOOD_PLANKS.get().defaultBlockState(), DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_SLAB = registerBlock("wormwood_slab", () -> new SlabBlock(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_MOSAIC = registerBlock("wormwood_mosaic", () -> new Block(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_MOSAIC_STAIRS = registerBlock("wormwood_mosaic_stairs", () -> new StairBlock(WORMWOOD_MOSAIC.get().defaultBlockState(), DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_MOSAIC_SLAB = registerBlock("wormwood_mosaic_slab", () -> new SlabBlock(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_FENCE = registerBlock("wormwood_fence", () -> new FenceBlock(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_FENCE_GATE = registerBlock("wormwood_fence_gate", () -> new FenceGateBlock(DDProperties.BlockP.WORMWOOD, DDBlockSetTypes.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_DOOR = registerBlock("wormwood_door", () -> new DoorBlock(DDProperties.BlockP.WORMWOOD_DOOR, DDBlockSetTypes.WORMWOOD_BLOCKSET));
    public static final RegistryObject<Block> WORMWOOD_TRAPDOOR = registerBlock("wormwood_trapdoor", () -> new TrapDoorBlock(DDProperties.BlockP.WORMWOOD_TRAPDOOR, DDBlockSetTypes.WORMWOOD_BLOCKSET));
    public static final RegistryObject<Block> WORMWOOD_BUTTON = registerBlock("wormwood_button", () -> new WormwoodButtonBlock(DDProperties.BlockP.WORMWOOD_BUTTON, DDBlockSetTypes.WORMWOOD_BLOCKSET));
    public static final RegistryObject<Block> WORMWOOD_PRESSURE_PLATE = registerBlock("wormwood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, DDProperties.BlockP.WORMWOOD_PRESSURE_PLATE, DDBlockSetTypes.WORMWOOD_BLOCKSET));
    public static final RegistryObject<Block> WORMWOOD_CABINET = registerBlock("wormwood_cabinet", () -> new CabinetBlock(DDProperties.BlockP.WORMWOOD_CABINET));

    //GROWABLES
    public static final RegistryObject<Block> EMBEDDED_EGGS = registerBlock("embedded_eggs", () -> new EmbeddedEggsBlock(DDProperties.BlockP.SCULK_EGGS));
    public static final RegistryObject<Block> HEAP_OF_ANCIENT_EGGS = registerBlock("heap_of_ancient_eggs", () -> new Block(DDProperties.BlockP.SCULK_EGGS));
    public static final RegistryObject<Block> ROTBULB_CROP = registerBlock("rotbulb_crop", () -> new RotbulbCropBlock(DDProperties.BlockP.ROTBULB));
    public static final RegistryObject<Block> ROTBULB_PLANT = registerBlock("rotbulb_plant", () -> new RotbulbPlantBlock(DDProperties.BlockP.ROTBULB));

    private static <T extends Block> RegistryObject<T> registerBlock(String id, Supplier<T> block) {
        RegistryObject<T> blockRegister = BLOCKS.register(id, block);
        registerBlockItem(id, blockRegister);
        return blockRegister;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWOItem(String id, Supplier<T> block) {
        return BLOCKS.register(id, block);
    }

    private static <T extends Block> void registerBlockItem(String id, RegistryObject<T> block) {
        DDItems.ITEMS.register(id, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerFeastBlock(String id, Supplier<T> block) {
        RegistryObject<T> blockRegister = BLOCKS.register(id, block);
        registerFeastBlockItem(id, blockRegister);
        return blockRegister;
    }

    private static <T extends Block> void registerFeastBlockItem(String id, RegistryObject<T> block) {
        DDItems.ITEMS.register(id, () -> new BlockItem(block.get(), new Item.Properties().stacksTo(1)));
    }
}
