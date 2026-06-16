package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Villager.class, Horse.class, AbstractHorse.class})
public class BlackAppleInteractMixin extends HollowingMixin {
    @Unique
    Mob mob = (Mob) (Object) this;

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);

        if (mob.level().isClientSide) return;

        if (stack.is(DDItems.BLACK_APPLE.get()) && mob.hasEffect(DDEffects.HOLLOWED.get()) && !isHollowing && mob.getType().is(DDTags.EntityT.CAN_HOLLOW)) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            player.swing(hand, true);
            startHollowing(player.getUUID(), mob);
            cir.setReturnValue(InteractionResult.CONSUME);
            cir.cancel();
        }
    }
}
