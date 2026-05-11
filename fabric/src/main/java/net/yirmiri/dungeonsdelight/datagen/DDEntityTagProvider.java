package net.yirmiri.dungeonsdelight.datagen;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.integration.IntegrationIds;

import java.util.concurrent.CompletableFuture;

public class DDEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public DDEntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendReapsSpiderMeat();
        appendReapsCreeperilla();
        appendReapsRottenTripe();
        appendReapsSlimeNoodles();
    }

    private void appendReapsSpiderMeat() {
        getOrCreateTagBuilder(DDTags.EntityT.REAPS_SPIDER_MEAT)
                .add(EntityType.SPIDER)
                .add(EntityType.CAVE_SPIDER)
                //INTEGRATION
                .addOptionalTag(RunicLib.customid(IntegrationIds.TF_ID, "hedge_spider"))
                .addOptionalTag(RunicLib.customid(IntegrationIds.TF_ID, "king_spider"))
                .addOptionalTag(RunicLib.customid(IntegrationIds.TF_ID, "carminite_broodling"))
                .addOptionalTag(RunicLib.customid(IntegrationIds.TF_ID, "swarm_spider"))
        ;
    }

    private void appendReapsCreeperilla() {
        getOrCreateTagBuilder(DDTags.EntityT.REAPS_CREEPERILLA)
                .add(EntityType.CREEPER)
        ;
    }

    private void appendReapsRottenTripe() {
        getOrCreateTagBuilder(DDTags.EntityT.REAPS_ROTTEN_TRIPE)
                .add(EntityType.ZOMBIE)
                .add(EntityType.ZOMBIE_VILLAGER)
                .add(EntityType.ZOMBIFIED_PIGLIN)
                .add(EntityType.ZOGLIN)
                .add(EntityType.HUSK)
                .add(EntityType.DROWNED)
                .add(EntityType.ZOMBIE_HORSE)
        ;
    }

    private void appendReapsSlimeNoodles() {
        getOrCreateTagBuilder(DDTags.EntityT.REAPS_SLIME_NOODLES)
                .add(EntityType.SLIME)
                //INTEGRATION
                .addOptionalTag(RunicLib.customid(IntegrationIds.TF_ID, "slime_beetle"))
                .addOptionalTag(RunicLib.customid(IntegrationIds.TF_ID, "maze_slime"))
                .addOptionalTag(RunicLib.customid(IntegrationIds.AE_ID, "blue_swet"))
                .addOptionalTag(RunicLib.customid(IntegrationIds.AE_ID, "golden_swet"))
        ;
    }
}