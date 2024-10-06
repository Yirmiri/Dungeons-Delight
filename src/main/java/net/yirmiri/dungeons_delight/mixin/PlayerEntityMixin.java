package net.yirmiri.dungeons_delight.mixin;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.yirmiri.dungeons_delight.util.DDTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(at = @At("RETURN"), method = "eatFood")
    private void dungeonsdelight_eatFood(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<ItemStack> cir) {
        if (!stack.isIn(DDTags.Items.MONSTER_FOODS)) {
            cir.cancel();
        }
    }
}
