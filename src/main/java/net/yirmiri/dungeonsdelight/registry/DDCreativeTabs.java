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
            //BLOCKS
            event.accept(DDBlocks.DUNGEON_STOVE);
            event.accept(DDBlocks.MONSTER_POT);

            event.accept(DDBlocks.WORMROOTS);
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

            //ITEMS
            event.accept(DDItems.STAINED_SCRAP);
            event.accept(DDItems.SPIDER_EXTRACT);

            //FOODS
            event.accept(DDItems.SPIDER_MEAT);
            event.accept(DDItems.SMOKED_SPIDER_MEAT);
            event.accept(DDItems.SLIME_SLAB);
            event.accept(DDItems.SLIME_NOODLES);
            event.accept(DDItems.SILVERFISH_THORAX);
            event.accept(DDItems.GHAST_TENTACLE);
            event.accept(DDItems.GHAST_CALAMARI);
            event.accept(DDItems.FRIED_GHAST_CALAMARI);

            event.accept(DDItems.SPIDER_TANGHULU);
            event.accept(DDItems.AMETHYST_ROCK_CANDY);
            event.accept(DDItems.CANDIED_SILVERFISH_SUCKER);
            event.accept(DDItems.CANDIED_VEX_SUCKER);

            event.accept(DDItems.SPIDER_EYE_SALMAGUNDI);
            event.accept(DDItems.GHOULASH);
            event.accept(DDItems.SILVERFISH_FRIED_RICE);
            //event.accept(DDItems.MONSTER_BURGER);

            //MISC
            event.accept(DDItems.LOGO_ITEM);
        }
    }
}
