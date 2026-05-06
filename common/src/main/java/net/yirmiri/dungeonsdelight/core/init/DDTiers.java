package net.yirmiri.dungeonsdelight.core.init;

import com.google.common.base.Suppliers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Objects;
import java.util.function.Supplier;

public enum DDTiers implements Tier {
    FLINT(1, 131, 3.0F, 1.0F, 10, () -> Ingredient.of(new ItemStack(Items.FLINT))),
    ;

    private final int level;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    DDTiers(final int level, final int itemDurability, final float miningSpeed, final float attackDamage, final int enchantability, final Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        Objects.requireNonNull(repairIngredient);
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    public int getUses() {
        return this.itemDurability;
    }

    public float getSpeed() {
        return this.miningSpeed;
    }

    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}