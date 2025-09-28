package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Rarity;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import org.objectweb.asm.Opcodes;
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
import java.util.function.UnaryOperator;

// Mixes in custom rarities from net.artyrian.frontiers.misc.ModRarity.
// ID OF RARITY FIELD: field_8905
@Mixin(Rarity.class)
public abstract class RarityMixin {
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static Rarity newRarity(String internalName, int internalId, int index, String name, UnaryOperator<Style> styleFunction) {
        throw new AssertionError();
    }

    // Get rarity field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable Rarity[] $VALUES;

    // Injects data.
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "Lnet/minecraft/world/item/Rarity;$VALUES:[Lnet/minecraft/world/item/Rarity;",
            shift = At.Shift.AFTER))
    private static void addCustomRarity(CallbackInfo ci) {
        // Get rarity list.
        var rarities = new ArrayList<>(Arrays.asList($VALUES));
        var last = rarities.get(rarities.size() - 1);

        var monsterRarity = newRarity("DUNGEONSDELIGHT_MONSTER", last.ordinal() + 1, rarities.size(),
                "dungeonsdelight_monster", style -> style.withColor(TextColor.fromRgb(0xC875C2)));
        DDProperties.MONSTER = monsterRarity;
        rarities.add(monsterRarity);

        // Inject.
        $VALUES = rarities.toArray(new Rarity[0]);
    }
}
