package net.yirmiri.dungeonsdelight.common.resources.wormouth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Map;

public class WormouthMappingResourceLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GERSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    public WormouthMappingResourceLoader(Gson gson, String directory)
    {
        super(GERSON, directory);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {

        // for later; if doing the either/or route for providing a tag, use these errors
        // when neither an item nor tag ID is provided:
        // throw new JsonParseException("Both \"tag\" and \"item\" fields are empty");
        // when both are provided
        // throw new JsonParseException("Both \"tag\" and \"item\" fields were provided; only one can be chosen, please use the one that best suits the case");
    }
}
