package net.yirmiri.dungeonsdelight.datagen;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.BlockGroup;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class DDModelProvider extends FabricModelProvider {
    private static final List<Block> manualBlockModels = new ArrayList<>();

    public DDModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        //Wormwood
        manualBlockModels.addAll(List.of(
                DDBlocks.WORMWOOD_MOSAIC.get(), DDBlocks.WORMWOOD_MOSAIC_STAIRS.get(), DDBlocks.WORMWOOD_MOSAIC_SLAB.get(),
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

        //Wormouth
        createWormouth(generator);

        //Stained Scrap
        manualBlockModels.addAll(List.of(DDBlocks.CUT_STAINED_SCRAP.get(), DDBlocks.CHISELED_STAINED_SCRAP.get(), DDBlocks.CUT_STAINED_SCRAP_STAIRS.get(), DDBlocks.CUT_STAINED_SCRAP_SLAB.get(),  DDBlocks.STAINED_SCRAP_BARS.get()));
        BlockModelGenerators.BlockFamilyProvider stained = generator.family(DDBlocks.CUT_STAINED_SCRAP.get());
        stained.stairs(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get());
        stained.slab(DDBlocks.CUT_STAINED_SCRAP_SLAB.get());
        ResourceLocation chiseledStainScr = TexturedModel.CUBE_TOP_BOTTOM.create(DDBlocks.CHISELED_STAINED_SCRAP.get(), generator.modelOutput);
        generator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(DDBlocks.CHISELED_STAINED_SCRAP.get(), chiseledStainScr));
        createBarLike(generator, DDBlocks.STAINED_SCRAP_BARS.get());

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
        generator.generateFlatItem(DDItems.SLIME_NOODLES.get(), ModelTemplates.FLAT_ITEM);
    }

    private static void createWormouth(BlockModelGenerators generator) {
        ResourceLocation me = ModelLocationUtils.getModelLocation(DDBlocks.WORMOUTH.get());

        generator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(DDBlocks.WORMOUTH.get())

                        .with(PropertyDispatch.property(BlockStateProperties.FACING)
                                .select(Direction.DOWN, Variant.variant().with(VariantProperties.MODEL, me))
                                .select(Direction.UP, Variant.variant().with(VariantProperties.MODEL, me).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                                .select(Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, me).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
                                .select(Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, me).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                                .select(Direction.WEST, Variant.variant().with(VariantProperties.MODEL, me).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                                .select(Direction.EAST, Variant.variant().with(VariantProperties.MODEL, me).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
                )
        );
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
        generator.createSimpleFlatItemModel(block);

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
