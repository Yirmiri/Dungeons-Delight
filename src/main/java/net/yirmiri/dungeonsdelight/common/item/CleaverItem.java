package net.yirmiri.dungeonsdelight.common.item;

import com.google.common.collect.Sets;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.entity.misc.CleaverEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDEnchantments;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.Set;

public class CleaverItem extends KnifeItem {
    public final float range;

    public CleaverItem(float range, Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.range = range;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);
        int serratedStrikeLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.SERRATED_STRIKE.get(), stack);

        if (serratedStrikeLevel > 0) {
            int duration = 40 + serratedStrikeLevel;

            if (target.hasEffect(DDEffects.SERRATED.get())) {
                duration += target.getEffect(DDEffects.SERRATED.get()).getDuration();
            }
            target.addEffect(new MobEffectInstance(DDEffects.SERRATED.get(), duration, 0));
            target.playSound(DDSounds.CLEAVER_SERRATED_STRIKE.get(), 2.0F, 1.0F);
        }
        return true;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        //return UseAnim.SPEAR;
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int timeLeft) {
        if (!(living instanceof Player player)) return;
        if (getUseDuration(stack) - timeLeft < 6 || player.getCooldowns().isOnCooldown(this)) return;

        if (!level.isClientSide) {
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(living.getUsedItemHand()));

            CleaverEntity cleaver = new CleaverEntity(level, player, this.getDefaultInstance());
            cleaver.setItem(this.getDefaultInstance());

            applyEnchantments(stack, cleaver);
            cleaver.setBaseDamage(cleaver.getBaseDamage() + getAttackDamage());

            if (stack.getEnchantmentLevel(DDEnchantments.RETRACTION.get()) > 0) {
                cleaver.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, range + 0.75F, 1.0F);
            } else cleaver.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, range, 1.0F);

            if (player.getAbilities().instabuild) {
                cleaver.pickup = AbstractArrow.Pickup.DISALLOWED;
            }

            level.addFreshEntity(cleaver);
            level.playSound(null, cleaver, DDSounds.CLEAVER_THROW.get(), SoundSource.PLAYERS, 2.0F, 1.0F);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
    }

    private void applyEnchantments(ItemStack stack, CleaverEntity cleaver) {
        int sharpness = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
        if (sharpness > 0) {
            cleaver.setBaseDamage(cleaver.getBaseDamage() + sharpness * 0.5 + 0.5);
        }

        int fireAspect = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, stack);
        if (fireAspect > 0) {
            cleaver.setSecondsOnFire(100 * fireAspect);
        }

        int ricochet = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.RICOCHET.get(), stack);
        if (ricochet > 0) {
            cleaver.ricochetsLeft += ricochet;
        }

        int serrated = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.SERRATED_STRIKE.get(), stack);
        if (serrated > 0) {
            cleaver.setSerratedLevel(serrated);
        }

        int persistence = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.PERSISTENCE.get(), stack);
        if (persistence > 0) {
            cleaver.pickup = AbstractArrow.Pickup.ALLOWED;
            cleaver.setPersistenceLevel(persistence);
            cleaver.despawnTime = 200 + (persistence * 40);
        }

        int retraction = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.RETRACTION.get(), stack);
        if (retraction > 0) {
            cleaver.setRetractionLevel(retraction);
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
        Set<Enchantment> ALLOWED_ENCHANTMENTS = Sets.newHashSet(Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.MOB_LOOTING);
        if (ALLOWED_ENCHANTMENTS.contains(enchantment)) {
            return true;
        } else {
            Set<Enchantment> DENIED_ENCHANTMENTS = Sets.newHashSet(Enchantments.BLOCK_FORTUNE, Enchantments.FIRE_ASPECT, Enchantments.KNOCKBACK);
            return !DENIED_ENCHANTMENTS.contains(enchantment) && enchantment.category.canEnchant(stack.getItem());
        }
    }
}
