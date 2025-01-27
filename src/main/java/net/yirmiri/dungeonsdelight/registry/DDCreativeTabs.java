package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DungeonsDelight.MOD_ID);

    public static RegistryObject<CreativeModeTab> DUNGEONSDELIGHT = CREATIVE_MODE_TABS.register("dungeonsdelight_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(DDBlocks.DUNGEON_STOVE.get())).title(Component.translatable("dungeonsdelight_tab")).build());

    @SubscribeEvent
    public static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == DUNGEONSDELIGHT.get()) {
            //MISC
            event.accept(DDItems.LOGO_ITEM);

            //BLOCKS
            event.accept(DDBlocks.DUNGEON_STOVE);
            event.accept(DDBlocks.MONSTER_POT);

            //FOODS
            event.accept(DDItems.SLIME_SLAB);
            event.accept(DDItems.SLIME_NOODLES);
            event.accept(DDItems.SILVERFISH_THORAX);

            event.accept(DDItems.AMETHYST_ROCK_CANDY);
            event.accept(DDItems.CANDIED_SILVERFISH_SUCKER);
            event.accept(DDItems.CANDIED_VEX_SUCKER);

            event.accept(DDItems.GHOULASH);
        }
    }
}
