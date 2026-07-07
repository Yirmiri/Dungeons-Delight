package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.menu.MonsterPotMenu;

import java.util.function.Supplier;

public class DDMenus {
    public static final Supplier<MenuType<MonsterPotMenu>> MONSTER_POT = register("monster_pot", MonsterPotMenu::new);

    private static <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String id, RLRegistryHelper.MenuSupplier<T> factory) {
        return Services.REGISTRY.registerMenu(DungeonsDelight.MOD_ID, id, factory);
    }

    public static void load() {
    }
}
