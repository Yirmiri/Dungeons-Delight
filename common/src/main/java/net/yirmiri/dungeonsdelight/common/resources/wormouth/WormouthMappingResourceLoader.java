package net.yirmiri.dungeonsdelight.common.resources.wormouth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.HashMap;
import java.util.Map;

public class WormouthMappingResourceLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GERSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final Map<ResourceLocation, WormouthMapping> def = new HashMap<>();
    public static final String LOCATION = "dungeonsdelight/wormouth";

    public WormouthMappingResourceLoader() {
        super(GERSON, LOCATION);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        def.clear();
        for (Map.Entry<ResourceLocation, JsonElement> entry : resourceLocationJsonElementMap.entrySet()) {
            ResourceLocation id = entry.getKey();
            JsonElement value = entry.getValue();
            try {
                WormouthMapping mapping = WormouthMapping.CODEC.parse(JsonOps.INSTANCE, value).getOrThrow(true, DungeonsDelight.LOGGER::info);
                if (mapping.item().isPresent() && mapping.tag().isPresent()) throw new JsonParseException("Both \"tag\" and \"item\" fields were provided; only one can be chosen, please use the one that best suits the case");
                else if (mapping.item().isEmpty() && mapping.tag().isEmpty()) throw new JsonParseException("Both \"tag\" and \"item\" fields are empty");
                else def.put(id, mapping);
            }
            catch (Exception exception) {
                DungeonsDelight.LOGGER.error("Failed to load Wormouth action mapping '{}'", id, exception);
            }
        }
        WormouthMappings.MAPS.putAll(def);
    }
}
