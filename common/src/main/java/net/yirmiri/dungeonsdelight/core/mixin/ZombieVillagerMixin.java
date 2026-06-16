package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieVillager.class)
public class ZombieVillagerMixin {
    @Shadow
    @Final
    private static EntityDataAccessor<Boolean> DATA_CONVERTING_ID;
    @Unique
    ZombieVillager zombieVillager = (ZombieVillager) (Object) this;

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (player.getItemInHand(hand).is(DDItems.BLACK_APPLE.get())) {
            player.getItemInHand(hand).shrink(1);

            zombieVillager.getEntityData().set(DATA_CONVERTING_ID, false);
            zombieVillager.addEffect(new MobEffectInstance(DDEffects.DECISIVE.get(), 100, 0));
            zombieVillager.level().broadcastEntityEvent(zombieVillager, (byte) 16);

            cir.setReturnValue(InteractionResult.sidedSuccess(player.level().isClientSide));
        }
    }
}