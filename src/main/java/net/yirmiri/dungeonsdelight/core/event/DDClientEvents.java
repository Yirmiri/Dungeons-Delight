package net.yirmiri.dungeonsdelight.core.event;

import com.google.common.collect.ImmutableList;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.DungeonStoveBlockEntityRenderer;
import net.yirmiri.dungeonsdelight.common.block.entity.ItemGrateBlockEntityRenderer;
import net.yirmiri.dungeonsdelight.common.block.monster_pot.MonsterPotRecipe;
import net.yirmiri.dungeonsdelight.common.block.monster_pot.MonsterPotRecipeBookTab;
import net.yirmiri.dungeonsdelight.common.block.monster_pot.MonsterPotRecipeCategories;
import net.yirmiri.dungeonsdelight.common.block.monster_pot.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.common.entity.misc.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.misc.GunkArrowRenderer;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityModel;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.zombified_dryad.ZombifiedDryadRenderer;
import net.yirmiri.dungeonsdelight.core.event.overlay.effect.RavenousRushEffectOverlay;
import net.yirmiri.dungeonsdelight.core.event.overlay.effect.VoracityEffectOverlay;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;
import net.yirmiri.dungeonsdelight.core.init.DDModelLayers;
import net.yirmiri.dungeonsdelight.core.registry.*;

