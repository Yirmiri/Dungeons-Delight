package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class PouncingData {
    private static final Map<UUID, PouncingData> DATA = new ConcurrentHashMap<>();
    public int charges;
    public int cooldown;
    public int initialized = -1;
    public boolean touchedGround;
    public boolean leftGround;
    public boolean isPouncing;
    public int pendingCooldown;

    public static PouncingData get(Player player) {
        return DATA.computeIfAbsent(player.getUUID(), id -> new PouncingData());
    }
}