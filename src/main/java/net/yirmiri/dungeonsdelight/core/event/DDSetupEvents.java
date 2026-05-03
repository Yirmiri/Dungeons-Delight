package net.yirmiri.dungeonsdelight.core.event;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.LivingFireBlock;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.common.entity.zombified_dryad.ZombifiedDryadEntity;
import net.yirmiri.dungeonsdelight.common.util.misc.RottenHeartManager;
import net.yirmiri.dungeonsdelight.common.util.misc.S2CRottenHeartsPacket;
import net.yirmiri.dungeonsdelight.common.util.misc.TrialSpawnerFlameParticleAccessor;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import net.yirmiri.dungeonsdelight.integration.content.twilightforest.TFItems;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DDSetupEvents {

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

    @SubscribeEvent
    public static void blockEntityAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(ModBlockEntityTypes.CABINET.get(), DDBlocks.WORMWOOD_CABINET.get());
    }

//    @SubscribeEvent
//    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
//        event.registrar(DungeonsDelight.MOD_ID)
//                .playToClient(S2CRottenHeartsPacket.TYPE, S2CRottenHeartsPacket.STREAM_CODEC, S2CRottenHeartsPacket::handle);
//    }

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
        ComposterBlock.COMPOSTABLES.put(DDItems.ROTGOURD_SLICE.get(), 0.5F);
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
        DispenserBlock.registerProjectileBehavior(DDItems.GUNK_ARROW.get());

        DispenserBlock.registerBehavior(DDItems.ROT_AND_STEEL.get(), new OptionalDispenseItemBehavior() {
            @Override
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                ServerLevel serverlevel = source.level();
                setSuccess(true);
                Direction direction = source.state().getValue(DispenserBlock.FACING);
                BlockPos blockpos = source.pos().relative(direction);
                BlockState blockstate = serverlevel.getBlockState(blockpos);
                if (BaseFireBlock.canBePlacedAt(serverlevel, blockpos, direction)) {
                    serverlevel.setBlockAndUpdate(blockpos, LivingFireBlock.getState(serverlevel, blockpos));
                    serverlevel.gameEvent(null, GameEvent.BLOCK_PLACE, blockpos);
                } else if (blockstate.getToolModifiedState(new UseOnContext(source.level(), null, InteractionHand.MAIN_HAND, stack,
                        new BlockHitResult(blockpos.getCenter(), direction.getOpposite(), blockpos, false)), ItemAbilities.FIRESTARTER_LIGHT,
                        false) instanceof BlockState blockstate2) {
                    serverlevel.setBlockAndUpdate(blockpos, blockstate2);
                    serverlevel.gameEvent(null, GameEvent.BLOCK_CHANGE, blockpos);
                } else if (blockstate.isFlammable(serverlevel, blockpos, source.state().getValue(DispenserBlock.FACING).getOpposite())) {
                    blockstate.onCaughtFire(serverlevel, blockpos, source.state().getValue(DispenserBlock.FACING).getOpposite(), null);
                    if (blockstate.getBlock() instanceof TntBlock)
                        serverlevel.removeBlock(blockpos, false);
                } else {
                    this.setSuccess(false);
                }

                if (this.isSuccess()) {
                    stack.hurtAndBreak(1, serverlevel, null, item -> {
                    });
                }

                return stack;
            }
        });
    }

    @SubscribeEvent
    public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(DDEntities.MONSTER_YAM.get(), MonsterYamEntity.createAttributes().build());
        event.put(DDEntities.ZOMBIFIED_DRYAD.get(), ZombifiedDryadEntity.createAttributes().build());
    }
}