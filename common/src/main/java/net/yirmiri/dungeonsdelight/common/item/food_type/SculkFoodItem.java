package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;

public class SculkFoodItem extends DDFoodItem {
    private final int level;
    private final float blastChance;

    public SculkFoodItem(int level, float blastChance, boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, int useTicks, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, useTicks, properties);
        this.level = level;
        this.blastChance = blastChance;
    }

    public SculkFoodItem(int level, float blastChance, boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
        this.level = level;
        this.blastChance = blastChance;
    }

    public SculkFoodItem(int level, float blastChance, boolean hasEffectTooltip, SoundEvent consumeSound, Properties properties) {
        super(hasEffectTooltip, consumeSound, properties);
        this.level = level;
        this.blastChance = blastChance;
    }

    public SculkFoodItem(int level, float blastChance, boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, properties);
        this.level = level;
        this.blastChance = blastChance;
    }

    public SculkFoodItem(int level, float blastChance, boolean hasEffectTooltip, int useTicks, Properties properties) {
        super(hasEffectTooltip, useTicks, properties);
        this.level = level;
        this.blastChance = blastChance;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        boolean successfulChance = level.random.nextFloat() < blastChance;
        super.finishUsingItem(stack, level, living);
        if (successfulChance && !level.isClientSide && living instanceof Player player) {
            switch (this.level) {
                case 1 -> DDUtil.echoBlastSmall(level, player, this.level);
                case 2 -> DDUtil.echoBlastMedium(level, player, this.level);
                case 3 -> DDUtil.echoBlastLarge(level, player, this.level);
                default -> throw new IllegalStateException("Unexpected value: " + this.level);
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (DungeonsDelight.CONFIG.getItemEffectTooltips()) {
            int percent = Math.round(blastChance * 100);

            DDUtil.addConsumeTooltip(tooltipComponents);
            tooltipComponents.add(Component.literal(percent + "% ")
                    .append(Component.translatable("tooltip.dungeonsdelight.effect.chance_to_blast_" + this.level)).withStyle(ChatFormatting.BLUE));
        }
    }
}
