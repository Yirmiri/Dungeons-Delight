package net.yirmiri.dungeonsdelight.core.mixin.hollowing;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.common.entity.misc.IHollowable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Villager.class)
public class VillagerHollowMixin extends HollowingMixin implements IHollowable {
    @Override
    public Mob dungeonsdelight$convertViaHollowable(ServerLevel level, Player player) {
        Villager villager = (Villager)(Object)this;
        if (player instanceof ServerPlayer svpl) {
            level.onReputationEvent(ReputationEventType.VILLAGER_KILLED, svpl, villager);
        }

        ZombieVillager zombie = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
        if (zombie != null) {
            zombie.finalizeSpawn(level, level.getCurrentDifficultyAt(zombie.blockPosition()), MobSpawnType.CONVERSION, null, null);
        }

        return zombie;
    }

    @Override
    public boolean dungeonsdelight$isHollowing() {
        return this.dungeonsdelight$isHollowing;
    }
}