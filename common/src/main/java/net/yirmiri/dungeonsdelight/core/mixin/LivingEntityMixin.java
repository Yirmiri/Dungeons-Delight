package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.leftovers.LeftoversEntity;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMapping;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappings;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.common.util.data.SpikedFoodData;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.init.DDMobTypes;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDAttributes;
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

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Unique
    LivingEntity living = (LivingEntity) (Object) this;

    @Shadow
    public abstract long getLootTableSeed();

    @Shadow
    public abstract ItemStack getMainHandItem();

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "createLivingAttributes", at = @At("TAIL"))
    private static void dungeonsdelight$createLivingAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
                .add(DDAttributes.THROWING_RANGE.get(), 0.66D)
                .add(DDAttributes.AIR_CONTROL.get())
                .add(DDAttributes.CHARGE_MULTIPLIER.get())
        ;
    }

    @Inject(at = @At("TAIL"), method = "dropAllDeathLoot")
    private void dungeonsdelight$dropAllDeathLoot(DamageSource source, CallbackInfo ci) {
        if ((source.is(DDTags.DamageT.REAPS_ITEMS) || (source.getEntity() instanceof Player player) && player.getMainHandItem().is(DDTags.ItemT.REAPS_ITEMS))
                && level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            for (CleaverMapping mapping : CleaverMappings.MAPS.values()) {
                if (mapping.entityType().map(type -> getType().builtInRegistryHolder().is(type)).orElse(false) ||
                        mapping.tag().map(getType()::is).orElse(false)) {

                    level().getServer().getLootData().getLootTable(mapping.table()).getRandomItems(new LootParams.Builder((ServerLevel) level())
                            .withParameter(LootContextParams.THIS_ENTITY, (LivingEntity) (Object) this)
                            .withParameter(LootContextParams.ORIGIN, position())
                            .withParameter(LootContextParams.DAMAGE_SOURCE, source)
                            .create(LootContextParamSets.ENTITY), getLootTableSeed(), this::spawnAtLocation);
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canDisableShield", cancellable = true)
    private void dungeonsdelight$canDisableShield(CallbackInfoReturnable<Boolean> cir) {
        if (getMainHandItem().is(DDTags.ItemT.CLEAVERS)) {
            cir.setReturnValue(true);
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float dungeonsdelight$modifyDamage(float amount) {
        if (living.hasEffect(DDEffects.EXUDATION.get()) && (living.getAbsorptionAmount() > 0) || DungeonsDelight.CONFIG.getExudationDamageMultiplierWhileHeartsActive()) {
            return amount * DungeonsDelight.CONFIG.getExudationDamageMultiplier();
        }
        return amount;
    }

    @Inject(at = @At("HEAD"), method = "hurt")
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (living.hasEffect(DDEffects.EXUDATION.get()) && living.getAbsorptionAmount() > 0 && living.hurtTime == 0 && !living.isInvulnerable()) {
            if (living.level() instanceof ServerLevel) {
                ((ServerLevel) living.level()).sendParticles(DDParticles.EXUDATION_BLAST.get(), living.getX(), living.getY() + 0.67, living.getZ(),
                        1, 0, 0, 0, 0);
            }

            living.level().playSound(null, living.getX(), living.getY(), living.getZ(),
                    SoundEvents.WARDEN_SONIC_BOOM, SoundSource.NEUTRAL, 0.75F, 1.0F);

            DDUtil.exudationBlast(living.level(), living, living);
            living.hurtTime = DungeonsDelight.CONFIG.getExudationInvulnerabilityTicks();
        }
    }

    @Inject(at = @At("HEAD"), method = "die")
    private void dungeonsdelight$die(DamageSource source, CallbackInfo ci) {
        if ((source.getEntity() instanceof LivingEntity attacker && attacker.hasEffect(DDEffects.DECISIVE.get()))) {
            if (living.level() instanceof ServerLevel) {
                ((ServerLevel) level()).sendParticles(DDParticles.CREEPERILLA_BLAST.get(), living.getX(), living.getY(), living.getZ(), 1, 0, 0, 0, 0.0F);
                level().playSound(living, living.blockPosition(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.75F, 1.0F);
            }
            DDUtil.decisiveBlast(living.level(), attacker, living);
        }

        if (source.is(DDDamageTypes.CREEPERILLA_BLAST)) {
            int initialDuration = 200;
            int count = 3;
            LeftoversEntity.LeftoversType type = LeftoversEntity.LeftoversType.GENERIC;
            List<MobEffect> mobEffects = List.of(DDEffects.TENACITY.get());
            List<Integer> effectDurations = List.of(100);
            List<Integer> effectAmplifiers = List.of(0);
            List<Integer> effectMaxDurations = List.of(600);

            if (living.getMobType() == MobType.UNDEFINED && !(living instanceof Monster)) {
                initialDuration = 60;
                count = 1;
                type = LeftoversEntity.LeftoversType.GENERIC_FRIENDLY;
                mobEffects = List.of(DDEffects.TENACITY.get());
                effectDurations = List.of(60);
                effectMaxDurations = List.of(200);
            }

            if (living.getMobType() == MobType.UNDEAD || living.getType().is(DDTags.EntityT.UNDEAD_LEFTOVERS)) {
                type = LeftoversEntity.LeftoversType.UNDEAD;
                mobEffects = List.of(DDEffects.VORACITY.get());
            }

            if (living.getMobType() == MobType.ARTHROPOD || living.getType().is(DDTags.EntityT.ARTHROPOD_LEFTOVERS)) {
                type = LeftoversEntity.LeftoversType.ARTHROPOD;
                mobEffects = List.of(DDEffects.BURROW_GUT.get(), DDEffects.POUNCING.get());
                effectDurations = List.of(100, 100);
                effectAmplifiers = List.of(0, 0);
                effectMaxDurations = List.of(600, 600);
            }

            if (living.getMobType() == DDMobTypes.ROTTEN || living.getType().is(DDTags.EntityT.ROTTEN_LEFTOVERS)) {
                type = LeftoversEntity.LeftoversType.ROTTEN;
                mobEffects = List.of(DDEffects.EXUDATION.get());
            }

            if (living.getType().is(DDTags.EntityT.NETHER_LEFTOVERS)) {
                type = LeftoversEntity.LeftoversType.NETHER;
                mobEffects = List.of(DDEffects.DIVER_DOWN.get(), DDEffects.DEBRIDEMENT.get());
                effectDurations = List.of(100, 100);
                effectAmplifiers = List.of(0, 0);
                effectMaxDurations = List.of(600, 600);
            }

            if (living.getType().is(DDTags.EntityT.SLIME_LEFTOVERS)) {
                count = 2;
                type = LeftoversEntity.LeftoversType.SLIME;
                mobEffects = List.of(MobEffects.JUMP);
                effectDurations =  List.of(60);
            }
            DDUtil.leftovers(living.level(), living, initialDuration, count, type, mobEffects, effectDurations, effectAmplifiers, effectMaxDurations);
        }
    }

    @Inject(at = @At("TAIL"), method = "hurt")
    private void dungeonsdelight$hurtTail(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (living instanceof Player player && source.getDirectEntity() instanceof CleaverEntity && player.isBlocking()) {
            player.disableShield(true);
        }
    }

    @Inject(method = "eat", at = @At("HEAD"))
    private void dungeonsdelight$eat(Level level, ItemStack food, CallbackInfoReturnable<ItemStack> cir) {
        SpikedFoodData.applyEffects(food, living);
    }
}
