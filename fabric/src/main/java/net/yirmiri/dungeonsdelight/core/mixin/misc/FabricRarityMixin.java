package net.yirmiri.dungeonsdelight.core.mixin.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
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

@Mixin(Rarity.class)
public class FabricRarityMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static Rarity newRarity(String internalName, int internalId, ChatFormatting formatter) {
        throw new AssertionError();
    }

    @SuppressWarnings("ShadowTarget")
    @Shadow @Final @Mutable private static Rarity[] $VALUES;

    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = 179, // PUTSTATIC
            target = "Lnet/minecraft/world/item/Rarity;$VALUES:[Lnet/minecraft/world/item/Rarity;",
            shift = At.Shift.AFTER))
    private static void addCustomRarity(CallbackInfo ci) {
        var rarities = new ArrayList<>(Arrays.asList($VALUES));
        var last = rarities.get(rarities.size() - 1);

        //1.21 CODE
        var monster = newRarity(DDRarities.MONSTER_STRING, last.ordinal() + 1, ChatFormatting.RED);
        DDRarities.MONSTER = monster;
        rarities.add(monster);

        $VALUES = rarities.toArray(new Rarity[0]);
    }
}
