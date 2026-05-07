package net.yirmiri.dungeonsdelight.core.mixin.misc;

import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Rarity.class)
public abstract class ForgeRarityMixin {
    //@SuppressWarnings("InvokerTarget")
    //@Invoker("<init>")
    //private static Rarity newRarity(String internalName, int internalId, UnaryOperator<Style> styleModifier) {
    //    throw new AssertionError();
    //}
//
    //@SuppressWarnings("ShadowTarget")
    //@Shadow @Final @Mutable private static Rarity[] $VALUES;
//
    //@Inject(method = "<clinit>", at = @At(
    //        value = "FIELD",
    //        opcode = 179, // PUTSTATIC
    //        target = "Lnet/minecraft/world/item/Rarity;$VALUES:[Lnet/minecraft/world/item/Rarity;",
    //        shift = At.Shift.AFTER))
    //private static void addCustomRarity(CallbackInfo ci) {
    //    var rarities = new ArrayList<>(Arrays.asList($VALUES));
    //    var last = rarities.get(rarities.size() - 1);
//
    //    var monster = newRarity("DUNGEONSDELIGHT_MONSTER", last.ordinal() + 1, (style) -> style.withColor(DDRarity.MONSTER_COLOR));
    //    DDRarity.MONSTER = monster;
    //    rarities.add(monster);
//
    //    $VALUES = rarities.toArray(new Rarity[0]);
    //}
}
