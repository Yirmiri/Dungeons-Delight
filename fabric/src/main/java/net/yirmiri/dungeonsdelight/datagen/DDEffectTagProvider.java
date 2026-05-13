package net.yirmiri.dungeonsdelight.datagen;

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
    }

    private void appendMonsterEffects() {
        getOrCreateTagBuilder(DDTags.EffectT.MONSTER_EFFECTS)
                .add(DDEffects.POUNCING.get())
        ;
    }
}
