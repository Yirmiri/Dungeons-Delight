package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void dungeonsdelight$tick(CallbackInfo ci) {
        ItemEntity entity = (ItemEntity) (Object) this;
        ItemStack stack = entity.getItem();

        if (stack.is(DDTags.ItemT.EVAPORATES_IN_WATER)) {
            if (entity.level().getBlockState(entity.blockPosition()).getBlock() == Blocks.WATER) {
                ItemStack container = stack.getCraftingRemainingItem();
                if (!stack.getCraftingRemainingItem().isEmpty()) {
                    ItemEntity containerEntity = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), container);
                    entity.level().addFreshEntity(containerEntity);
                }

                entity.playSound(SoundEvents.GENERIC_BURN);
                stack.shrink(1);

                if (stack.isEmpty()) {
                    entity.discard();
                } else {
                    entity.setItem(stack);
                }
            }
        }
    }
}
