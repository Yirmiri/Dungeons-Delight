package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.integration.content.aether.AEItems;
import net.yirmiri.dungeonsdelight.integration.content.alloyed.ALItems;
import net.yirmiri.dungeonsdelight.integration.content.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.content.fishy_fiesta.FFItems;
import net.yirmiri.dungeonsdelight.integration.content.minersdelight.MDItems;
import net.yirmiri.dungeonsdelight.integration.content.twilightforest.TFItems;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

import java.util.function.Supplier;

public class DDCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DungeonsDelight.MOD_ID);

    public static Supplier<CreativeModeTab> DUNGEONSDELIGHT = CREATIVE_MODE_TABS.register("dungeonsdelight_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(DDBlocks.DUNGEON_STOVE.get())).title(Component.translatable("dungeonsdelight_tab")).build());

    public static Supplier<CreativeModeTab> DUNGEONSDELIGHT_INTEGRATION = CREATIVE_MODE_TABS.register("zdungeonsdelight_compat_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(DDItems.LOGO_ITEM.get())).title(Component.translatable("dungeonsdelight_compat_tab")).build());

    @SubscribeEvent
    public static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == DUNGEONSDELIGHT.get()) {
            //FUNCTIONAL
            event.accept(DDItems.DUNGEON_STOVE.get());
            event.accept(DDBlocks.MONSTER_POT.get());

            //WORMWOOD
            event.accept(DDBlocks.WORMOUTH.get());
            event.accept(DDBlocks.WORMROOT_STALK.get());
            event.accept(DDBlocks.WORMROOT_TENDRILS.get());
            event.accept(DDBlocks.WORMROOTS_BLOCK.get());
            event.accept(DDBlocks.WORMWOOD_PLANKS.get());
            event.accept(DDBlocks.WORMWOOD_MOSAIC.get());
            event.accept(DDBlocks.WORMWOOD_STAIRS.get());
            event.accept(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get());
            event.accept(DDBlocks.WORMWOOD_SLAB.get());
            event.accept(DDBlocks.WORMWOOD_MOSAIC_SLAB.get());
            event.accept(DDBlocks.WORMWOOD_DOOR.get());
            event.accept(DDBlocks.WORMWOOD_TRAPDOOR.get());
            event.accept(DDBlocks.WORMWOOD_FENCE.get());
            event.accept(DDBlocks.WORMWOOD_FENCE_GATE.get());
            event.accept(DDBlocks.WORMWOOD_BUTTON.get());
            event.accept(DDBlocks.WORMWOOD_PRESSURE_PLATE.get());
            event.accept(DDBlocks.WORMWOOD_CABINET.get());
            event.accept(DDItems.WORMWOOD_BOAT.get());
            event.accept(DDItems.WORMWOOD_CHEST_BOAT.get());

            //STAINED SCRAP
            event.accept(DDItems.STAINED_SCRAP.get());
            event.accept(DDItems.STAINED_SCRAP_FRAGMENT.get());
            event.accept(DDItems.STAINED_SCRAP_BLOCK.get());
            event.accept(DDItems.CHISELED_STAINED_SCRAP.get());
            event.accept(DDItems.STAINED_SCRAP_PILLAR.get());
            event.accept(DDItems.STAINED_SCRAP_GRATE.get());
            event.accept(DDItems.CUT_STAINED_SCRAP.get());
            event.accept(DDItems.CUT_STAINED_SCRAP_STAIRS.get());
            event.accept(DDItems.CUT_STAINED_SCRAP_SLAB.get());
            event.accept(DDItems.STAINED_SCRAP_DOOR.get());
            event.accept(DDItems.STAINED_SCRAP_TRAPDOOR.get());
            event.accept(DDItems.STAINED_SCRAP_BARS.get());
            event.accept(DDItems.STAINED_LANTERN.get());
            event.accept(DDItems.LIVING_TORCH.get());
            event.accept(DDItems.LIVING_LANTERN.get());
            event.accept(DDItems.LIVING_CAMPFIRE.get());
            event.accept(DDItems.LIVING_CANDLE.get());

            //CROPS
            event.accept(DDBlocks.ROTBULB_PLANT.get());
            event.accept(DDBlocks.ROTBULB_CROP.get());
            event.accept(DDItems.ROTBULB.get());
            event.accept(DDItems.GUNK.get());
            event.accept(DDItems.GUNK_ARROW.get());

            event.accept(DDBlocks.ROTGOURD.get());
            event.accept(DDBlocks.CARVED_ROTGOURD.get());
            event.accept(DDBlocks.LIVING_JACK_O_LANTERN.get());
            event.accept(DDItems.ROTGOURD_SLICE.get());

            event.accept(DDBlocks.EMBEDDED_EGGS.get());
            event.accept(DDBlocks.HEAP_OF_ANCIENT_EGGS.get());
            event.accept(DDItems.ANCIENT_EGG.get());
            event.accept(DDItems.SCULK_POLYP.get());

            //MISC BLOCKS
            event.accept(DDBlocks.POISONOUS_POTATO_CRATE.get());
            event.accept(DDBlocks.ROTTEN_TOMATO_CRATE.get());
            event.accept(DDBlocks.ROTBULB_CRATE.get());
            event.accept(DDBlocks.ROTTEN_SPAWNER.get());
            event.accept(DDItems.GLUTTONY_POTTERY_SHERD.get());

            //CLEAVERS
            event.accept(DDItems.FLINT_CLEAVER.get());
            event.accept(DDItems.IRON_CLEAVER.get());
            event.accept(DDItems.DIAMOND_CLEAVER.get());
            event.accept(DDItems.NETHERITE_CLEAVER.get());
            event.accept(DDItems.GOLDEN_CLEAVER.get());

            //EXTRACTS
            event.accept(DDItems.SPIDER_EXTRACT.get());
            event.accept(DDItems.RANCID_REDUCTION.get());
            event.accept(DDItems.GHASTLY_SPIRITS.get());

            //---FOODS--- (Ingredient -> Finger -> Skewed -> Bowled -> Plated -> Placed -> Feast -> Drink)

            //ZOMBIE
            event.accept(DDItems.ROTTEN_TRIPE.get());

            event.accept(DDItems.MALICIOUS_SANDWICH.get());
            event.accept(DDItems.CHICKEN_JOCKEY_SANDWICH.get());

            event.accept(DDItems.OSSOBUCO_BLOCK.get());
            event.accept(DDItems.OSSOBUCO.get());

            //HUSK
            event.accept(DDItems.GRITTY_FLESH.get());

            event.accept(DDItems.RUBABOO.get());

            event.accept(DDItems.GYUDON.get());

            //DROWNED
            event.accept(DDItems.BRINED_FLESH.get());

            event.accept(DDItems.TERRINE_LOAF.get());

            event.accept(DDItems.SOAKED_SKEWER.get());

            event.accept(DDItems.SALT_SOAKED_STEW.get());

            //SPIDER
            event.accept(DDItems.SPIDER_MEAT.get());
            event.accept(DDItems.SMOKED_SPIDER_MEAT.get());

            event.accept(DDItems.SPIDER_TANGHULU.get());
            event.accept(DDItems.COB_N_CANDY.get());

            event.accept(DDItems.SPIDER_SALMAGUNDI.get());
            event.accept(DDItems.SPIDER_BISQUE.get());

            event.accept(DDItems.SPIDER_PIE.get());
            event.accept(DDItems.SPIDER_PIE_SLICE.get());
            event.accept(DDBlocks.SPIDER_DONUT.get());

            event.accept(DDItems.SPIDER_BUBBLE_TEA.get());

            //SILVERFISH
            event.accept(DDItems.SILVERFISH_ABDOMEN.get());

            event.accept(DDItems.SILVERFISH_FRIED_RICE.get());

            event.accept(DDItems.SILVERFISH_AND_CHIPS_BLOCK.get());
            event.accept(DDItems.SILVERFISH_AND_CHIPS.get());

            event.accept(DDItems.BLOODY_MARY.get());

            //SLIME
            event.accept(DDItems.SLIME_BAR.get());
            event.accept(DDItems.SLIME_NOODLES.get());

            event.accept(DDItems.GELLED_SALAD.get());

            event.accept(DDItems.GHOULASH.get());

            event.accept(DDItems.GUARDIAN_ANGEL_BLOCK.get());
            event.accept(DDItems.GUARDIAN_ANGEL.get());

            //BOGGED
            event.accept(DDItems.BOGGED_BRAIN.get());

            event.accept(DDItems.CROAK_MONSTER.get());
            event.accept(DDItems.HAGGIS.get());

            event.accept(DDItems.BRAINS_IN_A_BRICK.get()); //both bowl and plate (should be between them)

            //BREEZE
            event.accept(DDItems.WISPY_RICE_BALL.get());
            event.accept(DDItems.BREEZE_CREAM_CONE.get());
            event.accept(DDItems.MARSHBELLOW.get());
            event.accept(DDItems.JELLY_BEANS.get());

            event.accept(DDItems.TRIAL_FREAKSHAKE.get());

            //GHAST
            event.accept(DDItems.GHAST_TENTACLE.get());
            event.accept(DDItems.GHAST_CALAMARI.get());
            event.accept(DDItems.FRIED_GHAST_CALAMARI.get());

            event.accept(DDItems.GHAST_ROLL.get());

            event.accept(DDItems.SHIOKARA.get());

            event.accept(DDItems.TOKAYAKI.get());

            event.accept(DDItems.POLTERGHAST_PIZZA.get());
            event.accept(DDItems.POLTERGHAST_PIZZA_SLICE.get());

            //ROTTEN
            event.accept(DDItems.MONSTER_MUFFIN.get());

            event.accept(DDItems.POI.get());
            event.accept(DDItems.SINIGANG.get());

            event.accept(DDItems.MONSTER_CAKE.get());
            event.accept(DDItems.MONSTER_CAKE_SLICE.get());

            event.accept(DDItems.TARO_MILK_TEA.get());

            event.accept(DDBlocks.MONSTER_MOUSSE_BLOCK.get());
            event.accept(DDItems.MONSTER_MOUSSE.get());

            //PUTRID
            event.accept(DDItems.ROTPOP.get());

            event.accept(DDItems.ROT_ROAST.get());

            event.accept(DDItems.PUTRID_SPICE_LATTE.get());

            //SCULK
            event.accept(DDItems.CLEAVED_ANCIENT_EGG.get());
            event.accept(DDItems.DEVILISH_EGGS.get());
            event.accept(DDItems.SCULK_MAYO.get());
            event.accept(DDBlocks.SCULK_MAYO_BLOCK.get());
            event.accept(DDItems.WARDENZOLA.get());
            event.accept(DDItems.WARDENZOLA_CRUMBLES.get());

            event.accept(DDItems.SCULK_APPLE.get());
            event.accept(DDItems.BLOATED_BAKED_POTATO.get());

            event.accept(DDItems.ECHO_ROCK_CANDY.get());

            event.accept(DDItems.AU_ROTTEN_POTATOES.get());

            event.accept(DDItems.POISONOUS_POUTINE.get());

            event.accept(DDBlocks.SCULK_TART.get());
            event.accept(DDItems.SCULK_TART_SLICE.get());

            //SNIFFER
            event.accept(DDItems.SNIFFER_SHANK.get());
            event.accept(DDItems.COOKED_SNIFFER_SHANK.get());

            event.accept(DDItems.SNIFFERWURST.get());
            event.accept(DDItems.COOKED_SNIFFERWURST.get());
            event.accept(DDItems.SNUFFLEDOG.get());
            event.accept(DDItems.SOFT_SERVE_SNIFFER_EGG.get());

            event.accept(DDItems.CHLOROPASTA.get());

            //MISC
            event.accept(DDItems.BUBBLEGUNK.get());
            event.accept(DDItems.SLICORICE.get());

            event.accept(DDItems.AMETHYST_ROCK_CANDY.get());
            event.accept(DDItems.CANDIED_SILVERFISH_SUCKER.get()); //silverfish food but i'd rather group with rest of rock candy
            event.accept(DDItems.CANDIED_VEX_SUCKER.get());

            //SPAWN EGGS
            event.accept(DDItems.MONSTER_YAM_SPAWN_EGG.get());
            event.accept(DDItems.ZOMBIFIED_DRYAD_SPAWN_EGG.get());
        }

        if (event.getTab() == DUNGEONSDELIGHT_INTEGRATION.get()) { //INTEGRATION MODS SORTED ALPHABETICALLY //TODO REORGANIZE LIKE 1.4
            if (Services.PLATFORM.isModLoaded(IntegrationIds.AETHER)) {
                //ITEMS
                event.accept(AEItems.ZANITE_KNIFE.get());
                event.accept(AEItems.ZANITE_CLEAVER.get());
                event.accept(AEItems.GRAVITITE_KNIFE.get());
                event.accept(AEItems.GRAVITITE_CLEAVER.get());

                //DRINKS
                event.accept(AEItems.SKYBERRY_BREW.get());

                //BASIC FOODS
                event.accept(AEItems.MARBLED_MEAT.get());
                event.accept(AEItems.COOKED_MARBLED_MEAT.get());
                event.accept(AEItems.VOLAILLE.get());

                //BITEABLE FOODS
                event.accept(AEItems.FLUFFY_FLOSS.get());

                //GENERIC FOODS
                event.accept(AEItems.VENOMOUS_ONIGIRI.get());

                //PLATED FOODS
                event.accept(AEItems.AMBER_E_OLIO.get());
                event.accept(AEItems.AMBROSIA_RING.get());
            }

            if (Services.PLATFORM.isModLoaded(IntegrationIds.ALLOYED)) {
                //ITEMS
                event.accept(ALItems.STEEL_CLEAVER.get());
            }

            if (Services.PLATFORM.isModLoaded(IntegrationIds.APPLEDOG)) {
                //ITEMS
                event.accept(ADItems.SCULK_DOGAPPLE.get());
                event.accept(ADItems.SCULK_CATBLUEBERRY.get());
            }

            if (Services.PLATFORM.isModLoaded(IntegrationIds.FISHY_FIESTA)) {
                //ITEMS
                event.accept(FFItems.LUTEFISK.get());
            }

            if (Services.PLATFORM.isModLoaded(IntegrationIds.MINERSDELIGHT)) {
                //ITEMS
                event.accept(MDItems.RUBABOO_CUP.get());
                event.accept(MDItems.SALT_SOAKED_STEW_CUP.get());
                event.accept(MDItems.SPIDER_SALMAGUNDI_CUP.get());
                event.accept(MDItems.POI_CUP.get());
            }

            if (Services.PLATFORM.isModLoaded(IntegrationIds.TWILIGHTFOREST)) {
                //ITEMS
                event.accept(TFItems.IRONWOOD_KNIFE.get());
                event.accept(TFItems.IRONWOOD_CLEAVER.get());
                event.accept(TFItems.KNIGHTMETAL_KNIFE.get());
                event.accept(TFItems.KNIGHTMETAL_CLEAVER.get());
                event.accept(TFItems.STEELEAF_KNIFE.get());
                event.accept(TFItems.STEELEAF_CLEAVER.get());
                event.accept(TFItems.FIERY_KNIFE.get());
                event.accept(TFItems.FIERY_CLEAVER.get());

                //DRINKS
                event.accept(TFItems.LIVEROOT_BEER.get());
                event.accept(TFItems.TROLLBER_CHUTNEY.get());

                //BASIC FOODS
                event.accept(TFItems.BUG_CHOPS.get());
                event.accept(TFItems.FRIED_BUG_CHOPS.get());
                event.accept(TFItems.TORCHBERRY_RAISINS.get());

                //BITEABLE FOODS
                event.accept(TFItems.ARCANE_CHILI.get());

                //GENERIC FOODS
                event.accept(TFItems.WILDERNESS_LUNCHEON.get());
                event.accept(TFItems.MEEF_WELLINGTON.get());
                event.accept(TFItems.MAZE_ROLL.get());
                event.accept(TFItems.MAZE_SMORE.get());
                event.accept(TFItems.SWEETBREAD.get());
                event.accept(TFItems.TOWER_BOREITO.get());
                event.accept(TFItems.BLAZING_BLOOD_SAUSAGE.get());

                //BOWL FOODS
                event.accept(TFItems.AURORA_ICE_CREAM.get());

                //PLATED FOODS
                event.accept(TFItems.SCALY_FIDDLEHEAD_RISOTTO.get());
                event.accept(TFItems.HYDRA_FRICASSEE.get());
            }
        }
    }
}
