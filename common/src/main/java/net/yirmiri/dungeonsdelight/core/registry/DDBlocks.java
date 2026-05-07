package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.common.publicized.*;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.MorbidMushBlock;
import net.yirmiri.dungeonsdelight.common.block.WormouthBlock;
import net.yirmiri.dungeonsdelight.common.block.WormrootTendrilsBlock;
import net.yirmiri.dungeonsdelight.common.block.WormrootsStalkBlock;
import net.yirmiri.dungeonsdelight.common.util.BlockGroup;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;

import java.util.function.Supplier;

public class DDBlocks {

    //WORMWOOD
    public static final Supplier<Block> WORMROOT_TENDRILS = registerBlock("wormroot_tendrils", () -> new WormrootTendrilsBlock(DDProperties.BlockP.WORMROOT_TENDRILS), true, BlockGroup.WORMWOOD, BlockGroup.ModelMode.MULTIFACE);
    public static final Supplier<Block> WORMROOT_STALK = registerBlock("wormroot_stalk", () -> new WormrootsStalkBlock(DDProperties.BlockP.WORMWOOD), true, BlockGroup.WORMWOOD, BlockGroup.ModelMode.MANUAL);
    public static final Supplier<Block> WORMOUTH = registerBlock("wormouth", () -> new WormouthBlock(DDProperties.BlockP.WORMOUTH), true, BlockGroup.WORMWOOD, BlockGroup.ModelMode.MANUAL);
    public static final Supplier<Block> WORMROOTS_BLOCK = doBasicBlock("wormroots_block", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_PLANKS = doBasicBlock("wormwood_planks", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_STAIRS = doStairs("wormwood_stairs", WORMWOOD_PLANKS, DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_SLAB = doSlab("wormwood_slab", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_MOSAIC = doBasicBlock("wormwood_mosaic", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_MOSAIC_STAIRS = doStairs("wormwood_mosaic_stairs", WORMWOOD_MOSAIC, DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_MOSAIC_SLAB = doSlab("wormwood_mosaic_slab", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_FENCE = doFence("wormwood_fence", DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_FENCE_GATE = doFenceGate("wormwood_fence_gate", DDBlockSetTypes.WORMWOOD, DDProperties.BlockP.WORMWOOD, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_DOOR = doDoor("wormwood_door",DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_DOOR, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_TRAPDOOR = doTrapdoor("wormwood_trapdoor", DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_TRAPDOOR, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_BUTTON = doButton("wormwood_button", DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_BUTTON, 20, true, true, BlockGroup.WORMWOOD);
    public static final Supplier<Block> WORMWOOD_PRESSURE_PLATE = doPlate("wormwood_pressure_plate", DDBlockSetTypes.WORMWOOD_BLOCKSET, DDProperties.BlockP.WORMWOOD_PRESSURE_PLATE, PressurePlateBlock.Sensitivity.EVERYTHING, true, BlockGroup.WORMWOOD);

    //MISC
    public static final Supplier<Block> MORBID_MUSH = registerBlock("morbid_mush", () -> new MorbidMushBlock(DDProperties.BlockP.MORBID_MUSH), true, null, BlockGroup.ModelMode.MANUAL);

    ///////////////////////////////////////////

    private static Supplier<Block> doBasicBlock(String id, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new Block(properties), hasItem, group, BlockGroup.ModelMode.BLOCK); }

    private static Supplier<Block> doStairs(String id, Supplier<Block> block, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new PublicStairBlock(block.get().defaultBlockState(), properties), hasItem, group, BlockGroup.ModelMode.STAIRS); }
    private static Supplier<Block> doSlab(String id, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new SlabBlock(properties), hasItem, group, BlockGroup.ModelMode.SLAB); }
    private static Supplier<Block> doWall(String id, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new WallBlock(properties), hasItem, group, BlockGroup.ModelMode.WALL); }
    private static Supplier<Block> doFence(String id, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new FenceBlock(properties), hasItem, group, BlockGroup.ModelMode.FENCE); }
    private static Supplier<Block> doFenceGate(String id, WoodType type, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new FenceGateBlock(properties, type), hasItem, group, BlockGroup.ModelMode.FENCE_GATE); }
    private static Supplier<Block> doDoor(String id, BlockSetType type, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new PublicDoorBlock(properties, type), hasItem, group, BlockGroup.ModelMode.DOOR); }
    private static Supplier<Block> doTrapdoor(String id, BlockSetType type, BlockBehaviour.Properties properties, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new PublicTrapdoorBlock(properties, type), hasItem, group, BlockGroup.ModelMode.TRAPDOOR); }
    private static Supplier<Block> doButton(String id, BlockSetType type, BlockBehaviour.Properties properties, int pressTicks, boolean arrowHolds, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new PublicButtonBlock(properties, type, pressTicks, arrowHolds), hasItem, group, BlockGroup.ModelMode.BUTTON); }
    private static Supplier<Block> doPlate(String id, BlockSetType type, BlockBehaviour.Properties properties, PressurePlateBlock.Sensitivity sens, boolean hasItem, BlockGroup group) { return registerBlock(id, () -> new PublicPressurePlateBlock(sens, properties, type), hasItem, group, BlockGroup.ModelMode.PLATE); }

    ///////////////////////////////////////////

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