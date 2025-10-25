package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.common.util.misc.RottenHeartData;
import net.yirmiri.dungeonsdelight.common.util.misc.RottenHeartManager;
import net.yirmiri.dungeonsdelight.common.util.misc.S2CRottenHeartsPacket;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Unique LivingEntity living = (LivingEntity) (Object) this;
    @Shadow private Optional<BlockPos> lastClimbablePos;

    @Shadow public abstract ItemStack getMainHandItem();

    @Unique private static Random random = new Random();

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float dungeonsdelight$modifyDamage(float amount) {
        if (living.hasEffect(DDEffects.EXUDATION) && living.getAbsorptionAmount() > 0) {
            return amount * 1.5F;
        }
        return amount;
    }

    private static final Map<Holder<MobEffect>, Holder<MobEffect>> NORMAL_TO_MONSTER = Map.of(
            MobEffects.DAMAGE_BOOST, DDEffects.DECISIVE,
            MobEffects.JUMP, DDEffects.POUNCING,
            MobEffects.ABSORPTION, DDEffects.EXUDATION,
            ModEffects.NOURISHMENT, DDEffects.VORACITY,
            ModEffects.COMFORT, DDEffects.TENACITY,
            MobEffects.DIG_SPEED, DDEffects.BURROW_GUT,
            MobEffects.MOVEMENT_SPEED, DDEffects.SWIFT_STEP,
            MobEffects.REGENERATION, DDEffects.ROTGUT
    );

    @Inject(at = @At("HEAD"), method = "tickEffects")
    private void dungeonsdelight$tickEffects(CallbackInfo ci) {
        if ((Object) this instanceof LivingEntity living) {
            for (Map.Entry<Holder<MobEffect>, Holder<MobEffect>> entry : NORMAL_TO_MONSTER.entrySet()) {
                Holder<MobEffect> normal = entry.getKey();
                Holder<MobEffect> monster = entry.getValue();

                MobEffectInstance normalInstance = living.getEffect(normal);
                MobEffectInstance monsterInstance = living.getEffect(monster);

                if (normalInstance != null && monsterInstance != null) {
                    if (normalInstance.getDuration() >= monsterInstance.getDuration()) {
                        MobEffectInstance newMonster = new MobEffectInstance(monster, normalInstance.getDuration(), 0, monsterInstance.isAmbient(), monsterInstance.isVisible(), monsterInstance.showIcon());
                        living.addEffect(newMonster);
                    }
                    living.removeEffect(normal);
                }
            }
        }
    }

    @Inject(at = @At("TAIL"), method = "setHealth")
    private void dungeonsdelight$setHealth(float health, CallbackInfo ci) {
        RottenHeartData data = RottenHeartManager.get(living);
        if (data == null) return;

        int rotten = data.getRottenHearts();
        if (rotten <= 0) return;

        int allowedRottenHearts = getAllowedRottenHearts(living.getEffect(DDEffects.ROTGUT), living.getMaxHealth(), living.getHealth());
        if (rotten > allowedRottenHearts) {
            data.setRottenHearts(allowedRottenHearts);
            RottenHeartManager.save(living);

//            if (living instanceof ServerPlayer sp) {
//                PacketDistributor.sendToPlayer(sp, new S2CRottenHeartsPacket(data.getRottenHearts()));
//            }
        }
    }

    private static int getAllowedRottenHearts(MobEffectInstance rotgut, float maxHealth, float currentHealth) {
        int maxRottenHearts = 8 + (rotgut != null ? (rotgut.getAmplifier() + 1) * 2 : 0);
        int maxHealthHearts = Mth.ceil(maxHealth / 2.0F);
        int currentHealthHearts = Mth.ceil(currentHealth / 2.0F);
        int emptyContainers = Math.max(0, maxHealthHearts - currentHealthHearts);
        return Math.min(maxRottenHearts, emptyContainers * 2);
    }

    @Inject(at = @At("TAIL"), method = "hurt", cancellable = true)
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getEntity();
        if (attacker instanceof Player player && player.hasEffect(DDEffects.VORACITY)) {
            int voracityLevel = player.getEffect(DDEffects.VORACITY).getAmplifier();

            player.getFoodData().eat(getVoracityRefillAmount(player, amount), 0.3F + ((float) voracityLevel / 10));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 1.0F, 1.0F);
            DDUtil.spreadParticles(DDParticles.DECISIVE_CRITICAL.get(), living, random);
        }

        if (living instanceof MonsterYamEntity) {
            if (source.is(DamageTypeTags.IS_DROWNING) || source.is(DamageTypes.IN_WALL)) {
                cir.setReturnValue(false);
            }
        }

        if (living.hasEffect(DDEffects.ROTGUT)) {
            RottenHeartData data = RottenHeartManager.get(living);
            MobEffectInstance rotgut = living.getEffect(DDEffects.ROTGUT);
            int maxRottenHearts = 8 + (rotgut != null ? (rotgut.getAmplifier() + 1) * 2 : 0);
            int maxHealthBars = Mth.ceil(living.getMaxHealth() / 2.0F);
            int currentHealthBars = Mth.ceil(living.getHealth() / 2.0F);
            int emptyContainers = maxHealthBars - currentHealthBars;
            int availableSpace = Math.max(0, Math.min(emptyContainers, maxRottenHearts - data.getRottenHearts()));
            int amountToAdd = Math.max(1, Math.min((int) amount, availableSpace));

            data.addRottenHearts(amountToAdd, maxRottenHearts);
            RottenHeartManager.save(living);

//            if (living instanceof ServerPlayer serverPlayer) {
//                PacketDistributor.sendToPlayer(serverPlayer, new S2CRottenHeartsPacket(data.getRottenHearts()));
//            }
        }

        if (attacker instanceof LivingEntity attackerLiving) {
            MobEffectInstance rotgut = attackerLiving.getEffect(DDEffects.ROTGUT);
            RottenHeartData data = RottenHeartManager.get(attackerLiving);
            int rotten = data.getRottenHearts();

            if (rotgut != null) {
                if (rotten > 0) {
                    int healAmount = Math.max(1, Math.min((int) amount, rotten));
                    attackerLiving.heal(healAmount);
                    data.removeRottenHearts(healAmount);
                    RottenHeartManager.save(attackerLiving);

//                    if (attackerLiving instanceof ServerPlayer serverPlayer) {
//                        PacketDistributor.sendToPlayer(serverPlayer, new S2CRottenHeartsPacket(data.getRottenHearts()));
//                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onEffectRemoved")
    private void dungeonsdelight$onEffectRemoved(MobEffectInstance effectInstance, CallbackInfo ci) {
        if (effectInstance.is(DDEffects.ROTGUT)) {
            DDUtil.clearRottenHearts(living);
        }
    }

    @Inject(at = @At("HEAD"), method = "die")
    private void dungeonsdelight$die(DamageSource damageSource, CallbackInfo ci) {
        DDUtil.clearRottenHearts(living);
    }

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float dungeonsdelight$pouncingHurt(float amount, DamageSource source) {
        if (living.hasEffect(DDEffects.POUNCING) && source.is(DamageTypeTags.IS_FALL)) {
            int amplifier = living.getEffect(DDEffects.POUNCING).getAmplifier();
            float reduced = amount * (1.0F - 0.20F + 0.05F * amplifier);
            if (reduced < 1.0F) {
                return 0.0F;
            }
            return reduced;
        }
        return amount;
    }

    @Inject(at = @At("HEAD"), method = "createWitherRose")
    private void dungeonsdelight$createWitherRose(LivingEntity attacker, CallbackInfo ci) {
        if (attacker != null && attacker.hasEffect(DDEffects.VORACITY)) {
            attacker.addEffect(new MobEffectInstance(DDEffects.RAVENOUS_RUSH, 100, 0));
            DDUtil.spreadParticles(DDParticles.DECISIVE_CRITICAL.get(), living, random);
        }
    }

    private int getVoracityRefillAmount(LivingEntity living, float amount) {
        if ((amount / 2) < 1) {
            return 1;
        } else if ((living.getEffect(DDEffects.VORACITY).getAmplifier() + 4) > amount) {
            return (int) (amount / 2);
        } else return (living.getEffect(DDEffects.VORACITY).getAmplifier() + 4);
    }

    @Inject(at = @At("HEAD"), method = "isDamageSourceBlocked", cancellable = true)
    private void dungeonsdelight$isDamageSourceBlocked(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
//        if (source.getDirectEntity() instanceof CleaverEntity) {
//            cir.setReturnValue(false);
//        }
    }

    @Inject(at = @At("HEAD"), method = "canDisableShield", cancellable = true)
    private void dungeonsdelight$canDisableShield(CallbackInfoReturnable<Boolean> cir) {
        if (this.getMainHandItem().getItem() instanceof CleaverItem) {
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "onClimbable", cancellable = true)
    private void dungeonsdelight$onClimbable(CallbackInfoReturnable<Boolean> cir) {
        BlockPos blockpos = living.blockPosition();

        if (living.hasEffect(DDEffects.POUNCING) && living.horizontalCollision && !living.isCrouching()) {
            lastClimbablePos = Optional.of(blockpos);
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void dungeonsdelight$tick(CallbackInfo ci) {
        if (living.hasEffect(DDEffects.POUNCING) && living.horizontalCollision && living.isCrouching()) {
            Vec3 movement = living.getDeltaMovement();
            if (movement.y < -0.37) {
                double deltaMovement = -0.29 / movement.y;
                living.setDeltaMovement(new Vec3(movement.x * deltaMovement, -0.29, movement.z * deltaMovement));
            } else {
                living.setDeltaMovement(new Vec3(movement.x, -0.29, movement.z));
            }

            for(int i = 0; i < 5; ++i) {
                living.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, this.living.getBlockStateOn()),
                        living.getX(), living.getY(), living.getZ(), 0.0, 0.0, 0.0);
            }
            living.resetFallDistance();
        }
    }
}