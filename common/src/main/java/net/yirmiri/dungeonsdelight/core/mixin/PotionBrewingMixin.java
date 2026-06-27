package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PotionBrewing.class)
public interface PotionBrewingMixin {

    @Invoker("addMix")
    static void addMix(Potion input, Item reagent, Potion output) {
        throw new AssertionError();
    }
}