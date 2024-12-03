package net.yirmiri.dungeonsdelight.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDItemGroups {
    public static ItemGroup DUNGEONS_DELIGHT = Registry.register(Registries.ITEM_GROUP, Identifier.of(DungeonsDelight.MOD_ID, "dungeons_delight"),
            FabricItemGroup.builder().icon(() -> new ItemStack(DDItems.LOGO_ITEM))
                    .displayName(Text.translatable("itemgroup.dungeons_delight")).entries((ctx, entries) -> {
                entries.add(DDItems.LOGO_ITEM);
            }).build());

    public static void loadItemGroups() {
    }
}
