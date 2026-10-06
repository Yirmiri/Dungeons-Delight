package net.yirmiri.dungeonsdelight.core.mixin.hollowing;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Camel.class, Villager.class, Horse.class})
public class StupidCommonHollowMixin extends HollowingMixin {
    // Why can't things just. work
    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$mobInteractAtHead(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        this.dungeonsdelight$blackAppleOnCommons(player, hand, cir);
    }
}