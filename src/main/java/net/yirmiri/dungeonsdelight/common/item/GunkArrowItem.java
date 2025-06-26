package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.entity.misc.GunkArrowEntity;

public class GunkArrowItem extends ArrowItem {
    public GunkArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public GunkArrowEntity createArrow(Level level, ItemStack stack, LivingEntity living) {
        return new GunkArrowEntity(living, level);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        return false;
    }
}
