package net.yirmiri.dungeonsdelight.core.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class DDMaterials {
    public static final Tier STAINED = new Tier() {
        public int getUses() {return 1016;}
        public float getSpeed() {return 7.0F;}
        public float getAttackDamageBonus() {return 2.5F;}
        public int getEnchantmentValue() {return 20;}
        public Ingredient getRepairIngredient() {return Ingredient.of(DDTags.ItemT.REPAIRS_STAINED_TOOLS);}
        public TagKey<Block> getIncorrectBlocksForDrops() {return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;}
    };

    public static class DDCMaterials {
        //TWILIGHT FOREST
        public static final Tier IRONWOOD = new Tier() {
            public int getUses() {return 512;}
            public float getSpeed() {return 6.5F;}
            public float getAttackDamageBonus() {return 2F;}
            public int getEnchantmentValue() {return 25;}
            public Ingredient getRepairIngredient() {return Ingredient.of(DDTags.ItemT.REPAIRS_IRONWOOD_TOOLS);}
            public TagKey<Block> getIncorrectBlocksForDrops() {return BlockTags.INCORRECT_FOR_IRON_TOOL;}
        };

        public static final Tier STEELEAF = new Tier() {
            public int getUses() {return 131;}
            public float getSpeed() {return 8.0F;}
            public float getAttackDamageBonus() {return 3F;}
            public int getEnchantmentValue() {return 9;}
            public Ingredient getRepairIngredient() {return Ingredient.of(DDTags.ItemT.REPAIRS_STEELEAF_TOOLS);}
            public TagKey<Block> getIncorrectBlocksForDrops() {return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;}
        };

        public static final Tier FIERY = new Tier() {
            public int getUses() {return 1024;}
            public float getSpeed() {return 9.0F;}
            public float getAttackDamageBonus() {return 4F;}
            public int getEnchantmentValue() {return 10;}
            public Ingredient getRepairIngredient() {return Ingredient.of(DDTags.ItemT.REPAIRS_FIERY_TOOLS);}
            public TagKey<Block> getIncorrectBlocksForDrops() {return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;}
        };

        public static final Tier KNIGHTMETAL = new Tier() {
            public int getUses() {return 512;}
            public float getSpeed() {return 8.0F;}
            public float getAttackDamageBonus() {return 3F;}
            public int getEnchantmentValue() {return 8;}
            public Ingredient getRepairIngredient() {return Ingredient.of(DDTags.ItemT.REPAIRS_KNIGHTMETAL_TOOLS);}
            public TagKey<Block> getIncorrectBlocksForDrops() {return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;}
        };
    }

    public DDMaterials() {
    }
}
