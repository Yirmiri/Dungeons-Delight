package net.yirmiri.dungeonsdelight.datagen.tag;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.integration.IntegrationIds;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;

import java.util.concurrent.CompletableFuture;

public class DDEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public DDEntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendImpactProjectiles();
        appendReapsSpiderMeat();
        appendReapsCreeperilla();
        appendReapsRottenTripe();
        appendReapsSlimeNoodles();
        appendReapsGhastTentacle();
        appendReapsSilverfishAbdomen();
        appendReapsSnifferShank();
        appendCanHollow();
        appendProducesSpiderExtract();
        appendHasPotentSpiderExtract();
        appendReapsRavagerHaunch();
    }

    private void appendCanHollow() {
        getOrCreateTagBuilder(DDTags.EntityT.CAN_HOLLOW)
                .add(EntityType.VILLAGER)
                .add(EntityType.HORSE)
                .add(EntityType.PIGLIN)
                .add(EntityType.HOGLIN)
                .add(EntityType.CAMEL)
        ;
    }

    private void appendImpactProjectiles() {
        getOrCreateTagBuilder(EntityTypeTags.IMPACT_PROJECTILES)
                .add(DDEntities.CLEAVER.get())
        ;
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
                .add(DDEntities.CAMEL_HUSK.get())
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

    private void appendReapsGhastTentacle() {
        getOrCreateTagBuilder(DDTags.EntityT.REAPS_GHAST_TENTACLE)
                .add(EntityType.GHAST)
                //INTEGRATION
                .addOptionalTag(RunicLib.customid(IntegrationIds.TF_ID, "carminite_ghastling"))
                .addOptionalTag(RunicLib.customid(IntegrationIds.TF_ID, "ur_ghast"))
                .addOptionalTag(RunicLib.customid(IntegrationIds.AE_ID, "carminite_ghastguard"))
        ;
    }

    private void appendProducesSpiderExtract() {
        getOrCreateTagBuilder(DDTags.EntityT.PRODUCES_SPIDER_EXTRACT)
                .add(EntityType.SPIDER)
                .add(EntityType.CAVE_SPIDER)
        ;
    }

    private void appendHasPotentSpiderExtract() {
        getOrCreateTagBuilder(DDTags.EntityT.HAS_POTENT_SPIDER_EXTRACT)
                .add(EntityType.CAVE_SPIDER)
        ;
    }

    private void appendReapsSilverfishAbdomen() {
        getOrCreateTagBuilder(DDTags.EntityT.REAPS_SILVERFISH_ABDOMEN)
                .add(EntityType.SILVERFISH)
        ;
    }

    private void appendReapsSnifferShank() {
        getOrCreateTagBuilder(DDTags.EntityT.REAPS_SNIFFER_SHANK)
                .add(EntityType.SNIFFER)
        ;
    }

    private void appendReapsRavagerHaunch() {
        getOrCreateTagBuilder(DDTags.EntityT.REAPS_RAVAGER_HAUNCH)
                .add(EntityType.RAVAGER)
        ;
    }
}