package net.yirmiri.dungeonsdelight.common.resources.cleaver;

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

public class CleaverMappingResourceLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GERSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create(); //im old!
    private final Map<ResourceLocation, CleaverMapping> def = new HashMap<>();
    public static final String LOCATION = "dungeonsdelight/reaping"; //rule of cool

    public CleaverMappingResourceLoader() {
        super(GERSON, LOCATION);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        def.clear();
        for (Map.Entry<ResourceLocation, JsonElement> entry : resourceLocationJsonElementMap.entrySet()) {
            ResourceLocation id = entry.getKey();
            JsonElement value = entry.getValue();
            try {
                CleaverMapping mapping = CleaverMapping.CODEC.parse(JsonOps.INSTANCE, value).getOrThrow(true, DungeonsDelight.LOGGER::info);
                if (mapping.entityType().isPresent() && mapping.tag().isPresent()) throw new JsonParseException("Both \"tag\" and \"entity_type\" fields were provided; only one can be chosen, please use the one that best suits the case");
                else if (mapping.entityType().isEmpty() && mapping.tag().isEmpty()) throw new JsonParseException("Both \"tag\" and \"entity_type\" fields are empty");
                else def.put(id, mapping);
            }
            catch (Exception exception) {
                DungeonsDelight.LOGGER.error("Failed to load Cleaver Reaping mapping '{}'", id, exception);
            }
        }
        CleaverMappings.MAPS.putAll(def);
    }
}
