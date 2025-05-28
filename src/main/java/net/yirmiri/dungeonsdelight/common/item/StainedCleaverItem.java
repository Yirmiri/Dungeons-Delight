package net.yirmiri.dungeonsdelight.common.item;

//import net.minecraft.ChatFormatting;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.chat.Component;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.stats.Stats;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.entity.ExperienceOrb;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.entity.projectile.AbstractArrow;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Tier;
//import net.minecraft.world.item.TooltipFlag;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraft.world.level.Level;
//import net.yirmiri.dungeonsdelight.common.entity.misc.CleaverEntity;
//import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
//import net.yirmiri.dungeonsdelight.core.registry.DDEnchantments;
//import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
//import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
//
//import java.util.List;
//
//public class StainedCleaverItem extends CleaverItem {
//    public static final String CHARGE_TAG = "Charge";
//    public static final String HEATED_TAG = "Heated";
//    public static final int MAX_CHARGE = 64;
//
//    public StainedCleaverItem(float range, Tier tier, float attackDamage, float attackSpeed, Properties properties) {
//        super(range, tier, attackDamage, attackSpeed, properties);
//    }
//
//    @Override
//    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int timeLeft) {
//        if (!(living instanceof Player player)) return;
//        if (getUseDuration(stack) - timeLeft < 4 || player.getCooldowns().isOnCooldown(this)) return;
//
//        if (!level.isClientSide) {
//            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(living.getUsedItemHand()));
//
//            CleaverEntity cleaver = new CleaverEntity(DDEntities.CLEAVER.get(), level, player, stack.copy());
//            cleaver.setItem(stack.copy());
//
//            applyEnchantments(stack, cleaver);
//            cleaver.setBaseDamage(cleaver.getBaseDamage() + getAttackDamage());
//
////            if (stack.getEnchantmentLevel(DDEnchantments.RETRACTION.get()) > 0) {
////                cleaver.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, range + 0.75F, 1.0F);
////            } else
//            cleaver.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, range, 1.0F);
//
//            if (player.getAbilities().instabuild) {
//                cleaver.pickup = AbstractArrow.Pickup.DISALLOWED;
//            }
//
//            level.addFreshEntity(cleaver);
//            level.playSound(null, cleaver, DDSounds.CLEAVER_THROW.get(), SoundSource.PLAYERS, 2.0F, 1.0F);
//        }
//        player.awardStat(Stats.ITEM_USED.get(this));
//    }
//
//    @Override
//    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
//        stainedEffects(stack, target, attacker);
//        return super.hurtEnemy(stack, target, attacker);
//    }
//
//    public void stainedEffects(ItemStack stack, LivingEntity target, LivingEntity attacker) {
//        if (!attacker.level().isClientSide) {
//            CompoundTag tag = stack.getOrCreateTag();
//            int charge = tag.getInt(CHARGE_TAG);
//            boolean isHeated = tag.getBoolean(HEATED_TAG);
//
//            int lifeGraspLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.LIFE_GRASP.get(), stack);
//
//            if (isHeated) {
//                target.setSecondsOnFire(7);
//                spitExperiencePoint(target, attacker, 3 + lifeGraspLevel);
//                if (attacker instanceof Player player) {
//                    target.hurt(attacker.damageSources().playerAttack(player), 2.0F);
//                }
//
//                charge--;
//                if (charge <= 0) {
//                    charge = 0;
//                    isHeated = false;
//                }
//            } else {
//                charge += 1 + lifeGraspLevel;
//                if (charge >= MAX_CHARGE) {
//                    charge = MAX_CHARGE;
//                    isHeated = true;
//                    attacker.addEffect(new MobEffectInstance(DDEffects.RAVENOUS_RUSH.get(), 160, 0));
//                }
//            }
//
//            tag.putInt(CHARGE_TAG, charge);
//            tag.putBoolean(HEATED_TAG, isHeated);
//            stack.setTag(tag);
//        }
//    }
//
//    public void spitExperiencePoint(LivingEntity target, LivingEntity attacker, int exp) {
//        Level level = attacker.level();
//        ExperienceOrb xpOrb = new ExperienceOrb(level, target.getX(), target.getY() + 0.5, target.getZ(), exp);
//        xpOrb.setDeltaMovement((level.getRandom().nextDouble() - 0.5) * 0.5, (level.getRandom().nextDouble()) * 0.5, (level.getRandom().nextDouble() - 0.5) * 0.5);
//        level.addFreshEntity(xpOrb);
//    }
//
//    @Override
//    public boolean isBarVisible(ItemStack stack) {
//        return super.isBarVisible(stack);
//    }
//
//    @Override
//    public int getBarWidth(ItemStack stack) {
//        CompoundTag tag = stack.getOrCreateTag();
//        if (tag.getBoolean(HEATED_TAG)) {
//            int charge = tag.getInt(CHARGE_TAG);
//            return Math.round(13.0F * charge / MAX_CHARGE);
//        } else return super.getBarWidth(stack);
//    }
//
//    @Override
//    public int getBarColor(ItemStack stack) {
//        CompoundTag tag = stack.getOrCreateTag();
//        if (tag.getBoolean(HEATED_TAG)) {
//            return 0xd00b8a;
//        } else return super.getBarColor(stack);
//    }
//
//    @Override
//    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
//        CompoundTag tag = stack.getOrCreateTag();
//        if (tag.getBoolean(HEATED_TAG)) {
//            tooltip.add(Component.translatable("dungeonsdelight.tooltip.heat_charge").append(" " + stack.getOrCreateTag().getInt(CHARGE_TAG) + " / " + MAX_CHARGE)
//                    .withStyle(formatStyle -> formatStyle.withColor(0xd00b8a)));
//        } else
//            tooltip.add(Component.translatable("dungeonsdelight.tooltip.heat_charge").append(" " + stack.getOrCreateTag().getInt(CHARGE_TAG) + " / " + MAX_CHARGE)
//                    .withStyle(ChatFormatting.BLUE));
//        super.appendHoverText(stack, level, tooltip, flag);
//    }
//}
