package net.yirmiri.dungeonsdelight.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Rarity;
import net.yirmiri.dungeonsdelight.registry.DDEffects;

import java.util.function.ToIntFunction;

public class DDProperties {
    public static class BlockP {
        private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
            return (state) -> state.get(Properties.LIT) ? lightValue : 0;
        }

        //MISC
        public static final Block.Settings DUNGEON_POT = FabricBlockSettings.of().mapColor(MapColor.IRON_GRAY).hardness(0.5F).resistance(6.0F).sounds(BlockSoundGroup.LANTERN).luminance(bs -> 4);
        public static final Block.Settings DUNGEON_STOVE = FabricBlockSettings.copy(Blocks.BRICKS).luminance(litBlockEmission -> 10);
    }

    public static class ItemP {
        //MISC
        public static final Item.Settings GENERIC = new Item.Settings();
        public static final Item.Settings LOGO = new Item.Settings().food(FoodP.LOGO).rarity(Rarity.EPIC);

        //GENERIC FOODS

        //MEALS

        //SPECIAL FOODS
        public static final Item.Settings BREEZE_CREAM_CONE = new Item.Settings().food(FoodP.BREEZE_CREAM_CONE);
    }

    public static class FoodP {
        //MISC
        public static final FoodComponent LOGO = new FoodComponent.Builder().nutrition(-3).saturationModifier(0.0F).alwaysEdible().snack().build();

        //GENERIC FOODS

        //MEALS

        //SPECIAL FOODS
        public static final FoodComponent BREEZE_CREAM_CONE = new FoodComponent.Builder().nutrition(8).saturationModifier(6.0F).alwaysEdible()
                .statusEffect(new StatusEffectInstance(DDEffects.BREEZE_BOLT, 1200, 1), 1.0F).build();
    }
}
