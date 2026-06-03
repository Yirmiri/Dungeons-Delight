package net.yirmiri.dungeonsdelight.datagen;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.function.Consumer;

public class DDAdvancementProvider extends FabricAdvancementProvider {
    public DDAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(DDBlocks.MONSTER_POT.get()),
                        Component.translatable("advancement.dungeonsdelight.root"),
                        Component.translatable("advancement.dungeonsdelight.root.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        false, false, false))
                .requirements(RequirementsStrategy.OR)
                .addCriterion("obtain_monster_pot", InventoryChangeTrigger.TriggerInstance.hasItems(DDItems.MONSTER_POT.get()))
                .addCriterion("obtain_stained_scrap", InventoryChangeTrigger.TriggerInstance.hasItems(DDItems.STAINED_SCRAP.get()))
                .addCriterion("killed_something", KilledTrigger.TriggerInstance.playerKilledEntity())
                .save(consumer, DungeonsDelight.MOD_ID + ":root");
    }
}