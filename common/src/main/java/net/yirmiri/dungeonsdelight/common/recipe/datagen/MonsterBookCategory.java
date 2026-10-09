package net.yirmiri.dungeonsdelight.common.recipe.datagen;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum MonsterBookCategory implements StringRepresentable {
    FOOD("food", 0),
    DRINK("drink", 1),
    MISC("misc", 2);

    public static final Codec<MonsterBookCategory> CODEC = StringRepresentable.fromEnum(MonsterBookCategory::values);
    /*      todo: add in 1.21.1 - adding here bc i want the move for this in particular to be as painless as possible
    public static final IntFunction<MonsterBookCategory> BY_ID = ByIdMap.continuous(MonsterBookCategory::id, values(), OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, MonsterBookCategory> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, MonsterBookCategory::id);
    */
    private final String name;
    private final int id;

    private MonsterBookCategory(final String id, final int id) {
        this.name = id;
        this.id = id;
    }
    public String getSerializedName() { return this.name; }
    private int id() {
        return this.id;
    }
}
