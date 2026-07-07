package net.yirmiri.dungeonsdelight.mixin.recipe;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookCategories;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(RecipeBookCategories.class)
public class FabricRecipeBookCatMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static RecipeBookCategories newCat(String internalName, int internalId, ItemStack... stack) {
        throw new AssertionError();
    }

    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    RecipeBookCategories[] $VALUES;

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = 179, // PUTSTATIC
            target = "Lnet/minecraft/client/RecipeBookCategories;$VALUES:[Lnet/minecraft/client/RecipeBookCategories;",
            shift = At.Shift.AFTER))
    private static void dundelight$addCustomRecDescSomethingStopItIdc(CallbackInfo ci) {
        var values = new ArrayList<>(Arrays.asList($VALUES));
        var last = values.get(values.size() - 1);
        int i = last.ordinal() + 1;

        // DD Monsterpot - Search
        var search = newCat(DDRecipeBookCategories.MP_SEARCH_ID, i);
        DDRecipeBookCategories.DD_MONSTERPOT_SEARCH = search;
        values.add(search);
        i++;

        // DD Monsterpot - Meals
        var meals = newCat(DDRecipeBookCategories.MP_MEALS_ID, i);
        DDRecipeBookCategories.DD_MONSTERPOT_MEALS = meals;
        values.add(meals);
        i++;

        // DD Monsterpot - Drinks
        var drinks = newCat(DDRecipeBookCategories.MP_DRINKS_ID, i);
        DDRecipeBookCategories.DD_MONSTERPOT_DRINKS = drinks;
        values.add(drinks);
        i++;

        // DD Monsterpot - Misc
        var misc = newCat(DDRecipeBookCategories.MP_MISC_ID, i);
        DDRecipeBookCategories.DD_MONSTERPOT_MISC = misc;
        values.add(misc);
        i++;

        $VALUES = values.toArray(new RecipeBookCategories[0]);
        DDRecipeBookCategories.readyUpCategories();
    }
}