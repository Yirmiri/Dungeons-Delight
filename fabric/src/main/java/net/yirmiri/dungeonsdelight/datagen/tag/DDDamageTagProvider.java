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
        appendCleavers();
        appendKeepsHomeward();
        appendIsFire();
        appendWitchResistantTo();
        appendAvoidsGuardianThorns();
        appendAlwaysTriggersSilverfish();
        appendBypassesEnchantments();
    }

    private void appendNoKnockback() {
        getOrCreateTagBuilder(DamageTypeTags.NO_IMPACT)
                .addOptional(DDDamageTypes.SERRATED)
                .addOptional(DDDamageTypes.CLEAVER)
                .addOptional(DDDamageTypes.DUNGEON_STOVE_BURN)
                .addOptional(DDDamageTypes.SPIKE_TRAP)
        ;
    }

    private void appendBypassesArmor() {
        getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ARMOR)
                .addOptional(DDDamageTypes.SERRATED)
                .addOptional(DDDamageTypes.RAW_CREEPER)
                .addOptional(DDDamageTypes.EXUDATION_BLAST)
                .addOptional(DDDamageTypes.VEXING_FANGS)
                .addOptional(DDDamageTypes.SPIKE_TRAP)
        ;
    }

    private void appendBypassesShield() {
        getOrCreateTagBuilder(DamageTypeTags.BYPASSES_SHIELD)
                .addOptional(DDDamageTypes.SERRATED)
                .addOptional(DDDamageTypes.ECHO_BLAST)
                .addOptional(DDDamageTypes.SPIKE_TRAP)
        ;
    }

    private void appendBypassesEnchantments() {
        getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ENCHANTMENTS)
                .addOptional(DDDamageTypes.SPIKE_TRAP)
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

    private void appendIsFire() {
        getOrCreateTagBuilder(DamageTypeTags.IS_FIRE)
                .addOptional(DDDamageTypes.DUNGEON_STOVE_BURN)
                .addOptional(DDDamageTypes.IN_LIVING_FIRE)
        ;
    }

    private void appendIsProjectile() {
        getOrCreateTagBuilder(DamageTypeTags.IS_PROJECTILE)
                .addOptional(DDDamageTypes.CLEAVER)
                .addOptional(DDDamageTypes.ANCIENT_EGG)
                .addOptional(DDDamageTypes.RANCID_REDUCTION)
        ;
    }

    private void appendIsExplosion() {
        getOrCreateTagBuilder(DamageTypeTags.IS_EXPLOSION)
                .addOptional(DDDamageTypes.RAW_CREEPER)
                .addOptional(DDDamageTypes.EXUDATION_BLAST)
                .addOptional(DDDamageTypes.ECHO_BLAST)
        ;
    }

    private void appendReapsItems() {
        getOrCreateTagBuilder(DDTags.DamageT.REAPS_ITEMS)
                .addOptional(DDDamageTypes.CLEAVER)
                .addOptional(DDDamageTypes.SERRATED)
                .addOptional(DDDamageTypes.VEXING_FANGS)
        ;
    }

    private void appendCleavers() {
        getOrCreateTagBuilder(DDTags.DamageT.CLEAVERS)
                .addOptional(DDDamageTypes.CLEAVER)
        ;
    }

    private void appendDamagesHelmet() {
        getOrCreateTagBuilder(DamageTypeTags.DAMAGES_HELMET)
                .addOptional(DDDamageTypes.TRAMPLED)
                .addOptional(DDDamageTypes.HORSE_TRAMPLED)
                .addOptional(DDDamageTypes.DONKEY_TRAMPLED)
                .addOptional(DDDamageTypes.RANCID_REDUCTION)
        ;
    }

    private void appendKeepsHomeward() {
        getOrCreateTagBuilder(DDTags.DamageT.KEEPS_HOMEWARD)

        ;
    }

    private void appendWitchResistantTo() {
        getOrCreateTagBuilder(DamageTypeTags.WITCH_RESISTANT_TO)
                .addOptional(DDDamageTypes.VEXING_FANGS)
                .addOptional(DDDamageTypes.RANCID_REDUCTION)
        ;
    }

    private void appendAlwaysTriggersSilverfish() {
        getOrCreateTagBuilder(DamageTypeTags.ALWAYS_TRIGGERS_SILVERFISH)
                .addOptional(DDDamageTypes.VEXING_FANGS)
                .addOptional(DDDamageTypes.RANCID_REDUCTION)
        ;
    }

    private void appendAvoidsGuardianThorns() {
        getOrCreateTagBuilder(DamageTypeTags.AVOIDS_GUARDIAN_THORNS)
                .addOptional(DDDamageTypes.VEXING_FANGS)
                .addOptional(DDDamageTypes.SERRATED)
        ;
    }
}
