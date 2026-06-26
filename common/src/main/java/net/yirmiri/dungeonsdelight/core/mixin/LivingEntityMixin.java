package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMapping;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappings;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.common.util.data.SpikedFoodData;
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
                ((ServerLevel) living.level()).sendParticles(DDParticles.EXUDATION_BLAST.get(), living.getX(), living.getY() + 0.5, living.getZ(),
                        0, 0, 0, 0, 0);
            }

            living.level().playSound(null, living.getX(), living.getY(), living.getZ(),
                    SoundEvents.WARDEN_SONIC_BOOM, SoundSource.NEUTRAL, 0.75F, 2.0F);

            DDUtil.exudationBlast(living.level(), living, living);
            living.hurtTime = DungeonsDelight.CONFIG.getExudationInvulnerabilityTicks();
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
