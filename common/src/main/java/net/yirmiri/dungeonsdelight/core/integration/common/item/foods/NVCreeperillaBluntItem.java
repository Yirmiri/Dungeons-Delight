package net.yirmiri.dungeonsdelight.core.integration.common.item.foods;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.item.food_type.BiteableFoodItem;

public class NVCreeperillaBluntItem extends BiteableFoodItem {
    public NVCreeperillaBluntItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, SoundEvents.EMPTY, UseAnim.SPYGLASS, 32, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        if (consumer instanceof Player player) {
            player.getCooldowns().addCooldown(stack.getItem(), 20);
        }
        consumer.playSound(SoundEvents.CREEPER_PRIMED, 0.8F, -1.0F);

        var rotation = consumer.getLookAngle().scale(0.6);
        level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, consumer.getX() + rotation.x, consumer.getEyeY() + rotation.y, consumer.getZ() + rotation.z, 0.0, 0.2 + consumer.getRandom().nextDouble() * 0.1, 0.0);
        return super.finishUsingItem(stack, level, consumer);
    }
}
//todo more like actual nirvana items (aoe + 3d)