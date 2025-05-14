package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.common.publicized.PublicStairBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.*;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.function.Supplier;

public class DDBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DungeonsDelight.MOD_ID);

    //MISC
    public static final RegistryObject<Block> ROTBULB_CRATE = registerBlockWOItem("rotbulb_crate", () -> new Block(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> POISONOUS_POTATO_CRATE = registerBlock("poisonous_potato_crate", () -> new Block(DDProperties.BlockP.CRATE));
    public static final RegistryObject<Block> ROTTEN_TOMATO_CRATE = registerBlock("rotten_tomato_crate", () -> new Block(DDProperties.BlockP.CRATE));
    public static final RegistryObject<Block> SCULK_MAYO_BLOCK = registerBlock("sculk_mayo_block", () -> new Block(DDProperties.BlockP.SCULK_MAYO));
    public static final RegistryObject<Block> GUNK = registerBlockWOItem("gunk", () -> new GunkBlock(DDProperties.BlockP.GUNK));

    //STAINED SCRAP & LIVING FIRE
    public static final RegistryObject<Block> STAINED_SCRAP_BLOCK = registerBlockWOItem("stained_scrap_block", () -> new Block(DDProperties.BlockP.SPAWNER));
    public static final RegistryObject<Block> CUT_STAINED_SCRAP = registerBlockWOItem("cut_stained_scrap", () -> new Block(DDProperties.BlockP.SPAWNER));
    public static final RegistryObject<Block> CUT_STAINED_SCRAP_STAIRS = registerBlockWOItem("cut_stained_scrap_stairs", () -> new PublicStairBlock(CUT_STAINED_SCRAP.get().defaultBlockState(), DDProperties.BlockP.SPAWNER));
    public static final RegistryObject<Block> CUT_STAINED_SCRAP_SLAB = registerBlockWOItem("cut_stained_scrap_slab", () -> new SlabBlock(DDProperties.BlockP.SPAWNER));
    public static final RegistryObject<Block> STAINED_SCRAP_BARS = registerBlockWOItem("stained_scrap_bars", () -> new IronBarsBlock(DDProperties.BlockP.SPAWNER_BARS));
    public static final RegistryObject<Block> LIVING_FIRE = registerBlockWOItem("living_fire", () -> new LivingFireBlock(DDProperties.BlockP.LIVING_FIRE));

    //PLANTS
    public static final RegistryObject<Block> ROTBULB_CROP = registerBlockWOItem("rotbulb_crop", () -> new RotbulbCropBlock(DDProperties.BlockP.ROTBULB));
    public static final RegistryObject<Block> ROTBULB_PLANT = registerBlockWOItem("rotbulb_plant", () -> new RotbulbPlantBlock(DDProperties.BlockP.ROTBULB));
    public static final RegistryObject<Block> ROTTEN_CROP = registerBlockWOItem("rotten_crop", () -> new RottenCropBlock(DDProperties.BlockP.ROTTEN_CROP));
    public static final RegistryObject<Block> ROTTEN_POTATOES = registerBlockWOItem("rotten_potatoes", () -> new RottenPotatoCropBlock(DDProperties.BlockP.ROTTEN_CROP));
    public static final RegistryObject<Block> ROTTEN_TOMATOES = registerBlockWOItem("rotten_tomatoes", () -> new RottenTomatoesBlock(DDProperties.BlockP.ROTTEN_CROP));

    //FUNCTION BLOCKS
    public static final RegistryObject<Block> DUNGEON_STOVE = registerBlockWOItem("dungeon_stove", () -> new DungeonStoveBlock(DDProperties.BlockP.DUNGEON_STOVE));
    public static final RegistryObject<Block> MONSTER_POT = registerBlockWOItem("monster_pot", () -> new MonsterPotBlock(DDProperties.BlockP.MONSTER_POT));
    public static final RegistryObject<Block> EMBEDDED_EGGS = registerBlock("embedded_eggs", () -> new EmbeddedEggsBlock(DDProperties.BlockP.SCULK_EGGS));
    public static final RegistryObject<Block> HEAP_OF_ANCIENT_EGGS = registerBlock("heap_of_ancient_eggs", () -> new Block(DDProperties.BlockP.SCULK_EGGS));

    //CAKES/PIES
    public static final RegistryObject<Block> SPIDER_PIE = registerBlockWOItem("spider_pie", () -> new PieBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), DDItems.SPIDER_PIE_SLICE));
    public static final RegistryObject<Block> SCULK_TART = registerBlock("sculk_tart", () -> new ExperiencePieBlock(BlockBehaviour.Properties.copy(Blocks.CAKE).sound(SoundType.SCULK), 15, DDItems.SCULK_TART_SLICE));
    public static final RegistryObject<Block> MONSTER_CAKE = registerBlockWOItem("monster_cake", () -> new MonsterCakeBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));
    //CANDLE CAKES
    public static final RegistryObject<Block> CANDLE_MONSTER_CAKE = registerBlockWOItem("candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> WHITE_CANDLE_MONSTER_CAKE = registerBlockWOItem("white_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.WHITE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> LIGHT_GRAY_CANDLE_MONSTER_CAKE = registerBlockWOItem("light_gray_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.LIGHT_GRAY_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> GRAY_CANDLE_MONSTER_CAKE = registerBlockWOItem("gray_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.GRAY_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> BLACK_CANDLE_MONSTER_CAKE = registerBlockWOItem("black_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.BLACK_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> BROWN_CANDLE_MONSTER_CAKE = registerBlockWOItem("brown_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.BROWN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> RED_CANDLE_MONSTER_CAKE = registerBlockWOItem("red_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.RED_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> ORANGE_CANDLE_MONSTER_CAKE = registerBlockWOItem("orange_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.ORANGE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> YELLOW_CANDLE_MONSTER_CAKE = registerBlockWOItem("yellow_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.YELLOW_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> LIME_CANDLE_MONSTER_CAKE = registerBlockWOItem("lime_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.LIME_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> GREEN_CANDLE_MONSTER_CAKE = registerBlockWOItem("green_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.GREEN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> CYAN_CANDLE_MONSTER_CAKE = registerBlockWOItem("cyan_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.CYAN_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> LIGHT_BLUE_CANDLE_MONSTER_CAKE = registerBlockWOItem("light_blue_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.LIGHT_BLUE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> BLUE_CANDLE_MONSTER_CAKE = registerBlockWOItem("blue_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.BLUE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> PURPLE_CANDLE_MONSTER_CAKE = registerBlockWOItem("purple_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.PURPLE_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> MAGENTA_CANDLE_MONSTER_CAKE = registerBlockWOItem("magenta_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.MAGENTA_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));
    public static final RegistryObject<Block> PINK_CANDLE_MONSTER_CAKE = registerBlockWOItem("pink_candle_monster_cake", () -> new CandleMonsterCakeBlock(Blocks.PINK_CANDLE, BlockBehaviour.Properties.copy(Blocks.CANDLE_CAKE)));

    //FEASTS
    public static final RegistryObject<Block> GLOW_BERRY_GELATIN_BLOCK = registerFeastBlock("glow_berry_gelatin_block", () -> new GlowBerryGelatinBlock(DDProperties.BlockP.GLOW_BERRY_GELATIN_BLOCK, DDItems.GLOW_BERRY_GELATIN, true));
    public static final RegistryObject<Block> OSSOBUSCO_BLOCK = registerBlockWOItem("ossobusco_block", () -> new OssobuscoBlock(DDProperties.BlockP.OSSOBUSCO_BLOCK, DDItems.OSSOBUSCO, true));
    public static final RegistryObject<Block> GUARDIAN_ANGEL_BLOCK = registerBlockWOItem("guardian_angel_block", () -> new GuardianAngelBlock(DDProperties.BlockP.GUARDIAN_ANGEL_BLOCK, DDItems.GUARDIAN_ANGEL, true));

    //WORMWOOD
    public static final RegistryObject<Block> WORMROOTS = registerBlock("wormroots", () -> new WormrootsBlock(DDProperties.BlockP.WORMROOTS));
    public static final RegistryObject<Block> WORMROOTS_BLOCK = registerBlock("wormroots_block", () -> new Block(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_PLANKS = registerBlock("wormwood_planks", () -> new Block(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_STAIRS = registerBlock("wormwood_stairs", () -> new PublicStairBlock(WORMWOOD_PLANKS.get().defaultBlockState(), DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_SLAB = registerBlock("wormwood_slab", () -> new SlabBlock(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_MOSAIC = registerBlock("wormwood_mosaic", () -> new Block(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_MOSAIC_STAIRS = registerBlock("wormwood_mosaic_stairs", () -> new PublicStairBlock(WORMWOOD_MOSAIC.get().defaultBlockState(), DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_MOSAIC_SLAB = registerBlock("wormwood_mosaic_slab", () -> new SlabBlock(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_FENCE = registerBlock("wormwood_fence", () -> new FenceBlock(DDProperties.BlockP.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_FENCE_GATE = registerBlock("wormwood_fence_gate", () -> new FenceGateBlock(DDProperties.BlockP.WORMWOOD, DDBlockSetTypes.WORMWOOD));
    public static final RegistryObject<Block> WORMWOOD_DOOR = registerBlock("wormwood_door", () -> new DoorBlock(DDProperties.BlockP.WORMWOOD_DOOR, DDBlockSetTypes.WORMWOOD_BLOCKSET));
    public static final RegistryObject<Block> WORMWOOD_TRAPDOOR = registerBlock("wormwood_trapdoor", () -> new TrapDoorBlock(DDProperties.BlockP.WORMWOOD_TRAPDOOR, DDBlockSetTypes.WORMWOOD_BLOCKSET));
    public static final RegistryObject<Block> WORMWOOD_BUTTON = registerBlock("wormwood_button", () -> new WormwoodButtonBlock(DDProperties.BlockP.WORMWOOD_BUTTON, DDBlockSetTypes.WORMWOOD_BLOCKSET));
    public static final RegistryObject<Block> WORMWOOD_PRESSURE_PLATE = registerBlock("wormwood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, DDProperties.BlockP.WORMWOOD_PRESSURE_PLATE, DDBlockSetTypes.WORMWOOD_BLOCKSET));
    public static final RegistryObject<Block> WORMWOOD_CABINET = registerBlock("wormwood_cabinet", () -> new CabinetBlock(DDProperties.BlockP.WORMWOOD_CABINET));

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
