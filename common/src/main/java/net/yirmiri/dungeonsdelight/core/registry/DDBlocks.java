package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.common.publicized.*;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.*;
import net.yirmiri.dungeonsdelight.common.block.banquets.TelepotageBlock;
import net.yirmiri.dungeonsdelight.common.block.crops.*;
import net.yirmiri.dungeonsdelight.common.block.entity.cleaving_board.CleavingBoardBlock;
import net.yirmiri.dungeonsdelight.common.block.entity.fire.LivingCampfireBlock;
import net.yirmiri.dungeonsdelight.common.block.entity.fire.LivingFireBlock;
import net.yirmiri.dungeonsdelight.common.block.entity.fire.SpiritFireBlock;
import net.yirmiri.dungeonsdelight.common.block.entity.item_grate.ItemGrateBlock;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.MonsterPotBlock;
import net.yirmiri.dungeonsdelight.common.block.entity.wavy_block.WavyBlock;
import net.yirmiri.dungeonsdelight.common.block.entity.wormouth.WormouthBlock;
import net.yirmiri.dungeonsdelight.common.util.BlockGroup;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;

import java.util.function.Supplier;

public class DDBlocks {
    //FUNCTIONAL
    public static final Supplier<Block> MONSTER_POT = registerBlock("monster_pot", () -> new MonsterPotBlock(DDProperties.BlockP.MONSTER_POT), false);
    public static final Supplier<Block> DUNGEON_STOVE = registerBlock("dungeon_stove", () -> new DungeonStoveBlock(DDProperties.BlockP.DUNGEON_STOVE), false);
    public static final Supplier<Block> WORMWOOD_CLEAVING_BOARD = registerBlock("wormwood_cleaving_board", () -> new CleavingBoardBlock(DDProperties.BlockP.CLEAVING_BOARD), true);
    public static final Supplier<Block> BAMBOO_CLEAVING_BOARD = registerBlock("bamboo_cleaving_board", () -> new CleavingBoardBlock(DDProperties.BlockP.BAMBOO_CLEAVING_BOARD), true);
    public static final Supplier<Block> SPIKE_TRAP = registerBlock("spike_trap", () -> new SpikeTrapBlock(DDProperties.BlockP.SPIKE_TRAP), false);

    //CROPS
    public static final Supplier<Block> TERROR_PRETA = registerBlock("terror_preta", () -> new TerrorPretaBlock(DDProperties.BlockP.TERROR_PRETA), true);
    public static final Supplier<Block> EMBEDDED_EGGS = registerBlock("embedded_eggs", () -> new EmbeddedEggsBlock(DDProperties.BlockP.SCULK_EGGS), true);
    public static final Supplier<Block> BLEETS = registerBlock("bleets", () -> new BleetsCropBlock(DDProperties.BlockP.BLEETS), false);
    public static final Supplier<Block> ENDELVES = registerBlock("endelves", () -> new EndelveCropBlock(DDProperties.BlockP.ENDELVES), false);
    public static final Supplier<Block> MANALLIUMS = registerBlock("manalliums", () -> new ManalliumCropBlock(DDProperties.BlockP.MANALLIUMS), false);
    public static final Supplier<Block> SOUL_PEPPERS = registerBlock("soul_peppers", () -> new SoulPeppersCropBlock(DDProperties.BlockP.SOUL_PEPPERS), false);
    public static final Supplier<Block> ROTBULB = registerBlock("rotbulb", () -> new RotbulbCropBlock(DDProperties.BlockP.ROTBULB), false);

    //WILD CROPS
    public static final Supplier<Block> WILD_BLEETS = registerBlock("wild_bleets", () -> new WildCropBlock(DDProperties.BlockP.WILD_CROP), true);
    public static final Supplier<Block> WILD_ENDELVES = registerBlock("wild_endelves", () -> new WildCropBlock(DDProperties.BlockP.WILD_CROP), true);
    public static final Supplier<Block> WILD_MANALLIUMS = registerBlock("wild_manalliums", () -> new WildCropBlock(DDProperties.BlockP.WILD_CROP), true);
    public static final Supplier<Block> WILD_ROTBULB = registerBlock("wild_rotbulb", () -> new WildRotbulbBlock(DDProperties.BlockP.WILD_ROTBULB), false);

    //GENERIC ROTTEN CROPS
    public static final Supplier<Block> ROTTEN_CROP = registerBlock("rotten_crop", () -> new GenericRottenCropBlock(DDProperties.BlockP.ROTTEN_CROP), false);
    public static final Supplier<Block> PUTRESCENT_CARROTS = registerBlock("putrescent_carrots", () -> new PutrescentCarrotBlock(DDProperties.BlockP.ROTTEN_CARROTS), false);
    public static final Supplier<Block> POISONOUS_POTATOES = registerBlock("poisonous_potatoes", () -> new PoisonousPotatoBlock(DDProperties.BlockP.ROTTEN_POTATOES), false);
    public static final Supplier<Block> BLIGHTED_BEETROOTS = registerBlock("blighted_beetroots", () -> new BlightedBeetrootBlock(DDProperties.BlockP.ROTTEN_BEETS), false);

