//Based on Brewing and Chewin's FermentingBookCategory.java (which is under MIT License)
//Original Source: https://github.com/MerchantCalico/BrewinAndChewin/blob/1.21.1/common/src/main/java/umpaz/brewinandchewin/client/recipebook/FermentingBookCategory.java

package net.yirmiri.dungeonsdelight.common.block.monster_pot;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum MonsterPotRecipeBookTab implements StringRepresentable {
    MONSTER_MEALS("monster_meals", 0),
    MONSTER_DRINKS("monster_drinks", 1),
    MONSTER_MISC("monster_misc", 2);

    final String name;
    final int id;

    public static final Codec<MonsterPotRecipeBookTab> CODEC = StringRepresentable.fromEnum(MonsterPotRecipeBookTab::values);
    public static final IntFunction<MonsterPotRecipeBookTab> BY_ID = ByIdMap.continuous(MonsterPotRecipeBookTab::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, MonsterPotRecipeBookTab> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, MonsterPotRecipeBookTab::id);

    MonsterPotRecipeBookTab(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    private int id() {
        return id;
    }
}
