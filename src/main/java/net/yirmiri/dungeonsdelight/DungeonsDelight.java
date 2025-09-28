package net.yirmiri.dungeonsdelight;

import com.mojang.logging.LogUtils;
import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.common.entity.rotten_zombie.RottenZombieEntity;
import net.yirmiri.dungeonsdelight.core.event.DDClientEvents;
import net.yirmiri.dungeonsdelight.common.util.misc.TrialSpawnerFlameParticleAccessor;
import net.yirmiri.dungeonsdelight.core.init.DDDecoratedPotPatterns;
import net.yirmiri.dungeonsdelight.core.registry.*;
import net.yirmiri.dungeonsdelight.integration.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.twilightforest.TFItems;
import org.slf4j.Logger;

@Mod(DungeonsDelight.MOD_ID)
public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonsDelight(IEventBus modEventBus, ModContainer modContainer) {
        //TODO FOR 1.21.1 PORT
        //CLEAN UP MESSY CODE (ESPECIALLY EVENT STUFF)

        //TODO FOR 1.21.1 CONTENT
        //ADD BREEZE FOODS
        //ADD BOGGED SIDE FOODS
        //1.21 ADVANCEMENTS

        //TODO FOR FUTURE CONTENT UPDATE
        //ROTBULBS IN TRIAL CHAMBERS
        //IMPROVE GLUTTONY SHERD GENERATION
        //CUSTOM MONSTER EFFECT PARTICLES
        //POLTERGEIST PIZZA CREEPER SQUIB INSTEAD OF CABBAGE
        //POLTERGEIST PIZZA TOPPING = EFFECT(?), CAN HAVE 3 TOPPINGS
        //OVERHAUL STAINED WEAPONS
        //JADEN'S NETHER EXPANSION INTEGRATION (NEW CONTENT)
        //NETHER'S DELIGHT INTEGRATION (NEW CONTENT)
        //AETHER INTEGRATION (NEW CONTENT)

        modContainer.registerConfig(ModConfig.Type.COMMON, DDConfigCommon.COMMON, "dungeonsdelight-config.toml");
        modContainer.registerConfig(ModConfig.Type.CLIENT, DDConfigClient.CLIENT, "dungeonsdelight-client-config.toml");

        DDParticles.PARTICLE_TYPES.register(modEventBus);
        DDBlocks.BLOCKS.register(modEventBus);
        DDItems.ITEMS.register(modEventBus);
        DDEffects.MOB_EFFECTS.register(modEventBus);
        DDBlockEntities.BE_TYPES.register(modEventBus);
        DDRecipeRegistries.RECIPE_SERIALIZERS.register(modEventBus);
        DDRecipeRegistries.RECIPE_TYPES.register(modEventBus);
        DDMenuTypes.MENU_TYPES.register(modEventBus);
        DDCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        DDEntities.ENTITIES.register(modEventBus);
        DDSounds.SOUNDS.register(modEventBus);
        DDDataComponents.ENCHANTMENT.register(modEventBus);
        DDFeatures.FEATURES.register(modEventBus);
        DDCriteriaTriggers.TRIGGERS.register(modEventBus);

        //INTEGRATION
        ADItems.ITEMS.register(modEventBus);
        TFItems.ITEMS.register(modEventBus);

        //EVENTS
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DDClientEvents::clientSetup);

        modEventBus.addListener(DDBlockEntities::blockEntityAddBlocks);
        modEventBus.addListener(DDClientEvents::registerBlockRenderLayers);
        modEventBus.addListener(this::registerEntityAttributes);
        modEventBus.addListener(DDClientEvents::registerEntityRenderers);
        modEventBus.addListener(DDClientEvents::registerRenderLayers);
        modEventBus.addListener(DDClientEvents::registerLayerDefinitions);
        modEventBus.addListener(DDClientEvents::registerRenderers);
        //modEventBus.addListener(DDClientEvents::registerOverlays);
        //modEventBus.addListener(DDDatagen::gatherData);
        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);
    } // Magmaroni when - Hecco

    public void commonSetup(final FMLCommonSetupEvent event) {
        registerDispenserBehaviors();
        registerFlammables();
        registerCompostables();

        if (DDConfigCommon.TRIAL_SPAWNERS_EMIT_GREEN_FLAMES.get()) {
            setTrialFlameParticleType(TrialSpawner.FlameParticle.NORMAL, DDParticles.LIVING_FLAME.get());
            setTrialFlameParticleType(TrialSpawner.FlameParticle.OMINOUS, DDParticles.SPIRIT_FLAME.get());
        }
    }

    public static void setTrialFlameParticleType(TrialSpawner.FlameParticle particle, SimpleParticleType newParticle) {
        ((TrialSpawnerFlameParticleAccessor) (Object) particle).setParticleType(newParticle);
    }

    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(DDItems.ROTBULB_CROP.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(DDItems.ROTBULB.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(DDItems.GUNK.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(DDItems.ROTBULB_PLANT.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(DDItems.SCULK_TART_SLICE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(DDBlocks.SCULK_TART.get(), 1.0F);
        ComposterBlock.COMPOSTABLES.put(DDItems.MONSTER_CAKE_SLICE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(DDItems.POLTERGHAST_PIZZA_SLICE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(DDItems.MONSTER_CAKE.get(), 1.0F);
        ComposterBlock.COMPOSTABLES.put(DDItems.MONSTER_MUFFIN.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(DDItems.SPIDER_DONUT.get(), 0.85F);
        //INTEGRATION
        ComposterBlock.COMPOSTABLES.put(TFItems.TORCHBERRY_RAISINS.get(), 0.3F);
    }

    public static void registerFlammables() {
        RLRegistryHelper.createFlammable(DDBlocks.WORMROOTS_BLOCK.get(), 5, 5);
        RLRegistryHelper.createFlammable(DDBlocks.WORMWOOD_PLANKS.get(), 5, 20);
        RLRegistryHelper.createFlammable(DDBlocks.WORMWOOD_STAIRS.get(), 5, 20);
        RLRegistryHelper.createFlammable(DDBlocks.WORMWOOD_SLAB.get(), 5, 20);
        RLRegistryHelper.createFlammable(DDBlocks.WORMWOOD_FENCE.get(), 5, 20);
        RLRegistryHelper.createFlammable(DDBlocks.WORMWOOD_FENCE_GATE.get(), 5, 20);
        RLRegistryHelper.createFlammable(DDBlocks.WORMWOOD_MOSAIC.get(), 5, 20);
        RLRegistryHelper.createFlammable(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get(), 5, 20);
        RLRegistryHelper.createFlammable(DDBlocks.WORMWOOD_MOSAIC_SLAB.get(), 5, 20);
        RLRegistryHelper.createFlammable(DDBlocks.WORMROOT_TENDRILS.get(), 15, 100);
        RLRegistryHelper.createFlammable(DDBlocks.ROTBULB_PLANT.get(), 60, 100);
        RLRegistryHelper.createFlammable(DDBlocks.WORMROOT_STALK.get(), 10, 40);
    }

    public static void registerDispenserBehaviors() {
        DispenserBlock.registerProjectileBehavior(DDItems.ANCIENT_EGG.get());
        DispenserBlock.registerProjectileBehavior(DDItems.RANCID_REDUCTION.get());
    }

    public void registerEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(DDEntities.MONSTER_YAM.get(), MonsterYamEntity.createAttributes().build());
        event.put(DDEntities.ROTTEN_ZOMBIE.get(), RottenZombieEntity.createAttributes().build());
    }
}