    //MISC
    public static final Supplier<Block> GUNK = registerBlock("gunk", () -> new GunkBlock(DDProperties.BlockP.GUNK), false);
    public static final Supplier<Block> GUNK_BLOCK = wavyBlock("gunk_block", DDProperties.BlockP.WAVY_GOO, DDProperties.ItemP.GENERIC_MONSTER);
    public static final Supplier<Block> ROTTEN_FLESH_BLOCK = registerBlock("rotten_flesh_block", () -> new WavyBlock(DDProperties.BlockP.WAVY_GOO), true);
    public static final Supplier<Block> SCULK_MAYONNAISE_BLOCK = registerBlock("sculk_mayonnaise_block", () -> new WavyBlock(DDProperties.BlockP.SCULK_MAYO), true);
    public static final Supplier<Block> ROTTEN_SPAWNER = registerBlock("rotten_spawner", () -> new Block(DDProperties.BlockP.SPAWNER), false);
    public static final Supplier<Block> ENAMELED_GLASS = registerBlock("enameled_glass", () -> new EnameledGlassBlock(DDProperties.BlockP.ENAMELED_GLASS), false);

    //BANQUETS
    public static final Supplier<Block> TELEPOTAGE_BLOCK = registerBlock("telepotage_block", () -> new TelepotageBlock(DDProperties.BlockP.GENERIC), false);

    //LIVING ESSENCE
    public static final Supplier<Block> LIVING_TORCH = registerBlock("living_torch", () -> new LivingTorchBlock(DDProperties.BlockP.LIVING_TORCH), false);
    public static final Supplier<Block> WALL_LIVING_TORCH = registerBlock("wall_living_torch", () -> new WallLivingTorchBlock(DDProperties.BlockP.LIVING_TORCH), false);
    public static final Supplier<Block> LIVING_FIRE = registerBlock("living_fire", () -> new LivingFireBlock(DDProperties.BlockP.LIVING_FIRE), false);
    public static final Supplier<Block> SPIRIT_FIRE = registerBlock("spirit_fire", () -> new SpiritFireBlock(DDProperties.BlockP.LIVING_FIRE), false);
    public static final Supplier<Block> LIVING_CANDLE = registerBlock("living_candle", () -> new LivingCandleBlock(DDProperties.BlockP.LIVING_CANDLE), false);
    public static final Supplier<Block> LIVING_CAMPFIRE = registerBlock("living_campfire", () -> new LivingCampfireBlock(DDProperties.BlockP.LIVING_CAMPFIRE), false);
    public static final Supplier<Block> LIVING_LANTERN = registerBlock("living_lantern", () -> new LivingLanternBlock(DDProperties.BlockP.LIVING_LANTERN), false);

