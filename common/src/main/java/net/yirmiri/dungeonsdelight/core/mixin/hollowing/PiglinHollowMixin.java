package net.yirmiri.dungeonsdelight.core.mixin.hollowing;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.common.entity.misc.IHollowable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Piglin.class)
public abstract class PiglinHollowMixin extends HollowingMixin implements IHollowable {
    @Override
    public Mob dungeonsdelight$convertViaHollowable(ServerLevel level, Player player) {
        Piglin piglin = (Piglin)(Object)this;
        ZombifiedPiglin zombie = piglin.convertTo(EntityType.ZOMBIFIED_PIGLIN, true);

        if (zombie != null) {
            zombie.finalizeSpawn(level, level.getCurrentDifficultyAt(zombie.blockPosition()),
                    MobSpawnType.CONVERSION, null, null);
        }
        return zombie;
    }

    @Override
    public boolean dungeonsdelight$isHollowing() {
        return this.dungeonsdelight$isHollowing;
    }
}
