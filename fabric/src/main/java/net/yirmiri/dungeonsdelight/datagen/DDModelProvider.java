package net.yirmiri.dungeonsdelight.datagen;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.yirmiri.dungeonsdelight.common.block.entity.wormouth.WormouthBlock;
import net.yirmiri.dungeonsdelight.common.util.BlockGroup;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class DDModelProvider extends FabricModelProvider {
    private static final List<Block> manualBlockModels = new ArrayList<>();

    public DDModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        //MONSTER POT
        manualBlockModels.add(DDBlocks.MONSTER_POT.get());

        //CLEAVING BOARDS
        createCleavingBoard(generator, DDBlocks.WORMWOOD_CLEAVING_BOARD.get());
        createCleavingBoard(generator, DDBlocks.BAMBOO_CLEAVING_BOARD.get());
        manualBlockModels.addAll(List.of(DDBlocks.BAMBOO_CLEAVING_BOARD.get(), DDBlocks.WORMWOOD_CLEAVING_BOARD.get()));

        //WORMWOOD
        manualBlockModels.addAll(List.of(
                DDBlocks.WORMOUTH.get(), DDBlocks.WORMWOOD_MOSAIC.get(), DDBlocks.WORMWOOD_MOSAIC_STAIRS.get(), DDBlocks.WORMWOOD_MOSAIC_SLAB.get(),
                DDBlocks.WORMWOOD_PLANKS.get(), DDBlocks.WORMWOOD_STAIRS.get(), DDBlocks.WORMWOOD_SLAB.get(),
                DDBlocks.WORMWOOD_FENCE.get(), DDBlocks.WORMWOOD_FENCE_GATE.get(), DDBlocks.WORMWOOD_PRESSURE_PLATE.get(), DDBlocks.WORMWOOD_BUTTON.get()
        ));
        BlockModelGenerators.BlockFamilyProvider wormWood = generator.family(DDBlocks.WORMWOOD_PLANKS.get());
        wormWood.stairs(DDBlocks.WORMWOOD_STAIRS.get());
        wormWood.slab(DDBlocks.WORMWOOD_SLAB.get());
        wormWood.fence(DDBlocks.WORMWOOD_FENCE.get());
        wormWood.fenceGate(DDBlocks.WORMWOOD_FENCE_GATE.get());
        wormWood.pressurePlate(DDBlocks.WORMWOOD_PRESSURE_PLATE.get());
        wormWood.button(DDBlocks.WORMWOOD_BUTTON.get());
        BlockModelGenerators.BlockFamilyProvider wormWoodMos = generator.family(DDBlocks.WORMWOOD_MOSAIC.get());
        wormWoodMos.stairs(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get());
        wormWoodMos.slab(DDBlocks.WORMWOOD_MOSAIC_SLAB.get());

        //WORMOUTH
        createWormouth(generator);

        //STAINED
        manualBlockModels.addAll(List.of(DDBlocks.CUT_STAINED_SCRAP.get(), DDBlocks.CHISELED_STAINED_SCRAP.get(), DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), DDBlocks.CUT_STAINED_SCRAP_SLAB.get(), DDBlocks.STAINED_SCRAP_BARS.get(), DDBlocks.STAINED_SCRAP_GATE.get()));
        BlockModelGenerators.BlockFamilyProvider stained = generator.family(DDBlocks.CUT_STAINED_SCRAP.get());
        stained.stairs(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get());
        stained.slab(DDBlocks.CUT_STAINED_SCRAP_SLAB.get());
        ResourceLocation chiseledStainScr = TexturedModel.CUBE_TOP_BOTTOM.create(DDBlocks.CHISELED_STAINED_SCRAP.get(), generator.modelOutput);
        generator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(DDBlocks.CHISELED_STAINED_SCRAP.get(), chiseledStainScr));

        //COBBLED
        manualBlockModels.addAll(List.of(DDBlocks.COBBLED_BRICKS.get(), DDBlocks.COBBLED_BRICK_STAIRS.get(), DDBlocks.COBBLED_BRICK_SLAB.get(), DDBlocks.COBBLED_BRICK_WALL.get()));
        BlockModelGenerators.BlockFamilyProvider cobbledBricks = generator.family(DDBlocks.COBBLED_BRICKS.get());
        cobbledBricks.stairs(DDBlocks.COBBLED_BRICK_STAIRS.get());
        cobbledBricks.slab(DDBlocks.COBBLED_BRICK_SLAB.get());
        cobbledBricks.wall(DDBlocks.COBBLED_BRICK_WALL.get());

        manualBlockModels.addAll(List.of(DDBlocks.MOSSY_COBBLED_BRICKS.get(), DDBlocks.MOSSY_COBBLED_BRICK_STAIRS.get(), DDBlocks.MOSSY_COBBLED_BRICK_SLAB.get(), DDBlocks.MOSSY_COBBLED_BRICK_WALL.get()));
        BlockModelGenerators.BlockFamilyProvider mossyCobbledBricks = generator.family(DDBlocks.MOSSY_COBBLED_BRICKS.get());
        mossyCobbledBricks.stairs(DDBlocks.MOSSY_COBBLED_BRICK_STAIRS.get());
        mossyCobbledBricks.slab(DDBlocks.MOSSY_COBBLED_BRICK_SLAB.get());
        mossyCobbledBricks.wall(DDBlocks.MOSSY_COBBLED_BRICK_WALL.get());

        manualBlockModels.addAll(List.of(DDBlocks.COBBLED_TILES.get(), DDBlocks.COBBLED_TILE_STAIRS.get(), DDBlocks.COBBLED_TILE_SLAB.get()));
        BlockModelGenerators.BlockFamilyProvider cobbledTiles = generator.family(DDBlocks.COBBLED_TILES.get());
        cobbledTiles.stairs(DDBlocks.COBBLED_TILE_STAIRS.get());
        cobbledTiles.slab(DDBlocks.COBBLED_TILE_SLAB.get());

        manualBlockModels.addAll(List.of(DDBlocks.CRACKED_COBBLED_BRICKS.get()));
        generator.createTrivialCube(DDBlocks.CRACKED_COBBLED_BRICKS.get());

        createCropBlockNoItem(generator, DDBlocks.ENDELVES.get(), BlockStateProperties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);
        createCropBlockNoItem(generator, DDBlocks.MANALLIUMS.get(), BlockStateProperties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);

        generator.createTrivialCube(DDBlocks.ROTTEN_FLESH_BLOCK.get());
        generator.createTrivialCube(DDBlocks.SCULK_MAYONNAISE_BLOCK.get());
        generator.createTrivialCube(DDBlocks.GUNK_BLOCK.get());

        createCropBlockNoItem(generator, DDBlocks.ROTTEN_CROP.get(), BlockStateProperties.AGE_7, 0, 1, 2, 3, 4, 5, 6, 7);
        createCropBlockNoItem(generator, DDBlocks.PUTRESCENT_CARROTS.get(), BlockStateProperties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);
        createCropBlockNoItem(generator, DDBlocks.POISONOUS_POTATOES.get(), BlockStateProperties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);
        createCropBlockNoItem(generator, DDBlocks.BLIGHTED_BEETROOTS.get(), BlockStateProperties.AGE_3, 0, 1, 2, 3);

        autogenerate(generator);
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(DDItems.LOGO_ITEM.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.MUSIC_DISC_MALADY.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.MUSIC_DISC_MALADY_B_SIDE.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.FLINT_CLEAVER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.GOLDEN_CLEAVER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.IRON_CLEAVER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.DIAMOND_CLEAVER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.NETHERITE_CLEAVER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.SPIDER_MEAT.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.COOKED_SPIDER_MEAT.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.STAINED_SCRAP.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.STAINED_SCRAP_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDBlocks.WORMROOT_TENDRILS.get().asItem(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.ROTTEN_TRIPE.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.CREEPERILLA.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.CREEPERILLA_SQUIB.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.SLIME_NOODLES.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.MAGMARONI.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.GHAST_TENTACLE.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.GHAST_CALAMARI.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.COOKED_GHAST_CALAMARI.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.SILVERFISH_ABDOMEN.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.SNIFFER_SHANK.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.COOKED_SNIFFER_SHANK.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.BLEET.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.BLEET_SEEDS.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.ENDELVE.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.MANALLIUM.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.ROTBULB.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.AMETHYST_ROCK_CANDY.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.CANDIED_SILVERFISH_SUCKER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.CANDIED_VEX_SUCKER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.CANDIED_ENDERMITE_SUCKER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.GHOULASH.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.SPIDER_TANGHULU.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.FOUL_SKEWER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.SALMAGUNDI.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.SILVERFISH_FRIED_RICE.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.GUNPOWDER_BAKED_ARACHNID.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.BLACK_APPLE.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.SCULK_APPLE.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.CAMEL_HUSK_SPAWN_EGG.get(),
                new ModelTemplate(Optional.of(RunicLib.customid("minecraft", "item/template_spawn_egg")), Optional.empty()));
        generator.generateFlatItem(DDItems.MONSTER_YAM_SPAWN_EGG.get(),
                new ModelTemplate(Optional.of(RunicLib.customid("minecraft", "item/template_spawn_egg")), Optional.empty()));
        generator.generateFlatItem(DDItems.SPIDER_EXTRACT.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.DYNAMITE_ROLL.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.SPIDER_PIE.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.SLICORICE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generator.generateFlatItem(DDItems.RAVAGER_HAUNCH.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.GHAST_ROLL.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.TELEPOTAGE.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.SCULK_POLYP.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.ANCIENT_EGG.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.CLEAVED_ANCIENT_EGG.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.SCULK_MAYONNAISE.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.EGGNOG.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.TARO_MILK_TEA.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.BUBBLE_EYE_TEA.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.BUBBLEGUNK.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.WILD_ROTBULB.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.ROTBULB_SEEDS.get(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.RANCID_REDUCTION.get(), ModelTemplates.FLAT_ITEM);
    }

    private static void createWormouth(BlockModelGenerators generator) {
        ResourceLocation me = ModelLocationUtils.getModelLocation(DDBlocks.WORMOUTH.get());
        ResourceLocation me_closed = ModelLocationUtils.getModelLocation(DDBlocks.WORMOUTH.get(), "_closed");

        Block block = DDBlocks.WORMOUTH.get();

        generator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block)
                        .with(PropertyDispatch.property(WormouthBlock.EATING)
                                .select(false, Variant.variant().with(VariantProperties.MODEL, me))
                                .select(true, Variant.variant().with(VariantProperties.MODEL, me_closed))
                        )
                        .with(PropertyDispatch.property(BlockStateProperties.FACING)
                                .select(Direction.DOWN, Variant.variant())
                                .select(Direction.UP, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                                .select(Direction.NORTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                                .select(Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                                .select(Direction.WEST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                                .select(Direction.EAST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                )
        );
    }

    private static void createCleavingBoard(BlockModelGenerators generator, Block block) {
        ResourceLocation me = ModelLocationUtils.getModelLocation(block);
        //ResourceLocation me_ceil = ModelLocationUtils.getModelLocation(block, "_ceiling");
        //ResourceLocation me_floor = ModelLocationUtils.getModelLocation(block, "_floor");

        generator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block)
                        .with(PropertyDispatch.property(BlockStateProperties.FACING)
                                .select(Direction.DOWN, Variant.variant().with(VariantProperties.MODEL, me).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                                .select(Direction.UP, Variant.variant().with(VariantProperties.MODEL, me).with(VariantProperties.X_ROT, VariantProperties.Rotation.R270))//.with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                                .select(Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, me))
                                .select(Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, me).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                                .select(Direction.WEST, Variant.variant().with(VariantProperties.MODEL, me).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                                .select(Direction.EAST, Variant.variant().with(VariantProperties.MODEL, me).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        )
        );
    }

    public final void createCropBlockNoItem(BlockModelGenerators generator, Block cropBlock, Property<Integer> ageProperty, int... ageToVisualStageMapping) {
        if (ageProperty.getPossibleValues().size() != ageToVisualStageMapping.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<ResourceLocation> int2ObjectMap = new Int2ObjectOpenHashMap();
            PropertyDispatch propertyDispatch = PropertyDispatch.property(ageProperty).generate((integer) -> {
                int i = ageToVisualStageMapping[integer];
                ResourceLocation resourceLocation = int2ObjectMap.computeIfAbsent(i, (j) ->
                        generator.createSuffixedVariant(cropBlock, "_stage" + i, ModelTemplates.CROP, TextureMapping::crop));
                return Variant.variant().with(VariantProperties.MODEL, resourceLocation);
            });
            generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(cropBlock).with(propertyDispatch));
        }
    }

    private static void createMultifaceNoItem(BlockModelGenerators generator, Block multifaceBlock) {
        ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation(multifaceBlock);
        MultiPartGenerator multiPartGenerator = MultiPartGenerator.multiPart(multifaceBlock);
        Condition.TerminalCondition terminalCondition = Util.make(Condition.condition(), (terminalConditionx) -> {
            BlockModelGenerators.MULTIFACE_GENERATOR.stream().map(Pair::getFirst).forEach((booleanProperty) -> {
                if (multifaceBlock.defaultBlockState().hasProperty(booleanProperty)) {
                    terminalConditionx.term(booleanProperty, false);
                }

            });
        });
        Iterator<Pair<BooleanProperty, Function<ResourceLocation, Variant>>> var5 = BlockModelGenerators.MULTIFACE_GENERATOR.iterator();

        while (var5.hasNext()) {
            Pair<BooleanProperty, Function<ResourceLocation, Variant>> pair = var5.next();
            BooleanProperty booleanProperty = pair.getFirst();
            Function<ResourceLocation, Variant> function = pair.getSecond();
            if (multifaceBlock.defaultBlockState().hasProperty(booleanProperty)) {
                multiPartGenerator.with(Condition.condition().term(booleanProperty, true), function.apply(resourceLocation));
                multiPartGenerator.with(terminalCondition, function.apply(resourceLocation));
            }
        }

        generator.blockStateOutput.accept(multiPartGenerator);
    }

    private void createBarLike(BlockModelGenerators generator, Block block) {
        generator.createSimpleFlatItemModel(block.asItem());

        ResourceLocation postends = ModelLocationUtils.getModelLocation(block, "_post_ends");
        ResourceLocation post = ModelLocationUtils.getModelLocation(block, "_post");
        ResourceLocation cap = ModelLocationUtils.getModelLocation(block, "_cap");
        ResourceLocation capAlt = ModelLocationUtils.getModelLocation(block, "_cap_alt");
        ResourceLocation side = ModelLocationUtils.getModelLocation(block, "_side");
        ResourceLocation sideAlt = ModelLocationUtils.getModelLocation(block, "_side_alt");
        generator.blockStateOutput.accept(
                MultiPartGenerator.multiPart(block).with(
                        Variant.variant().with(VariantProperties.MODEL, postends)).with(Condition.condition()
                                .term(BlockStateProperties.NORTH, false)
                                .term(BlockStateProperties.EAST, false)
                                .term(BlockStateProperties.SOUTH, false)
                                .term(BlockStateProperties.WEST, false),
                        Variant.variant().with(VariantProperties.MODEL, post)).with(Condition.condition()
                                .term(BlockStateProperties.NORTH, true)
                                .term(BlockStateProperties.EAST, false)
                                .term(BlockStateProperties.SOUTH, false)
                                .term(BlockStateProperties.WEST, false),
                        Variant.variant().with(VariantProperties.MODEL, cap)).with(Condition.condition()
                                .term(BlockStateProperties.NORTH, false)
                                .term(BlockStateProperties.EAST, true)
                                .term(BlockStateProperties.SOUTH, false)
                                .term(BlockStateProperties.WEST, false),
                        Variant.variant().with(VariantProperties.MODEL, cap).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition()
                                .term(BlockStateProperties.NORTH, false)
                                .term(BlockStateProperties.EAST, false)
                                .term(BlockStateProperties.SOUTH, true)
                                .term(BlockStateProperties.WEST, false),
                        Variant.variant().with(VariantProperties.MODEL, capAlt)).with(Condition.condition()
                                .term(BlockStateProperties.NORTH, false)
                                .term(BlockStateProperties.EAST, false)
                                .term(BlockStateProperties.SOUTH, false)
                                .term(BlockStateProperties.WEST, true),
                        Variant.variant().with(VariantProperties.MODEL, capAlt).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition()
                                .term(BlockStateProperties.NORTH, true),
                        Variant.variant().with(VariantProperties.MODEL, side)).with(Condition.condition()
                                .term(BlockStateProperties.EAST, true),
                        Variant.variant().with(VariantProperties.MODEL, side).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition()
                                .term(BlockStateProperties.SOUTH, true),
                        Variant.variant().with(VariantProperties.MODEL, sideAlt)).with(Condition.condition()
                                .term(BlockStateProperties.WEST, true),
                        Variant.variant().with(VariantProperties.MODEL, sideAlt).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)));
    }

    private static void autogenerate(BlockModelGenerators generator) {
        for (BlockGroup set : BlockGroup.SETS) {
            Map<Supplier<Block>, BlockGroup.ModelMode> models = set.models();
            for (Supplier<Block> supp : models.keySet()) {
                Block block = supp.get();
                if (!manualBlockModels.contains(block) && models.get(supp) != BlockGroup.ModelMode.MANUAL) {
                    switch (models.get(supp)) {
                        case BLOCK -> generator.createTrivialCube(block);
                        case PILLAR -> generator.createAxisAlignedPillarBlock(block, TexturedModel.COLUMN);
                        case MULTIFACE -> createMultifaceNoItem(generator, block);
                        case DOOR -> generator.createDoor(block);
                        case TRAPDOOR -> generator.createOrientableTrapdoor(block);
                    }
                }
            }
        }
    }
}
