package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import net.yirmiri.dungeonsdelight.core.init.DDDecoratedPotPatterns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// TODO: 1.21.1 replaces all inst of ResourceKey<String> with ResourceKey<DecoratedPotPattern>!
@Mixin(DecoratedPotPatterns.class)
public abstract class DecoratedPotPatternsMixin {
    @Inject(method = "getResourceKey", at = @At("RETURN"), cancellable = true)
    private static void dungeonsDelight$getPatternFromItem(Item item, CallbackInfoReturnable<ResourceKey<String>> cir) {
        if (DDDecoratedPotPatterns.map().containsKey(item)) {
            cir.setReturnValue(DDDecoratedPotPatterns.map().get(item));
        }
    }

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void dungeonsDelight$bootstrap(Registry<String> registry, CallbackInfoReturnable<String> cir) {
        DDDecoratedPotPatterns.register(registry, DDDecoratedPotPatterns.GLUTTONY, "gluttony_pottery_pattern");
        DDDecoratedPotPatterns.register(registry, DDDecoratedPotPatterns.TITANIC, "titanic_pottery_pattern");
    }
}