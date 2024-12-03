package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.util.DDTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    PlayerEntity player = (PlayerEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "eatFood", cancellable = true)
    private void dungeonsdelight_eatFood(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<ItemStack> cir) {
        if (!stack.isIn(DDTags.Items.MONSTER_FOODS) && player.hasStatusEffect(DDEffects.BURROW_GUT)) {
            cir.setReturnValue(TypedActionResult.fail(stack).getValue());
            player.sendMessage(Text.translatable("dungeons_delight.burrow_gut.cannot_consume"), true);
        }
    }
}