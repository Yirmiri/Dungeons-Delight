package net.yirmiri.dungeonsdelight.item.compat.twilightforest;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.item.compat.CompatKnifeItem;
import net.yirmiri.dungeonsdelight.util.DDUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KnightmetalKnifeItem extends CompatKnifeItem {
    public KnightmetalKnifeItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(DDUtil.TF_ID, tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, level, components, flag);
        components.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
    }
}
