package net.yirmiri.dungeonsdelight.mixin;

import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.recipe.Ingredient;
import net.yirmiri.dungeonsdelight.util.DDTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AllayEntity.class)
public class AllayEntityMixin {

    @Mutable @Shadow @Final private static Ingredient DUPLICATION_INGREDIENT;

    static {
        DUPLICATION_INGREDIENT = Ingredient.fromTag(DDTags.ItemT.ALLAY_DUPLICATING_ITEMS);
    }
}
