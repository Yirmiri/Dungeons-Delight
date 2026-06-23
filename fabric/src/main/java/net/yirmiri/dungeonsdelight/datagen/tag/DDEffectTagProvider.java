package net.yirmiri.dungeonsdelight.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

import java.util.concurrent.CompletableFuture;

public class DDEffectTagProvider extends FabricTagProvider<MobEffect> {
    public DDEffectTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.MOB_EFFECT, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        appendMonsterEffects();
        appendMonsterEffectsThatPreserveAmplifier();
    }

    private void appendMonsterEffects() {
        getOrCreateTagBuilder(DDTags.EffectT.MONSTER_EFFECTS)
                .add(DDEffects.EXUDATION.get())
                .add(DDEffects.BURROW_GUT.get())
                .add(DDEffects.VORACITY.get())
                .add(DDEffects.DEBRIDEMENT.get())
                .add(DDEffects.POUNCING.get())
                .add(DDEffects.DECISIVE.get())
                .add(DDEffects.HORDE_OMEN.get())
                .add(DDEffects.DIVER_DOWN.get())
        ;
    }

    private void appendMonsterEffectsThatPreserveAmplifier() {
        getOrCreateTagBuilder(DDTags.EffectT.MONSTER_EFFECTS_THAT_PRESERVE_AMPLIFIER)
                .add(DDEffects.EXUDATION.get())
        ;
    }
}
