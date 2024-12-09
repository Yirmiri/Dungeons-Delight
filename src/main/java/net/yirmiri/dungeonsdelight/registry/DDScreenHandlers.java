package net.yirmiri.dungeonsdelight.registry;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.block.entity.container.DungeonPotMenu;

public class DDScreenHandlers {
    public static final ScreenHandlerType<DungeonPotMenu> DUNGEON_POT_MENU = Registry.register(Registries.SCREEN_HANDLER,
            Identifier.of(DungeonsDelight.MOD_ID, "dungeon_pot_menu"), new ExtendedScreenHandlerType<>(DungeonPotMenu::new, BlockPos.PACKET_CODEC));

    public static void loadScreenHandlers() {
    }
}