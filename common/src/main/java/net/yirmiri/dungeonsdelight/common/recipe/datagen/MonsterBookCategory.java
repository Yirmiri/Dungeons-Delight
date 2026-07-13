package net.yirmiri.dungeonsdelight.common.recipe.datagen;

import net.minecraft.util.StringRepresentable;

public enum MonsterBookCategory implements StringRepresentable {
    MEALS("meals"),
    DRINKS("drinks"),
    MISC("misc");

    public static final StringRepresentable.EnumCodec<MonsterBookCategory> CODEC = StringRepresentable.fromEnum(MonsterBookCategory::values);
    private final String name;

    private MonsterBookCategory(String name) {
        this.name = name;
    }
    public String getSerializedName() { return this.name; }
}
