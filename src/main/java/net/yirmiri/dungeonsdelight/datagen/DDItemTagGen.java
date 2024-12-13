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
        appendAllayDuplicatingItems();
    }

    public void appendAllayDuplicatingItems() {
        getOrCreateTagBuilder(DDTags.ItemT.ALLAY_DUPLICATING_ITEMS)
                .add(Items.AMETHYST_SHARD)
                .add(DDItems.AMETHYST_ROCK_CANDY)
        ;
    }

    public void appendMonsterFoods() {
        getOrCreateTagBuilder(DDTags.ItemT.MONSTER_FOODS) //TODO: ASK TWIX WHAT GOES HERE
//                .add(Items.ROTTEN_FLESH)
//                .add(Items.SPIDER_EYE)
        ;
    }

    public void appendDungeonsDelightFoods() {
        getOrCreateTagBuilder(DDTags.ItemT.DUNGEONS_DELIGHT_FOODS)
                .add(DDItems.BREEZE_CREAM_CONE)
                .add(DDItems.TRIAL_FREAKSHAKE)
                .add(DDItems.SLIME_NOODLES)
                .add(DDItems.SLIME_SLAB)
                .add(DDItems.AMETHYST_ROCK_CANDY)
                .add(DDItems.CANDIED_VEX_SUCKER)
                .add(DDItems.CANDIED_SILVERFISH_SUCKER)
                .add(DDItems.SILVERFISH_THORAX)
                .add(DDItems.GHOULASH)
        ;
    }
}