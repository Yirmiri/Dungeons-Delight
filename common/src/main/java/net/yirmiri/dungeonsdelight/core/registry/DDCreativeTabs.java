package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDCreativeTabs {
    public static final Supplier<CreativeModeTab> DUNGEONSDELIGHT = Services.REGISTRY.registerCreativeModeTab(
            DungeonsDelight.MOD_ID, "dungeonsdelight", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemgroup.dungeonsdelight"))
                    .icon(() -> new ItemStack(DDBlocks.DUNGEON_STOVE.get()))
                    .displayItems((displayParameters, entry) -> {
                        //FUNCTIONAL
                        entry.accept(DDBlocks.MONSTER_POT.get());
                        entry.accept(DDBlocks.DUNGEON_STOVE.get());
                        entry.accept(DDBlocks.WORMWOOD_CLEAVING_BOARD.get());
                        entry.accept(DDBlocks.BAMBOO_CLEAVING_BOARD.get());
                        entry.accept(DDBlocks.SPIKE_TRAP.get());

                        //TOOLS
                        entry.accept(DDItems.FLINT_CLEAVER.get());
                        entry.accept(DDItems.IRON_CLEAVER.get());
                        entry.accept(DDItems.GOLDEN_CLEAVER.get());
                        entry.accept(DDItems.DIAMOND_CLEAVER.get());
                        entry.accept(DDItems.NETHERITE_CLEAVER.get());

                        //CROPS
                        entry.accept(DDBlocks.TERROR_PRETA.get());

                        entry.accept(DDItems.ENDELVE.get());
                        entry.accept(DDItems.MANALLIUM.get());
                        entry.accept(DDItems.BLEET.get());
                        entry.accept(DDItems.BLEET_SEEDS.get());
                        entry.accept(DDItems.SOUL_PEPPER.get());
                        entry.accept(DDItems.SOUL_PEPPER_SEEDS.get());

                        entry.accept(DDItems.ROTBULB.get());
                        entry.accept(DDItems.ROTBULB_SEEDS.get());

                        //WILD
                        entry.accept(DDBlocks.WILD_ENDELVES.get());
                        entry.accept(DDBlocks.WILD_MANALLIUMS.get());
                        entry.accept(DDBlocks.WILD_BLEETS.get());
                        entry.accept(DDItems.WILD_ROTBULB.get());

                        //MISC INGREDIENTS
                        entry.accept(DDItems.GUNK.get());
                        entry.accept(DDItems.SLICORICE.get());

                        //---FOODS--- (Ingredient -> Drink -> Plated -> Bowled -> Skewed -> Finger -> Banquet)

                        //UNDEAD
                        entry.accept(DDItems.ROTTEN_TRIPE.get());

                        entry.accept(DDItems.GHOULASH.get());
                        entry.accept(DDItems.FOUL_SKEWER.get());

                        //SPIDER
                        entry.accept(DDItems.SPIDER_MEAT.get());
                        entry.accept(DDItems.COOKED_SPIDER_MEAT.get());
                        entry.accept(DDItems.SPIDER_EXTRACT.get());

                        entry.accept(DDItems.BUBBLE_EYE_TEA.get());
                        entry.accept(DDItems.SALMAGUNDI.get());
                        entry.accept(DDItems.SPIDER_TANGHULU.get());
                        entry.accept(DDItems.SPIDER_PIE.get());

                        //CREEPER
                        entry.accept(DDItems.CREEPERILLA.get());
                        entry.accept(DDItems.CREEPERILLA_SQUIB.get());

                        entry.accept(DDItems.GUNPOWDER_BAKED_ARACHNID.get());
                        entry.accept(DDItems.DYNAMITE_ROLL.get());

                        //SILVERFISH
                        entry.accept(DDItems.SILVERFISH_ABDOMEN.get());

                        entry.accept(DDItems.SILVERFISH_FRIED_RICE.get());

                        //TREASURE BUG
                        entry.accept(DDItems.TREASURE_BUG_ABDOMEN.get());

                        //SLIME
                        entry.accept(DDItems.SLIME_NOODLES.get());

                        //MAGMA CUBE
                        entry.accept(DDItems.MAGMARONI.get());

                        //GHAST
                        entry.accept(DDItems.GHAST_TENTACLE.get());
                        entry.accept(DDItems.GHAST_CALAMARI.get());
                        entry.accept(DDItems.COOKED_GHAST_CALAMARI.get());

                        entry.accept(DDItems.GHAST_ROLL.get());

                        //ROTTEN
                        entry.accept(DDItems.TARO_MILK_TEA.get());
                        entry.accept(DDItems.BLACK_APPLE.get());
                        entry.accept(DDItems.BUBBLEGUNK.get());
                        entry.accept(DDItems.RANCID_REDUCTION.get());

                        //SCULK
                        entry.accept(DDItems.SCULK_POLYP.get());
                        entry.accept(DDItems.ANCIENT_EGG.get());
                        entry.accept(DDItems.CLEAVED_ANCIENT_EGG.get());
                        entry.accept(DDItems.SCULK_MAYONNAISE.get());

                        entry.accept(DDItems.EGGNOG.get());
                        entry.accept(DDItems.SCULK_APPLE.get());

                        //SNIFFER
                        entry.accept(DDItems.SNIFFER_SHANK.get());
                        entry.accept(DDItems.COOKED_SNIFFER_SHANK.get());

                        //MISC FOODS
                        entry.accept(DDBlocks.TELEPOTAGE_BLOCK.get());
                        entry.accept(DDItems.TELEPOTAGE.get());

                        entry.accept(DDItems.RAVAGER_HAUNCH.get());

                        entry.accept(DDItems.AMETHYST_ROCK_CANDY.get());
                        entry.accept(DDItems.CANDIED_SILVERFISH_SUCKER.get());
                        entry.accept(DDItems.CANDIED_ENDERMITE_SUCKER.get());
                        entry.accept(DDItems.CANDIED_VEX_SUCKER.get());

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
                        entry.accept(DDBlocks.STAINED_SCRAP_GATE.get());
                        entry.accept(DDItems.LIVING_TORCH.get());
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

                        //COBBLED
                        entry.accept(DDBlocks.COBBLED_BRICKS.get());
                        entry.accept(DDBlocks.COBBLED_BRICK_STAIRS.get());
                        entry.accept(DDBlocks.COBBLED_BRICK_SLAB.get());
                        entry.accept(DDBlocks.COBBLED_BRICK_WALL.get());
                        entry.accept(DDBlocks.MOSSY_COBBLED_BRICKS.get());
                        entry.accept(DDBlocks.MOSSY_COBBLED_BRICK_STAIRS.get());
                        entry.accept(DDBlocks.MOSSY_COBBLED_BRICK_SLAB.get());
                        entry.accept(DDBlocks.MOSSY_COBBLED_BRICK_WALL.get());
                        entry.accept(DDBlocks.CRACKED_COBBLED_BRICKS.get());
                        entry.accept(DDBlocks.COBBLED_TILES.get());
                        entry.accept(DDBlocks.COBBLED_TILE_STAIRS.get());
                        entry.accept(DDBlocks.COBBLED_TILE_SLAB.get());

                        //MISC BLOCKS
                        entry.accept(DDBlocks.ROTTEN_FLESH_BLOCK.get());
                        entry.accept(DDBlocks.GUNK_BLOCK.get());
                        entry.accept(DDBlocks.SCULK_MAYONNAISE_BLOCK.get());
                        entry.accept(DDBlocks.EMBEDDED_EGGS.get());
                        entry.accept(DDBlocks.ROTTEN_SPAWNER.get());

                        //MISC
                        entry.accept(DDItems.MUSIC_DISC_MALADY.get());
                        entry.accept(DDItems.MUSIC_DISC_MALADY_B_SIDE.get());

                        //POTIONS
                        entry.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DDPotions.HOLLOWED.get()));
                        entry.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DDPotions.LONG_HOLLOWED.get()));
                        entry.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DDPotions.HOLLOWED.get()));
                        entry.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DDPotions.LONG_HOLLOWED.get()));
                        entry.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), DDPotions.HOLLOWED.get()));
                        entry.accept(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), DDPotions.LONG_HOLLOWED.get()));
                        entry.accept(PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), DDPotions.HOLLOWED.get()));
                        entry.accept(PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), DDPotions.LONG_HOLLOWED.get()));

                        //SPAWN EGGS (Alphabetical)
                        entry.accept(DDItems.CAMEL_HUSK_SPAWN_EGG.get());
                        entry.accept(DDItems.MONSTER_YAM_SPAWN_EGG.get());
                        entry.accept(DDItems.TREASURE_BUG_SPAWN_EGG.get());

                    }).build());

    public static void load() {
    }
}
