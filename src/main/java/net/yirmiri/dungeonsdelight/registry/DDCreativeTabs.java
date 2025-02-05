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
import net.yirmiri.dungeonsdelight.registry.compat.*;
import net.yirmiri.dungeonsdelight.util.DDUtil;

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
            event.accept(DDBlocks.WORMWOOD_CABINET);

            event.accept(DDBlocks.EMBEDDED_EGGS);
            event.accept(DDBlocks.HEAP_OF_ANCIENT_EGGS);

            //ITEMS
            event.accept(DDItems.STAINED_SCRAP);
            event.accept(DDItems.SPIDER_EXTRACT);
            event.accept(DDItems.SCULK_POLYP);
            event.accept(DDItems.ANCIENT_EGG);
            event.accept(DDItems.CLEAVED_ANCIENT_EGG);
            event.accept(DDItems.SCULK_MAYO);

            //BASIC FOODS
            event.accept(DDItems.SPIDER_MEAT);
            event.accept(DDItems.SMOKED_SPIDER_MEAT);
            event.accept(DDItems.SLIME_SLAB);
            event.accept(DDItems.SLIME_NOODLES);
            event.accept(DDItems.SILVERFISH_THORAX);
            event.accept(DDItems.GHAST_TENTACLE);
            event.accept(DDItems.GHAST_CALAMARI);
            event.accept(DDItems.FRIED_GHAST_CALAMARI);

            //GENERIC FOODS
            //event.accept(DDItems.MONSTER_BURGER);

            //STICK FOODS
            event.accept(DDItems.SPIDER_TANGHULU);
            event.accept(DDItems.AMETHYST_ROCK_CANDY);
            event.accept(DDItems.CANDIED_SILVERFISH_SUCKER);
            event.accept(DDItems.CANDIED_VEX_SUCKER);

            //BITEABLE FOODS
            event.accept(DDItems.BUBBLEGUNK);

            //BOWL FOODS
            event.accept(DDItems.SPIDER_EYE_SALMAGUNDI);
            event.accept(DDItems.SILVERFISH_FRIED_RICE);

            //PLATED FOODS
            event.accept(DDItems.GHOULASH);
        }

        if (event.getTab() == DUNGEONSDELIGHT_COMPAT.get()) {
            if (DungeonsDelight.isModLoaded(DDUtil.TF_ID)) {
                //TF KNIVES
                event.accept(DDCTFKnives.IRONWOOD_KNIFE);
                event.accept(DDCTFKnives.STEELEAF_KNIFE);
                event.accept(DDCTFKnives.KNIGHTMETAL_KNIFE);
                event.accept(DDCTFKnives.FIERY_KNIFE);

                //DRINKS
                event.accept(DDCItems.LIVEROOT_BEER);

                //BASIC FOODS
                event.accept(DDCItems.TORCHBERRY_RAISINS);

                //GENERIC FOODS
                event.accept(DDCItems.MEEF_WELLINGTON);

                //BITEABLE FOODS
                event.accept(DDCItems.BRAISED_GLOWWORM_QUEEN);
            }
        }
    }
}
