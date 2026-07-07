package net.yirmiri.dungeonsdelight.mixin.recipe;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.inventory.RecipeBookType;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Map;

@Mixin(RecipeBookSettings.class)
public class FabricRecipeBookSetMixin {
    @Shadow @Final @Mutable private static Map<RecipeBookType, Pair<String, String>> TAG_FIELDS;

    // This will put the necessary recipe type metadata into the tag fields on fabric
    static {
        Map<RecipeBookType, Pair<String, String>> preGo = new HashMap<>(Map.copyOf(TAG_FIELDS));
        preGo.put(DDRecipeBookTypes.DD_MONSTERPOT, Pair.of(DDRecipeBookTypes.DD_MP_OPEN, DDRecipeBookTypes.DD_MP_FILTERING));
        TAG_FIELDS = ImmutableMap.copyOf(preGo);
    }
}
