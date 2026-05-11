package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.common.publicized.*;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.*;
import net.yirmiri.dungeonsdelight.common.util.BlockGroup;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;

import java.util.function.Supplier;

public class DDBlocks {

    //STAINED SCRAP //todo stainmed scrap blocks need uncommon rarity
    public static final Supplier<Block> STAINED_SCRAP_BLOCK = basicBlock("stained_scrap_block", DDProperties.BlockP.STAINED, true, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> CUT_STAINED_SCRAP = basicBlock("cut_stained_scrap", DDProperties.BlockP.STAINED, true, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> CUT_STAINED_SCRAP_STAIRS = stairs("cut_stained_scrap_stairs", CUT_STAINED_SCRAP, DDProperties.BlockP.STAINED, true, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> CUT_STAINED_SCRAP_SLAB = slab("cut_stained_scrap_slab", DDProperties.BlockP.STAINED, true, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> STAINED_SCRAP_BARS = registerBlock("stained_scrap_bars", () -> new PublicIronBarsBlock(DDProperties.BlockP.STAINED_SCRAP_BARS), true, BlockGroup.STAINED_SCRAP, BlockGroup.ModelMode.BARS);
    public static final Supplier<Block> CHISELED_STAINED_SCRAP = basicBlock("chiseled_stained_scrap", DDProperties.BlockP.STAINED, true, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> STAINED_SCRAP_PILLAR = registerBlock("stained_scrap_pillar", () -> new RotatedPillarBlock(DDProperties.BlockP.STAINED), true, BlockGroup.STAINED_SCRAP, BlockGroup.ModelMode.PILLAR);
    public static final Supplier<Block> STAINED_SCRAP_DOOR = door("stained_scrap_door", DDBlockSetTypes.STAINED_BLOCKSET, DDProperties.BlockP.STAINED_SCRAP_DOOR, true, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> STAINED_SCRAP_TRAPDOOR = trapdoor("stained_scrap_trapdoor", DDBlockSetTypes.STAINED_BLOCKSET, DDProperties.BlockP.STAINED_SCRAP_TRAPDOOR, true, BlockGroup.STAINED_SCRAP);
    public static final Supplier<Block> STAINED_SCRAP_GRATE = registerBlock("stained_scrap_grate", () -> new ItemGrateBlock(DDProperties.BlockP.STAINED_GRATE), true, BlockGroup.STAINED_SCRAP, BlockGroup.ModelMode.BLOCK);

    //WORMWOOD
    public static final Supplier<Block> WORMROOT_TENDRILS = registerBlock("wormroot_tendrils", () -> new WormrootTendrilsBlock(DDProperties.BlockP.WORMROOT_TENDRILS), true, BlockGroup.WORMWOOD, BlockGroup.ModelMode.MULTIFACE);
    public static final Supplier<Block> WORMROOT_STALK = registerBlock("wormroot_stalk", () -> new WormrootsStalkBlock(DDProperties.BlockP.WORMWOOD), true, BlockGroup.WORMWOOD, BlockGroup.ModelMode.MANUAL);
    public static final Supplier<Block> WORMOUTH = registerBlock("wormouth", () -> new WormouthBlock(DDProperties.BlockP.WORMOUTH), true, BlockGroup.WORMWOOD, BlockGroup.ModelMode.MANUAL);
    public static final Supplier<Block> WORMROOTS_BLOCK = basicBlock("wormroots_block", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_PLANKS = basicBlock("wormwood_planks", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_STAIRS = stairs("wormwood_stairs", WORMWOOD_PLANKS, DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_SLAB = slab("wormwood_slab", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_MOSAIC = basicBlock("wormwood_mosaic", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_MOSAIC_STAIRS = stairs("wormwood_mosaic_stairs", WORMWOOD_MOSAIC, DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_MOSAIC_SLAB = slab("wormwood_mosaic_slab", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_FENCE = fence("wormwood_fence", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_FENCE_GATE = fenceGate("wormwood_fence_gate", DDBlockSetTypes.WORMWOOD, DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_DOOR = door("wormwood_door",DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_DOOR, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_TRAPDOOR = trapdoor("wormwood_trapdoor", DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_TRAPDOOR, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_BUTTON = button("wormwood_button", DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_BUTTON, 20, true, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_PRESSURE_PLATE = pressurePlate("wormwood_pressure_plate", DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_PRESSURE_PLATE, PressurePlateBlock.Sensitivity.EVERYTHING, true, BlockGroup.WORMWOOD);

    //MISC
    public static final Supplier<Block> TERROR_PRETA = registerBlock("terror_preta", () -> new TerrorPretaBlock(DDProperties.BlockP.TERROR_PRETA), true);

    //REGISTRY
    private static Supplier<Block> basicBlock(String id, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new Block(properties), hasItem, group, BlockGroup.ModelMode.BLOCK); }
    private static Supplier<Block> stairs(String id, Supplier<Block> block, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new PublicStairBlock(block.get().defaultBlockState(), properties), hasItem, group, BlockGroup.ModelMode.STAIRS); }
    private static Supplier<Block> slab(String id, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new SlabBlock(properties), hasItem, group, BlockGroup.ModelMode.SLAB); }
    private static Supplier<Block> wall(String id, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new WallBlock(properties), hasItem, group, BlockGroup.ModelMode.WALL); }
    private static Supplier<Block> fence(String id, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new FenceBlock(properties), hasItem, group, BlockGroup.ModelMode.FENCE); }
    private static Supplier<Block> fenceGate(String id, WoodType type, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new FenceGateBlock(properties, type), hasItem, group, BlockGroup.ModelMode.FENCE_GATE); }
    private static Supplier<Block> door(String id, BlockSetType type, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new PublicDoorBlock(properties, type), hasItem, group, BlockGroup.ModelMode.DOOR); }
    private static Supplier<Block> trapdoor(String id, BlockSetType type, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new PublicTrapdoorBlock(properties, type), hasItem, group, BlockGroup.ModelMode.TRAPDOOR); }
    private static Supplier<Block> button(String id, BlockSetType type, BlockBehaviour.Properties properties, int pressTicks, boolean arrowHolds, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new PublicButtonBlock(properties, type, pressTicks, arrowHolds), hasItem, group, BlockGroup.ModelMode.BUTTON); }
    private static Supplier<Block> pressurePlate(String id, BlockSetType type, BlockBehaviour.Properties properties, PressurePlateBlock.Sensitivity sens, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new PublicPressurePlateBlock(sens, properties, type), hasItem, group, BlockGroup.ModelMode.PLATE); }

    public static Supplier<Block> registerBlock(String id, Supplier<Block> supplier, boolean hasItem) {
        return registerBlock(id, supplier, hasItem, null, BlockGroup.ModelMode.MANUAL);
    }

    public static Supplier<Block> registerBlock(String id, Supplier<Block> supplier, boolean hasItem, BlockGroup group, BlockGroup.ModelMode mode) {
        Supplier<Block> block = RLServices.REGISTRY.registerBlock(DungeonsDelight.MOD_ID, id, supplier, hasItem);
        if (group != null && mode != null) {
            group.addQuick(block, id, mode);
        }
        return block;
    }

    public static void load() {
    }
}