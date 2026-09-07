package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.misc.ThrownCocktail;
import net.yirmiri.dungeonsdelight.common.entity.misc.leftovers.LeftoversEntity;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

import java.util.List;

public class CocktailItem extends Item {
    public int particleColor;
    public LeftoversEntity.LeftoversType type;
    public List<MobEffect> mobEffects;
    public List<Integer> effectDurations;
    public List<Integer> effectAmplifiers;
    public List<Integer> effectMaxDurations;
    public int hunger;
    public float saturation;

    public CocktailItem(int particleColor, LeftoversEntity.LeftoversType type, List<MobEffect> mobEffects, List<Integer> effectDurations, List<Integer> effectAmplifiers, List<Integer> effectMaxDurations, int hunger, float saturation, Properties properties) {
        super(properties);
        this.particleColor = particleColor;
        this.type = type;
        this.mobEffects = mobEffects;
        this.effectDurations = effectDurations;
        this.effectAmplifiers = effectAmplifiers;
        this.effectMaxDurations = effectMaxDurations;
        this.hunger = hunger;
        this.saturation = saturation;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SPLASH_POTION_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        for (Holder<Item> item : BuiltInRegistries.ITEM.getTagOrEmpty(DDTags.ItemT.COCKTAILS)) {
            if (!(DungeonsDelight.CONFIG.getCocktailCooldownTicks() == 0)) {
                player.getCooldowns().addCooldown(item.value(), DungeonsDelight.CONFIG.getCocktailCooldownTicks());
            }
        }

        if (!level.isClientSide) {
            ThrownCocktail thrownCocktail = new ThrownCocktail(level, player, particleColor,
                    type, mobEffects, effectDurations, effectAmplifiers, effectMaxDurations, hunger, saturation);
            thrownCocktail.setItem(heldStack);
            thrownCocktail.shootFromRotation(player, player.getXRot(), player.getYRot(), -20.0F, 0.5F, 1.0F);
            level.addFreshEntity(thrownCocktail);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            heldStack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(heldStack, level.isClientSide());
    }
}