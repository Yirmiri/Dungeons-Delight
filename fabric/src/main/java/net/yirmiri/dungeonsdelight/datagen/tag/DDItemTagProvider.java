package net.yirmiri.dungeonsdelight.datagen.tag;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.yirmiri.dungeonsdelight.common.util.BlockGroup;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.integration.IntegrationIds;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class DDItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public DDItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        automateGroups();
        appendMusicDiscs();
        appendCleavers();
        appendFlamingCleavers();
        appendUsesDullCleaverSound();
        appendRepairsStainedTools();
        appendRottenFleshes();
        appendReapsItems();
        appendMonsterGreens();
        appendAllayDuplicatingItems();
        appendRockCandies();
        appendPiglinLoved();
        appendHasEffectTooltip();
    }

    private void appendCleavers() {
        getOrCreateTagBuilder(DDTags.ItemT.CLEAVERS)
                .add(DDItems.FLINT_CLEAVER.get())
                .add(DDItems.IRON_CLEAVER.get())
                .add(DDItems.GOLDEN_CLEAVER.get())
                .add(DDItems.DIAMOND_CLEAVER.get())
                .add(DDItems.NETHERITE_CLEAVER.get())
        ;
    }

    private void appendRottenFleshes() {
        getOrCreateTagBuilder(DDTags.ItemT.FLESHES)
                .add(Items.ROTTEN_FLESH)
                .add(DDItems.ROTTEN_TRIPE.get())
                //INTEGRATION
                .addOptional(RunicLib.customid(IntegrationIds.CC_ID, "fresh_flesh"))
        ;
    }

    private void appendMonsterGreens() {
        getOrCreateTagBuilder(DDTags.ItemT.MONSTER_GREENS)
                .add(DDItems.CREEPERILLA.get())
                .add(DDItems.ENDELVE.get())
        ;
    }

    private void appendHasEffectTooltip() {
        getOrCreateTagBuilder(DDTags.ItemT.HAS_EFFECT_TOOLTIP)
                .add(Items.GOLDEN_APPLE)
                .add(Items.ENCHANTED_GOLDEN_APPLE)
                .add(Items.ROTTEN_FLESH)
                .add(Items.SPIDER_EYE)
                .add(Items.CHICKEN)
                .add(Items.PUFFERFISH)
                //INTEGRATION
                .addOptional(RunicLib.customid(IntegrationIds.FR_ID, "truffle_oil"))
        ;
    }

    private void appendAllayDuplicatingItems() {
        getOrCreateTagBuilder(DDTags.ItemT.ALLAY_DUPLICATING_ITEMS)
                .add(Items.AMETHYST_SHARD)
                .addTag(DDTags.ItemT.ROCK_CANDIES)
        ;
    }

    private void appendRockCandies() {
        getOrCreateTagBuilder(DDTags.ItemT.ROCK_CANDIES)
                .add(DDItems.AMETHYST_ROCK_CANDY.get())
                .add(DDItems.CANDIED_SILVERFISH_SUCKER.get())
                .add(DDItems.CANDIED_VEX_SUCKER.get())
        ;
    }

    private void appendUsesDullCleaverSound() {
        getOrCreateTagBuilder(DDTags.ItemT.USES_DULL_CLEAVER_SOUND)
                .add(DDItems.FLINT_CLEAVER.get())
        ;
    }

    private void appendReapsItems() {
        getOrCreateTagBuilder(DDTags.ItemT.REAPS_ITEMS)
                .addTag(DDTags.ItemT.CLEAVERS)
        ;
    }

    // TODO: will not exist in 1.21
    private void appendMusicDiscs() {
        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
                .add(DDItems.MUSIC_DISC_MALADY.get())
                .add(DDItems.MUSIC_DISC_MALADY_B_SIDE.get())
        ;
    }

    private void appendPiglinLoved() {
        getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED)
                .add(DDItems.GOLDEN_CLEAVER.get())
        ;
    }

    private void appendRepairsStainedTools() {
        getOrCreateTagBuilder(DDTags.ItemT.REPAIRS_STAINED_TOOLS)
                .add(DDItems.STAINED_SCRAP.get())
        ;
    }

    //INTEGRATION
    private void appendFlamingCleavers() {
        getOrCreateTagBuilder(DDTags.ItemT.FLAMING_CLEAVERS)

        ;
    }

    private void automateGroups() {
        for (BlockGroup set : BlockGroup.SETS) {
            List<Supplier<Block>> blocks = set.getRegisteredBlocks();
            Map<Supplier<Block>, BlockGroup.ModelMode> models = set.models();
            boolean wood = set.isWooden();
            for (Supplier<Block> block : blocks) {
                for (TagKey<Item> tag : set.commonItemTag) getOrCreateTagBuilder(tag).add(block.get().asItem());
                if (models.containsKey(block)) {
                    switch (models.get(block)) {
                        case TRAPDOOR -> getOrCreateTagBuilder((wood) ? ItemTags.WOODEN_TRAPDOORS : ItemTags.TRAPDOORS).add(block.get().asItem());
                        case DOOR -> getOrCreateTagBuilder((wood) ? ItemTags.WOODEN_DOORS : ItemTags.DOORS).add(block.get().asItem());
                        case SLAB -> getOrCreateTagBuilder((wood) ? ItemTags.WOODEN_SLABS : ItemTags.SLABS).add(block.get().asItem());
                        case STAIRS -> getOrCreateTagBuilder((wood) ? ItemTags.WOODEN_STAIRS : ItemTags.STAIRS).add(block.get().asItem());
                        case FENCE -> getOrCreateTagBuilder((wood) ? ItemTags.WOODEN_FENCES : ItemTags.FENCES).add(block.get().asItem());
                        case FENCE_GATE -> getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(block.get().asItem());
                        case PLATE -> { if (wood) getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(block.get().asItem()); }
                        case BUTTON -> getOrCreateTagBuilder((wood) ? ItemTags.WOODEN_BUTTONS : ItemTags.BUTTONS).add(block.get().asItem());
                    }
                }
            }
        }
    }
}
