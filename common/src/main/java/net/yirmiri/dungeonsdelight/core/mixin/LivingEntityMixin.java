package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMapping;
import net.yirmiri.dungeonsdelight.common.resources.cleaver.CleaverMappings;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    public abstract long getLootTableSeed();

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("TAIL"), method = "createLivingAttributes")
    private static void dungeonsdelight$createLivingAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
                .add(DDAttributes.THROWING_RANGE.get())
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
}
