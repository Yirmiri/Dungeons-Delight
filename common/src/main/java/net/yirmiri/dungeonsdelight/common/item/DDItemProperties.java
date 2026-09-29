package net.yirmiri.dungeonsdelight.common.item;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDItemProperties {
    public static final ResourceLocation SLIC_EATING = RunicLib.customid(DungeonsDelight.MOD_ID, "eating");

    public static float slicorice(ItemStack stack, ClientLevel level, LivingEntity entity, int seed) {
        if (entity == null || level == null) return -1.0F;
        return (entity.isUsingItem() && entity.getItemInHand(entity.getUsedItemHand()).equals(stack)) ? 1.0F : 0.0F;
    };
}
