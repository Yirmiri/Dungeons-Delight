package net.yirmiri.dungeonsdelight.common.item.food_type;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
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
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;

import java.util.List;

public class EchoBlastFoodItem extends DDFoodItem {
    private final int level;
    private final float blastChance;

    public EchoBlastFoodItem(int level, float blastChance, boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, int useTicks, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, useTicks, properties);
        this.level = level;
        this.blastChance = blastChance;
    }

    public EchoBlastFoodItem(int level, float blastChance, boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
        this.level = level;
        this.blastChance = blastChance;
    }

    public EchoBlastFoodItem(int level, float blastChance, boolean hasEffectTooltip, SoundEvent consumeSound, Properties properties) {
        super(hasEffectTooltip, consumeSound, properties);
        this.level = level;
        this.blastChance = blastChance;
    }

    public EchoBlastFoodItem(int level, float blastChance, boolean hasEffectTooltip, SoundEvent consumeSound, UseAnim useAnimation, Properties properties) {
        super(hasEffectTooltip, consumeSound, useAnimation, properties);
        this.level = level;
        this.blastChance = blastChance;
    }

    public EchoBlastFoodItem(int level, float blastChance, boolean hasEffectTooltip, int useTicks, Properties properties) {
        super(hasEffectTooltip, useTicks, properties);
        this.level = level;
        this.blastChance = blastChance;
    }

    private float getBlastChance(LivingEntity living) {
        return this.blastChance + (DDUtil.getSeredipityLuck(living) / 10);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        boolean successfulChance = level.random.nextFloat() < getBlastChance(living);
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
            Player player = Minecraft.getInstance().player;
            int percent = Math.round(blastChance * 100);
            int seredipityPercent = Math.round((DDUtil.getSeredipityLuck(player) / 10) * 100);

            DDUtil.addConsumeTooltip(tooltipComponents);
            if (!(blastChance == 1.0F)) {
                if (!player.hasEffect(DDEffects.SERENDIPITY.get())) {
                    tooltipComponents.add(Component.literal(percent + "% ")
                            .append(Component.translatable("tooltip.dungeonsdelight.effect.chance_to_echo_blast_" + this.level)).withStyle(ChatFormatting.BLUE));
                } else {
                    tooltipComponents.add(Component.literal(percent + "% ").withStyle(ChatFormatting.BLUE)
                            .append(Component.literal("(+" + seredipityPercent + "%) ").withStyle(ChatFormatting.DARK_GREEN))
                            .append(Component.translatable("tooltip.dungeonsdelight.effect.chance_to_echo_blast_" + this.level).withStyle(ChatFormatting.BLUE)));
                }
            } else {
                tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.echo_blast_" + this.level).withStyle(ChatFormatting.BLUE));
            }
        }
    }
}
