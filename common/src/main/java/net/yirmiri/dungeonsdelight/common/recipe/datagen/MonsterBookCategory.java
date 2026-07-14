package net.yirmiri.dungeonsdelight.common.recipe.datagen;

import net.minecraft.util.StringRepresentable;

public enum MonsterBookCategory implements StringRepresentable {
    TIER_1("tier_1"),
    TIER_2("tier_2"),
    TIER_3("tier_3"),
    MISC("misc");

    public static final StringRepresentable.EnumCodec<MonsterBookCategory> CODEC = StringRepresentable.fromEnum(MonsterBookCategory::values);
    private final String name;

    private MonsterBookCategory(String id) {
        this.name = id;
    }
    public String getSerializedName() { return this.name; }
}
