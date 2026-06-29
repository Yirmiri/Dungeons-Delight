package net.yirmiri.dungeonsdelight.core.registry;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.yirmiri.dungeonsdelight.core.mixin.PotionBrewingMixin;

import static net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES;

public class DDRegistries {
    public static void load() {
        loadCompostables();
        loadPotionRecipes();
    }

    public static void loadCompostables() {
        Object2FloatMap<ItemLike> compostables = new Object2FloatOpenHashMap<>();

        compostables.put(DDItems.BLEET.get(), 0.65F);
        compostables.put(DDItems.BLEET_SEEDS.get(), 0.3F);
        compostables.put(DDItems.MANALLIUM.get(), 0.65F);
        compostables.put(DDItems.ENDELVE.get(), 0.65F);
        compostables.put(DDItems.BLACK_APPLE.get(), 0.65F);
        compostables.put(DDItems.SCULK_APPLE.get(), 0.65F);

        COMPOSTABLES.putAll(compostables);
    }

    public static void loadPotionRecipes() {
        PotionBrewingMixin.addMix(Potions.AWKWARD, DDItems.BLACK_APPLE.get(), DDPotions.HOLLOWED.get());
        PotionBrewingMixin.addMix(DDPotions.HOLLOWED.get(), Items.REDSTONE, DDPotions.LONG_HOLLOWED.get());
    }
}
