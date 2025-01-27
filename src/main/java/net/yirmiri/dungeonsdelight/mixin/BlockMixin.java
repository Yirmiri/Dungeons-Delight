package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Block.class)
public class BlockMixin {
    private static Random random = new Random();

    @Inject(at = @At("HEAD"), method = "playerDestroy")
    private void dungeonsdelight$afterBreak(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci) {
        if (player.hasEffect(DDEffects.BURROW_GUT.get())) {
            double luckAmount = player.getAttributeValue(Attributes.LUCK);
            int burrowGutLevel = player.getEffect(DDEffects.BURROW_GUT.get()).getAmplifier();

            if (player.isAlive()) {
                if ((state.getDestroySpeed(level, pos) * (30 + (luckAmount * 4)) > random.nextDouble(100.0))) {
                    player.getFoodData().eat(1 + burrowGutLevel, 0.5F + burrowGutLevel);
                }
            }
        }
    }
}
