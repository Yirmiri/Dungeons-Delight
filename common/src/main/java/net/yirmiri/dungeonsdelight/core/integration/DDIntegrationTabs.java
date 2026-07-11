package net.yirmiri.dungeonsdelight.core.integration;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.integration.nirvana.NVItems;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.function.Supplier;

public class DDIntegrationTabs { //todo make icon change based on context (whichever compat mod is loaded)
    public static final Supplier<CreativeModeTab> DUNGEONSDELIGHT_INTEGRATION = Services.REGISTRY.registerCreativeModeTab(
            DungeonsDelight.MOD_ID, "dungeonsdelight_integration", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.dungeonsdelight_integration"))
                    .icon(() -> new ItemStack(DDItems.LOGO_ITEM.get()))
                    .displayItems((displayParameters, entry) -> {
                        //---FOODS--- (Ingredient -> Drink -> Plated -> Bowled -> Skewed -> Finger -> Banquet)

                        //NIRVANA
                        if (Services.PLATFORM.isModLoaded(DDIntegration.NV_ID)) {
                            entry.accept(NVItems.HEMP_WRAPPED_BLUNT.get());
                        }

                    }).build());

    public static void load() {
    }
}
