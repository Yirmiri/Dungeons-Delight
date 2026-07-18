package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowlFoodItem.class)
public class BowlFoodItemMixin extends Item {
    public BowlFoodItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(at = @At("HEAD"), method = "finishUsingItem", cancellable = true)
    private void dungeonsdelight$finishUsingItem(ItemStack stack, Level level, LivingEntity consumer, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.is(DDTags.ItemT.HAS_MEAL_STACK_SIZE) && DungeonsDelight.CONFIG.getIncreasedVanillaMealStackSize()) {
            Item craftRemainderItem = Items.BOWL;

            if (stack.isEdible()) {
                super.finishUsingItem(stack, level, consumer);
            } else if (consumer instanceof Player player) {
                if (player instanceof ServerPlayer serverPlayer) {
                    CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
                }
                player.awardStat(Stats.ITEM_USED.get(this));

                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }

            ItemStack stackRemainder = new ItemStack(craftRemainderItem);
            if (stack.isEmpty()) {
                cir.setReturnValue(stackRemainder);
            }

            if (consumer instanceof Player player && !player.getAbilities().instabuild) {
                if (!player.getInventory().add(stackRemainder)) {
                    player.drop(stackRemainder, false);
                }
            }
            cir.setReturnValue(stack);
        }
    }
}
