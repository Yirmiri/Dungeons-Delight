package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.yirmiri.dungeonsdelight.core.init.DDDecoratedPotPatterns;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.world.level.block.entity.DecoratedPotPatterns.class)
public abstract class DecoratedPotPatternsMixin {

    @Inject(method = "getPatternFromItem", at = @At("RETURN"), cancellable = true)
    private static void dungeonsDelight$getPatternFromItem(Item item, CallbackInfoReturnable<ResourceKey<DecoratedPotPattern>> cir) {
        if (item == DDItems.GLUTTONY_POTTERY_SHERD.get()) {
            cir.setReturnValue(DDDecoratedPotPatterns.GLUTTONY);
        }
    }

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void dungeonsDelight$bootstrap(Registry<DecoratedPotPattern> registry, CallbackInfoReturnable<DecoratedPotPattern> cir) {
        DDDecoratedPotPatterns.register(registry, DDDecoratedPotPatterns.GLUTTONY, "gluttony_pottery_pattern");
    }
}