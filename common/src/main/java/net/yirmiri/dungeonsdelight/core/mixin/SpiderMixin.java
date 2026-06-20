package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Spider.class)
public class SpiderMixin {
    Spider spider = (Spider) (Object) this;

    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$isClimbing(CallbackInfoReturnable<Boolean> cir) {
        Level level = spider.level();
        BlockPos pos = spider.blockPosition();

        if (cancelsSpiderClimb(level, pos)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "setClimbing", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$setClimbing(boolean climbing, CallbackInfo ci) {
        Level level = spider.level();
        BlockPos pos = spider.blockPosition();

        if (cancelsSpiderClimb(level, pos)) {
            ci.cancel();
        }
    }

    private boolean cancelsSpiderClimb(Level level, BlockPos pos) {
        return level.getBlockState(pos.north()).is(DDTags.BlockT.PREVENTS_SPIDER_CLIMBING)
                || level.getBlockState(pos.south()).is(DDTags.BlockT.PREVENTS_SPIDER_CLIMBING)
                || level.getBlockState(pos.east()).is(DDTags.BlockT.PREVENTS_SPIDER_CLIMBING)
                || level.getBlockState(pos.west()).is(DDTags.BlockT.PREVENTS_SPIDER_CLIMBING)
                || spider.getBlockStateOn().is(DDTags.BlockT.PREVENTS_SPIDER_CLIMBING)
                || spider.getFeetBlockState().is(DDTags.BlockT.PREVENTS_SPIDER_CLIMBING);
    }
}