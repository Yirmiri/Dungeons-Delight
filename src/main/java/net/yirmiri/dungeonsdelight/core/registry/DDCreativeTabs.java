package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.integration.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.twilightforest.TFItems;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

public class DDCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DungeonsDelight.MOD_ID);

    public static RegistryObject<CreativeModeTab> DUNGEONSDELIGHT = CREATIVE_MODE_TABS.register("dungeonsdelight_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(DDBlocks.DUNGEON_STOVE.get())).title(Component.translatable("dungeonsdelight_tab")).build());

    public static RegistryObject<CreativeModeTab> DUNGEONSDELIGHT_COMPAT = CREATIVE_MODE_TABS.register("zdungeonsdelight_compat_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(DDItems.LOGO_ITEM.get())).title(Component.translatable("dungeonsdelight_compat_tab")).build());

    @SubscribeEvent
    public static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == DUNGEONSDELIGHT.get()) {
            //BLOCKS
            event.accept(DDItems.DUNGEON_STOVE);
            event.accept(DDBlocks.MONSTER_POT);

            event.accept(DDBlocks.POISONOUS_POTATO_CRATE);
            event.accept(DDBlocks.ROTTEN_TOMATO_CRATE);
            event.accept(DDBlocks.ROTBULB_CRATE);
            event.accept(DDBlocks.ROTTEN_SPAWNER);

            event.accept(DDBlocks.WORMROOTS);
            event.accept(DDBlocks.WORMROOTS_BLOCK);
            event.accept(DDBlocks.WORMWOOD_PLANKS);
            event.accept(DDBlocks.WORMWOOD_MOSAIC);
            event.accept(DDBlocks.WORMWOOD_STAIRS);
            event.accept(DDBlocks.WORMWOOD_MOSAIC_STAIRS);
            event.accept(DDBlocks.WORMWOOD_SLAB);
            event.accept(DDBlocks.WORMWOOD_MOSAIC_SLAB);
            event.accept(DDBlocks.WORMWOOD_DOOR);
            event.accept(DDBlocks.WORMWOOD_TRAPDOOR);
            event.accept(DDBlocks.WORMWOOD_FENCE);
            event.accept(DDBlocks.WORMWOOD_FENCE_GATE);
            event.accept(DDBlocks.WORMWOOD_BUTTON);
            event.accept(DDBlocks.WORMWOOD_PRESSURE_PLATE);
            event.accept(DDBlocks.WORMWOOD_CABINET);

            event.accept(DDItems.STAINED_SCRAP);
            event.accept(DDItems.STAINED_SCRAP_FRAGMENT);
            event.accept(DDItems.STAINED_SCRAP_BLOCK);
            event.accept(DDItems.CUT_STAINED_SCRAP);
            event.accept(DDItems.CUT_STAINED_SCRAP_STAIRS);
            event.accept(DDItems.CUT_STAINED_SCRAP_SLAB);
            event.accept(DDItems.STAINED_SCRAP_GRATE);
            event.accept(DDItems.STAINED_SCRAP_BARS);
            event.accept(DDItems.LIVING_TORCH);
            event.accept(DDItems.LIVING_LANTERN);
            event.accept(DDItems.STAINED_SCRAP_CHAIN);
            event.accept(DDItems.LIVING_CAMPFIRE);
            event.accept(DDItems.LIVING_CANDLE);

            event.accept(DDBlocks.EMBEDDED_EGGS);
            event.accept(DDBlocks.HEAP_OF_ANCIENT_EGGS);

            //CLEAVERS
            event.accept(DDItems.FLINT_CLEAVER);
            event.accept(DDItems.IRON_CLEAVER);
            event.accept(DDItems.DIAMOND_CLEAVER);
            event.accept(DDItems.NETHERITE_CLEAVER);
            event.accept(DDItems.STAINED_KNIFE);
            event.accept(DDItems.STAINED_CLEAVER);
            event.accept(DDItems.GOLDEN_CLEAVER);

            event.accept(DDBlocks.ROTBULB_PLANT);
            event.accept(DDBlocks.ROTBULB_CROP);
            event.accept(DDItems.ROTBULB);
            event.accept(DDItems.GUNK);

            //ITEMS
            event.accept(DDItems.SCULK_POLYP);
            event.accept(DDItems.SPIDER_EXTRACT);
            event.accept(DDItems.RANCID_REDUCTION);
            event.accept(DDItems.GHASTLY_SPIRITS);

            //DRINKS
            event.accept(DDItems.BLOODY_MARY);
            event.accept(DDItems.SPIDER_BUBBLE_TEA);
            event.accept(DDItems.TARO_MILK_TEA);

            //BASIC FOODS
            event.accept(DDItems.SNIFFER_SHANK);
            event.accept(DDItems.COOKED_SNIFFER_SHANK);
            event.accept(DDItems.GRITTY_FLESH);
            event.accept(DDItems.BRINED_FLESH);
            event.accept(DDItems.ROTTEN_TRIPE);
            event.accept(DDItems.SPIDER_MEAT);
            event.accept(DDItems.SMOKED_SPIDER_MEAT);
            //event.accept(DDItems.CREEPERILLA);
            event.accept(DDItems.SLIME_BAR);
            event.accept(DDItems.SLIME_NOODLES);
            event.accept(DDItems.SILVERFISH_ABDOMEN);
            event.accept(DDItems.GHAST_TENTACLE);
            event.accept(DDItems.GHAST_CALAMARI);
            event.accept(DDItems.FRIED_GHAST_CALAMARI);
            event.accept(DDItems.ANCIENT_EGG); //not a food but wtv
            event.accept(DDItems.CLEAVED_ANCIENT_EGG);
            event.accept(DDItems.WARDENZOLA);
            event.accept(DDItems.WARDENZOLA_CRUMBLES);

            //GENERIC FOODS
            event.accept(DDItems.SNIFFERWURST);
            event.accept(DDItems.COOKED_SNIFFERWURST);
            event.accept(DDItems.SNUFFLEDOG);
            event.accept(DDItems.SOFT_SERVE_SNIFFER_EGG);
            event.accept(DDItems.CHICKEN_JOCKEY_SANDWICH);
            event.accept(DDItems.OMINOUS_OMELETTE);
            event.accept(DDItems.TERRINE_LOAF);
            event.accept(DDItems.GHAST_ROLL);
            event.accept(DDItems.DEVILISH_EGGS);
            event.accept(DDItems.SCULK_MAYO);
            event.accept(DDBlocks.SCULK_MAYO_BLOCK); //not a food but also wtv
            event.accept(DDItems.SCULK_APPLE);
            event.accept(DDItems.BLOATED_BAKED_POTATO);
            event.accept(DDItems.MALICIOUS_SANDWICH);
            event.accept(DDItems.MONSTER_MUFFIN);
            event.accept(DDBlocks.SPIDER_DONUT);
            //event.accept(DDItems.MONSTER_BURGER);

            //SLICEABLE FOOD
            event.accept(DDItems.SPIDER_PIE);
            event.accept(DDBlocks.SCULK_TART);
            event.accept(DDItems.MONSTER_CAKE);
            event.accept(DDItems.SPIDER_PIE_SLICE);
            event.accept(DDItems.SCULK_TART_SLICE);
            event.accept(DDItems.MONSTER_CAKE_SLICE);

            //STICK FOODS
            event.accept(DDItems.SLICORICE); //doesnt count as one but wanna group near them

            event.accept(DDItems.AMETHYST_ROCK_CANDY);
            event.accept(DDItems.CANDIED_SILVERFISH_SUCKER);
            event.accept(DDItems.CANDIED_VEX_SUCKER);
            event.accept(DDItems.SOAKED_SKEWER);
            event.accept(DDItems.SPIDER_TANGHULU);
            event.accept(DDItems.COB_N_CANDY);

            //BITEABLE FOODS
            event.accept(DDItems.BUBBLEGUNK);

            //BOWL FOODS
            event.accept(DDItems.CHLOROPASTA);
            event.accept(DDItems.SHIOKARA);
            event.accept(DDItems.SALT_SOAKED_STEW);
            event.accept(DDItems.GELLED_SALAD);
            event.accept(DDItems.SPIDER_SALMAGUNDI);
            event.accept(DDItems.SILVERFISH_FRIED_RICE);
            event.accept(DDItems.AU_ROTTEN_POTATOES);
            event.accept(DDItems.POI);
            event.accept(DDItems.SINIGANG);

            //PLATED FOODS
            event.accept(DDItems.GYUDON);
            event.accept(DDItems.GHOULASH);
            event.accept(DDItems.TOKAYAKI);
            event.accept(DDItems.POISONOUS_POUTINE);

            //FEAST FOODS
            event.accept(DDBlocks.GLOW_BERRY_GELATIN_BLOCK);
            event.accept(DDItems.GLOW_BERRY_GELATIN);
            event.accept(DDItems.OSSOBUSCO_BLOCK);
            event.accept(DDItems.OSSOBUSCO);
            event.accept(DDItems.GUARDIAN_ANGEL_BLOCK);
            event.accept(DDItems.GUARDIAN_ANGEL);

            //SPAWN EGGS
            event.accept(DDItems.MONSTER_YAM_SPAWN_EGG);
        }

        if (event.getTab() == DUNGEONSDELIGHT_COMPAT.get()) {
            if (Services.PLATFORM.isModLoaded(IntegrationIds.APPLEDOG)) {
                //ITEMS
                event.accept(ADItems.SCULK_DOGAPPLE.get());
                event.accept(ADItems.SCULK_CATBLUEBERRY.get());
            }

            if (Services.PLATFORM.isModLoaded(IntegrationIds.TWILIGHTFOREST)) {
                //DRINKS
                event.accept(TFItems.LIVEROOT_BEER.get());
                event.accept(TFItems.TROLLBER_CHUTNEY.get());

                //BASIC FOODS
                event.accept(TFItems.BUG_CHOPS.get());
                event.accept(TFItems.FRIED_BUG_CHOPS.get());
                event.accept(TFItems.TORCHBERRY_RAISINS.get());

                //GENERIC FOODS
                event.accept(TFItems.WILDERNESS_LUNCHEON.get());
                event.accept(TFItems.MEEF_WELLINGTON.get());
                event.accept(TFItems.MAZE_ROLL.get());
                event.accept(TFItems.SWEETBREAD.get());
                event.accept(TFItems.TOWER_BOREITO.get());
                event.accept(TFItems.BLAZING_BLOOD_SAUSAGE.get());

                //STICK FOODS

                //BITEABLE FOODS
                event.accept(TFItems.ARCANE_CHILI.get());

                //BOWL FOODS
                event.accept(TFItems.AURORA_ICE_CREAM.get());

                //PLATED FOODS
                event.accept(TFItems.SCALY_FIDDLEHEAD_RISOTTO.get());
                event.accept(TFItems.HYDRA_FRICASSEE.get());

                //FEAST FOODS
            }
        }
    }
}
