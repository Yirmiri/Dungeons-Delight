package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.registry.*;

public class CleaverItem extends KnifeItem { //i dont wanna extend sworditem but also we have to replace knifeitem for the port
    public final float range;
    public final float attackDamage;

    public CleaverItem(float range, Tier tier, float attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, properties.attributes(CleaverItem.createAttributes(tier, attackDamage, attackSpeed)));
        this.range = range;
        this.attackDamage = attackDamage;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);
        int serratedStrikeLevel = EnchantmentHelper.getItemEnchantmentLevel(DDUtil.getEnchantmentHolder(target, DDEnchantments.SERRATED_STRIKE), stack);

        if (serratedStrikeLevel > 0) {
            int duration = 40 + (serratedStrikeLevel * 20);

            if (target.hasEffect(DDEffects.SERRATED)) {
                duration = duration / 2;
                duration += target.getEffect(DDEffects.SERRATED).getDuration();
            }
            target.addEffect(new MobEffectInstance(DDEffects.SERRATED, duration, 0));
            target.playSound(DDSounds.CLEAVER_SERRATED_STRIKE.get(), 2.0F, target.level().random.nextFloat() * 0.1F + 0.9F);
        }

        if (stack.is(DDTags.ItemT.FLAMING_KNIVES)) {
            target.setRemainingFireTicks(target.getRemainingFireTicks() + 80);
        }
        return true;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    public static float getPowerForTime(int charge) {
        float v = (float) charge / 20.0F;
        v = (v * v + v * 2.0F) / 3.0F;
        if (v > 1.0F) {
            v = 1.0F;
        }
        return v;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public void onUseTick(Level level, LivingEntity living, ItemStack stack, int timeLeft) {
        if (!(living instanceof Player player)) return;

        int usedTicks = getUseDuration(stack, living) - timeLeft;

        if (usedTicks == 32) {
            level.playSound(null, player, DDSounds.CLEAVER_READY.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        super.onUseTick(level, living, stack, timeLeft);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int timeLeft) {
        float fullyCharged = getPowerForTime(32);
        float threeQuarterCharged = getPowerForTime(24);
        float halfCharged = getPowerForTime(16);
        float quarterCharged = getPowerForTime(8);

        if (!(living instanceof Player player)) return;
        if (getUseDuration(stack, living) - timeLeft < 6 || player.getCooldowns().isOnCooldown(this)) return;

        if (!level.isClientSide) {
            if (!player.isCreative()) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
            }

            CleaverEntity cleaver = new CleaverEntity(level, player, stack.copy());
            cleaver.setItem(stack.copy());
            applyEffects(player, stack, cleaver);
            cleaver.setBaseDamage(cleaver.getBaseDamage() + attackDamage + getTier().getAttackDamageBonus());

            float charge = getPowerForTime(getUseDuration(stack, living) - timeLeft);
            float scale = charge / threeQuarterCharged;
            float velocity = range * scale;
            float maxVelocity = range * (fullyCharged / threeQuarterCharged);

            velocity = Math.min(velocity, maxVelocity);

            if (charge >= fullyCharged) {
                cleaver.setFullyCharged(true);
                cleaver.setLongCooldown(false);
                cleaver.setBaseDamage(cleaver.getBaseDamage() * 1.5);
            }
//            if (charge < fullyCharged) {
//                cleaver.setLongCooldown(true);
//            }

            cleaver.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, velocity, 1.0F);

            if (player.getAbilities().instabuild) {
                cleaver.pickup = AbstractArrow.Pickup.DISALLOWED;
            }

            level.addFreshEntity(cleaver);
            cleaver.setOwner(player);
            level.playSound(null, cleaver, DDSounds.CLEAVER_THROW.get(), SoundSource.PLAYERS, 1.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
    }

    public void applyEffects(LivingEntity player, ItemStack stack, CleaverEntity cleaver) {
        int sharpness = EnchantmentHelper.getItemEnchantmentLevel(DDUtil.getEnchantmentHolder(player, Enchantments.SHARPNESS), stack);
        if (sharpness > 0) {
            cleaver.setBaseDamage(cleaver.getBaseDamage() + sharpness * 0.5 + 0.5);
        }

        int fireAspect = EnchantmentHelper.getItemEnchantmentLevel(DDUtil.getEnchantmentHolder(player, Enchantments.FIRE_ASPECT), stack);
        if (fireAspect > 0) {
            cleaver.setRemainingFireTicks(100 * fireAspect);
        }

        if (stack.is(DDTags.ItemT.FLAMING_KNIVES)) {
            cleaver.setRemainingFireTicks(cleaver.getRemainingFireTicks() + 80);
        }

        int ricochet = EnchantmentHelper.getItemEnchantmentLevel(DDUtil.getEnchantmentHolder(player, DDEnchantments.RICOCHET), stack);
        if (ricochet > 0) {
            cleaver.ricochetsLeft += ricochet;
        }

        int serrated = EnchantmentHelper.getItemEnchantmentLevel(DDUtil.getEnchantmentHolder(player, DDEnchantments.SERRATED_STRIKE), stack);
        if (serrated > 0) {
            cleaver.setSerratedLevel(serrated);
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
}
