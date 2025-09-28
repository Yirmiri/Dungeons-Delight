package net.yirmiri.dungeonsdelight.core.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.MonsterCakeBlock;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;

@EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class DDCommonEvents {

    @SubscribeEvent
    public static void onMonsterCakeInteraction(PlayerInteractEvent.RightClickBlock event) {
        ItemStack toolStack = event.getEntity().getItemInHand(event.getHand());
        if (toolStack.is(ModTags.KNIVES)) {
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            BlockState state = event.getLevel().getBlockState(pos);
            Block block = state.getBlock();
            if (state.is(DDBlocks.CANDLE_MONSTER_CAKE.get())) {
                level.setBlock(pos, DDBlocks.MONSTER_CAKE.get().defaultBlockState().setValue(MonsterCakeBlock.BITES, 1), 3);
                Block.dropResources(state, level, pos);
                ItemUtils.spawnItemEntity(level, new ItemStack(DDItems.MONSTER_CAKE_SLICE.get()), pos.getX(), (double)pos.getY() + 0.2, (double)pos.getZ() + 0.5, -0.05, 0.0, 0.0);
                level.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }

            if (block == DDBlocks.MONSTER_CAKE.get()) {
                int bites = state.getValue(MonsterCakeBlock.BITES);
                if (bites < 6) {
                    level.setBlock(pos, state.setValue(MonsterCakeBlock.BITES, bites + 1), 3);
                } else {
                    level.removeBlock(pos, false);
                }

                ItemUtils.spawnItemEntity(level, new ItemStack(DDItems.MONSTER_CAKE_SLICE.get()), (double)pos.getX() + (double)bites * 0.1, (double)pos.getY() + 0.2, (double)pos.getZ() + 0.5, -0.05, 0.0, 0.0);
                level.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }

//    @SubscribeEvent
//    public static void onMonsterCakeInteraction(PlayerInteractEvent.RightClickBlock event) {
//        ItemStack toolStack = event.getEntity().getItemInHand(event.getHand());
//
//        if (!toolStack.is(ModTags.KNIVES)) {
//            return;
//        }
//
//        Level level = event.getLevel();
//        BlockPos pos = event.getPos();
//        BlockState state = event.getLevel().getBlockState(pos);
//        Block block = state.getBlock();
//
//        if (state.is(ModTags.DROPS_CAKE_SLICE)) {
//            level.setBlock(pos, DDBlocks.MONSTER_CAKE.get().defaultBlockState().setValue(MonsterCakeBlock.BITES, 1), 3);
//            Block.dropResources(state, level, pos);
//            ItemUtils.spawnItemEntity(level, new ItemStack(DDItems.MONSTER_CAKE_SLICE.get()),
//                    pos.getX(), pos.getY() + 0.2, pos.getZ() + 0.5,
//                    -0.05, 0, 0);
//            level.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
//
//            event.setCancellationResult(InteractionResult.SUCCESS);
//            event.setCanceled(true);
//        }
//
//        if (block == DDBlocks.MONSTER_CAKE.get()) {
//            int bites = state.getValue(MonsterCakeBlock.BITES);
//            if (bites < 6) {
//                level.setBlock(pos, state.setValue(MonsterCakeBlock.BITES, bites + 1), 3);
//            } else {
//                level.removeBlock(pos, false);
//            }
//            ItemUtils.spawnItemEntity(level, new ItemStack(DDItems.MONSTER_CAKE_SLICE.get()),
//                    pos.getX() + (bites * 0.1), pos.getY() + 0.2, pos.getZ() + 0.5,
//                    -0.05, 0, 0);
//            level.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
//
//            event.setCancellationResult(InteractionResult.SUCCESS);
//            event.setCanceled(true);
//        }
//    }

//    @SubscribeEvent
//    public static void handleAdditionalFoodEffects(LivingEntityUseItemEvent.Finish event) {
//        if (DDConfigCommon.FD_STICK_FOODS_GRANT_STRENGTH.get() && event.getItem().getItem().equals(ModItems.BARBECUE_STICK.get())) {
//            event.getEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0));
//        }
//
//        if (DDConfigCommon.FD_STICK_FOODS_GRANT_STRENGTH.get() && event.getItem().getItem().equals(ModItems.MELON_POPSICLE.get())) {
//            event.getEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0));
//        }
//
//        if (DDConfigCommon.FD_GLOWING_FOODS_GRANT_PERCEPTION.get() && event.getItem().getItem().equals(ModItems.GLOW_BERRY_CUSTARD.get())) {
//            event.getEntity().addEffect(new MobEffectInstance(RLMobEffects.PERCEPTION, 1200, 0));
//        }
//    }

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