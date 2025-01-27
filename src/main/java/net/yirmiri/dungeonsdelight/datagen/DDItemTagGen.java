package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import net.yirmiri.dungeonsdelight.util.DDTags;

import java.util.concurrent.CompletableFuture;

public class DDItemTagGen extends ItemTagsProvider {
    public DDItemTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> tags, ExistingFileHelper helper) {
        super(output, future, tags, DungeonsDelight.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendAllayDuplicatingItems();
        appendMonsterFoods();
        appendDungeonsDelightFoods();
        appendRockCandies();
    }

    private void appendMonsterFoods() {
        tag(DDTags.ItemT.MONSTER_FOODS) //TODO: ASK TWIX WHAT GOES HERE
                .add(DDItems.LOGO_ITEM.get()) //only here for testing purposes
        ;
    }

    private void appendAllayDuplicatingItems() {
        tag(DDTags.ItemT.ALLAY_DUPLICATING_ITEMS)
                .add(DDItems.AMETHYST_ROCK_CANDY.get())
                .add(DDItems.CANDIED_SILVERFISH_SUCKER.get())
                .add(DDItems.CANDIED_VEX_SUCKER.get())
        ;
    }

    private void appendDungeonsDelightFoods() {
        tag(DDTags.ItemT.DUNGEONS_DELIGHT_FOODS)
                .add(DDItems.SLIME_NOODLES.get())
                .add(DDItems.SLIME_SLAB.get())
                .add(DDItems.GHOULASH.get())
                .addTag(DDTags.ItemT.ROCK_CANDIES)
                .add(DDItems.SILVERFISH_THORAX.get())
        ;
    }

    private void appendRockCandies() {
        tag(DDTags.ItemT.ROCK_CANDIES)
                .add(DDItems.AMETHYST_ROCK_CANDY.get())
                .add(DDItems.CANDIED_VEX_SUCKER.get())
                .add(DDItems.CANDIED_SILVERFISH_SUCKER.get())
        ;
    }
}
