package net.yirmiri.dungeonsdelight.common.item.food;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.entity.misc.RancidReductionEntity;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

public class RancidReductionItem extends Item implements ProjectileItem {
    public RancidReductionItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            RancidReductionEntity rancidReduction = new RancidReductionEntity(level, player);
            rancidReduction.setItem(itemStack);
            rancidReduction.shootFromRotation(player, player.getXRot(), player.getYRot(), -20.0F, 0.5F, 0.5F);
            level.addFreshEntity(rancidReduction);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        player.getCooldowns().addCooldown(this, 20);

        itemStack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> tooltip, TooltipFlag isAdvanced) {
        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            tooltip.add(TextUtils.getTranslation("tooltip." + this).withStyle(ChatFormatting.BLUE));
        }
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        RancidReductionEntity rancidReduction = new RancidReductionEntity(level, pos.x(), pos.y(), pos.z());
        rancidReduction.setItem(stack);
        return rancidReduction;
    }
}
