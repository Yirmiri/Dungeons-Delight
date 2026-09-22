package net.yirmiri.dungeonsdelight.common.item;

import net.azurune.runiclib.common.item.IAlwaysTickingItem;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.item.food_type.DDFoodItem;
import net.yirmiri.dungeonsdelight.common.util.data.SquibTickData;

public class CreeperillaSquibItem extends DDFoodItem implements IAlwaysTickingItem {
    private static final int BAR_COLOR = 0xDB2F1A;

    public CreeperillaSquibItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        SquibTickData.commonTickdown(null, stack, entity.level(), entity, null);
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        if (!itemEntity.level().isClientSide) {
            SquibTickData.explode(Context.ITEM_IN_WORLD, itemEntity.getItem(), itemEntity.level(), itemEntity, null);
        }
    }

    @Override
    public boolean canFitInsideContainerItems()
    {
        return super.canFitInsideContainerItems();
    }

    @Override
    public void runicItemTick(Context context, ItemStack stack, Level level, Entity entity, Container container) {
        SquibTickData.commonTickdown(context, stack, level, entity, container);
    }

    @Override public boolean isBarVisible(ItemStack stack) { return SquibTickData.getTime(stack) < SquibTickData.MAX_TICKS; }
    @Override public int getBarWidth(ItemStack stack) { return Math.min(1 + 12 * SquibTickData.getTime(stack) / SquibTickData.MAX_TICKS, 13); }
    @Override public int getBarColor(ItemStack stack) { return BAR_COLOR; }
}