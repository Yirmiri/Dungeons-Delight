package net.yirmiri.dungeonsdelight.item;

import com.google.common.collect.Sets;
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
import net.yirmiri.dungeonsdelight.registry.DDEnchantments;
import net.yirmiri.dungeonsdelight.registry.DDSounds;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.Set;

public class CleaverItem extends KnifeItem {
    private final float range;

    public CleaverItem(float range, Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.range = range;
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
            if (getUseDuration(stack) - timeLeft >= 6 && !player.getCooldowns().isOnCooldown(this)) {
                if (!level.isClientSide) {
                    stack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(living.getUsedItemHand()));

                    CleaverEntity cleaverEntity = new CleaverEntity(level, player, this.getDefaultInstance());
                    cleaverEntity.setItem(this.getDefaultInstance());

                    int sharpnessLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
                    if (sharpnessLevel > 0) {
                        cleaverEntity.setBaseDamage(cleaverEntity.getBaseDamage() + (double) sharpnessLevel * 0.5 + 0.5);
                    }

                    int fireAspectLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, stack);
                    if (fireAspectLevel > 0) {
                        cleaverEntity.setSecondsOnFire(100 * fireAspectLevel);
                    }

                    int ricochetLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.RICOCHET.get(), stack);
                    if (ricochetLevel > 0) {
                        cleaverEntity.ricochetsLeft = cleaverEntity.ricochetsLeft + ricochetLevel;
                    }

                    cleaverEntity.setBaseDamage(cleaverEntity.getBaseDamage() + this.getAttackDamage());

                    cleaverEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, range, 1.0F);

                    if (player.getAbilities().instabuild) {
                        cleaverEntity.pickup = AbstractArrow.Pickup.DISALLOWED;
                    }

                    level.addFreshEntity(cleaverEntity);
                    level.playSound(null, cleaverEntity, DDSounds.CLEAVER_THROW.get(), SoundSource.PLAYERS, 2.0F, 1.0F);
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
