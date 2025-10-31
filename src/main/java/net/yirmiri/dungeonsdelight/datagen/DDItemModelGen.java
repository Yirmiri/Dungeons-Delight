package net.yirmiri.dungeonsdelight.datagen;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.integration.content.aether.AEItems;
import net.yirmiri.dungeonsdelight.integration.content.alloyed.ALItems;
import net.yirmiri.dungeonsdelight.integration.content.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.content.fishy_fiesta.FFItems;
import net.yirmiri.dungeonsdelight.integration.content.minersdelight.MDItems;
import net.yirmiri.dungeonsdelight.integration.content.twilightforest.TFItems;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

import java.util.Objects;
import java.util.function.Supplier;

public class DDItemModelGen extends ItemModelProvider {
    public DDItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DungeonsDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //DUNGEONS DELIGHT
        genericItem(DDItems.LOGO_ITEM, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SLIME_NOODLES, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SLIME_BAR, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GHOULASH, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.AMETHYST_ROCK_CANDY, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.CANDIED_VEX_SUCKER, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.CANDIED_SILVERFISH_SUCKER, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SILVERFISH_ABDOMEN, DungeonsDelight.MOD_ID);
        genericItem(DDItems.STAINED_SCRAP, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.LIVING_CANDLE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GHAST_CALAMARI, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GHAST_TENTACLE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.FRIED_GHAST_CALAMARI, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SILVERFISH_FRIED_RICE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SPIDER_EXTRACT, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SPIDER_MEAT, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SMOKED_SPIDER_MEAT, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.SPIDER_TANGHULU, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SPIDER_SALMAGUNDI, DungeonsDelight.MOD_ID);
        genericItem(DDItems.MONSTER_BURGER, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.WORMWOOD_DOOR, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.STAINED_SCRAP_DOOR, DungeonsDelight.MOD_ID);
        buttonInventory(DDBlocks.WORMWOOD_BUTTON.get().asItem(), "wormwood_planks");
        blockItem(DDBlocks.WORMROOT_TENDRILS, DungeonsDelight.MOD_ID);
        genericItem(DDItems.BUBBLEGUNK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SCULK_POLYP, DungeonsDelight.MOD_ID);
        genericItem(DDItems.ANCIENT_EGG, DungeonsDelight.MOD_ID);
        genericItem(DDItems.CLEAVED_ANCIENT_EGG, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SCULK_MAYO, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.ECHO_ROCK_CANDY, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GLUTTONY_POTTERY_SHERD, DungeonsDelight.MOD_ID);
//        handheldItem(DDItems.FLINT_CLEAVER, DungeonsDelight.MOD_ID);
//        handheldItem(DDItems.IRON_CLEAVER, DungeonsDelight.MOD_ID);
//        handheldItem(DDItems.GOLDEN_CLEAVER, DungeonsDelight.MOD_ID);
//        handheldItem(DDItems.DIAMOND_CLEAVER, DungeonsDelight.MOD_ID);
//        handheldItem(DDItems.NETHERITE_CLEAVER, DungeonsDelight.MOD_ID);
        genericItem(DDItems.MONSTER_MOUSSE, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.MONSTER_MOUSSE_BLOCK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.ROTTEN_TRIPE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GELLED_SALAD, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.ROTBULB_CROP, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.ROTBULB_PLANT, DungeonsDelight.MOD_ID);
        genericItem(DDItems.ROTBULB, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GUNK, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.STAINED_SCRAP_BARS, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.SLICORICE, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.COB_N_CANDY, DungeonsDelight.MOD_ID);
        genericItem(DDItems.BRINED_FLESH, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GRITTY_FLESH, DungeonsDelight.MOD_ID);
        genericItem(DDItems.DEVILISH_EGGS, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GHAST_ROLL, DungeonsDelight.MOD_ID);
        genericItem(DDItems.TOKAYAKI, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SALT_SOAKED_STEW, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.SOAKED_SKEWER, DungeonsDelight.MOD_ID);
        genericItem(DDItems.POI, DungeonsDelight.MOD_ID);
        genericItem(DDItems.MONSTER_MUFFIN, DungeonsDelight.MOD_ID);
        genericItem(DDItems.RANCID_REDUCTION, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SCULK_TART_SLICE, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.SCULK_TART, DungeonsDelight.MOD_ID);
        genericItem(DDItems.MONSTER_CAKE_SLICE, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.MONSTER_CAKE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.OSSOBUCO, DungeonsDelight.MOD_ID);
        genericItem(DDItems.OSSOBUCO_BLOCK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SPIDER_PIE_SLICE, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.SPIDER_PIE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SCULK_APPLE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SHIOKARA, DungeonsDelight.MOD_ID);
        genericItem(DDItems.BLOODY_MARY, DungeonsDelight.MOD_ID);
        genericItem(DDItems.WARDENZOLA, DungeonsDelight.MOD_ID);
        genericItem(DDItems.WARDENZOLA_CRUMBLES, DungeonsDelight.MOD_ID);
        genericItem(DDItems.MALICIOUS_SANDWICH, DungeonsDelight.MOD_ID);
        genericItem(DDItems.TARO_MILK_TEA, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.SNIFFER_SHANK, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.COOKED_SNIFFER_SHANK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SOFT_SERVE_SNIFFER_EGG, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SNIFFERWURST, DungeonsDelight.MOD_ID);
        genericItem(DDItems.COOKED_SNIFFERWURST, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GYUDON, DungeonsDelight.MOD_ID);
        genericItem(DDItems.TERRINE_LOAF, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GHASTLY_SPIRITS, DungeonsDelight.MOD_ID);
        genericItem(DDItems.CREEPERILLA, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SINIGANG, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.GUNK_ARROW, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SNUFFLEDOG, DungeonsDelight.MOD_ID);
        genericItem(DDItems.CHLOROPASTA, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GUARDIAN_ANGEL, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GUARDIAN_ANGEL_BLOCK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.CHICKEN_JOCKEY_SANDWICH, DungeonsDelight.MOD_ID);
        genericItem(DDItems.POISONOUS_POUTINE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.BLOATED_BAKED_POTATO, DungeonsDelight.MOD_ID);
        genericItem(DDItems.AU_ROTTEN_POTATOES, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SPIDER_BUBBLE_TEA, DungeonsDelight.MOD_ID);
        genericItem(DDItems.LIVING_CAMPFIRE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.LIVING_LANTERN, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.SPIDER_DONUT, DungeonsDelight.MOD_ID);
        genericItem(DDItems.STAINED_SCRAP_FRAGMENT, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.STAINED_KNIFE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SILVERFISH_AND_CHIPS, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SILVERFISH_AND_CHIPS_BLOCK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.CREEPERILLA_SQUIB, DungeonsDelight.MOD_ID);
        genericItem(DDItems.RUBABOO, DungeonsDelight.MOD_ID);
        genericItem(DDItems.WORMWOOD_BOAT, DungeonsDelight.MOD_ID);
        genericItem(DDItems.WORMWOOD_CHEST_BOAT, DungeonsDelight.MOD_ID);
        //TODO //genericItem(DDItems.COLESLAW, DungeonsDelight.MOD_ID);
        //TODO //genericItem(DDItems.GUNPOWDER_BAKED_SPIDER, DungeonsDelight.MOD_ID);
        //TODO //genericItem(DDItems.DYNAMITE_ROLL, DungeonsDelight.MOD_ID);
        genericItem(DDItems.JELLY_BEANS, DungeonsDelight.MOD_ID);
        genericItem(DDItems.WISPY_RICE_BALL, DungeonsDelight.MOD_ID);
        genericItem(DDItems.POLTERGHAST_PIZZA, DungeonsDelight.MOD_ID);
        genericItem(DDItems.POLTERGHAST_PIZZA_SLICE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.BREEZE_CREAM_CONE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.MARSHBELLOW, DungeonsDelight.MOD_ID);
        genericItem(DDItems.TRIAL_FREAKSHAKE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.BOGGED_BRAIN, DungeonsDelight.MOD_ID);
        genericItem(DDItems.CROAK_MONSTER, DungeonsDelight.MOD_ID);
        genericItem(DDItems.HAGGIS, DungeonsDelight.MOD_ID);
        genericItem(DDItems.BRAINS_IN_A_BRICK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.ROTGOURD_SLICE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.PUTRID_SPICE_LATTE, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.ROTPOP, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SPIDER_BISQUE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.ROT_ROAST, DungeonsDelight.MOD_ID);
        //INTEGRATION
        handheldItem(AEItems.GRAVITITE_CLEAVER, IntegrationIds.AETHER);
        handheldItem(AEItems.ZANITE_CLEAVER, IntegrationIds.AETHER);
        handheldItem(AEItems.GRAVITITE_KNIFE, IntegrationIds.AETHER);
        handheldItem(AEItems.ZANITE_KNIFE, IntegrationIds.AETHER);
        genericItem(AEItems.MARBLED_MEAT, IntegrationIds.AETHER);
        genericItem(AEItems.COOKED_MARBLED_MEAT, IntegrationIds.AETHER);
        handheldItem(AEItems.VOLAILLE, IntegrationIds.AETHER);
        genericItem(AEItems.VENOMOUS_ONIGIRI, IntegrationIds.AETHER);
        handheldItem(AEItems.FLUFFY_FLOSS, IntegrationIds.AETHER);
        genericItem(AEItems.AMBER_E_OLIO, IntegrationIds.AETHER);
        genericItem(AEItems.AMBROSIA_RING, IntegrationIds.AETHER);
        genericItem(AEItems.SKYBERRY_BREW, IntegrationIds.AETHER);
        genericItem(ALItems.STEEL_CLEAVER, IntegrationIds.ALLOYED);
        genericItem(TFItems.MAZE_SMORE, IntegrationIds.TWILIGHTFOREST);
        genericItem(MDItems.POI_CUP, IntegrationIds.MINERSDELIGHT);
        genericItem(MDItems.RUBABOO_CUP, IntegrationIds.MINERSDELIGHT);
        genericItem(MDItems.SALT_SOAKED_STEW_CUP, IntegrationIds.MINERSDELIGHT);
        genericItem(MDItems.SPIDER_SALMAGUNDI_CUP, IntegrationIds.MINERSDELIGHT);
        genericItem(FFItems.LUTEFISK, IntegrationIds.FISHY_FIESTA);
        genericItem(ADItems.SCULK_DOGAPPLE, IntegrationIds.APPLEDOG);
        genericItem(ADItems.SCULK_CATBLUEBERRY, IntegrationIds.APPLEDOG);
        genericItem(TFItems.BUG_CHOPS, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.FRIED_BUG_CHOPS, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.LIVEROOT_BEER, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.TORCHBERRY_RAISINS, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.WILDERNESS_LUNCHEON, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.MAZE_ROLL, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.TOWER_BOREITO, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.AURORA_ICE_CREAM, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.MEEF_WELLINGTON, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.BLAZING_BLOOD_SAUSAGE, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.ARCANE_CHILI, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.HYDRA_FRICASSEE, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.TROLLBER_CHUTNEY, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.SWEETBREAD, IntegrationIds.TWILIGHTFOREST);
        genericItem(TFItems.SCALY_FIDDLEHEAD_RISOTTO, IntegrationIds.TWILIGHTFOREST);
        genericItem(ADItems.SCULK_CATBLUEBERRY, IntegrationIds.APPLEDOG);
        handheldItem(TFItems.KNIGHTMETAL_KNIFE, IntegrationIds.TWILIGHTFOREST);
        handheldItem(TFItems.KNIGHTMETAL_CLEAVER, IntegrationIds.TWILIGHTFOREST);
        handheldItem(TFItems.IRONWOOD_KNIFE, IntegrationIds.TWILIGHTFOREST);
        handheldItem(TFItems.IRONWOOD_CLEAVER, IntegrationIds.TWILIGHTFOREST);
        handheldItem(TFItems.STEELEAF_KNIFE, IntegrationIds.TWILIGHTFOREST);
        handheldItem(TFItems.STEELEAF_CLEAVER, IntegrationIds.TWILIGHTFOREST);
        handheldItem(TFItems.FIERY_KNIFE, IntegrationIds.TWILIGHTFOREST);
        handheldItem(TFItems.FIERY_CLEAVER, IntegrationIds.TWILIGHTFOREST);
    }

    private ResourceLocation getKey(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    private String key(Item item) {
        return getKey(item).getPath();
    }

    private ItemModelBuilder genericItem(Supplier<? extends Item> item, String modid) {
        if (Objects.equals(modid, "dungeonsdelight")) {
            return withExistingParent(key(item.get()),
                    RunicLib.customid("minecraft", "item/generated")).texture("layer0",
                    RunicLib.customid(DungeonsDelight.MOD_ID, "item/" + key(item.get())));
        } else {
            return withExistingParent(key(item.get()),
                    RunicLib.customid("minecraft","item/generated")).texture("layer0",
                    RunicLib.customid(DungeonsDelight.MOD_ID, "item/" + modid + "/" + key(item.get())));
        }
    }

    private ItemModelBuilder handheldItem(Supplier<? extends Item> item, String modid) {
        if (Objects.equals(modid, "dungeonsdelight")) {
            return withExistingParent(key(item.get()),
                    RunicLib.customid("minecraft","item/handheld")).texture("layer0",
                    RunicLib.customid(DungeonsDelight.MOD_ID, "item/" + key(item.get())));
        } else {
            return withExistingParent(key(item.get()),
                    RunicLib.customid("minecraft", "item/handheld")).texture("layer0",
                    RunicLib.customid(DungeonsDelight.MOD_ID, "item/" + modid + "/" + key(item.get())));
        }
    }

    private ItemModelBuilder blockItem(Supplier<? extends Block> block, String modid) {
        if (Objects.equals(modid, "dungeonsdelight")) {
            return withExistingParent(key(block.get().asItem()),
                    RunicLib.customid("minecraft","item/generated")).texture("layer0",
                    RunicLib.customid(DungeonsDelight.MOD_ID, "item/" + key(block.get().asItem())));
        } else {
            return withExistingParent(key(block.get().asItem()),
                    RunicLib.customid("minecraft","item/generated")).texture("layer0",
                    RunicLib.customid(DungeonsDelight.MOD_ID, "item/" + modid + "/" + key(block.get().asItem())));
        }
    }

    public void buttonInventory(Item item, String texture) {
        withExistingParent(key(item), BLOCK_FOLDER + "/button_inventory").texture("texture", 
                RunicLib.customid(DungeonsDelight.MOD_ID, ItemModelProvider.BLOCK_FOLDER + "/" + texture));
    }
}
