//Thanks to @TheArtyrian (GitHub), for letting me use their mixin

package net.yirmiri.dungeonsdelight.core.mixin.client;

import com.google.common.collect.Lists;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.ProfilerFiller;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.common.util.misc.MonsterizedSplashRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(SplashManager.class)
public abstract class SplashManagerMixin {

    @Shadow @Final private static RandomSource RANDOM;
    @Unique private final List<String> dungeonsDelightsSplashes = Lists.newArrayList();

    @Unique private static final ResourceLocation DUNGEONSDELIGHT_SPLASHES = new ResourceLocation(DungeonsDelight.MOD_ID, "texts/splashes.txt");

    @ModifyReturnValue(method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Ljava/util/List;", at = @At("RETURN"))
    protected List<String> dungeonsDelights$addSplashes(List<String> original, @Local(argsOnly = true) ResourceManager resourceManager, @Local(argsOnly = true) ProfilerFiller profiler) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Minecraft.getInstance().getResourceManager().open(DUNGEONSDELIGHT_SPLASHES),
                StandardCharsets.UTF_8))) {
            List<String> customSplashes = reader.lines()
                    .map(String::trim)
                    .filter(s -> s.hashCode() != 125780783) // Exclude "missingno"
                    .collect(Collectors.toList());
            original.addAll(customSplashes);
            return original;
        } catch (IOException e) {
            // Optionally log the error
            return original;
        }
    }

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("TAIL"))
    protected void dungeonsDelights$applyNewSplashes(List<String> list, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci) {
        this.dungeonsDelightsSplashes.addAll(list);
    }

    @Inject(method = "getSplash", at = @At("HEAD"), cancellable = true)
    private void dungeonsDelights$getSplash(CallbackInfoReturnable<SplashRenderer> cir) {
        if (DDUtil.EVENTS.IS_ANNIVERSARY) cir.setReturnValue(MonsterizedSplashRenderer.ANNIVERSARY_SPLASH);
        if (!this.dungeonsDelightsSplashes.isEmpty() && RANDOM.nextInt(this.dungeonsDelightsSplashes.size()) == 66) cir.setReturnValue(MonsterizedSplashRenderer.MONSTERIZED_SPLASH);
    }
}