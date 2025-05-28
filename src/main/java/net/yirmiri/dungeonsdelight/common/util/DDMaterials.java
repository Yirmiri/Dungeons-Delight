package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

public class DDMaterials {
    public static final Tier STAINED = new Tier() {
        public int getUses() {
            return 1016;
        }

        public float getSpeed() {
            return 7.0F;
        }

        public float getAttackDamageBonus() {
            return 2.5F;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
        }

        public int getEnchantmentValue() {
            return 20;
        }

        public Ingredient getRepairIngredient() {
            return Ingredient.of(DDItems.STAINED_SCRAP.get());
        }
    };

    public DDMaterials() {
    }
}
