package net.yirmiri.dungeonsdelight.core.mixin;

import com.google.gson.JsonElement;
import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;
import net.yirmiri.dungeonsdelight.core.integration.DDIntegration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "apply*", at = @At("HEAD"))
    private void dungeonsdelight$apply(Map<ResourceLocation, JsonElement> recipes, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci) {
        if (!Services.PLATFORM.isModLoaded(DDIntegration.NV_ID)) {
            recipes.remove(RunicLib.customid(DDIntegration.NV_ID, "creepers_lettuce"));
        }
    }
}