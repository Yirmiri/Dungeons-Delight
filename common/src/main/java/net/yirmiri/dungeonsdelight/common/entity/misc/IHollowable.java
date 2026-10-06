package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public interface IHollowable {
    Mob dungeonsdelight$convertViaHollowable(ServerLevel level, Player player);
    boolean dungeonsdelight$isHollowing();
}
