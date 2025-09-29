//Based on Brewing and Chewin's FermentingBookCategory.java (which is under MIT License)
//Original Source: https://github.com/MerchantCalico/BrewinAndChewin/blob/1.21.1/common/src/main/java/umpaz/brewinandchewin/BrewinAndChewin.java#L27

package net.yirmiri.dungeonsdelight.common.util.misc;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.monster_pot.MonsterPotRecipeCategories;

public record SendRecipeBookValuesClientboundPacket(boolean open, boolean filtering) implements CustomPacketPayload {
    public static final ResourceLocation ID = RunicLib.customid(DungeonsDelight.MOD_ID, "send_recipe_book_values");
    public static final Type<SendRecipeBookValuesClientboundPacket> TYPE = new Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, SendRecipeBookValuesClientboundPacket> STREAM_CODEC = StreamCodec.of(SendRecipeBookValuesClientboundPacket::encode, SendRecipeBookValuesClientboundPacket::new);

    public SendRecipeBookValuesClientboundPacket(RegistryFriendlyByteBuf buf) {
        this(buf.readBoolean(), buf.readBoolean());
    }

    public static void encode(FriendlyByteBuf buf, SendRecipeBookValuesClientboundPacket packet) {
        buf.writeBoolean(packet.open);
        buf.writeBoolean(packet.filtering);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle() {
        Minecraft.getInstance().execute(() -> {
            ClientRecipeBook recipeBook = Minecraft.getInstance().player.getRecipeBook();
            recipeBook.setOpen(MonsterPotRecipeCategories.MONSTER_COOKING, open);
            recipeBook.setOpen(MonsterPotRecipeCategories.MONSTER_COOKING, filtering);
        });
    }
}
