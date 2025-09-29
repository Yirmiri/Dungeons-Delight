package net.yirmiri.dungeonsdelight.common.block.monster_pot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.StringRepresentable;

import java.util.EnumSet;

public enum MonsterPotRecipeBookTab implements StringRepresentable {
    MONSTER_MEALS("monster_meals"),
    MONSTER_DRINKS("monster_drinks"),
    MONSTER_MISC("monster_misc");

    public static final Codec<MonsterPotRecipeBookTab> CODEC = Codec.STRING.flatXmap(s -> {
        MonsterPotRecipeBookTab tab = findByName(s);
        if (tab == null) {
            return DataResult.error(() -> "Optional field 'recipe_book_tab' does not match any valid tab. If defined, must be one of the following: " + EnumSet.allOf(MonsterPotRecipeBookTab.class));
        }
        return DataResult.success(tab);
    }, tab -> DataResult.success(tab.toString()));

    public final String name;

    MonsterPotRecipeBookTab(String name) {
        this.name = name;
    }

    public static MonsterPotRecipeBookTab findByName(String name) {
        for (MonsterPotRecipeBookTab value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
