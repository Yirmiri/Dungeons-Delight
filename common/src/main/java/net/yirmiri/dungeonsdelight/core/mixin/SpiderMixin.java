package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Spider.class)
public class SpiderMixin {
    Spider spider = (Spider) (Object) this;

    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$isClimbing(CallbackInfoReturnable<Boolean> cir) {
        Level level = spider.level();
        BlockPos pos = spider.blockPosition();

        if (level.getBlockState(pos.north()).is(DDBlocks.STAINED_SCRAP_BARS.get())
                || level.getBlockState(pos.south()).is(DDBlocks.STAINED_SCRAP_BARS.get())
                || level.getBlockState(pos.east()).is(DDBlocks.STAINED_SCRAP_BARS.get())
                || level.getBlockState(pos.west()).is(DDBlocks.STAINED_SCRAP_BARS.get())
                || spider.getBlockStateOn().is(DDBlocks.STAINED_SCRAP_BARS.get())
                || spider.getFeetBlockState().is(DDBlocks.STAINED_SCRAP_BARS.get())
        ) {
            cir.setReturnValue(false);
        }
    }
}