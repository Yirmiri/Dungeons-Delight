package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class MobMixin {
    @Unique
    Mob mob = (Mob) (Object) this;

    @Inject(method = "aiStep", at = @At("TAIL"))
    private void burnInDaylight(CallbackInfo ci) {
//        if (mob instanceof ZombieHorse zombieHorse && zombieHorse.isAlive()) {
//            if (isSunBurnTickGasStation() && (!zombieHorse.isWearingArmor()) || !zombieHorse.isSaddled()) {
//                zombieHorse.setSecondsOnFire(8);
//            } //todo
//        }
    }

    protected boolean isSunBurnTickGasStation() {
        if (mob.level().isDay() && !mob.level().isClientSide) {
            float f = mob.getLightLevelDependentMagicValue();
            BlockPos blockpos = BlockPos.containing(mob.getX(), mob.getEyeY(), mob.getZ());
            boolean flag = mob.isInWaterRainOrBubble() || mob.isInPowderSnow || mob.wasInPowderSnow;
            return f > 0.5F && mob.getRandom().nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !flag && mob.level().canSeeSky(blockpos);
        }
        return false;
    }
}