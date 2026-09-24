package net.yirmiri.dungeonsdelight.core.integration;

import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.integration.farmersdelight.FDItems;
import net.yirmiri.dungeonsdelight.core.integration.nirvana.NVItems;
import net.yirmiri.dungeonsdelight.core.integration.subterrous.STItems;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.function.Supplier;

public class DDIntegrationTabs {
    public static final Supplier<CreativeModeTab> DUNGEONSDELIGHT_INTEGRATION = RLServices.REGISTRY.registerCreativeModeTab(
            DungeonsDelight.MOD_ID, "dungeonsdelight_integration", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.dungeonsdelight_integration"))
                    .icon(() -> new ItemStack(DDItems.LOGO_ITEM.get()))
                    .displayItems((displayParameters, entry) -> {
                        //---FOODS--- (Ingredient -> Drink -> Plated -> Bowled -> Skewed -> Finger -> Banquet)
                        //FARMER'S DELIGHT
                        if (RLServices.PLATFORM.isModLoaded(DDIntegration.FD_ID)) {
                            entry.accept(FDItems.FLINT_CLEAVER.get());
                        }

                        //NIRVANA
                        if (RLServices.PLATFORM.isModLoaded(DDIntegration.NV_ID)) {
                            entry.accept(NVItems.CREEPERS_LETTUCE.get());
                        }

                        //SUBTERROUS
                        if (RLServices.PLATFORM.isModLoaded(DDIntegration.ST_ID)) {
                            entry.accept(STItems.WOLFRAM_CLEAVER.get());
                        }
                    }).build());

    public static void load() {
    }
}
