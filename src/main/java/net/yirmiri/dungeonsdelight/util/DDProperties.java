package net.yirmiri.dungeonsdelight.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class DDProperties {
    public static class BlockP {
        //MISC
        public static final Block.Settings MONSTER_POT = FabricBlockSettings.copyOf(ModBlocks.COOKING_POT.get());
    }

    public static class ItemP {
        //MISC
        public static final Item.Settings GENERIC = new Item.Settings();
        public static final Item.Settings LOGO = new Item.Settings().food(FoodP.LOGO).rarity(Rarity.EPIC);
    }

    public static class FoodP {
        public static final FoodComponent LOGO = new FoodComponent.Builder().nutrition(-10).saturationModifier(0.0F).alwaysEdible().snack().build();
    }
}
