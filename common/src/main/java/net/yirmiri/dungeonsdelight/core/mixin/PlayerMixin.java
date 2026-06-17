package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {
    @Unique
    Player player = (Player) (Object) this;

    @Inject(at = @At("HEAD"), method = "attack")
    private void dungeonsdelight$attack(Entity target, CallbackInfo ci) {
        ItemStack mainhand = player.getMainHandItem();
        if (mainhand.is(DDItems.AMETHYST_ROCK_CANDY.get())) {
            Item tryme = null;

            if (!player.getCooldowns().isOnCooldown(DDItems.AMETHYST_ROCK_CANDY.get())) {
                if (target instanceof Silverfish silverfish) {
                    tryme = DDItems.CANDIED_SILVERFISH_SUCKER.get();
                    player.awardStat(Stats.ITEM_USED.get(mainhand.getItem()));

                    silverfish.handleEntityEvent(EntityEvent.POOF);
                    silverfish.playSound(SoundEvents.SILVERFISH_DEATH, 1.0F, 1.0F);
                    silverfish.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.0F);
                    silverfish.remove(Entity.RemovalReason.DISCARDED);
                }

                if (target instanceof Vex vex) {
                    tryme = DDItems.CANDIED_VEX_SUCKER.get();
                    player.awardStat(Stats.ITEM_USED.get(mainhand.getItem()));

                    vex.handleEntityEvent(EntityEvent.POOF);
                    vex.playSound(SoundEvents.VEX_DEATH, 1.0F, 1.0F);
                    vex.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.0F);
                    vex.remove(Entity.RemovalReason.DISCARDED);
                }

                if (tryme != null) {
                    if (!player.isCreative()) {
                        mainhand.shrink(1);
                        player.getCooldowns().addCooldown(DDItems.AMETHYST_ROCK_CANDY.get(), DungeonsDelight.CONFIG.getRockCandyPickupCooldownTicks());
                    }

                    if (mainhand.isEmpty()) player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(tryme));
                    else if (!player.getInventory().add(new ItemStack(tryme))) player.drop(new ItemStack(tryme), false);
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
