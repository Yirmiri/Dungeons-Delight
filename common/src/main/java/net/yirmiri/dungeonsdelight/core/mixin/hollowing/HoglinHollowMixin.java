package net.yirmiri.dungeonsdelight.core.mixin.hollowing;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.common.entity.misc.IHollowable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Hoglin.class)
public abstract class HoglinHollowMixin extends HollowingMixin implements IHollowable {
    @Override
    public Mob dungeonsdelight$convertViaHollowable(ServerLevel level, Player player) {
        Hoglin hoglin = (Hoglin)(Object)this;
        Zoglin zombie = hoglin.convertTo(EntityType.ZOGLIN, true);

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
