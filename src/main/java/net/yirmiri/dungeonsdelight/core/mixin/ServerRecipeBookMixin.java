package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.network.protocol.game.ClientboundRecipePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.ServerRecipeBook;
import net.neoforged.neoforge.network.PacketDistributor;
import net.yirmiri.dungeonsdelight.common.block.monster_pot.MonsterPotRecipeCategories;
import net.yirmiri.dungeonsdelight.common.util.misc.SendRecipeBookValuesClientboundPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerRecipeBook.class)
public class ServerRecipeBookMixin extends RecipeBook {
    @Inject(method = "sendRecipes", at = @At("TAIL"))
    private void dungeonsDelight$sendFermentingRecipeValues(ClientboundRecipePacket.State state, ServerPlayer player, List<ResourceLocation> recipes, CallbackInfo ci) {
        if (!player.level().isClientSide) {
            PacketDistributor.sendToPlayer(player, new SendRecipeBookValuesClientboundPacket(
                    getBookSettings().isOpen(MonsterPotRecipeCategories.MONSTER_COOKING), getBookSettings().isFiltering(MonsterPotRecipeCategories.MONSTER_COOKING)));
        }
    }
}
