package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

public class DDMaterials {
    public static final Tier STAINED = new Tier() {
        public int getLevel() {
            return 4;
        }

        public int getUses() {
            return 1016;
        }

        public float getSpeed() {
            return 7.0F;
        }

        public float getAttackDamageBonus() {
            return 2.5F;
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
