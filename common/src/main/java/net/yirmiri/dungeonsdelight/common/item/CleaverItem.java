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
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.common.enchantment.DartingEnchantment;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDAttributes;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDEnchantments;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.UUID;

public class CleaverItem extends DiggerItem {
    public final float range;
    public final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> cleaverModifiers;

    public CleaverItem(float range, float attackDamage, float attackSpeed, Tier tier, Properties properties) {
        super(attackDamage, attackSpeed, tier, DDTags.BlockT.CLEAVER_MINEABLE, properties);
        this.range = range;
        this.attackDamage = attackDamage;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", getAttackDamage(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(DDAttributes.THROWING_RANGE.get(), new AttributeModifier(UUID.fromString("e260333d-b58b-457e-a699-f47dfb449cc4"), "Tool modifier", range, AttributeModifier.Operation.ADDITION));
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
        int serratedStrikeLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.SERRATED_STRIKE.get(), stack);

        if (serratedStrikeLevel > 0) {
            int duration = 40 + (serratedStrikeLevel * 20);

            if (target.hasEffect(DDEffects.SERRATED.get())) {
                duration = duration / 2;
                duration += target.getEffect(DDEffects.SERRATED.get()).getDuration();
            }
            target.addEffect(new MobEffectInstance(DDEffects.SERRATED.get(), duration, serratedStrikeLevel - 1));
            target.playSound(DDSounds.CLEAVER_SERRATED_STRIKE.get(), 2.0F, target.level().random.nextFloat() * 0.1F + 0.9F);
        }

        if (stack.is(DDTags.ItemT.FLAMING_CLEAVERS)) {
            target.setRemainingFireTicks(target.getRemainingFireTicks() + 80);
        }
        return true;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    public static float getPowerForTime(int charge, int dartingLevel) {
        float v = charge * (1.0F + (dartingLevel * DartingEnchantment.dartingChargePercentIncrease())) / 20.0F;
        v = (v * v + v * 2.0F) / 3.0F;
        if (v > 1.0F) {
            v = 1.0F;
        }
        return v;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        int dartingLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.DARTING.get(), stack);
        return (int) (72000 / (1.0F + (dartingLevel * DartingEnchantment.dartingChargePercentIncrease())));
    }

    @Override
    public void onUseTick(Level level, LivingEntity living, ItemStack stack, int timeLeft) {
        if (!(living instanceof Player player)) return;

        int dartingLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.DARTING.get(), stack);
        int readyTicks = (int) (32 / (1.0F + (dartingLevel * DartingEnchantment.dartingChargePercentIncrease())));
        int usedTicks = getUseDuration(stack) - timeLeft;

        if (usedTicks == readyTicks) {
            level.playSound(null, player, DDSounds.CLEAVER_READY.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        super.onUseTick(level, living, stack, timeLeft);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int timeLeft) {
        int dartingLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.DARTING.get(), stack);

        float fullyCharged = getPowerForTime(32, dartingLevel);
        float threeQuarterCharged = getPowerForTime(24, dartingLevel);
        float halfCharged = getPowerForTime(16, dartingLevel);
        float quarterCharged = getPowerForTime(8, dartingLevel);

        if (!(living instanceof Player player)) return;
        if (getUseDuration(stack) - timeLeft < quarterCharged || player.getCooldowns().isOnCooldown(this)) return;

        if (!level.isClientSide) {
            if (!player.isCreative()) {
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(living.getUsedItemHand()));
            }

            CleaverEntity cleaver = new CleaverEntity(level, player, stack.copy());
            cleaver.setItem(stack.copy());
            applyEffects(player, stack, cleaver);
            cleaver.setBaseDamage(cleaver.getBaseDamage() + attackDamage + getTier().getAttackDamageBonus());

            float charge = getPowerForTime(getUseDuration(stack) - timeLeft, dartingLevel);
            float scale = charge / threeQuarterCharged;
            float velocity = (float) ((living.getAttributeValue(DDAttributes.THROWING_RANGE.get()) + dartingThrowRange(stack)) * scale);
            float maxVelocity = (float) ((living.getAttributeValue(DDAttributes.THROWING_RANGE.get()) + dartingThrowRange(stack)) * (fullyCharged / threeQuarterCharged));

            velocity = Math.min(velocity, maxVelocity);

            if (charge >= fullyCharged) {
                cleaver.setFullyCharged(true);
                cleaver.setLongCooldown(false);
                cleaver.setBaseDamage(cleaver.getBaseDamage() * 1.5);
            }
            if (charge < fullyCharged) {
                cleaver.setLongCooldown(true);
            }

            cleaver.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, velocity, 1.0F);

            if (player.getAbilities().instabuild) {
                cleaver.pickup = AbstractArrow.Pickup.DISALLOWED;
            }

            level.addFreshEntity(cleaver);
            cleaver.setOwner(player);

            if (stack.is(DDTags.ItemT.USES_DULL_CLEAVER_SOUND)) {
                level.playSound(null, cleaver, DDSounds.CLEAVER_THROW_DULL.get(), SoundSource.PLAYERS, 1.5F,
                        level.random.nextFloat() * 0.1F + 0.9F);
            } else {
                level.playSound(null, cleaver, DDSounds.CLEAVER_THROW.get(), SoundSource.PLAYERS, 1.5F,
                        level.random.nextFloat() * 0.1F + 0.9F);
            }
        }
        player.awardStat(Stats.ITEM_USED.get(this));
    }

    public float dartingThrowRange(ItemStack stack) {
        //TODO: change to attribute enchantment thing in  1.21
        int dartingLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.DARTING.get(), stack);

        if (dartingLevel > 0) {
            return (float) dartingLevel / 6;
        } else return 0;
    }

    public void applyEffects(LivingEntity player, ItemStack stack, CleaverEntity cleaver) {
        int sharpnessLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
        if (sharpnessLevel > 0) {
            cleaver.setBaseDamage(cleaver.getBaseDamage() + sharpnessLevel * 0.5 + 0.5);
        }

        int fireAspectLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, stack);
        if (fireAspectLevel > 0) {
            cleaver.setRemainingFireTicks(100 * fireAspectLevel);
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

//        int reapingLevel = EnchantmentHelper.getItemEnchantmentLevel(DDEnchantments.REAPING.get(), stack);
//        if (reapingLevel > 0) {
//            cleaver.setReapingLevel(reapingLevel);
//        }
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