    //STAINED SCRAP
    public static final Supplier<Block> STAINED_SCRAP_BLOCK = basicBlock("stained_scrap_block", DDProperties.BlockP.STAINED, DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> CUT_STAINED_SCRAP = basicBlock("cut_stained_scrap", DDProperties.BlockP.STAINED, DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> CUT_STAINED_SCRAP_STAIRS = stairs("cut_stained_scrap_stairs", CUT_STAINED_SCRAP, DDProperties.BlockP.STAINED, DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> CUT_STAINED_SCRAP_SLAB = slab("cut_stained_scrap_slab", DDProperties.BlockP.STAINED, DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> STAINED_SCRAP_BARS = registerBlock("stained_scrap_bars", () -> new StainedScrapBarsBlock(DDProperties.BlockP.STAINED_SCRAP_BARS), DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP, BlockGroup.ModelMode.MANUAL);
    public static final Supplier<Block> STAINED_SCRAP_GATE = registerBlock("stained_scrap_gate", () -> new StainedScrapGateBlock(DDProperties.BlockP.STAINED_SCRAP_BARS), DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP, BlockGroup.ModelMode.MANUAL);
    public static final Supplier<Block> CHISELED_STAINED_SCRAP = basicBlock("chiseled_stained_scrap", DDProperties.BlockP.STAINED, DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> STAINED_SCRAP_PILLAR = registerBlock("stained_scrap_pillar", () -> new RotatedPillarBlock(DDProperties.BlockP.STAINED), DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP, BlockGroup.ModelMode.PILLAR);
    public static final Supplier<Block> STAINED_SCRAP_DOOR = door("stained_scrap_door", DDBlockSetTypes.STAINED_BLOCKSET, DDProperties.BlockP.STAINED_SCRAP_DOOR, DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> STAINED_SCRAP_TRAPDOOR = trapdoor("stained_scrap_trapdoor", DDBlockSetTypes.STAINED_BLOCKSET, DDProperties.BlockP.STAINED_SCRAP_TRAPDOOR, DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> STAINED_SCRAP_GRATE = registerBlock("stained_scrap_grate", () -> new ItemGrateBlock(DDProperties.BlockP.STAINED_GRATE), DDProperties.ItemP.GENERIC_UNCOMMON, BlockGroup.STAINED_SCRAP, BlockGroup.ModelMode.BLOCK);
    public static final Supplier<Block> STAINED_SCRAP_CHAIN = registerBlock("stained_scrap_chain", () -> new ChainBlock(DDProperties.BlockP.STAINED_SCRAP_CHAIN), false);

    //WORMWOOD
    public static final Supplier<Block> WORMROOT_TENDRILS = registerBlock("wormroot_tendrils", () -> new WormrootTendrilsBlock(DDProperties.BlockP.WORMROOT_TENDRILS), DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD, BlockGroup.ModelMode.MULTIFACE);
    public static final Supplier<Block> WORMROOT_STALK = registerBlock("wormroot_stalk", () -> new WormrootsStalkBlock(DDProperties.BlockP.WORMWOOD), DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD, BlockGroup.ModelMode.MANUAL);
    public static final Supplier<Block> WORMOUTH = registerBlock("wormouth", () -> new WormouthBlock(DDProperties.BlockP.WORMOUTH), DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD, BlockGroup.ModelMode.MANUAL);
    public static final Supplier<Block> WORMROOTS_BLOCK = basicBlock("wormroots_block", DDProperties.BlockP.WORMWOOD, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_PLANKS = basicBlock("wormwood_planks", DDProperties.BlockP.WORMWOOD, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_STAIRS = stairs("wormwood_stairs", WORMWOOD_PLANKS, DDProperties.BlockP.WORMWOOD, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_SLAB = slab("wormwood_slab", DDProperties.BlockP.WORMWOOD, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_MOSAIC = basicBlock("wormwood_mosaic", DDProperties.BlockP.WORMWOOD, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_MOSAIC_STAIRS = stairs("wormwood_mosaic_stairs", WORMWOOD_MOSAIC, DDProperties.BlockP.WORMWOOD, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_MOSAIC_SLAB = slab("wormwood_mosaic_slab", DDProperties.BlockP.WORMWOOD, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_FENCE = fence("wormwood_fence", DDProperties.BlockP.WORMWOOD, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_FENCE_GATE = fenceGate("wormwood_fence_gate", DDBlockSetTypes.WORMWOOD, DDProperties.BlockP.WORMWOOD, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_DOOR = door("wormwood_door",DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_DOOR, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_TRAPDOOR = trapdoor("wormwood_trapdoor", DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_TRAPDOOR, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_BUTTON = button("wormwood_button", DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_BUTTON, 20, true, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_PRESSURE_PLATE = pressurePlate("wormwood_pressure_plate", DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_PRESSURE_PLATE, PressurePlateBlock.Sensitivity.EVERYTHING, DDProperties.ItemP.GENERIC, BlockGroup.WORMWOOD);

    //COBBLED
    public static final Supplier<Block> COBBLED_BRICKS = basicBlock("cobbled_bricks", DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> COBBLED_BRICK_STAIRS = stairs("cobbled_brick_stairs", COBBLED_BRICKS, DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> COBBLED_BRICK_SLAB = slab("cobbled_brick_slab", DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> COBBLED_BRICK_WALL = wall("cobbled_brick_wall", DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> MOSSY_COBBLED_BRICKS = basicBlock("mossy_cobbled_bricks", DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> MOSSY_COBBLED_BRICK_STAIRS = stairs("mossy_cobbled_brick_stairs", COBBLED_BRICKS, DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> MOSSY_COBBLED_BRICK_SLAB = slab("mossy_cobbled_brick_slab", DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> MOSSY_COBBLED_BRICK_WALL = wall("mossy_cobbled_brick_wall", DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> CRACKED_COBBLED_BRICKS = basicBlock("cracked_cobbled_bricks", DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> COBBLED_TILES = basicBlock("cobbled_tiles", DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> COBBLED_TILE_STAIRS = stairs("cobbled_tile_stairs", COBBLED_BRICKS, DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> COBBLED_TILE_SLAB = slab("cobbled_tile_slab", DDProperties.BlockP.COBBLED, DDProperties.ItemP.GENERIC, BlockGroup.COBBLED);
    public static final Supplier<Block> CHISELED_COBBLE = registerBlock("chiseled_cobble", () -> new ChiseledCobbleBlock(DDProperties.BlockP.COBBLED), true, BlockGroup.COBBLED, BlockGroup.ModelMode.MANUAL);

    //REGISTRY
    private static Supplier<Block> basicBlock(String id, BlockBehaviour.Properties properties, Item.Properties itemprops, BlockGroup group) { return registerBlock(id, () -> new Block(properties), itemprops, group, BlockGroup.ModelMode.BLOCK); }
    private static Supplier<Block> wavyBlock(String id, BlockBehaviour.Properties properties, Item.Properties itemprops) { return registerBlock(id, () -> new WavyBlock(properties), itemprops, null, BlockGroup.ModelMode.MANUAL); }
    private static Supplier<Block> stairs(String id, Supplier<Block> block, BlockBehaviour.Properties properties, Item.Properties itemprops, BlockGroup group) { return registerBlock(id, () -> new PublicStairBlock(block.get().defaultBlockState(), properties), itemprops, group, BlockGroup.ModelMode.STAIRS); }
    private static Supplier<Block> slab(String id, BlockBehaviour.Properties properties, Item.Properties itemprops, BlockGroup group) { return registerBlock(id, () -> new SlabBlock(properties), itemprops, group, BlockGroup.ModelMode.SLAB); }
    private static Supplier<Block> wall(String id, BlockBehaviour.Properties properties, Item.Properties itemprops, BlockGroup group) { return registerBlock(id, () -> new WallBlock(properties), itemprops, group, BlockGroup.ModelMode.WALL); }
    private static Supplier<Block> fence(String id, BlockBehaviour.Properties properties, Item.Properties itemprops, BlockGroup group) { return registerBlock(id, () -> new FenceBlock(properties), itemprops, group, BlockGroup.ModelMode.FENCE); }
    private static Supplier<Block> fenceGate(String id, WoodType type, BlockBehaviour.Properties properties, Item.Properties itemprops, BlockGroup group) { return registerBlock(id, () -> new FenceGateBlock(properties, type), itemprops, group, BlockGroup.ModelMode.FENCE_GATE); }
    private static Supplier<Block> door(String id, BlockSetType type, BlockBehaviour.Properties properties, Item.Properties itemprops, BlockGroup group) { return registerBlock(id, () -> new PublicDoorBlock(properties, type), itemprops, group, BlockGroup.ModelMode.DOOR); }
    private static Supplier<Block> trapdoor(String id, BlockSetType type, BlockBehaviour.Properties properties, Item.Properties itemprops, BlockGroup group) { return registerBlock(id, () -> new PublicTrapdoorBlock(properties, type), itemprops, group, BlockGroup.ModelMode.TRAPDOOR); }
    private static Supplier<Block> button(String id, BlockSetType type, BlockBehaviour.Properties properties, int pressTicks, boolean arrowHolds, Item.Properties itemprops, BlockGroup group) { return registerBlock(id, () -> new PublicButtonBlock(properties, type, pressTicks, arrowHolds), itemprops, group, BlockGroup.ModelMode.BUTTON); }
    private static Supplier<Block> pressurePlate(String id, BlockSetType type, BlockBehaviour.Properties properties, PressurePlateBlock.Sensitivity sens, Item.Properties itemprops, BlockGroup group) { return registerBlock(id, () -> new PublicPressurePlateBlock(sens, properties, type), itemprops, group, BlockGroup.ModelMode.PLATE); }

    //MAIN REGISTRATION METHODS
    public static Supplier<Block> registerBlock(String id, Supplier<Block> supplier, boolean hasItem) {
        return registerBlock(id, supplier, hasItem, null, BlockGroup.ModelMode.MANUAL);
    }

    public static Supplier<Block> registerBlock(String id, Supplier<Block> supplier, boolean hasItem, BlockGroup group, BlockGroup.ModelMode mode) {
        Supplier<Block> block = Services.REGISTRY.registerBlock(DungeonsDelight.MOD_ID, id, supplier, hasItem);
        if (group != null && mode != null) {
            group.addQuick(block, id, mode);
        }
        return block;
    }

    public static Supplier<Block> registerBlock(String id, Supplier<Block> supplier, Item.Properties properties, BlockGroup group, BlockGroup.ModelMode mode) {
        Supplier<Block> block = Services.REGISTRY.registerBlock(DungeonsDelight.MOD_ID, id, supplier, false);

        if (properties != null) Services.REGISTRY.registerItem(DungeonsDelight.MOD_ID, id, () -> new BlockItem(block.get(), properties));

        if (group != null && mode != null) {
            group.addQuick(block, id, mode);
        }
        return block;
    }

    public static void load() {
    }
}