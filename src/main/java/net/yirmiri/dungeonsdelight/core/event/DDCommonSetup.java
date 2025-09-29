package net.yirmiri.dungeonsdelight.core.event;

import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.common.entity.rotten_zombie.RottenZombieEntity;
import net.yirmiri.dungeonsdelight.common.util.misc.SendRecipeBookValuesClientboundPacket;
import net.yirmiri.dungeonsdelight.common.util.misc.TrialSpawnerFlameParticleAccessor;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import net.yirmiri.dungeonsdelight.integration.twilightforest.TFItems;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DDCommonSetup {

    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
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

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        event.registrar("3").playToClient(SendRecipeBookValuesClientboundPacket.TYPE, SendRecipeBookValuesClientboundPacket.STREAM_CODEC, (payload, ctx) -> payload.handle());
    }

    @SubscribeEvent
    public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(DDEntities.MONSTER_YAM.get(), MonsterYamEntity.createAttributes().build());
        event.put(DDEntities.ROTTEN_ZOMBIE.get(), RottenZombieEntity.createAttributes().build());
    }
}