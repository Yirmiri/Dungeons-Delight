package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.monster_pot.MonsterPotMenu;

import java.util.function.Supplier;

public class DDMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, DungeonsDelight.MOD_ID);

    public static final Supplier<MenuType<MonsterPotMenu>> MONSTER_POT = MENU_TYPES.register("monster_pot", () -> IMenuTypeExtension.create(MonsterPotMenu::new));
}
