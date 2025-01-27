package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
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
        appendWoodenDoors();
        appendWoodenTrapDoors();
        appendWoodenStairs();
        appendWoodenSlabs();
        appendPlanks();
        appendWoodenPressurePlates();
        appendWoodenButtons();
        appendWoodenFenceGates();
        appendWoodenFences();
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
                .add(DDItems.GHAST_TENTACLE.get())
                .add(DDItems.GHAST_CALAMARI.get())
                .add(DDItems.FRIED_GHAST_CALAMARI.get())
                .add(DDItems.SILVERFISH_FRIED_RICE.get())
                .add(DDItems.SPIDER_MEAT.get())
                .add(DDItems.SMOKED_SPIDER_MEAT.get())
                .add(DDItems.SPIDER_TANGHULU.get())
                .add(DDItems.SPIDER_EYE_SALMAGUNDI.get())
                .add(DDItems.MONSTER_BURGER.get())
        ;
    }

    private void appendRockCandies() {
        tag(DDTags.ItemT.ROCK_CANDIES)
                .add(DDItems.AMETHYST_ROCK_CANDY.get())
                .add(DDItems.CANDIED_VEX_SUCKER.get())
                .add(DDItems.CANDIED_SILVERFISH_SUCKER.get())
        ;
    }

    private void appendPlanks() {
        tag(ItemTags.PLANKS)
                .add(DDBlocks.WORMWOOD_PLANKS.get().asItem())
        ;
    }

    private void appendWoodenStairs() {
        tag(ItemTags.WOODEN_STAIRS)
                .add(DDBlocks.WORMWOOD_STAIRS.get().asItem())
                .add(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get().asItem())
        ;
    }

    private void appendWoodenSlabs() {
        tag(ItemTags.WOODEN_SLABS)
                .add(DDBlocks.WORMWOOD_SLAB.get().asItem())
                .add(DDBlocks.WORMWOOD_MOSAIC_SLAB.get().asItem())
        ;
    }

    private void appendWoodenDoors() {
        tag(ItemTags.WOODEN_DOORS)
                .add(DDBlocks.WORMWOOD_DOOR.get().asItem())
        ;
    }

    private void appendWoodenTrapDoors() {
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(DDBlocks.WORMWOOD_TRAPDOOR.get().asItem())
        ;
    }

    private void appendWoodenPressurePlates() {
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(DDBlocks.WORMWOOD_PRESSURE_PLATE.get().asItem())
        ;
    }

    private void appendWoodenButtons() {
        tag(ItemTags.WOODEN_BUTTONS)
                .add(DDBlocks.WORMWOOD_BUTTON.get().asItem())
        ;
    }

    private void appendWoodenFences() {
        tag(ItemTags.WOODEN_FENCES)
                .add(DDBlocks.WORMWOOD_FENCE.get().asItem())
        ;
    }

    private void appendWoodenFenceGates() {
        tag(ItemTags.FENCE_GATES)
                .add(DDBlocks.WORMWOOD_FENCE_GATE.get().asItem())
        ;
    }
}
