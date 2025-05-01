package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BaseSpawner;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseSpawner.class)
public class BaseSpawnerMixin {
    @Shadow private int minSpawnDelay;

    @Shadow private int maxSpawnDelay;

    @Shadow private int spawnCount;

    @Inject(at = @At("HEAD"), method = "serverTick")
    private void dungeonsdelight$serverTick(ServerLevel level, BlockPos pos, CallbackInfo ci) {
        if (level.getBlockState(pos.above(1)).is(DDBlocks.MONSTER_POT.get())) {
            minSpawnDelay = 100;
            maxSpawnDelay = 400;
            spawnCount = 6;
        } else {
            minSpawnDelay = 200;
            maxSpawnDelay = 800;
            spawnCount = 4;
        }
    }
}
