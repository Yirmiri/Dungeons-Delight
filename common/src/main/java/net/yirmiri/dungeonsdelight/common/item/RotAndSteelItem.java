package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.yirmiri.dungeonsdelight.common.block.entity.fire.LivingFireBlock;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

public class RotAndSteelItem extends FlintAndSteelItem {
    public RotAndSteelItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Player player = ctx.getPlayer();
        Level level = ctx.getLevel();
        BlockPos blockpos = ctx.getClickedPos();

        if (ctx.getClickedFace() != Direction.UP) {
            return InteractionResult.FAIL;
        }

        BlockPos blockpos1 = blockpos.relative(ctx.getClickedFace());
        if (BaseFireBlock.canBePlacedAt(level, blockpos1, ctx.getHorizontalDirection())) {
            level.playSound(player, blockpos1, DDSounds.ROT_AND_STEEL_USE.get(), SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            BlockState blockstate1 = LivingFireBlock.getState(level, blockpos1);
            level.setBlock(blockpos1, blockstate1, 11);
            level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
            ItemStack itemstack = ctx.getItemInHand();

            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, blockpos1, itemstack);
                itemstack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(ctx.getHand()));
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        } else {
            return InteractionResult.FAIL;
        }
    }

    @Override
    public int getBarColor(ItemStack stack) {
        float durability = Math.max(0.0F, (float) (stack.getMaxDamage() - stack.getDamageValue()) / stack.getMaxDamage());
        return Mth.hsvToRgb(Mth.lerp(durability, 0.22F, 0.92F), 1.0F, 1.0F);
    }
}