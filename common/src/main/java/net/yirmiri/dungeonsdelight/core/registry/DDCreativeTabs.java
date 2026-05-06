package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDCreativeTabs {

    public static final Supplier<CreativeModeTab> DUNGEONSDELIGHT = Services.REGISTRY.registerCreativeModeTab(
            DungeonsDelight.MOD_ID, "dungeonsdelight", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.dungeonsdelight"))
                    .icon(() -> new ItemStack(DDItems.LOGO_ITEM.get()))
                    .displayItems((displayParameters, entry) -> {
                        //TOOLS
                        entry.accept(DDItems.FLINT_CLEAVER.get());
                        entry.accept(DDItems.IRON_CLEAVER.get());
                        entry.accept(DDItems.GOLDEN_CLEAVER.get());
                        entry.accept(DDItems.DIAMOND_CLEAVER.get());
                        entry.accept(DDItems.NETHERITE_CLEAVER.get());

                        //MISC
                        entry.accept(DDItems.MUSIC_DISC_MALADY.get());
                        entry.accept(DDItems.MUSIC_DISC_MALADY_B_SIDE.get());

                    }).build());

    public static void load() {
    }
}
