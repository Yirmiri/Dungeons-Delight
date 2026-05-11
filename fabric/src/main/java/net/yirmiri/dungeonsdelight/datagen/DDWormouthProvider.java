package net.yirmiri.dungeonsdelight.datagen;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappingResourceLoader;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.init.DDLootTables;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public class DDWormouthProvider implements DataProvider {
    private static final String TAG = "tag";
    private static final String ITEM = "item";
    private static final String LOOT = "table";
    private static final String EXHAUST = "exhaust";

    protected final FabricDataOutput dataOutput;
    private final String mod;
    private final CompletableFuture<HolderLookup.Provider> registryLookup;

    public DDWormouthProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        this.mod = DungeonsDelight.MOD_ID;
        this.dataOutput = dataOutput;
        this.registryLookup = registryLookup;
    }

    //Use this to generate wormouth mappers
    private void generate(HolderLookup.Provider lookup, MapperFactory factory) {
        //ITEM
        factory.addItem(DDItems.MUSIC_DISC_MALADY.get(), DDLootTables.WORMOUTH_MALADY_B_SIDE, false);
        factory.addItem(DDItems.MUSIC_DISC_MALADY_B_SIDE.get(), DDLootTables.WORMOUTH_MALADY, false);
        // TAG
        factory.addTag(DDTags.ItemT.CLEAVERS, BuiltInLootTables.CLERIC_GIFT, false);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NOTE FOR OTHER DD DEVS: Do NOT mess with the code below this point; the generate method is all you need to use.
    //Messing with anything below could seriously mess up the data generator.
    //-Artyrian
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return this.registryLookup.thenCompose((lookup) -> {
            List<CompletableFuture<?>> futures = new ArrayList<>();

            TreeMap<String, JsonObject> mapper = new TreeMap<>();
            this.generate(lookup, (name, raw, isTag, table, exhaust) -> {
                Objects.requireNonNull(table);
                if (mapper.containsKey(name)) throw new IllegalArgumentException(String.format("Duplicate definition for %1s", name));
                else {
                    JsonObject json = new JsonObject();
                    json.addProperty((isTag) ? TAG : ITEM, raw);
                    json.addProperty(LOOT, table.toString());
                    json.addProperty(EXHAUST, exhaust);
                    mapper.put(name, json);
                }
            });

            for (String path : mapper.keySet())
            {
                JsonObject obj = mapper.get(path);

                futures.add(DataProvider.saveStable(
                        output,
                        obj,
                        this.dataOutput.getOutputFolder(PackOutput.Target.DATA_PACK)
                                .resolve(this.mod)
                                .resolve(WormouthMappingResourceLoader.LOCATION)
                                .resolve(path + ".json")
                ));
            }

            if (futures.isEmpty()) return CompletableFuture.allOf();

            int i = 0;
            for (CompletableFuture<?> x : futures) i++;
            CompletableFuture<?>[] array = new CompletableFuture[i];
            i = 0;
            for (CompletableFuture<?> y : futures) array[i++] = y;
            return CompletableFuture.allOf(array);
        });
    }

    @Override
    public String getName() { return "Dungeon's Delight - Wormouth Mappings"; }

    protected static class MapperDefinition {
        private final String rawItemID;
        private final boolean isTag;
        private final ResourceKey<LootTable> table;
        private final boolean doExhaust;

        private MapperDefinition(String rawItemID, boolean isTag, ResourceKey<LootTable> table, boolean doExhaust) {
            this.rawItemID = rawItemID;
            this.isTag = isTag;
            this.table = table;
            this.doExhaust = doExhaust;
        }

        private void putTo(JsonObject json) {
            json.addProperty((this.isTag) ? TAG : ITEM, this.rawItemID);
            json.addProperty(LOOT, this.table.location().toString());
            json.addProperty(EXHAUST, this.doExhaust);
        }
    }

    @FunctionalInterface
    private interface MapperFactory {
        default void addTag(TagKey<Item> tag, ResourceLocation table, boolean exhaust) {
            ResourceLocation key = tag.location();
            this.add(key.getPath() + "_tag_wormouth", key.toString(), true, table, exhaust);
        }

        default void addItem(Item item, ResourceLocation table, boolean exhaust) {
            ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
            this.add(key.getPath() + "_item_wormouth", key.toString(), false, table, exhaust);
        }

        void add(String name, String raw, boolean isTag, ResourceLocation table, boolean exhaust);
    }
}
