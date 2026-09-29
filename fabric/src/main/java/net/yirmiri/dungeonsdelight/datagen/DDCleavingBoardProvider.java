package net.yirmiri.dungeonsdelight.datagen;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.resources.cleaving_board.CleavingBoardMapping;
import net.yirmiri.dungeonsdelight.common.resources.cleaving_board.CleavingBoardMappingResourceLoader;
import net.yirmiri.dungeonsdelight.core.init.DDLootTables;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public class DDCleavingBoardProvider implements DataProvider {
    protected final FabricDataOutput dataOutput;
    private final String mod;
    private final CompletableFuture<HolderLookup.Provider> registryLookup;

    public DDCleavingBoardProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        this.mod = DungeonsDelight.MOD_ID;
        this.dataOutput = dataOutput;
        this.registryLookup = registryLookup;
    }

    //Use this to generate cleaving board mappers
    private void generate(HolderLookup.Provider lookup, DDCleavingBoardProvider.MapperFactory factory) {
        //ITEM
        factory.addItem(Items.ROTTEN_FLESH, DDLootTables.CLEAVING_BOARD_ROTTEN_FLESH, 0);
        factory.addItem(DDItems.CREEPERILLA.get(), DDLootTables.CLEAVING_BOARD_CREEPERILLA, 0);
        factory.addItem(Items.SLIME_BALL, DDLootTables.CLEAVING_BOARD_SLIME_BALL, 0);
        factory.addItem(DDItems.ROTBULB.get(), DDLootTables.CLEAVING_BOARD_ROTBULB, 0);
        factory.addItem(DDItems.GUNK.get(), DDLootTables.CLEAVING_BOARD_GUNK, 0);
        factory.addItem(DDItems.ANCIENT_EGG.get(), DDLootTables.CLEAVING_BOARD_ANCIENT_EGG, 0);
        factory.addItem(DDItems.GHAST_TENTACLE.get(), DDLootTables.CLEAVING_BOARD_GHAST_TENTACLE, 0);
        factory.addItem(Items.MAGMA_CREAM, DDLootTables.CLEAVING_BOARD_MAGMA_CREAM, 0);
        //factory.addItem(DDItems.WARDENZOLA, DDLootTables.CLEAVING_BOARD_WARDENZOLA, 0);
        // TAG
        //factory.addTag(DDTags.ItemT.CLEAVERS, BuiltInLootTables.CLERIC_GIFT, 0);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // NOTE FOR OTHER DD DEVS: Unless you know how it works, you don't need to mess with the code below this point.
    // The generate method is all you need to use. Messing with anything below could seriously mess up the data generator.
    // - Artyrian
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return this.registryLookup.thenCompose((lookup) -> {
            List<CompletableFuture<?>> futures = new ArrayList<>();

            TreeMap<String, JsonObject> mapper = new TreeMap<>();
            this.generate(lookup, (name, raw, isTag, table, exp) -> {
                Objects.requireNonNull(table);
                if (mapper.containsKey(name)) throw new IllegalArgumentException(String.format("Duplicate definition for %1s", name));
                else {
                    JsonObject json = new JsonObject();
                    jsonRegister(json, isTag, raw, table.toString(), exp);
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
                                .resolve(CleavingBoardMappingResourceLoader.LOCATION)
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
    public String getName() { return "Dungeon's Delight - Cleaving Board"; }

    @FunctionalInterface
    private interface MapperFactory {
        default void addTag(TagKey<Item> tag, ResourceLocation table, int expGrant) {
            ResourceLocation key = tag.location();
            this.add(key.getPath() + "_tag_cleaving_board", key.toString(), true, table, expGrant);
        }

        default void addItem(Item item, ResourceLocation table, int expGrant) {
            ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
            this.add(key.getPath() + "_item_cleaving_board", key.toString(), false, table, expGrant);
        }

        void add(String name, String raw, boolean isTag, ResourceLocation table, int expGrant);
    }

    private static void jsonRegister(JsonObject json, boolean isTag, String rawItem, String table, int exp) {
        json.addProperty((isTag) ? CleavingBoardMapping.TAG : CleavingBoardMapping.ITEM, rawItem);
        json.addProperty(CleavingBoardMapping.LOOT, table);
        json.addProperty(CleavingBoardMapping.EXP, exp);
    }
}