package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import net.yirmiri.dungeonsdelight.init.DDTags;
import net.yirmiri.dungeonsdelight.util.DDUtil;
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
        appendRefillsBraisedGlowwormQueen();
        appendRefillsBubblegunk();
        appendRefillsCobNCandy();
        appendRawGhast();
        appendBeaconPaymentItems();
        appendExtracts();
        appendSeaPlants();
        appendAcidics();
        appendFleshes();
        appendSlimeBalls();
        appendSculkFoods();
        appendSnifferFoods();
    }

    private void appendMonsterFoods() {
        tag(DDTags.ItemT.MONSTER_FOODS)
                .add(DDItems.LOGO_ITEM.get())
                .add(DDItems.SPIDER_TANGHULU.get())
                .add(DDItems.SPIDER_EYE_SALMAGUNDI.get())
                .add(DDItems.GHOULASH.get())
                .add(DDItems.SILVERFISH_FRIED_RICE.get())
                .add(DDItems.MONSTER_BURGER.get())
                .add(DDItems.BUBBLEGUNK.get())
                .add(DDItems.GELLED_SALAD.get())
                .add(DDItems.COB_N_CANDY.get())
                .add(DDItems.GHAST_ROLL.get())
                .add(DDItems.TOKAYAKI.get())
                .add(DDItems.SALT_SOAKED_STEW.get())
                .add(DDItems.SOAKED_SKEWER.get())
                .add(DDItems.MONSTER_MUFFIN.get())
                .add(DDItems.MONSTER_CAKE_SLICE.get())
                .add(DDItems.OSSOBUSCO.get())
                .add(DDItems.SHIOKARA_BOWL.get())
                .add(DDItems.BLOODY_MARY.get())
                .add(DDItems.MALICIOUS_SANDWICH.get())
                .add(DDItems.TARO_MILK_TEA.get())
        ;
    }

    private void appendSculkFoods() {
        tag(DDTags.ItemT.SCULK_FOODS)
                .add(DDItems.WARDENZOLA.get())
                .add(DDItems.WARDENZOLA_CRUMBLES.get())
                .add(DDItems.CLEAVED_ANCIENT_EGG.get())
                .add(DDItems.SCULK_MAYO.get())
                .add(DDItems.SCULK_TART_SLICE.get())
                .add(DDItems.SCULK_APPLE.get())
                .add(DDItems.TOKAYAKI.get())
                .add(DDItems.MONSTER_CAKE_SLICE.get())
                .add(DDItems.MALICIOUS_SANDWICH.get())
                .add(DDItems.DEVILISH_EGGS.get())
        ;
    }

    private void appendSnifferFoods() {
        tag(DDTags.ItemT.SNIFFER_FOODS)
                .add(DDItems.SNIFFER_SHANK.get())
                .add(DDItems.COOKED_SNIFFER_SHANK.get())
        ;
    }

    private void appendBiteableFoods() {
        tag(DDTags.ItemT.BITEABLE_FOODS)
                .add(DDItems.BUBBLEGUNK.get())
                .add(DDItems.COB_N_CANDY.get())
        ;
    }

    private void appendFleshes() {
        tag(DDTags.ItemT.FLESHES)
                .add(Items.ROTTEN_FLESH)
                .add(DDItems.ROTTEN_TRIPE.get())
                .add(DDItems.GRITTY_FLESH.get())
                .add(DDItems.BRINED_FLESH.get())
                .addOptional(new ResourceLocation(DDUtil.CC_ID, "fresh_flesh"))
        ;
    }

    private void appendSlimeBalls() {
        tag(DDTags.ItemT.SLIME_BALLS)
                .add(Items.SLIME_BALL)
                .add(Items.MAGMA_CREAM)
        ;
    }

    private void appendSeaPlants() {
        tag(DDTags.ItemT.SEA_PLANTS)
                .add(Items.SEAGRASS)
                .add(Items.KELP)
                .addOptional(new ResourceLocation(DDUtil.BF_ID, "spongekin_slice"))
        ;
    }

    private void appendAcidics() {
        tag(DDTags.ItemT.ACIDICS)
                .add(DDItems.SPIDER_EXTRACT.get())
                .addOptional(new ResourceLocation(DDUtil.BF_ID, "citrus_essence"))
        ;
    }

    private void appendExtracts() {
        tag(DDTags.ItemT.EXTRACTS)
                .add(DDItems.SPIDER_EXTRACT.get())
                .add(DDItems.RANCID_REDUCTION.get())
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

    private void appendRawGhast() {
        tag(DDTags.ItemT.RAW_GHAST)
                .add(DDItems.GHAST_TENTACLE.get())
                .add(DDItems.GHAST_CALAMARI.get())
        ;
    }

    private void appendBeaconPaymentItems() {
        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(DDItems.STAINED_SCRAP.get())
        ;
    }

    private void appendAllayDuplicatingItems() {
        tag(DDTags.ItemT.ALLAY_DUPLICATING_ITEMS)
                .add(DDItems.AMETHYST_ROCK_CANDY.get())
                .add(DDItems.CANDIED_SILVERFISH_SUCKER.get())
                .add(DDItems.CANDIED_VEX_SUCKER.get())
        ;
    }

    private void appendRefillsCobNCandy() {
        tag(DDTags.ItemT.REFILLS_COB_N_CANDY)
                .add(Blocks.COBWEB.asItem()) //TODO: CHANGE
        ;
    }

    private void appendRefillsBubblegunk() {
        tag(DDTags.ItemT.REFILLS_BUBBLEGUNK)
                .add(DDBlocks.WORMROOTS_BLOCK.get().asItem()) //TODO: CHANGE
        ;
    }

    private void appendRefillsBraisedGlowwormQueen() {
        tag(DDTags.ItemT.REFILLS_BRAISED_GLOWWORM_QUEEN)
                .add(Items.SLIME_BLOCK) //TODO CHANGE
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
        ;
    }

    //--- FORGE TAGS ---
    private void appendToolsKnives() {
        tag(ForgeTags.TOOLS_KNIVES)
                .addTag(DDTags.ItemT.CLEAVERS)
        ;
    }
}
