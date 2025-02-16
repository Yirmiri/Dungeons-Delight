package net.yirmiri.dungeonsdelight.item;

import com.google.common.collect.Sets;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.entity.CleaverEntity;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.Set;

public class CleaverItem extends KnifeItem {
    public CleaverItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
        //return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int timeLeft) {
        if (living instanceof Player player) {
            if (getUseDuration(stack) - timeLeft >= 8 && player.getCooldowns().isOnCooldown(this)) {
                if (!level.isClientSide) {
                    stack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(living.getUsedItemHand()));

                    CleaverEntity cleaverEntity = new CleaverEntity(level, player, this.getDefaultInstance());

                    int sharpness = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
                    if (sharpness > 0) {
                        cleaverEntity.setBaseDamage(cleaverEntity.getBaseDamage() + (double) sharpness * 0.5 + 0.5);
                    }

                    int fireAspect = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, stack);
                    if (fireAspect > 0) {
                        cleaverEntity.setSecondsOnFire(100 * fireAspect);
                    }

                    cleaverEntity.setBaseDamage(cleaverEntity.getBaseDamage() + this.getAttackDamage());

                    cleaverEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 1.0F);

                    if (player.getAbilities().instabuild) {
                        cleaverEntity.pickup = AbstractArrow.Pickup.DISALLOWED;
                    }

                    level.addFreshEntity(cleaverEntity);
                    level.playSound(null, cleaverEntity, SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getDamageValue() >= stack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(stack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        Set<Enchantment> ALLOWED_ENCHANTMENTS = Sets.newHashSet(Enchantments.LOYALTY, Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.KNOCKBACK, Enchantments.FIRE_ASPECT, Enchantments.MOB_LOOTING);
        if (ALLOWED_ENCHANTMENTS.contains(enchantment)) {
            return true;
        } else {
            Set<Enchantment> DENIED_ENCHANTMENTS = Sets.newHashSet(Enchantments.BLOCK_FORTUNE);
            return !DENIED_ENCHANTMENTS.contains(enchantment) && enchantment.category.canEnchant(stack.getItem());
        }
    }
}
