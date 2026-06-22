package net.yirmiri.dungeonsdelight.common.util;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.azurune.runiclib.RunicLib;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDCriteriaTriggers;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class DDUtil {
    public static final int MONSTER_COLOR = 0xc875c2;

    public static final ResourceLocation MONSTER_EFFECT_BG = RunicLib.customid(
            DungeonsDelight.MOD_ID, "textures/gui/sprites/container/inventory/monster_mob_effect_old.png");

    public static final List<Supplier<MobEffect>> MONSTER_EFFECTS_THAT_PRESERVE_AMPLIFIER = List.of( //todo make a tag in 1.21
            DDEffects.EXUDATION, DDEffects.HORDE_OMEN
    );

    public static ItemStack convertItem(Player player, SoundEvent soundEvent, ItemStack stack, ItemStack newStack) {
        player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        player.playSound(soundEvent, 1.0F, 1.0F);
        return ItemUtils.createFilledResult(stack, player, newStack, true);
    }

    public static void applyEffectSwap(LivingEntity living, MobEffect oldEffect, MobEffect newEffect, boolean preserveAmplifier) {
        if (living.hasEffect(oldEffect)) {
            int duration = living.getEffect(oldEffect).getDuration();
            int amplifier = 0;
            if (preserveAmplifier) {
                amplifier = living.getEffect(oldEffect).getAmplifier();
            }

            if (living.hasEffect(newEffect)) {
                duration = Math.max(duration, living.getEffect(newEffect).getDuration());
            }

            living.removeEffect(oldEffect);
            living.addEffect(new MobEffectInstance(newEffect, duration, amplifier));
        }
    }

    public static void applyMonsterEffectSwap(LivingEntity living, MobEffect oldEffect, MobEffect newEffect, boolean preserveAmplifier) {
        applyEffectSwap(living, oldEffect, newEffect, preserveAmplifier);
        if (living instanceof ServerPlayer serverPlayer) {
            //DDCriteriaTriggers.MONSTERIZE_EFFECT.get().trigger(serverPlayer);
            serverPlayer.level().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(),
                    DDSounds.GENERIC_MONSTERIZE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    public static void exudationBlast(Level level, LivingEntity effectUser, Entity targetEntity) {
        boolean effectPlayers = targetEntity instanceof Player;
        float exudationRange = DungeonsDelight.CONFIG.getExudationBaseRange() + effectUser.getEffect(DDEffects.EXUDATION.get()).getAmplifier() * 2;

        if (effectUser.hasEffect(DDEffects.RAVENOUS_RUSH.get())) {
            exudationRange += (3 * effectUser.getEffect(DDEffects.RAVENOUS_RUSH.get()).getAmplifier());
        }

        if (effectUser.hasEffect(DDEffects.EXUDATION.get())) {
            level.getEntitiesOfClass(LivingEntity.class,
                    targetEntity.getBoundingBox().inflate(exudationRange),

                    getTargetPredicate(effectUser, targetEntity, effectPlayers)).forEach(entity -> {

                DamageSource source = new DamageSource(entity.level().registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DDDamageTypes.EXUDATION_BLAST));

                entity.hurt(source, DungeonsDelight.CONFIG.getExudationBaseDamage()
                        + (effectUser.getEffect(DDEffects.EXUDATION.get()).getAmplifier() * 3));

                Vec3 vec3d = entity.position().subtract(targetEntity.position());
                Vec3 vec3d2 = vec3d.normalize().multiply(0.75, 0.75, 0.75);
                entity.setDeltaMovement(vec3d2.x, 0.25F, vec3d2.z);
            });
        }
    }

    private static Predicate<LivingEntity> getTargetPredicate(LivingEntity player, Entity attacked, boolean dontEffectPlayers) {
        return entity -> {
            TamableAnimal tamableAnimal;
            boolean notSpectator = !entity.isSpectator();
            boolean notAttacked = entity != player && entity != attacked;
            boolean notTeammate = !player.isAlliedTo(entity);
            boolean notTamed = !(entity instanceof TamableAnimal && (tamableAnimal = (TamableAnimal)entity).isTame() && player.getUUID().equals(tamableAnimal.getOwnerUUID()));
            boolean distance = attacked.distanceTo(entity) <= Math.pow(3.5, 2.0);
            boolean notPlayer = !(entity instanceof Player);
            if (dontEffectPlayers) {
                return notSpectator && notAttacked && notTeammate && notTamed && distance && notPlayer;
            } else return notSpectator && notAttacked && notTeammate && notTamed && distance;
        };
    }

    public static void addConsumeTooltip(List<Component> tooltipComponents) {
        tooltipComponents.add(CommonComponents.EMPTY);
        tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.when_consumed").withStyle(ChatFormatting.GRAY));
    }

    public static void addEffectTooltip(FoodProperties foodProperties, List<Component> tooltipComponents, float durationFactor) {
        List<Pair<Attribute, AttributeModifier>> list = Lists.newArrayList();

        if (foodProperties.getEffects().isEmpty()) {
            tooltipComponents.add(Component.translatable("effect.none").withStyle(ChatFormatting.GRAY));
        } else {
            for (Pair<MobEffectInstance, Float> effectPair : foodProperties.getEffects()) {
                MobEffectInstance mobeffectinstance = effectPair.getFirst();

                MutableComponent mutablecomponent = Component.translatable(mobeffectinstance.getDescriptionId());
                MobEffect mobeffect = mobeffectinstance.getEffect();

                Map<Attribute, AttributeModifier> map = mobeffect.getAttributeModifiers();
                if (!map.isEmpty()) {
                    for (Map.Entry<Attribute, AttributeModifier> entry : map.entrySet()) {
                        AttributeModifier attributemodifier = entry.getValue();
                        AttributeModifier attributemodifier1 = new AttributeModifier(
                                attributemodifier.getName(),
                                mobeffect.getAttributeModifierValue(
                                        mobeffectinstance.getAmplifier(),
                                        attributemodifier
                                ),
                                attributemodifier.getOperation()
                        );
                        list.add(new Pair<>(entry.getKey(), attributemodifier1));
                    }
                }

                if (mobeffectinstance.getAmplifier() > 0) {
                    mutablecomponent = Component.translatable(
                            "potion.withAmplifier",
                            mutablecomponent,
                            Component.translatable("potion.potency." + mobeffectinstance.getAmplifier())
                    );
                }
                if (!mobeffectinstance.endsWithin(20)) {
                    mutablecomponent = Component.translatable(
                            "potion.withDuration",
                            mutablecomponent,
                            MobEffectUtil.formatDuration(mobeffectinstance, durationFactor)
                    );
                }
                tooltipComponents.add(mutablecomponent.withStyle(mobeffect.getCategory().getTooltipFormatting()));
            }
        }

        if (!list.isEmpty()) {
            tooltipComponents.add(CommonComponents.EMPTY);
            tooltipComponents.add(Component.translatable("potion.whenDrank").withStyle(ChatFormatting.DARK_PURPLE));

            for (Pair<Attribute, AttributeModifier> pair : list) {
                AttributeModifier attributemodifier2 = pair.getSecond();
                double d0 = attributemodifier2.getAmount();
                double d1;

                if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE
                        && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    d1 = attributemodifier2.getAmount();
                } else {
                    d1 = attributemodifier2.getAmount() * 100.0F;
                }

                if (d0 > 0.0D) {
                    tooltipComponents.add(
                            Component.translatable(
                                    "attribute.modifier.plus." + attributemodifier2.getOperation().toValue(),
                                    ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1),
                                    Component.translatable(pair.getFirst().getDescriptionId())
                            ).withStyle(ChatFormatting.BLUE)
                    );
                } else if (d0 < 0.0D) {
                    d1 *= -1.0D;
                    tooltipComponents.add(
                            Component.translatable(
                                    "attribute.modifier.take." + attributemodifier2.getOperation().toValue(),
                                    ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1),
                                    Component.translatable(pair.getFirst().getDescriptionId())
                            ).withStyle(ChatFormatting.RED)
                    );
                }
            }
        }
    }

    public static void addEffectTooltipWithChance(FoodProperties foodProperties, List<Component> tooltipComponents, float durationFactor) {
        List<Pair<Attribute, AttributeModifier>> list = Lists.newArrayList();

        if (foodProperties.getEffects().isEmpty()) {
            tooltipComponents.add(Component.translatable("effect.none").withStyle(ChatFormatting.GRAY));
        } else {
            for (Pair<MobEffectInstance, Float> effectPair : foodProperties.getEffects()) {
                MobEffectInstance mobeffectinstance = effectPair.getFirst();

                MutableComponent mutablecomponent = Component.translatable(mobeffectinstance.getDescriptionId());
                MobEffect mobeffect = mobeffectinstance.getEffect();

                Map<Attribute, AttributeModifier> map = mobeffect.getAttributeModifiers();
                if (!map.isEmpty()) {
                    for (Map.Entry<Attribute, AttributeModifier> entry : map.entrySet()) {
                        AttributeModifier attributemodifier = entry.getValue();
                        AttributeModifier attributemodifier1 = new AttributeModifier(
                                attributemodifier.getName(),
                                mobeffect.getAttributeModifierValue(
                                        mobeffectinstance.getAmplifier(),
                                        attributemodifier
                                ),
                                attributemodifier.getOperation()
                        );
                        list.add(new Pair<>(entry.getKey(), attributemodifier1));
                    }
                }

                if (mobeffectinstance.getAmplifier() > 0) {
                    mutablecomponent = Component.translatable(
                            "potion.withAmplifier",
                            mutablecomponent,
                            Component.translatable("potion.potency." + mobeffectinstance.getAmplifier())
                    );
                }

                if (!mobeffectinstance.endsWithin(20)) {
                    mutablecomponent = Component.translatable(
                            "potion.withDuration",
                            mutablecomponent,
                            MobEffectUtil.formatDuration(mobeffectinstance, durationFactor)
                    );
                }

                if (effectPair.getSecond() < 0.999F) {
                    mutablecomponent = mutablecomponent
                            .append(Component.literal(" ").append(Component.literal(String.valueOf(Math.round(effectPair.getSecond() * 100)))).append("%"));
                }
                tooltipComponents.add(mutablecomponent.withStyle(mobeffect.getCategory().getTooltipFormatting()));
            }
        }

        if (!list.isEmpty()) {
            tooltipComponents.add(CommonComponents.EMPTY);
            tooltipComponents.add(Component.translatable("potion.whenDrank").withStyle(ChatFormatting.DARK_PURPLE));

            for (Pair<Attribute, AttributeModifier> pair : list) {
                AttributeModifier attributemodifier2 = pair.getSecond();
                double d0 = attributemodifier2.getAmount();
                double d1;

                if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE
                        && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    d1 = attributemodifier2.getAmount();
                } else {
                    d1 = attributemodifier2.getAmount() * 100.0F;
                }

                if (d0 > 0.0D) {
                    tooltipComponents.add(
                            Component.translatable(
                                    "attribute.modifier.plus." + attributemodifier2.getOperation().toValue(),
                                    ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1),
                                    Component.translatable(pair.getFirst().getDescriptionId())
                            ).withStyle(ChatFormatting.BLUE)
                    );
                } else if (d0 < 0.0D) {
                    d1 *= -1.0D;
                    tooltipComponents.add(
                            Component.translatable(
                                    "attribute.modifier.take." + attributemodifier2.getOperation().toValue(),
                                    ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1),
                                    Component.translatable(pair.getFirst().getDescriptionId())
                            ).withStyle(ChatFormatting.RED)
                    );
                }
            }
        }
    }
}
