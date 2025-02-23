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
import net.yirmiri.dungeonsdelight.registry.compat.DDCItems;
import net.yirmiri.dungeonsdelight.registry.compat.DDCTFKnives;
import net.yirmiri.dungeonsdelight.util.DDTags;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;

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
        appendCabinets();
        appendKnives();
        appendToolsKnives();
        appendBiteableFoods();
        appendCleavers();
    }

    private void appendMonsterFoods() { //feeding to wormroots
        tag(DDTags.ItemT.MONSTER_FOODS)
                .add(DDItems.LOGO_ITEM.get())
                .add(DDItems.SPIDER_TANGHULU.get())
                .add(DDItems.SPIDER_EYE_SALMAGUNDI.get())
                .add(DDItems.GHOULASH.get())
                .add(DDItems.SILVERFISH_FRIED_RICE.get())
                .add(DDItems.MONSTER_BURGER.get())
                .add(DDItems.BUBBLEGUNK.get())
                .add(DDItems.GELLED_SALAD.get())
                //COMPAT
                .add(DDCItems.MEEF_WELLINGTON.get())
        ;
    }

    private void appendBiteableFoods() {
        tag(DDTags.ItemT.BITEABLE_FOODS)
                .add(DDItems.BUBBLEGUNK.get())
                //COMPAT
                .add(DDCItems.BRAISED_GLOWWORM_QUEEN.get())
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
                .add(DDItems.SLIME_BAR.get())
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
                .add(DDItems.BUBBLEGUNK.get())
                .add(DDItems.CLEAVED_ANCIENT_EGG.get())
                .add(DDItems.SCULK_MAYO.get())
                .add(DDItems.GLOWBERRY_GELATIN.get())
                .add(DDItems.ROTTEN_TRIPE.get())
                .add(DDItems.GELLED_SALAD.get())
                //COMPAT
                .add(DDCItems.MEEF_WELLINGTON.get())
                .add(DDCItems.BRAISED_GLOWWORM_QUEEN.get())
                .add(DDCItems.LIVEROOT_BEER.get())
        ;
    }

    private void appendCleavers() {
        tag(DDTags.ItemT.CLEAVERS)
                .add(DDItems.FLINT_CLEAVER.get())
                .add(DDItems.IRON_CLEAVER.get())
                .add(DDItems.GOLDEN_CLEAVER.get())
                .add(DDItems.DIAMOND_CLEAVER.get())
                .add(DDItems.NETHERITE_CLEAVER.get())
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

    //--- FARMER'S DELIGHT TAGS ---
    private void appendCabinets() {
        tag(ModTags.WOODEN_CABINETS)
                .add(DDBlocks.WORMWOOD_DOOR.get().asItem())
        ;
    }

    private void appendKnives() {
        tag(ModTags.KNIVES)
                .addTag(DDTags.ItemT.CLEAVERS)
                //COMPAT
                .add(DDCTFKnives.KNIGHTMETAL_KNIFE.get())
                .add(DDCTFKnives.STEELEAF_KNIFE.get())
                .add(DDCTFKnives.FIERY_KNIFE.get())
                .add(DDCTFKnives.IRONWOOD_KNIFE.get())
        ;
    }

    //--- FORGE TAGS ---
    private void appendToolsKnives() {
        tag(ForgeTags.TOOLS_KNIVES)
                .addTag(DDTags.ItemT.CLEAVERS)
                //COMPAT
                .add(DDCTFKnives.KNIGHTMETAL_KNIFE.get())
                .add(DDCTFKnives.STEELEAF_KNIFE.get())
                .add(DDCTFKnives.FIERY_KNIFE.get())
                .add(DDCTFKnives.IRONWOOD_KNIFE.get())
        ;
    }
}
