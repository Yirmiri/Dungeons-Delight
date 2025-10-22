package net.yirmiri.dungeonsdelight.datagen;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.integration.content.aether.AEItems;
import net.yirmiri.dungeonsdelight.integration.content.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.content.fishy_fiesta.FFItems;
import net.yirmiri.dungeonsdelight.integration.content.minersdelight.MDItems;
import net.yirmiri.dungeonsdelight.integration.content.twilightforest.TFItems;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class DDItemTagGen extends ItemTagsProvider {
    public DDItemTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, DungeonsDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendMeals();
        appendDrinks();
        appendFeasts();
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
        appendWoodenCabinets();
        appendKnives();
        appendToolsKnives();
        appendBiteableFoods();
        appendCleavers();
        appendRawGhast();
        appendBeaconPaymentItems();
        appendExtracts();
        appendSeaPlants();
        appendAcidics();
        appendFleshes();
        appendSlimeBalls();
        appendSculkFoods();
        appendSnifferFoods();
        appendAncientFlora();
        appendSculkCheese();
        appendArrows();
        appendWormouthBlacklist();
        appendWormouthFavorites();
        appendRepairsStainedTools();
        appendFlamingKnives();
        appendTrimMaterials();
        appendBoats();
        appendChestBoats();
        appendMeat();
        appendRubabooIngredients();
        appendStainedEnchantable();
        appendCleaverEnchantable();
        appendMiningEnchantable();
        appendSharpEnchantable();
        appendMiningLootEnchantable();
        appendDurabilityEnchantable();
        appendWeaponEnchantable();
        appendDecoratedPotSherds();
        appendDecoratedPotIngredients();
        appendFishes();
        appendEvaporatesInWater();
        appendCCleavers();
    }

    private void appendMonsterFoods() {
        tag(DDTags.ItemT.MONSTER_FOODS)
                //TODO //.add(DDItems.COLESLAW.get())
                //TODO //.add(DDItems.GUNPOWDER_BAKED_SPIDER.get())
                //TODO //.add(DDItems.DYNAMITE_ROLL.get())
                .add(DDItems.JELLY_BEANS.get())
                .add(DDItems.POLTERGHAST_PIZZA_SLICE.get())
                .add(DDItems.WISPY_RICE_BALL.get())
                .add(DDItems.LOGO_ITEM.get())
                .add(DDItems.SPIDER_TANGHULU.get())
                .add(DDItems.RUBABOO.get())
                .add(DDItems.SPIDER_SALMAGUNDI.get())
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
                .add(DDItems.OSSOBUCO.get())
                .add(DDItems.SHIOKARA.get())
                .add(DDItems.BLOODY_MARY.get())
                .add(DDItems.MALICIOUS_SANDWICH.get())
                .add(DDItems.TARO_MILK_TEA.get())
                .add(DDItems.TERRINE_LOAF.get())
                .add(DDItems.GYUDON.get())
                .add(DDItems.SINIGANG.get())
                .add(DDItems.GUARDIAN_ANGEL.get())
                .add(DDItems.POI.get())
                .add(DDItems.CHICKEN_JOCKEY_SANDWICH.get())
                .add(DDItems.POISONOUS_POUTINE.get())
                .add(DDItems.AU_ROTTEN_POTATOES.get())
                .add(DDItems.BLOATED_BAKED_POTATO.get())
                .add(DDItems.WARDENZOLA.get())
                .add(DDItems.WARDENZOLA_CRUMBLES.get())
                .add(DDItems.CANDIED_VEX_SUCKER.get())
                .add(DDItems.CANDIED_SILVERFISH_SUCKER.get())
                .add(DDItems.SPIDER_BUBBLE_TEA.get())
                .add(DDItems.BREEZE_CREAM_CONE.get())
                .add(DDItems.MARSHBELLOW.get())
                .add(DDItems.TRIAL_FREAKSHAKE.get())
                .add(DDItems.CROAK_MONSTER.get())
                .add(DDItems.BRAINS_IN_A_BRICK.get())
                .add(DDItems.HAGGIS.get())
                .add(DDItems.ROTGOURD_SLICE.get())
                .add(DDItems.PUTRID_SPICE_LATTE.get())
                //INTEGRATION
                .add(MDItems.SALT_SOAKED_STEW_CUP.get())
                .add(MDItems.SPIDER_SALMAGUNDI_CUP.get())
                .add(MDItems.POI_CUP.get())
                .add(MDItems.RUBABOO_CUP.get())
                .add(TFItems.MAZE_SMORE.get())
                .add(TFItems.SWEETBREAD.get())
                .add(TFItems.TOWER_BOREITO.get())
                .add(TFItems.AURORA_ICE_CREAM.get())
                .add(TFItems.BLAZING_BLOOD_SAUSAGE.get())
                .add(TFItems.ARCANE_CHILI.get())
                .add(TFItems.HYDRA_FRICASSEE.get())
                .add(TFItems.SCALY_FIDDLEHEAD_RISOTTO.get())
                .add(AEItems.VENOMOUS_ONIGIRI.get())
                .add(AEItems.FLUFFY_FLOSS.get())
                .add(AEItems.AMBER_E_OLIO.get())
                .add(AEItems.AMBROSIA_RING.get())
                .add(AEItems.SKYBERRY_BREW.get())
        ;
    }

    private void appendCleavers() {
        tag(DDTags.ItemT.CLEAVERS)
                .add(DDItems.FLINT_CLEAVER.get())
                .add(DDItems.IRON_CLEAVER.get())
                .add(DDItems.GOLDEN_CLEAVER.get())
                .add(DDItems.DIAMOND_CLEAVER.get())
                .add(DDItems.NETHERITE_CLEAVER.get())
                .add(DDItems.STAINED_CLEAVER.get())
                //INTEGRATION
                .add(TFItems.KNIGHTMETAL_CLEAVER.get())
                .add(TFItems.IRONWOOD_CLEAVER.get())
                .add(TFItems.STEELEAF_CLEAVER.get())
                .add(TFItems.FIERY_CLEAVER.get())
                .add(AEItems.ZANITE_CLEAVER.get())
                .add(AEItems.GRAVITITE_CLEAVER.get())
        ;
    }

    private void appendSculkFoods() {
        tag(DDTags.ItemT.SCULK_FOODS)
                .add(DDItems.POLTERGHAST_PIZZA_SLICE.get())
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
                .add(DDItems.TERRINE_LOAF.get())
                .add(DDItems.GYUDON.get())
                .add(DDItems.CHICKEN_JOCKEY_SANDWICH.get())
                .add(DDItems.POISONOUS_POUTINE.get())
                .add(DDItems.AU_ROTTEN_POTATOES.get())
                .add(DDItems.BLOATED_BAKED_POTATO.get())
                .add(DDItems.ECHO_ROCK_CANDY.get())
                .add(DDItems.CROAK_MONSTER.get())
                //INTEGRATION
                .add(ADItems.SCULK_DOGAPPLE.get())
                .add(ADItems.SCULK_CATBLUEBERRY.get())
                .add(FFItems.LUTEFISK.get())
        ;
    }

    private void appendBiteableFoods() {
        tag(DDTags.ItemT.BITEABLE_FOODS)
                .add(DDItems.BUBBLEGUNK.get())
                .add(DDItems.COB_N_CANDY.get())
                .add(DDItems.RUBABOO.get())
                .add(DDItems.AU_ROTTEN_POTATOES.get())
                .add(DDItems.ECHO_ROCK_CANDY.get())
                .add(DDItems.TRIAL_FREAKSHAKE.get())
                .add(DDItems.BRAINS_IN_A_BRICK.get())
                //INTEGRATION
                .add(MDItems.RUBABOO_CUP.get())
                .add(TFItems.ARCANE_CHILI.get())
                .add(FFItems.LUTEFISK.get())
                .add(AEItems.FLUFFY_FLOSS.get())
        ;
    }

    private void appendMeat() {
        tag(ItemTags.MEAT)
                .add(DDItems.SILVERFISH_ABDOMEN.get())
                .add(DDItems.GHAST_CALAMARI.get())
                .add(DDItems.FRIED_GHAST_CALAMARI.get())
                .add(DDItems.GHAST_TENTACLE.get())
                .add(DDItems.SPIDER_MEAT.get())
                .add(DDItems.SMOKED_SPIDER_MEAT.get())
                .add(DDItems.ROTTEN_TRIPE.get())
                .add(DDItems.BRINED_FLESH.get())
                .add(DDItems.GRITTY_FLESH.get())
                .add(DDItems.BOGGED_BRAIN.get())
                //INTEGRATION
                .add(TFItems.BUG_CHOPS.get())
                .add(TFItems.FRIED_BUG_CHOPS.get())
                .add(AEItems.VOLAILLE.get())
                .add(AEItems.MARBLED_MEAT.get())
                .add(AEItems.COOKED_MARBLED_MEAT.get())
        ;
    }

    private void appendWormouthFavorites() {
        tag(DDTags.ItemT.WORMOUTH_FAVORITES)
                .addTag(ModTags.MEALS)
                .add(DDItems.CANDIED_VEX_SUCKER.get())
                .add(DDItems.CANDIED_SILVERFISH_SUCKER.get())
                .add(DDItems.SPIDER_TANGHULU.get())
                .add(DDItems.SOAKED_SKEWER.get())
                .add(DDItems.MONSTER_BURGER.get())
                .add(DDItems.SPIDER_PIE_SLICE.get())
                .add(DDItems.MONSTER_CAKE_SLICE.get())
                .add(DDItems.MALICIOUS_SANDWICH.get())
                .add(DDItems.TERRINE_LOAF.get())
                .add(DDItems.CHICKEN_JOCKEY_SANDWICH.get())
                .add(DDItems.BREEZE_CREAM_CONE.get())
                .add(DDItems.WISPY_RICE_BALL.get())
        ;
    }

    private void appendWormouthBlacklist() {
        tag(DDTags.ItemT.WORMOUTH_BLACKLIST)
                .add(DDItems.ROTTEN_TRIPE.get())
                .add(DDItems.SLIME_NOODLES.get())
                .add(DDItems.BRINED_FLESH.get())
                .add(DDItems.GRITTY_FLESH.get())
                .add(Items.SPIDER_EYE)
                .add(Items.ROTTEN_FLESH)
        ;
    }

    private void appendSnifferFoods() {
        tag(DDTags.ItemT.SNIFFER_FOODS)
                .add(DDItems.SNIFFER_SHANK.get())
                .add(DDItems.COOKED_SNIFFER_SHANK.get())
                .add(DDItems.SOFT_SERVE_SNIFFER_EGG.get())
                .add(DDItems.SNIFFERWURST.get())
                .add(DDItems.COOKED_SNIFFERWURST.get())
                .add(DDItems.SNUFFLEDOG.get())
                .add(DDItems.CHLOROPASTA.get())
        ;
    }

    private void appendEvaporatesInWater() {
        tag(DDTags.ItemT.EVAPORATES_IN_WATER)
                .add(DDItems.COB_N_CANDY.get())
                //INTEGRATION
                .add(AEItems.FLUFFY_FLOSS.get())
        ;
    }

    private void appendCleaverEnchantable() {
        tag(DDTags.ItemT.CLEAVER_ENCHANTABLE)
                .addTag(DDTags.ItemT.CLEAVERS)
        ;
    }

    private void appendDurabilityEnchantable() {
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(DDTags.ItemT.CLEAVERS)
        ;
    }

    private void appendWeaponEnchantable() {
        tag(ItemTags.WEAPON_ENCHANTABLE)
                .addTag(DDTags.ItemT.CLEAVERS)
        ;
    }

    private void appendSharpEnchantable() {
        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .addTag(DDTags.ItemT.CLEAVERS)
        ;
    }

    private void appendMiningEnchantable() {
        tag(ItemTags.MINING_ENCHANTABLE)
                .addTag(DDTags.ItemT.CLEAVERS)
        ;
    }

    private void appendMiningLootEnchantable() {
        tag(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addTag(DDTags.ItemT.CLEAVERS)
        ;
    }

    private void appendStainedEnchantable() {
        tag(DDTags.ItemT.STAINED_ENCHANTABLE)
                .add(DDItems.STAINED_KNIFE.get())
                .add(DDItems.STAINED_CLEAVER.get())
        ;
    }

    private void appendDecoratedPotSherds() {
        tag(ItemTags.DECORATED_POT_SHERDS)
                .add(DDItems.GLUTTONY_POTTERY_SHERD.get())
        ;
    }

    private void appendDecoratedPotIngredients() {
        tag(ItemTags.DECORATED_POT_INGREDIENTS)
                .add(DDItems.GLUTTONY_POTTERY_SHERD.get())
        ;
    }

    private void appendRepairsStainedTools() {
        tag(DDTags.ItemT.REPAIRS_STAINED_TOOLS)
                .add(DDItems.STAINED_SCRAP.get())
        ;
    }

    private void appendFlamingKnives() {
        tag(DDTags.ItemT.FLAMING_KNIVES)
                .add(TFItems.FIERY_KNIFE.get())
                .add(TFItems.FIERY_CLEAVER.get())
        ;
    }

    private void appendBoats() {
        tag(ItemTags.BOATS)
                .add(DDItems.WORMWOOD_BOAT.get())
        ;
    }

    private void appendChestBoats() {
        tag(ItemTags.CHEST_BOATS)
                .add(DDItems.WORMWOOD_CHEST_BOAT.get())
        ;
    }

    private void appendArrows() {
        tag(ItemTags.ARROWS)
                .add(DDItems.GUNK_ARROW.get())
        ;
    }

    private void appendAncientFlora() {
        tag(DDTags.ItemT.ANCIENT_FLORA)
                .add(Items.TORCHFLOWER)
                .add(Items.PITCHER_PLANT)
                .addOptional(RunicLib.customid(IntegrationIds.BOUNTIFULFARES, "hoary_apple"))
                .addOptional(RunicLib.customid(IntegrationIds.BOUNTIFULFARES, "lapisberries"))
                .addOptional(RunicLib.customid(IntegrationIds.EXCESSIVE, "ancient_fruit"))
                .addOptional(RunicLib.customid(IntegrationIds.ANC, "lotus_pistil"))
        ;
    }

    private void appendRubabooIngredients() {
        tag(DDTags.ItemT.RUBABOO_INGREDIENTS)
                .add(Items.GLOW_BERRIES)
                .add(Items.MELON_SLICE)
                .add(Items.SUGAR)
                .add(Items.SWEET_BERRIES)
        ;
    }

    private void appendFleshes() {
        tag(DDTags.ItemT.FLESHES)
                .add(Items.ROTTEN_FLESH)
                .add(DDItems.ROTTEN_TRIPE.get())
                .add(DDItems.GRITTY_FLESH.get())
                .add(DDItems.BRINED_FLESH.get())
                .addOptional(RunicLib.customid(IntegrationIds.CANNIBAL, "fresh_flesh"))
        ;
    }

    private void appendSculkCheese() {
        tag(DDTags.ItemT.SCULK_CHEESE)
                .add(DDItems.WARDENZOLA.get())
                .add(DDItems.WARDENZOLA_CRUMBLES.get())
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
                .addOptional(RunicLib.customid(IntegrationIds.BOUNTIFULFARES, "spongekin_slice"))
        ;
    }

    private void appendAcidics() {
        tag(DDTags.ItemT.ACIDICS)
                .add(DDItems.SPIDER_EXTRACT.get())
                .addOptional(RunicLib.customid(IntegrationIds.BOUNTIFULFARES, "citrus_essence"))
        ;
    }

    private void appendExtracts() {
        tag(DDTags.ItemT.EXTRACTS)
                .add(DDItems.SPIDER_EXTRACT.get())
                .add(DDItems.RANCID_REDUCTION.get())
                .add(DDItems.GHASTLY_SPIRITS.get())
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
                .add(Items.AMETHYST_SHARD)
                .add(DDItems.AMETHYST_ROCK_CANDY.get())
                .add(DDItems.CANDIED_SILVERFISH_SUCKER.get())
                .add(DDItems.CANDIED_VEX_SUCKER.get())
        ;
    }

    private void appendRockCandies() {
        tag(DDTags.ItemT.ROCK_CANDIES)
                .add(DDItems.AMETHYST_ROCK_CANDY.get())
                .add(DDItems.CANDIED_VEX_SUCKER.get())
                .add(DDItems.CANDIED_SILVERFISH_SUCKER.get())
                .add(DDItems.ECHO_ROCK_CANDY.get()) //todo i forgot what this tag is for but maybe remove?????
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

    private void appendFishes() {
        tag(ItemTags.FISHES)
                //INTEGRATION
                .add(FFItems.LUTEFISK.get())
        ;
    }

    private void appendTrimMaterials() {
        tag(ItemTags.TRIM_MATERIALS)
                .add(DDItems.STAINED_SCRAP.get())
        ;
    }

    //--- CONVENTIAL TAGS ---
    private void appendCCleavers() {
        tag(DDTags.ItemT.C_CLEAVERS)
                .addTag(DDTags.ItemT.CLEAVERS)
        ;
    }

    //--- FARMER'S DELIGHT TAGS ---
    private void appendWoodenCabinets() {
        tag(ModTags.WOODEN_CABINETS)
                .add(DDBlocks.WORMWOOD_CABINET.get().asItem())
        ;
    }

    private void appendKnives() {
        tag(ModTags.KNIVES)
                .addTag(DDTags.ItemT.CLEAVERS)
                .add(DDItems.STAINED_KNIFE.get())
                //INTEGRATION
                .add(TFItems.KNIGHTMETAL_KNIFE.get())
                .add(TFItems.STEELEAF_KNIFE.get())
                .add(TFItems.FIERY_KNIFE.get())
                .add(TFItems.IRONWOOD_KNIFE.get())
                .add(AEItems.ZANITE_KNIFE.get())
                .add(AEItems.GRAVITITE_KNIFE.get())
        ;
    }

    //--- FORGE TAGS ---
    private void appendToolsKnives() {
        tag(CommonTags.TOOLS_KNIFE)
                .addTag(DDTags.ItemT.CLEAVERS)
                .add(DDItems.STAINED_KNIFE.get())
        ;
    }

    private void appendMeals() {
        tag(ModTags.MEALS)
                .add(DDItems.SALT_SOAKED_STEW.get())
                .add(DDItems.SPIDER_SALMAGUNDI.get())
                .add(DDItems.POI.get())
                .add(DDItems.GELLED_SALAD.get())
                .add(DDItems.SILVERFISH_FRIED_RICE.get())
                .add(DDItems.AU_ROTTEN_POTATOES.get())
                .add(DDItems.SINIGANG.get())
                .add(DDItems.GHOULASH.get())
                .add(DDItems.GYUDON.get())
                .add(DDItems.TOKAYAKI.get())
                .add(DDItems.POISONOUS_POUTINE.get())
                .add(DDItems.OSSOBUCO.get())
                .add(DDItems.GUARDIAN_ANGEL.get())
                .add(DDItems.SILVERFISH_AND_CHIPS.get())
                .add(DDItems.SHIOKARA.get())
                //TODO //.add(DDItems.COLESLAW.get())
                //TODO //.add(DDItems.GUNPOWDER_BAKED_SPIDER.get())
                //INTEGRATION
                .add(MDItems.SALT_SOAKED_STEW_CUP.get())
                .add(MDItems.SPIDER_SALMAGUNDI_CUP.get())
                .add(MDItems.POI_CUP.get())
                .add(TFItems.SCALY_FIDDLEHEAD_RISOTTO.get())
                .add(TFItems.AURORA_ICE_CREAM.get())
                .add(TFItems.ARCANE_CHILI.get())
                .add(TFItems.HYDRA_FRICASSEE.get())
                .add(AEItems.AMBER_E_OLIO.get())
                .add(AEItems.AMBROSIA_RING.get())
        ;
    }

    private void appendFeasts() {
        tag(ModTags.FEASTS)
                .add(DDItems.OSSOBUCO_BLOCK.get())
                .add(DDItems.GUARDIAN_ANGEL_BLOCK.get())
                .add(DDBlocks.GLOW_BERRY_GELATIN_BLOCK.get().asItem())
                .add(DDItems.SILVERFISH_AND_CHIPS_BLOCK.get())
        ;
    }

    private void appendDrinks() {
        tag(ModTags.DRINKS)
                .add(DDItems.SPIDER_BUBBLE_TEA.get())
                .add(DDItems.BLOODY_MARY.get())
                .add(DDItems.TARO_MILK_TEA.get())
                .add(DDItems.TRIAL_FREAKSHAKE.get())
                //INTEGRATION
                .add(TFItems.LIVEROOT_BEER.get())
                .add(AEItems.SKYBERRY_BREW.get())
        ;
    }
}
