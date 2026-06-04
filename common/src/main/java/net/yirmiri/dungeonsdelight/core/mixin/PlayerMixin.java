package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Unique
    Player player = (Player) (Object) this;

    @Inject(at = @At("HEAD"), method = "attack")
    private void dungeonsdelight$attack(Entity target, CallbackInfo ci) {
        if (player.getMainHandItem().is(DDItems.AMETHYST_ROCK_CANDY.get())) {
            boolean success = false;

            if (!player.getCooldowns().isOnCooldown(DDItems.AMETHYST_ROCK_CANDY.get())) {
                if (target instanceof Silverfish silverfish) {
                    DDUtil.convertItem(player, SoundEvents.SILVERFISH_DEATH, player.getMainHandItem(), new ItemStack(DDItems.CANDIED_SILVERFISH_SUCKER.get()));
                    success = true;
                    silverfish.remove(Entity.RemovalReason.DISCARDED);
                }

                if (target instanceof Vex vex) {
                    DDUtil.convertItem(player, SoundEvents.VEX_DEATH, player.getMainHandItem(), new ItemStack(DDItems.CANDIED_VEX_SUCKER.get()));
                    success = true;
                    vex.remove(Entity.RemovalReason.DISCARDED);
                }

                if (!player.isCreative() && success) {
                    player.getCooldowns().addCooldown(DDItems.AMETHYST_ROCK_CANDY.get(), DungeonsDelight.CONFIG.getRockCandyPickupCooldownTicks());
                }
            }
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;heal(F)V"), method = "aiStep", cancellable = true)
    private void dungeonsdelight$canFoodHeal(CallbackInfo ci) {
        if (player.hasEffect(DDEffects.TENACITY.get())) {
            ci.cancel();
        }
    }
}
