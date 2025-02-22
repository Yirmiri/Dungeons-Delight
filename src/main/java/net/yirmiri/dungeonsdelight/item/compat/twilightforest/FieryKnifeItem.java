package net.yirmiri.dungeonsdelight.item.compat.twilightforest;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.item.compat.CompatKnifeItem;
import net.yirmiri.dungeonsdelight.util.DDUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FieryKnifeItem extends CompatKnifeItem {
    public FieryKnifeItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(DDUtil.TF_ID, tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, level, components, flag);
        components.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.FIRE_ASPECT && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack enchantBook) {
        return !EnchantmentHelper.getEnchantments(enchantBook).containsKey(Enchantments.FIRE_ASPECT) && super.isBookEnchantable(stack, enchantBook);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.fireImmune()) {
            target.setSecondsOnFire(15);
            target.setSecondsOnFire(target.getRemainingFireTicks() + 1);
        }
        if (target.level().isClientSide) {
            for (int var1 = 0; var1 < 20; ++var1) {
                double px = target.getX() + (target.level().getRandom().nextFloat() * target.getBbWidth() * 2.0F) - target.getBbWidth();
                double py = target.getY() + (target.level().getRandom().nextFloat() * target.getBbHeight());
                double pz = target.getZ() + (target.level().getRandom().nextFloat() * target.getBbWidth() * 2.0F) - target.getBbWidth();
                target.level().addParticle(ParticleTypes.FLAME, px, py, pz, 0.02, 0.02, 0.02);
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
