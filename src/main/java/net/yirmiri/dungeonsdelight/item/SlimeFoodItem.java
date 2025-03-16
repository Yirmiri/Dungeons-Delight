package net.yirmiri.dungeonsdelight.item;

import vectorwing.farmersdelight.common.item.ConsumableItem;

public class SlimeFoodItem extends ConsumableItem {
    private final float chance;

    public SlimeFoodItem(Properties properties, float chance, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip, false);
        this.chance = chance;
    }

    //TODO MAKE CONSUME SOMETIMES

//    @Override @OnlyIn(Dist.CLIENT)
//    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
//        if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
//            tooltip.add(TextUtils.getTranslation("tooltip.chance_to_not_consume").withStyle(ChatFormatting.BLUE));
//            super.appendHoverText(stack, level, tooltip, isAdvanced);
//        }
//    }
}
