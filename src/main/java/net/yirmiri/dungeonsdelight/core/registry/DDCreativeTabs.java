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
import net.yirmiri.dungeonsdelight.integration.appledog.ADItems;
import net.yirmiri.dungeonsdelight.integration.twilightforest.TFItems;
import net.yirmiri.dungeonsdelight.integration.util.IntegrationIds;

import java.util.function.Supplier;

public class DDCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DungeonsDelight.MOD_ID);

    public static Supplier<CreativeModeTab> DUNGEONSDELIGHT = CREATIVE_MODE_TABS.register("dungeonsdelight_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(DDBlocks.DUNGEON_STOVE.get())).title(Component.translatable("dungeonsdelight_tab")).build());

    public static Supplier<CreativeModeTab> DUNGEONSDELIGHT_COMPAT = CREATIVE_MODE_TABS.register("zdungeonsdelight_compat_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(DDItems.LOGO_ITEM.get())).title(Component.translatable("dungeonsdelight_compat_tab")).build());

    @SubscribeEvent
    public static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == DUNGEONSDELIGHT.get()) {
            //BLOCKS
            event.accept(DDItems.DUNGEON_STOVE.get());
            event.accept(DDBlocks.MONSTER_POT.get());

            event.accept(DDBlocks.POISONOUS_POTATO_CRATE.get());
            event.accept(DDBlocks.ROTTEN_TOMATO_CRATE.get());
            event.accept(DDBlocks.ROTBULB_CRATE.get());
            event.accept(DDBlocks.ROTTEN_SPAWNER.get());

            event.accept(DDBlocks.WORMROOTS.get());
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

            event.accept(DDItems.STAINED_SCRAP.get());
            event.accept(DDItems.STAINED_SCRAP_FRAGMENT.get());
            event.accept(DDItems.STAINED_SCRAP_BLOCK.get());
            event.accept(DDItems.CUT_STAINED_SCRAP.get());
            event.accept(DDItems.CUT_STAINED_SCRAP_STAIRS.get());
            event.accept(DDItems.CUT_STAINED_SCRAP_SLAB.get());
            event.accept(DDItems.STAINED_SCRAP_GRATE.get());
            event.accept(DDItems.STAINED_SCRAP_BARS.get());
            event.accept(DDItems.LIVING_TORCH.get());
            event.accept(DDItems.LIVING_LANTERN.get());
            event.accept(DDItems.STAINED_SCRAP_CHAIN.get());
            event.accept(DDItems.LIVING_CAMPFIRE.get());
            event.accept(DDItems.LIVING_CANDLE.get());

            event.accept(DDBlocks.EMBEDDED_EGGS.get());
            event.accept(DDBlocks.HEAP_OF_ANCIENT_EGGS.get());

            //CLEAVERS
            event.accept(DDItems.FLINT_CLEAVER.get());
            event.accept(DDItems.IRON_CLEAVER.get());
            event.accept(DDItems.DIAMOND_CLEAVER.get());
            event.accept(DDItems.NETHERITE_CLEAVER.get());
            event.accept(DDItems.GOLDEN_CLEAVER.get());
            event.accept(DDItems.STAINED_CLEAVER.get());
            event.accept(DDItems.STAINED_KNIFE.get());

            event.accept(DDBlocks.ROTBULB_PLANT.get());
            event.accept(DDBlocks.ROTBULB_CROP.get());
            event.accept(DDItems.ROTBULB.get());
            event.accept(DDItems.GUNK.get());

            //ITEMS
            event.accept(DDItems.SCULK_POLYP.get());
            event.accept(DDItems.SPIDER_EXTRACT.get());
            event.accept(DDItems.RANCID_REDUCTION.get());
            event.accept(DDItems.GHASTLY_SPIRITS.get());

            //DRINKS
            event.accept(DDItems.BLOODY_MARY.get());
            event.accept(DDItems.SPIDER_BUBBLE_TEA.get());
            event.accept(DDItems.TARO_MILK_TEA.get());

            //BASIC FOODS
            event.accept(DDItems.SNIFFER_SHANK.get());
            event.accept(DDItems.COOKED_SNIFFER_SHANK.get());
            event.accept(DDItems.GRITTY_FLESH.get());
            event.accept(DDItems.BRINED_FLESH.get());
            event.accept(DDItems.ROTTEN_TRIPE.get());
            event.accept(DDItems.SPIDER_MEAT.get());
            event.accept(DDItems.SMOKED_SPIDER_MEAT.get());
            //event.accept(DDItems.CREEPERILLA.get());
            event.accept(DDItems.SLIME_BAR.get());
            event.accept(DDItems.SLIME_NOODLES.get());
            event.accept(DDItems.SILVERFISH_ABDOMEN.get());
            event.accept(DDItems.GHAST_TENTACLE.get());
            event.accept(DDItems.GHAST_CALAMARI.get());
            event.accept(DDItems.FRIED_GHAST_CALAMARI.get());
            event.accept(DDItems.ANCIENT_EGG.get()); //not a food but wtv
            event.accept(DDItems.CLEAVED_ANCIENT_EGG.get());
            event.accept(DDItems.WARDENZOLA.get());
            event.accept(DDItems.WARDENZOLA_CRUMBLES.get());

            //GENERIC FOODS
            event.accept(DDItems.SNIFFERWURST.get());
            event.accept(DDItems.COOKED_SNIFFERWURST.get());
            event.accept(DDItems.SNUFFLEDOG.get());
            event.accept(DDItems.SOFT_SERVE_SNIFFER_EGG.get());
            event.accept(DDItems.CHICKEN_JOCKEY_SANDWICH.get());
            event.accept(DDItems.OMINOUS_OMELETTE.get());
            event.accept(DDItems.TERRINE_LOAF.get());
            event.accept(DDItems.GHAST_ROLL.get());
            event.accept(DDItems.DEVILISH_EGGS.get());
            event.accept(DDItems.SCULK_MAYO.get());
            event.accept(DDBlocks.SCULK_MAYO_BLOCK.get()); //not a food but also wtv
            event.accept(DDItems.SCULK_APPLE.get());
            event.accept(DDItems.BLOATED_BAKED_POTATO.get());
            event.accept(DDItems.MALICIOUS_SANDWICH.get());
            event.accept(DDItems.MONSTER_MUFFIN.get());
            event.accept(DDBlocks.SPIDER_DONUT.get());
            //event.accept(DDItems.MONSTER_BURGER.get());

            //SLICEABLE FOOD
            event.accept(DDItems.SPIDER_PIE.get());
            event.accept(DDBlocks.SCULK_TART.get());
            event.accept(DDItems.MONSTER_CAKE.get());
            event.accept(DDItems.SPIDER_PIE_SLICE.get());
            event.accept(DDItems.SCULK_TART_SLICE.get());
            event.accept(DDItems.MONSTER_CAKE_SLICE.get());

            //STICK FOODS
            event.accept(DDItems.SLICORICE.get()); //doesnt count as one but wanna group near them

            event.accept(DDItems.AMETHYST_ROCK_CANDY.get());
            event.accept(DDItems.CANDIED_SILVERFISH_SUCKER.get());
            event.accept(DDItems.CANDIED_VEX_SUCKER.get());
            event.accept(DDItems.SOAKED_SKEWER.get());
            event.accept(DDItems.SPIDER_TANGHULU.get());
            event.accept(DDItems.COB_N_CANDY.get());

            //BITEABLE FOODS
            event.accept(DDItems.BUBBLEGUNK.get());

            //BOWL FOODS
            event.accept(DDItems.CHLOROPASTA.get());
            event.accept(DDItems.SHIOKARA.get());
            event.accept(DDItems.SALT_SOAKED_STEW.get());
            event.accept(DDItems.GELLED_SALAD.get());
            event.accept(DDItems.SPIDER_SALMAGUNDI.get());
            event.accept(DDItems.SILVERFISH_FRIED_RICE.get());
            event.accept(DDItems.AU_ROTTEN_POTATOES.get());
            event.accept(DDItems.POI.get());
            event.accept(DDItems.SINIGANG.get());

            //PLATED FOODS
            event.accept(DDItems.GYUDON.get());
            event.accept(DDItems.GHOULASH.get());
            event.accept(DDItems.TOKAYAKI.get());
            event.accept(DDItems.POISONOUS_POUTINE.get());

            //FEAST FOODS
            event.accept(DDBlocks.GLOW_BERRY_GELATIN_BLOCK.get());
            event.accept(DDItems.GLOW_BERRY_GELATIN.get());
            event.accept(DDItems.OSSOBUCO_BLOCK.get());
            event.accept(DDItems.OSSOBUCO.get());
            event.accept(DDItems.GUARDIAN_ANGEL_BLOCK.get());
            event.accept(DDItems.GUARDIAN_ANGEL.get());

            //SPAWN EGGS
            event.accept(DDItems.MONSTER_YAM_SPAWN_EGG.get());
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
