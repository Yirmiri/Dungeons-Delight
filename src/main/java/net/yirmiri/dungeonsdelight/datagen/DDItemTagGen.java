package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import net.yirmiri.dungeonsdelight.util.DDTags;

import java.util.concurrent.CompletableFuture;

public class DDItemTagGen extends FabricTagProvider.ItemTagProvider {
    public DDItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> future) {
        super(output, future);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        appendMonsterFoods();
        appendDungeonsDelightFoods();
    }

    public void appendMonsterFoods() {
        getOrCreateTagBuilder(DDTags.ItemT.MONSTER_FOODS)
                .add(Items.ROTTEN_FLESH)
                .add(Items.SPIDER_EYE)
        ;
    }

    public void appendDungeonsDelightFoods() {
        getOrCreateTagBuilder(DDTags.ItemT.DUNGEONS_DELIGHT_FOODS)
                .add(DDItems.BREEZE_CREAM_CONE)
                .add(DDItems.TRIAL_FREAKSHAKE)
                .add(DDItems.SLIME_NOODLES)
                .add(DDItems.SLIME_SLAB)
        ;
    }
}