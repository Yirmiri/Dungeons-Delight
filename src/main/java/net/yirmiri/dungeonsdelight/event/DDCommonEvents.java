package net.yirmiri.dungeonsdelight.event;

import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDItems;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class DDCommonEvents {
    @SubscribeEvent
    public static void missingMappingsEvent(MissingMappingsEvent event) {
        Map<ResourceLocation, Supplier<Item>> itemsMap = new ImmutableMap.Builder<ResourceLocation, Supplier<Item>>()
                .put(new ResourceLocation(DungeonsDelight.MOD_ID, "slime_slab"), DDItems.SLIME_BAR)
                .build();

        Map<ResourceLocation, Supplier<Block>> blocksMap = new ImmutableMap.Builder<ResourceLocation, Supplier<Block>>()
                .put(new ResourceLocation(DungeonsDelight.MOD_ID, "dungeon_pot"), DDBlocks.MONSTER_POT)
                .build();

        for (MissingMappingsEvent.Mapping<Item> itemMapping : event.getMappings(ForgeRegistries.Keys.ITEMS, DungeonsDelight.MOD_ID)) {
            if (itemsMap.get(itemMapping.getKey()) != null) {
                itemMapping.remap(Objects.requireNonNull(itemsMap.get(itemMapping.getKey())).get());
            }
        }

        for (MissingMappingsEvent.Mapping<Block> blockMapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, DungeonsDelight.MOD_ID)) {
            if (blocksMap.get(blockMapping.getKey()) != null) {
                blockMapping.remap(Objects.requireNonNull(blocksMap.get(blockMapping.getKey())).get());
            }
        }
    }
}
