package net.yirmiri.dungeonsdelight.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

import java.util.concurrent.CompletableFuture;

public class DDDamageTagProvider extends FabricTagProvider<DamageType> {
    public DDDamageTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.DAMAGE_TYPE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        appendBypassesArmor();
        appendBypassesShield();
        appendBypassesEffects();
        appendBypassesResistance();
        appendIsProjectile();
        appendNoKnockback();
        appendIsExplosion();
        appendReapsItems();
        appendDamagesHelmet();
    }

    private void appendNoKnockback() {
        getOrCreateTagBuilder(DamageTypeTags.NO_IMPACT)
                .addOptional(DDDamageTypes.SERRATED)
                .addOptional(DDDamageTypes.CLEAVER)
        ;
    }

    private void appendBypassesArmor() {
        getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ARMOR)
                .addOptional(DDDamageTypes.SERRATED)
                .addOptional(DDDamageTypes.RAW_CREEPER)
                .addOptional(DDDamageTypes.EXUDATION_BLAST)
        ;
    }

    private void appendBypassesShield() {
        getOrCreateTagBuilder(DamageTypeTags.BYPASSES_SHIELD)
                .addOptional(DDDamageTypes.SERRATED)
        ;
    }

    private void appendBypassesEffects() {
        getOrCreateTagBuilder(DamageTypeTags.BYPASSES_EFFECTS)
                .addOptional(DDDamageTypes.RAW_CREEPER)
        ;
    }

    private void appendBypassesResistance() {
        getOrCreateTagBuilder(DamageTypeTags.BYPASSES_RESISTANCE)
                .addOptional(DDDamageTypes.RAW_CREEPER)
        ;
    }

    private void appendIsProjectile() {
        getOrCreateTagBuilder(DamageTypeTags.IS_PROJECTILE)
                .addOptional(DDDamageTypes.CLEAVER)
        ;
    }

    private void appendIsExplosion() {
        getOrCreateTagBuilder(DamageTypeTags.IS_EXPLOSION)
                .addOptional(DDDamageTypes.RAW_CREEPER)
                .addOptional(DDDamageTypes.EXUDATION_BLAST)
        ;
    }

    private void appendReapsItems() {
        getOrCreateTagBuilder(DDTags.DamageT.REAPS_ITEMS)
                .addOptional(DDDamageTypes.CLEAVER)
                .addOptional(DDDamageTypes.SERRATED)
        ;
    }

    private void appendDamagesHelmet() {
        getOrCreateTagBuilder(DamageTypeTags.DAMAGES_HELMET)
                .addOptional(DDDamageTypes.TRAMPLED)
                .addOptional(DDDamageTypes.HORSE_TRAMPLED)
                .addOptional(DDDamageTypes.DONKEY_TRAMPLED)
        ;
    }
}
