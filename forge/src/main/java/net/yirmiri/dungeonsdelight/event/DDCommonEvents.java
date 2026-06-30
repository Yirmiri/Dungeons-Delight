package net.yirmiri.dungeonsdelight.event;

import com.google.common.collect.ImmutableMap;
import net.azurune.runiclib.RunicLib;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.networking.ForgeDDNetworking;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDRegistries;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class DDCommonEvents {

    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        DDRegistries.load();

        ForgeDDNetworking.init();
    }

    @SubscribeEvent
    public static void missingMappingsEvent(MissingMappingsEvent event) { //todo add mapping migration from older dungeonsdelight versions
        Map<ResourceLocation, Supplier<Item>> itemsMap = new ImmutableMap.Builder<ResourceLocation, Supplier<Item>>()
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "smoked_spider_meat"), DDItems.COOKED_SPIDER_MEAT)
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "soaked_skewer"), DDItems.FOUL_SKEWER)
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "spider_salmagundi"), DDItems.SALMAGUNDI)
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "sculk_mayo"), DDItems.SCULK_MAYONNAISE)
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "sculk_mayo_block"), (Supplier<Item>) DDBlocks.SCULK_MAYONNAISE_BLOCK.get().asItem())
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "heap_of_ancient_eggs"), (Supplier<Item>) DDBlocks.EMBEDDED_EGGS.get().asItem())
                .build();

        Map<ResourceLocation, Supplier<Block>> blocksMap = new ImmutableMap.Builder<ResourceLocation, Supplier<Block>>()
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "sculk_mayo_block"), (Supplier<Block>) DDBlocks.SCULK_MAYONNAISE_BLOCK.get())
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "heap_of_ancient_eggs"), (Supplier<Block>) DDBlocks.EMBEDDED_EGGS.get())
                .build();

        Map<ResourceLocation, Item> vanillaItemsMap = new ImmutableMap.Builder<ResourceLocation, Item>()
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "brined_flesh"), Items.ROTTEN_FLESH)
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "gritty_flesh"), Items.ROTTEN_FLESH)
                .put(RunicLib.customid(DungeonsDelight.MOD_ID, "slime_bar"), Items.SLIME_BALL)
                .build();

        Map<ResourceLocation, Block> vanillaBlocksMap = new ImmutableMap.Builder<ResourceLocation, Block>()
                .build();

        for (MissingMappingsEvent.Mapping<Item> itemMapping : event.getMappings(ForgeRegistries.Keys.ITEMS, DungeonsDelight.MOD_ID)) {
            if (itemsMap.get(itemMapping.getKey()) != null) {
                itemMapping.remap(Objects.requireNonNull(itemsMap.get(itemMapping.getKey())).get());
            }
            if (vanillaItemsMap.get(itemMapping.getKey()) != null) {
                itemMapping.remap(Objects.requireNonNull(vanillaItemsMap.get(itemMapping.getKey())));
            }
        }

        for (MissingMappingsEvent.Mapping<Block> blockMapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, DungeonsDelight.MOD_ID)) {
            if (blocksMap.get(blockMapping.getKey()) != null) {
                blockMapping.remap(Objects.requireNonNull(blocksMap.get(blockMapping.getKey())).get());
            }
            if (vanillaBlocksMap.get(blockMapping.getKey()) != null) {
                blockMapping.remap(Objects.requireNonNull(vanillaBlocksMap.get(blockMapping.getKey())));
            }
        }
    }
}
