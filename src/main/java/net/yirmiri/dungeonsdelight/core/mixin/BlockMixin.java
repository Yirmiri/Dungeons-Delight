package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Block.class)
public class BlockMixin {
    @Unique private static Random random = new Random();

    @Inject(at = @At("HEAD"), method = "playerDestroy")
    private void dungeonsdelight$playerDestroy(Level level, Player player, BlockPos pos, BlockState state, BlockEntity entity, ItemStack stack, CallbackInfo ci) {
        if (player.hasEffect(DDEffects.BURROW_GUT.get())) {
            player.getFoodData().eat(getBurrowGutRefillAmount(player, state.getDestroySpeed(level, pos)), 0.1F);

            if ((state.getDestroySpeed(level, pos) * 10) > random.nextDouble(100.0) && !player.hasEffect(DDEffects.RAVENOUS_RUSH.get())) {
                player.addEffect(new MobEffectInstance(DDEffects.RAVENOUS_RUSH.get(), 120, 0));
            }

            if (player.hasEffect(DDEffects.RAVENOUS_RUSH.get())) {
                if (state.is(Blocks.HONEY_BLOCK) && player.hasEffect(MobEffects.POISON)) {
                    player.removeEffect(MobEffects.POISON);
                }
            }
        }
    }

    private int getBurrowGutRefillAmount(LivingEntity living, float amount) {
        if (amount < 1) {
            return 1;
        } else if ((living.getEffect(DDEffects.BURROW_GUT.get()).getAmplifier() + 5) > amount) {
            return (int) (amount);
        } else return (living.getEffect(DDEffects.BURROW_GUT.get()).getAmplifier() + 5);
    }
}
