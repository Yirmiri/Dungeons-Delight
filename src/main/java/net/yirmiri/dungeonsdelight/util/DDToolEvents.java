package net.yirmiri.dungeonsdelight.util;

import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.compat.DDCTFKnives;
import vectorwing.farmersdelight.common.item.enchantment.BackstabbingEnchantment;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class DDToolEvents {

    @SubscribeEvent
    public static void knightmetalKnifeAttack(LivingHurtEvent event) {
        LivingEntity target = event.getEntity();
        float extraDamage = 2.0F;

        if (!target.level().isClientSide()) {
            if (event.getSource().getDirectEntity() instanceof LivingEntity living) {
                ItemStack weapon = living.getMainHandItem();
                if (BackstabbingEnchantment.isLookingBehindTarget(event.getEntity(), event.getSource().getSourcePosition()) && target.getArmorValue() > 0 && (weapon.is(DDCTFKnives.KNIGHTMETAL_KNIFE.get()))) {
                    if (target.getArmorCoverPercentage() > 0.0F) {
                        event.setAmount(event.getAmount() + (int) (extraDamage * target.getArmorCoverPercentage()));
                    } else {
                        event.setAmount(event.getAmount() + extraDamage);
                    }
                    ((ServerLevel) target.level()).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
                } else if (!BackstabbingEnchantment.isLookingBehindTarget(event.getEntity(), event.getSource().getSourcePosition()) && target.getArmorValue() == 0 && weapon.is(DDCTFKnives.KNIGHTMETAL_KNIFE.get())) {
                    event.setAmount(event.getAmount() + extraDamage);
                    ((ServerLevel) target.level()).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
                }
            }
        }
    }
}
