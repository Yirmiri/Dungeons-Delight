package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDCreativeTabs {
    public static final Supplier<CreativeModeTab> DUNGEONSDELIGHT = RLServices.REGISTRY.registerCreativeModeTab(
            DungeonsDelight.MOD_ID, "dungeonsdelight", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.dungeonsdelight"))
                    .icon(() -> new ItemStack(DDBlocks.MONSTER_POT.get()))
                    .displayItems((displayParameters, entry) -> {
                        //FUNCTIONAL
                        entry.accept(DDBlocks.MONSTER_POT.get());

                        //TOOLS
                        entry.accept(DDItems.FLINT_CLEAVER.get());
                        entry.accept(DDItems.IRON_CLEAVER.get());
                        entry.accept(DDItems.GOLDEN_CLEAVER.get());
                        entry.accept(DDItems.DIAMOND_CLEAVER.get());
                        entry.accept(DDItems.NETHERITE_CLEAVER.get());

                        //INGREDIENT FOODS
                        entry.accept(DDItems.ROTTEN_TRIPE.get());
                        entry.accept(DDItems.SPIDER_MEAT.get());
                        entry.accept(DDItems.COOKED_SPIDER_MEAT.get());
                        entry.accept(DDItems.CREEPERILLA.get());
                        entry.accept(DDItems.SLIME_NOODLES.get());
                        entry.accept(DDItems.SILVERFISH_ABDOMEN.get());
                        entry.accept(DDItems.SNIFFER_SHANK.get());
                        entry.accept(DDItems.COOKED_SNIFFER_SHANK.get());
                        entry.accept(DDItems.GHAST_TENTACLE.get());

                        //CROPS
                        entry.accept(DDBlocks.TERROR_PRETA.get());

                        entry.accept(DDItems.ENDELVE.get());
                        entry.accept(DDItems.MANALLIUM.get());
                        entry.accept(DDItems.BLEET.get());
                        entry.accept(DDItems.BLEET_SEEDS.get());

                        //STAINED SCRAP
                        entry.accept(DDItems.STAINED_SCRAP.get());
                        entry.accept(DDItems.STAINED_SCRAP_FRAGMENT.get());
                        entry.accept(DDBlocks.STAINED_SCRAP_BLOCK.get());
                        entry.accept(DDBlocks.CHISELED_STAINED_SCRAP.get());
                        entry.accept(DDBlocks.STAINED_SCRAP_PILLAR.get());
                        entry.accept(DDBlocks.STAINED_SCRAP_GRATE.get());
                        entry.accept(DDBlocks.CUT_STAINED_SCRAP.get());
                        entry.accept(DDBlocks.CUT_STAINED_SCRAP_STAIRS.get());
                        entry.accept(DDBlocks.CUT_STAINED_SCRAP_SLAB.get());
                        entry.accept(DDBlocks.STAINED_SCRAP_DOOR.get());
                        entry.accept(DDBlocks.STAINED_SCRAP_TRAPDOOR.get());
                        entry.accept(DDBlocks.STAINED_SCRAP_BARS.get());
                        //entry.accept(DDBlocks.STAINED_LANTERN.get());
                        //entry.accept(DDBlocks.LIVING_TORCH.get());
                        //entry.accept(DDBlocks.LIVING_LANTERN.get());
                        //entry.accept(DDBlocks.LIVING_CAMPFIRE.get());
                        //entry.accept(DDBlocks.LIVING_CANDLE.get());

                        //WORMWOOD
                        entry.accept(DDBlocks.WORMOUTH.get());
                        entry.accept(DDBlocks.WORMROOT_STALK.get());
                        entry.accept(DDBlocks.WORMROOT_TENDRILS.get());
                        entry.accept(DDBlocks.WORMROOTS_BLOCK.get());
                        entry.accept(DDBlocks.WORMWOOD_PLANKS.get());
                        entry.accept(DDBlocks.WORMWOOD_MOSAIC.get());
                        entry.accept(DDBlocks.WORMWOOD_STAIRS.get());
                        entry.accept(DDBlocks.WORMWOOD_MOSAIC_STAIRS.get());
                        entry.accept(DDBlocks.WORMWOOD_SLAB.get());
                        entry.accept(DDBlocks.WORMWOOD_MOSAIC_SLAB.get());
                        entry.accept(DDBlocks.WORMWOOD_DOOR.get());
                        entry.accept(DDBlocks.WORMWOOD_TRAPDOOR.get());
                        entry.accept(DDBlocks.WORMWOOD_FENCE.get());
                        entry.accept(DDBlocks.WORMWOOD_FENCE_GATE.get());
                        entry.accept(DDBlocks.WORMWOOD_BUTTON.get());
                        entry.accept(DDBlocks.WORMWOOD_PRESSURE_PLATE.get());

                        //MISC
                        entry.accept(DDItems.MUSIC_DISC_MALADY.get());
                        entry.accept(DDItems.MUSIC_DISC_MALADY_B_SIDE.get());

                    }).build());

    public static void load() {
    }
}
