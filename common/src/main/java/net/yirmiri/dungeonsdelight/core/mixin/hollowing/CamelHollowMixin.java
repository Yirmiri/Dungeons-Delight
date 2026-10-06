package net.yirmiri.dungeonsdelight.core.mixin.hollowing;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.IHollowable;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import org.spongepowered.asm.mixin.Mixin;

import java.util.UUID;

@Mixin(Camel.class)
public abstract class CamelHollowMixin extends HollowingMixin implements IHollowable {
    @Override
    public Mob dungeonsdelight$convertViaHollowable(ServerLevel level, Player player) {
        Camel camel = (Camel)(Object)this;
        UUID owner = camel.getOwnerUUID();
        boolean tamed = camel.isTamed();
        boolean saddle = camel.isSaddled();

        CamelHuskEntity zombie = camel.convertTo(DDEntities.CAMEL_HUSK.get(), true);

        if (zombie != null) {
            zombie.finalizeSpawn(level, level.getCurrentDifficultyAt(zombie.blockPosition()),
                    MobSpawnType.CONVERSION, null, null);

            if (tamed) {
                zombie.setTamed(true);
                zombie.setOwnerUUID(owner);
            }
            if (saddle) {
                zombie.getSlot(400).set(Items.SADDLE.getDefaultInstance());
            }
        }
        return zombie;
    }

    @Override
    public boolean dungeonsdelight$isHollowing() {
        return this.dungeonsdelight$isHollowing;
    }
}
