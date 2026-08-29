package net.yirmiri.dungeonsdelight.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.enchantment.cleaver.DartingEnchantment;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.*;

import java.util.UUID;

public class CleaverItem extends DiggerItem {
    public final float range;
    public final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> cleaverModifiers;

    public CleaverItem(float range, float attackDamage, float attackSpeed, float chargeMultiplier, Tier tier, Properties properties) {
        super(attackDamage, attackSpeed, tier, DDTags.BlockT.CLEAVER_MINEABLE, properties);
        this.range = range;
        this.attackDamage = attackDamage;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", getAttackDamage(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(DDAttributes.THROWING_RANGE.get(), new AttributeModifier(UUID.fromString("e260333d-b58b-457e-a699-f47dfb449cc4"), "Tool modifier", range, AttributeModifier.Operation.ADDITION));
        builder.put(DDAttributes.CHARGE_MULTIPLIER.get(), new AttributeModifier(UUID.fromString("b7d3c9c4-6b3e-4c75-9a5a-8e0a5b0f7e21"), "Tool modifier", chargeMultiplier, AttributeModifier.Operation.ADDITION));
        this.cleaverModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        super.getDefaultAttributeModifiers(slot);
        return slot == EquipmentSlot.MAINHAND ? this.cleaverModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);

        if (stack.is(DDTags.ItemT.FLAMING_CLEAVERS)) {
            target.setRemainingFireTicks(target.getRemainingFireTicks() + 80);
        }
        return true;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    public static float getPowerForTime(ItemStack stack, LivingEntity living, int charge) {
        float v = charge * getChargeTimeMultiplier(stack, living) / 20.0F;
        v = (v * v + v * 2.0F) / 3.0F;
        if (v > 1.0F) {
            v = 1.0F;
        }
        return v;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onUseTick(Level level, LivingEntity living, ItemStack stack, int timeLeft) {
        if (!(living instanceof Player player)) return;

        int readyTicks = (int) Math.ceil(32.0F / getChargeTimeMultiplier(stack, player));
        int usedTicks = getUseDuration(stack) - timeLeft;

        if (usedTicks == readyTicks) {
            level.playSound(null, player, DDSounds.CLEAVER_READY.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        super.onUseTick(level, living, stack, timeLeft);
    }

    private static float getChargeTimeMultiplier(ItemStack stack, LivingEntity living) {
        if (!(stack.getItem() instanceof CleaverItem)) {
            return 1.0F;
        }

        double multiplier = living.getAttributeValue(DDAttributes.CHARGE_MULTIPLIER.get());

        int dartingLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.DARTING.get(), stack);
        if (dartingLevel > 0) {
            multiplier *= 1.0F + (dartingLevel * DartingEnchantment.dartingChargePercentIncrease());
        }

        return (float) multiplier;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int timeLeft) {
        if (!(living instanceof Player player)) return;

        int usedTicks = getUseDuration(stack) - timeLeft;
        float charge = getPowerForTime(stack, player, usedTicks);
        float minimumCharge = getPowerForTime(stack, player, 8);

        if (charge < minimumCharge || player.getCooldowns().isOnCooldown(this)) return;

        throwCleaver(player, stack, charge, true);

        player.awardStat(Stats.ITEM_USED.get(this));
        player.awardStat(DDStats.CLEAVERS_THROWN.get());
    }

    public static boolean throwCleaver(LivingEntity thrower, ItemStack stack, float charge, boolean damageItem) {
        Level level = thrower.level();

        if (!(stack.getItem() instanceof CleaverItem cleaverItem)) return false;

        float fullyCharged = getPowerForTime(stack, thrower, 32);
        float threeQuarterCharged = getPowerForTime(stack, thrower, 24);

        if (!level.isClientSide) {
            if (damageItem) {
                if (thrower instanceof Player player && !player.isCreative()) {
                    stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(thrower.getUsedItemHand()));
                }
            }

            CleaverEntity cleaver = new CleaverEntity(level, thrower, stack.copy());
            cleaver.setItem(stack.copy());

            cleaverItem.applyEffects(thrower, stack, cleaver);
            cleaver.setBaseDamage(cleaver.getBaseDamage() + cleaverItem.attackDamage + cleaverItem.getTier().getAttackDamageBonus());

            float scale = charge / threeQuarterCharged;
            float velocity = (float) ((thrower.getAttributeValue(DDAttributes.THROWING_RANGE.get()) + cleaverItem.dartingThrowRange(stack)) * scale);
            float maxVelocity = (float) ((thrower.getAttributeValue(DDAttributes.THROWING_RANGE.get()) + cleaverItem.dartingThrowRange(stack)) * (fullyCharged / threeQuarterCharged));

            velocity = Math.min(velocity, maxVelocity);

            if (charge >= fullyCharged) {
                cleaver.setFullyCharged(true);
                cleaver.setLongCooldown(false);
                if (thrower instanceof Player || DungeonsDelight.CONFIG.getNonPlayersFullChargeCleavers()) {
                    cleaver.setBaseDamage(cleaver.getBaseDamage() * 1.5D);
                }
            } else {
                cleaver.setLongCooldown(true);
            }

            cleaver.shootFromRotation(thrower, thrower.getXRot(), thrower.getYRot(), 0.0F, velocity, 1.0F);

            level.addFreshEntity(cleaver);
            cleaver.setOwner(thrower);

            if (stack.is(DDTags.ItemT.USES_DULL_CLEAVER_SOUND)) {
                level.playSound(null, cleaver, DDSounds.CLEAVER_THROW_DULL.get(), SoundSource.HOSTILE, 1.5F,
                        level.random.nextFloat() * 0.1F + 0.9F);
            } else {
                level.playSound(null, cleaver, DDSounds.CLEAVER_THROW.get(), SoundSource.HOSTILE, 1.5F,
                        level.random.nextFloat() * 0.1F + 0.9F);
            }
        }
        return true;
    }

    public float dartingThrowRange(ItemStack stack) {
        int dartingLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.DARTING.get(), stack);

        if (dartingLevel > 0) {
            return (float) dartingLevel / DungeonsDelight.CONFIG.getCleaverDartingRangeDivsor();
        } else return 0;
    }

    public void applyEffects(LivingEntity player, ItemStack stack, CleaverEntity cleaver) {
        int sharpnessLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
        if (sharpnessLevel > 0) {
            cleaver.setBaseDamage(cleaver.getBaseDamage() + sharpnessLevel * 0.5 + 0.5);
        }

        int fireAspectLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, stack);
        if (fireAspectLevel > 0) {
            cleaver.setFireAspectLevel(fireAspectLevel);

            int fireDuration = 100;
            if (cleaver.getFullyCharged()) {
                fireDuration *= 2;
            }
            cleaver.setRemainingFireTicks(fireDuration * fireAspectLevel);
        }

        if (stack.is(DDTags.ItemT.FLAMING_CLEAVERS)) {
            cleaver.setRemainingFireTicks(cleaver.getRemainingFireTicks() + 80);
        }

        int ricochetLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.RICOCHET.get(), stack);
        if (ricochetLevel > 0) {
            cleaver.ricochetsLeft += ricochetLevel;
        }

        int serratedStrikeLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.SERRATED_STRIKE.get(), stack);
        if (serratedStrikeLevel > 0) {
            cleaver.setSerratedLevel(serratedStrikeLevel);
        }

        int dartingLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.DARTING.get(), stack);
        if (dartingLevel > 0) {
            cleaver.setDartingLevel(dartingLevel);
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
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return !player.isCreative();
    }
}