import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber(modid = DungeonsDelight.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DDClientEvents {
    @SubscribeEvent
    public static void registerBlockRenderLayers(final FMLClientSetupEvent event) {
        //CUTOUT
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.MONSTER_POT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_POTATOES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_TOMATOES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WALL_LIVING_TORCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_CAMPFIRE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMROOT_TENDRILS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.GUNK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.GUARDIAN_ANGEL_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.CANDLE_MONSTER_CAKE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_GRATE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.SILVERFISH_AND_CHIPS_BLOCK.get(), RenderType.cutout());

        //CUTOUT MIPPED
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTBULB_PLANT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTBULB_CROP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_BARS.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.LIVING_FIRE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTTEN_SPAWNER.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_TRAPDOOR.get(), RenderType.cutoutMipped());

        //TRANSLUCENT
    }

    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        //MISC
        event.registerSpriteSet(DDParticles.LIVING_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(DDParticles.SPIRIT_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(DDParticles.DUNGEON_BUBBLE.get(), BubblePopParticle.Provider::new);
        event.registerSpriteSet(DDParticles.SKULL_HEART_BLAST.get(), SonicBoomParticle.Provider::new);
        event.registerSpriteSet(DDParticles.DECISIVE_CRITICAL.get(), CritParticle.Provider::new);
        event.registerSpriteSet(DDParticles.ROTTEN_GLINT.get(), SuspendedTownParticle.HappyVillagerProvider::new);
        event.registerSpriteSet(DDParticles.MONSTER_SMOKE.get(), CampfireSmokeParticle.CosyProvider::new);
        event.registerSpriteSet(DDParticles.MONSTER_STEAM.get(), CampfireSmokeParticle.CosyProvider::new);
        event.registerSpriteSet(DDParticles.ROT_CLOUD.get(), SuspendedTownParticle.HappyVillagerProvider::new);
        event.registerSpriteSet(DDParticles.SPIRIT.get(), SoulParticle.Provider::new);

        //EFFECT
        event.registerSpriteSet(DDParticles.DECISIVE.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(DDParticles.EXUDATION.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(DDParticles.TENACITY.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(DDParticles.VORACITY.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(DDParticles.BURROW_GUT.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(DDParticles.POUNCING.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(DDParticles.SWIFT_STEP.get(), SpellParticle.Provider::new);
        event.registerSpriteSet(DDParticles.ROTGUT.get(), SpellParticle.Provider::new);
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        Sheets.addWoodType(DDBlockSetTypes.WORMWOOD);
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(DDMenuTypes.MONSTER_POT.get(), MonsterPotScreen::new);
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerBelowAll(RunicLib.customid(DungeonsDelight.MOD_ID, "ravenous_rush_vignette"), new RavenousRushEffectOverlay());
        event.registerBelowAll(RunicLib.customid(DungeonsDelight.MOD_ID, "voracity_effect_overlay"), new VoracityEffectOverlay());
    }

    @SubscribeEvent
    public static void registerRenderLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MonsterYamEntityModel.LAYER_LOC, MonsterYamEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        Supplier<LayerDefinition> main = () -> LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64);
        Supplier<LayerDefinition> outer = () -> LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64);
        Supplier<LayerDefinition> innerArmor = () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION), 64, 32);
        Supplier<LayerDefinition> outerArmor = () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32);
        event.registerLayerDefinition(DDModelLayers.ZOMBIFIED_DRYAD, main);
        event.registerLayerDefinition(DDModelLayers.ZOMBIFIED_DRYAD_OUTER_LAYER, outer);
        event.registerLayerDefinition(DDModelLayers.ZOMBIFIED_DRYAD_INNER_ARMOR, innerArmor);
        event.registerLayerDefinition(DDModelLayers.ZOMBIFIED_DRYAD_OUTER_ARMOR, outerArmor);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(DDBlockEntities.DUNGEON_STOVE.get(), DungeonStoveBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(DDBlockEntities.LIVING_CAMPFIRE.get(), CampfireRenderer::new);
        event.registerBlockEntityRenderer(DDBlockEntities.ITEM_GRATE.get(), ItemGrateBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DDEntities.ANCIENT_EGG.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.MONSTER_YAM.get(), MonsterYamEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.RANCID_REDUCTION.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(DDEntities.GUNK_ARROW.get(), GunkArrowRenderer::new);
        event.registerEntityRenderer(DDEntities.ZOMBIFIED_DRYAD.get(), ZombifiedDryadRenderer::new);
    }

    @SubscribeEvent
    public static void registerRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
        event.registerBookCategories(MonsterPotRecipeCategories.MONSTER_COOKING, ImmutableList.of(
                MonsterPotRecipeCategories.MONSTER_SEARCH,
                MonsterPotRecipeCategories.MONSTER_MEALS,
                MonsterPotRecipeCategories.MONSTER_DRINKS,
                MonsterPotRecipeCategories.MONSTER_MISC
        ));

        event.registerAggregateCategory(MonsterPotRecipeCategories.MONSTER_SEARCH, ImmutableList.of(
                MonsterPotRecipeCategories.MONSTER_MEALS,
                MonsterPotRecipeCategories.MONSTER_DRINKS,
                MonsterPotRecipeCategories.MONSTER_MISC
        ));

        event.registerRecipeCategoryFinder(DDRecipeRegistries.MONSTER_COOKING_RECIPE_TYPE.get(), recipe -> {
            if (recipe.value() instanceof MonsterPotRecipe monsterPotRecipe) {
                MonsterPotRecipeBookTab tab = monsterPotRecipe.getRecipeBookTab();
                return switch (tab) {
                    case MONSTER_MEALS -> MonsterPotRecipeCategories.MONSTER_MEALS;
                    case MONSTER_DRINKS -> MonsterPotRecipeCategories.MONSTER_DRINKS;
                    case MONSTER_MISC -> MonsterPotRecipeCategories.MONSTER_MISC;
                };
            }
            return null;
        });
    }

    public static final EnumProxy<RecipeBookCategories> PROXY_MONSTER_SEARCH = new EnumProxy<>(
            RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(Items.COMPASS))
    );

    public static final EnumProxy<RecipeBookCategories> PROXY_MONSTER_MEALS = new EnumProxy<>(
            RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(DDItems.GHOULASH.get()))
    );

    public static final EnumProxy<RecipeBookCategories> PROXY_MONSTER_DRINKS = new EnumProxy<>(
            RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(DDItems.TARO_MILK_TEA.get()))
    );

    public static final EnumProxy<RecipeBookCategories> PROXY_MONSTER_MISC = new EnumProxy<>(
            RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(DDItems.MONSTER_MUFFIN.get()),
            new ItemStack(DDItems.WARDENZOLA_CRUMBLES.get()))
    );
}
