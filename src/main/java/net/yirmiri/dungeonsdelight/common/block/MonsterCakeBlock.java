package net.yirmiri.dungeonsdelight.common.block;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;

import java.util.Iterator;

public class MonsterCakeBlock extends CakeBlock {
    public MonsterCakeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack heldStack = player.getItemInHand(hand);
        Item stat = heldStack.getItem();
        if (heldStack.is(DDItems.LIVING_CANDLE.get()) && state.getValue(BITES) == 0) {
            if (!player.isCreative()) {
                heldStack.shrink(1);
            }
            level.playSound(null, pos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlockAndUpdate(pos, DDBlocks.CANDLE_MONSTER_CAKE.get().defaultBlockState());
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            player.awardStat(Stats.ITEM_USED.get(stat));
            return InteractionResult.SUCCESS;
        }
        if (level.isClientSide) {
            if (heldStack.is(ModTags.KNIVES)) {
                return this.cutSlice(level, pos, state, player);
            }

            if (eat(level, pos, state, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (heldStack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }
        return heldStack.is(ModTags.KNIVES) ? this.cutSlice(level, pos, state, player) : eat(level, pos, state, player);
    }

    protected static InteractionResult eat(LevelAccessor accessor, BlockPos pos, BlockState state, Player player) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            player.awardStat(Stats.EAT_CAKE_SLICE);
            player.getFoodData().eat(3, 0.5F);

            if (!player.level().isClientSide) {
                player.giveExperiencePoints(5 + player.level().random.nextInt((int) (5 * 1.33)));
                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
            }

            if (DDItems.MONSTER_CAKE_SLICE.get().isEdible()) {
                Iterator foodEffects = DDItems.MONSTER_CAKE_SLICE.get().getFoodProperties().getEffects().iterator();

                while(foodEffects.hasNext()) {
                    Pair<MobEffectInstance, Float> pair = (Pair)foodEffects.next();
                    if (!player.level().isClientSide && pair.getFirst() != null && player.level().random.nextFloat() < pair.getSecond()) {
                        player.addEffect(new MobEffectInstance(pair.getFirst()));
                    }
                }
            }

            int bites = state.getValue(BITES);
            accessor.gameEvent(player, GameEvent.EAT, pos);
            if (bites < 6) {
                accessor.setBlock(pos, state.setValue(BITES, bites + 1), 3);
            } else {
                accessor.removeBlock(pos, false);
                accessor.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }
            return InteractionResult.SUCCESS;
        }
    }

    protected InteractionResult cutSlice(Level level, BlockPos pos, BlockState state, Player player) {
        int bites = state.getValue(BITES);
        if (bites < 7 - 1) {
            level.setBlock(pos, state.setValue(BITES, bites + 1), 3);
        } else {
            level.removeBlock(pos, false);
        }

        Direction direction = player.getDirection().getOpposite();
        ItemUtils.spawnItemEntity(level, DDItems.MONSTER_CAKE_SLICE.get().getDefaultInstance(), (double)pos.getX() + 0.5, (double)pos.getY() + 0.3, (double)pos.getZ() + 0.5, (double)direction.getStepX() * 0.15, 0.05, (double)direction.getStepZ() * 0.15);
        level.playSound(null, pos, SoundEvents.WOOL_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
        return InteractionResult.SUCCESS;
    }
}
