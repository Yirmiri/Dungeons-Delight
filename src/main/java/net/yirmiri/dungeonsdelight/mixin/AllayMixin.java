package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.item.crafting.Ingredient;
import net.yirmiri.dungeonsdelight.init.DDTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Allay.class)
public class AllayMixin {
    @Mutable @Shadow @Final private static Ingredient DUPLICATION_ITEM;

    static {
        DUPLICATION_ITEM = Ingredient.of(DDTags.ItemT.ALLAY_DUPLICATING_ITEMS);
    }
}
