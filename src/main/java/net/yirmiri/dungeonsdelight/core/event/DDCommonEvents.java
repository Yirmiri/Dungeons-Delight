package net.yirmiri.dungeonsdelight.core.event;

import com.google.common.collect.ImmutableMap;
import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.core.Position;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.common.entity.misc.AncientEggEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.RancidReductionEntity;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.integration.twilightforest.TFItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

@EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class DDCommonEvents {

    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        registerDispenserBehaviors();
        registerFlammables();
        registerCompostables();
    }

//    @SubscribeEvent
//    public static void missingMappingsEvent(MissingMappingsEvent event) {
//        Map<ResourceLocation, Supplier<Item>> itemsMap = new ImmutableMap.Builder<ResourceLocation, Supplier<Item>>()
//                .put(new ResourceLocation(DungeonsDelight.MOD_ID, "spider_eye_salmagundi"), DDItems.SPIDER_SALMAGUNDI)
//                .put(new ResourceLocation(DungeonsDelight.MOD_ID, "silverfish_abdomen"), DDItems.SILVERFISH_ABDOMEN)
//                .put(new ResourceLocation(DungeonsDelight.MOD_ID, "ossobuco"), DDItems.OSSOBUCO)
//                .build();
//
//        Map<ResourceLocation, Supplier<Block>> blocksMap = new ImmutableMap.Builder<ResourceLocation, Supplier<Block>>()
//                .put(new ResourceLocation(DungeonsDelight.MOD_ID, "ossobuco_block"), DDBlocks.OSSOBUCO_BLOCK)
//                .build();
//
//        for (MissingMappingsEvent.Mapping<Item> itemMapping : event.getMappings(ForgeRegistries.Keys.ITEMS, DungeonsDelight.MOD_ID)) {
//            if (itemsMap.get(itemMapping.getKey()) != null) {
//                itemMapping.remap(Objects.requireNonNull(itemsMap.get(itemMapping.getKey())).get());
//            }
//        }
//
//        for (MissingMappingsEvent.Mapping<Block> blockMapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, DungeonsDelight.MOD_ID)) {
//            if (blocksMap.get(blockMapping.getKey()) != null) {
//                blockMapping.remap(Objects.requireNonNull(blocksMap.get(blockMapping.getKey())).get());
//            }
//        }
//    }

    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(DDBlocks.ROTBULB_CROP.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(DDItems.ROTBULB.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(DDItems.GUNK.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(DDBlocks.ROTBULB_PLANT.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(DDItems.SCULK_TART_SLICE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(DDBlocks.SCULK_TART.get(), 1.0F);
        ComposterBlock.COMPOSTABLES.put(DDItems.MONSTER_CAKE_SLICE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(DDBlocks.MONSTER_CAKE.get(), 1.0F);
        ComposterBlock.COMPOSTABLES.put(DDItems.MONSTER_MUFFIN.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(DDBlocks.SPIDER_DONUT.get(), 0.85F);
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
        RLRegistryHelper.createFlammable(DDBlocks.WORMROOTS.get(), 15, 100);
        RLRegistryHelper.createFlammable(DDBlocks.ROTBULB_PLANT.get(), 60, 100);
    }

    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(DDEntities.MONSTER_YAM.get(), MonsterYamEntity.createAttributes().build());
    }

    public static void registerDispenserBehaviors() {
//        DispenserBlock.registerBehavior(DDItems.ANCIENT_EGG.get(), new AbstractProjectileDispenseBehavior() {
//            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
//                return new AncientEggEntity(level, position.x(), position.y(), position.z());
//            }
//        });
//
//        DispenserBlock.registerBehavior(DDItems.RANCID_REDUCTION.get(), new AbstractProjectileDispenseBehavior() {
//            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
//                return new RancidReductionEntity(level, position.x(), position.y(), position.z());
//            }
//        });

//        DispenserBlock.registerBehavior(DDItems.FLINT_CLEAVER.get(), new AbstractCleaverDispenserBehaviour() {
//            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
//                return new CleaverEntity(DDEntities.CLEAVER.get(), level, position.x(), position.y(), position.z());
//            }
//        });
//
//        DispenserBlock.registerBehavior(DDItems.IRON_CLEAVER.get(), new AbstractCleaverDispenserBehaviour() {
//            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
//                return new CleaverEntity(DDEntities.CLEAVER.get(), level, position.x(), position.y(), position.z());
//            }
//        });
//
//        DispenserBlock.registerBehavior(DDItems.GOLDEN_CLEAVER.get(), new AbstractCleaverDispenserBehaviour() {
//            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
//                return new CleaverEntity(DDEntities.CLEAVER.get(), level, position.x(), position.y(), position.z());
//            }
//        });
//
//        DispenserBlock.registerBehavior(DDItems.DIAMOND_CLEAVER.get(), new AbstractCleaverDispenserBehaviour() {
//            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
//                return new CleaverEntity(DDEntities.CLEAVER.get(), level, position.x(), position.y(), position.z());
//            }
//        });
//
//        DispenserBlock.registerBehavior(DDItems.NETHERITE_CLEAVER.get(), new AbstractCleaverDispenserBehaviour() {
//            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
//                return new CleaverEntity(DDEntities.CLEAVER.get(), level, position.x(), position.y(), position.z());
//            }
//        });
//
//        DispenserBlock.registerBehavior(DDItems.STAINED_CLEAVER.get(), new AbstractCleaverDispenserBehaviour() {
//            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
//                return new CleaverEntity(DDEntities.CLEAVER.get(), level, position.x(), position.y(), position.z());
//            }
//        });
    }

    @SubscribeEvent
    public static void handleAdditionalFoodEffects(LivingEntityUseItemEvent.Finish event) {
        if (DDConfigCommon.FD_STICK_FOODS_GRANT_STRENGTH.get() && event.getItem().getItem().equals(ModItems.BARBECUE_STICK.get())) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0));
        }

        if (DDConfigCommon.FD_STICK_FOODS_GRANT_STRENGTH.get() && event.getItem().getItem().equals(ModItems.MELON_POPSICLE.get())) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0));
        }

        if (DDConfigCommon.FD_GLOWING_FOODS_GRANT_PERCEPTION.get() && event.getItem().getItem().equals(ModItems.GLOW_BERRY_CUSTARD.get())) {
            event.getEntity().addEffect(new MobEffectInstance(RLMobEffects.PERCEPTION, 1200, 0));
        }
    }

//    @SubscribeEvent
//    public static void feralBiteAttack(LivingHurtEvent event) {
//        Random random = new Random();
//        LivingEntity target = event.getEntity();
//
//        if (!target.level().isClientSide() && event.getSource().getDirectEntity() instanceof LivingEntity living && living.hasEffect(DDEffects.FERAL_BITE.get())) {
//            if (random.nextInt(4) == 0) {
//                target.addEffect(new MobEffectInstance(DDEffects.SERRATED.get(), 160, 0));
//                //TODO: feral bite sound
//            }
//        }
//    }
}