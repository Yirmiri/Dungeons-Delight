package net.yirmiri.dungeonsdelight.common.resources.crop_rotting;

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

public class CropRottingMappingResourceLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GERSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final Map<ResourceLocation, CropRottingMapping> def = new HashMap<>();
    public static final String LOCATION = "dungeonsdelight/crop_rotting";

    public CropRottingMappingResourceLoader() {
        super(GERSON, LOCATION);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        def.clear();
        for (Map.Entry<ResourceLocation, JsonElement> entry : resourceLocationJsonElementMap.entrySet()) {
            ResourceLocation id = entry.getKey();
            JsonElement value = entry.getValue();
            try {
                CropRottingMapping mapping = CropRottingMapping.CODEC.parse(JsonOps.INSTANCE, value).getOrThrow(true, DungeonsDelight.LOGGER::info);
                if (mapping.block().isEmpty() || mapping.rottenBlock().isEmpty()) throw new JsonParseException("Either \"block\" or \"rotten_block\" field is empty");
                else def.put(id, mapping);
            }
            catch (Exception exception) {
                DungeonsDelight.LOGGER.error("Failed to load Crop Rotting mapping '{}'", id, exception);
            }
        }
        CropRottingMappings.MAPS.putAll(def);
    }
}
