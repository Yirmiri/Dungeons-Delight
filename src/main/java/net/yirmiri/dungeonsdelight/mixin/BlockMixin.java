package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.util.DDTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Block.class)
public class BlockMixin {
    private static Random random = new Random();

    @Inject(at = @At("HEAD"), method = "afterBreak")
    private void dungeonsdelight_afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool, CallbackInfo ci) {
        if (player.hasStatusEffect(DDEffects.BURROW_GUT)) {
            if ((state.getHardness(world, pos) * 30) > random.nextDouble(100.0)) {
                player.getHungerManager().add(1 + player.getStatusEffect(DDEffects.BURROW_GUT).getAmplifier(), 0.5F + player.getStatusEffect(DDEffects.BURROW_GUT).getAmplifier());
            }
        }
    }
}