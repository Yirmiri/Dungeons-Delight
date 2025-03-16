package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.entity.misc.AncientEggEntity;

public class AncientEggItem extends Item {
    public AncientEggItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack inHand = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            AncientEggEntity ancientEgg = new AncientEggEntity(level, player);
            ancientEgg.setItem(inHand);
            ancientEgg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 1.0F);
            level.addFreshEntity(ancientEgg);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            inHand.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(inHand, level.isClientSide());
    }
}
