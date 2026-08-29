package net.yirmiri.dungeonsdelight.datagen;

import net.azurune.runiclib.RunicLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.advancement.MonsterizeEffectTrigger;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
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

        Advancement obtain_stained_scrap = Advancement.Builder.advancement()
                .parent(root).display(new DisplayInfo(new ItemStack(DDItems.STAINED_SCRAP.get()),
                        Component.translatable("advancement.dungeonsdelight.obtain_stained_scrap"),
                        Component.translatable("advancement.dungeonsdelight.obtain_stained_scrap.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("obtain_stained_scrap", InventoryChangeTrigger.TriggerInstance.hasItems(DDItems.STAINED_SCRAP.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":obtain_stained_scrap");

        Advancement place_dungeon_stove = Advancement.Builder.advancement()
                .parent(obtain_stained_scrap).display(new DisplayInfo(new ItemStack(DDBlocks.DUNGEON_STOVE.get()),
                        Component.translatable("advancement.dungeonsdelight.place_dungeon_stove"),
                        Component.translatable("advancement.dungeonsdelight.place_dungeon_stove.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("place_dungeon_stove", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(DDBlocks.DUNGEON_STOVE.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":place_dungeon_stove");

        Advancement place_monster_pot = Advancement.Builder.advancement()
                .parent(place_dungeon_stove).display(new DisplayInfo(new ItemStack(DDBlocks.MONSTER_POT.get()),
                        Component.translatable("advancement.dungeonsdelight.place_monster_pot"),
                        Component.translatable("advancement.dungeonsdelight.place_monster_pot.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("place_monster_pot", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(DDBlocks.MONSTER_POT.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":place_monster_pot");

        Advancement eat_monster_food = Advancement.Builder.advancement()
                .parent(place_monster_pot).display(new DisplayInfo(new ItemStack(DDItems.GHOULASH.get()),
                        Component.translatable("advancement.dungeonsdelight.eat_monster_food"),
                        Component.translatable("advancement.dungeonsdelight.eat_monster_food.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("eat_monster_food", ConsumeItemTrigger.TriggerInstance.usedItem(ItemPredicate.Builder.item().of(DDTags.ItemT.MONSTER_FOODS).build()))
                .save(consumer, DungeonsDelight.MOD_ID + ":eat_monster_food");

        Advancement monsterize_effect = Advancement.Builder.advancement()
                .parent(eat_monster_food).display(new DisplayInfo(new ItemStack(DDItems.FOUL_SKEWER.get()),
                        Component.translatable("advancement.dungeonsdelight.monsterize_effect"),
                        Component.translatable("advancement.dungeonsdelight.monsterize_effect.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("monsterize_effect", MonsterizeEffectTrigger.TriggerInstance.trigger())
                .save(consumer, DungeonsDelight.MOD_ID + ":monsterize_effect");

        Advancement use_cleaver = Advancement.Builder.advancement()
                .parent(root).display(new DisplayInfo(new ItemStack(DDItems.IRON_CLEAVER.get()),
                        Component.translatable("advancement.dungeonsdelight.use_cleaver"),
                        Component.translatable("advancement.dungeonsdelight.use_cleaver.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("use_cleaver", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(DamagePredicate.Builder.damageInstance().type(DamageSourcePredicate.Builder.damageType().direct(EntityPredicate.Builder.entity().of(DDEntities.CLEAVER.get())))))
                .save(consumer, DungeonsDelight.MOD_ID + ":use_cleaver");

        Advancement knife_fight = Advancement.Builder.advancement()
                .parent(use_cleaver).display(new DisplayInfo(new ItemStack(DDItems.GOLDEN_CLEAVER.get()),
                        Component.translatable("advancement.dungeonsdelight.knife_fight"),
                        Component.translatable("advancement.dungeonsdelight.knife_fight.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.CHALLENGE,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .rewards(AdvancementRewards.Builder.experience(50))
                .addCriterion("knife_fight", KilledTrigger.TriggerInstance.playerKilledEntity(
                        EntityPredicate.Builder.entity().of(EntityType.SKELETON).distance(DistancePredicate.horizontal(MinMaxBounds.Doubles.atLeast(25.0F))),
                        DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(DDTags.DamageT.CLEAVERS))))
                .save(consumer, DungeonsDelight.MOD_ID + ":knife_fight");

        Advancement obtain_netherite_cleaver = Advancement.Builder.advancement()
                .parent(use_cleaver).display(new DisplayInfo(new ItemStack(DDItems.NETHERITE_CLEAVER.get()),
                        Component.translatable("advancement.dungeonsdelight.obtain_netherite_cleaver"),
                        Component.translatable("advancement.dungeonsdelight.obtain_netherite_cleaver.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.CHALLENGE,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("obtain_netherite_cleaver", InventoryChangeTrigger.TriggerInstance.hasItems(DDItems.NETHERITE_CLEAVER.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":obtain_netherite_cleaver");

        Advancement cleaving_board = Advancement.Builder.advancement() //todo make custom criterion
                .parent(use_cleaver).display(new DisplayInfo(new ItemStack(DDBlocks.WORMWOOD_CLEAVING_BOARD.get()),
                        Component.translatable("advancement.dungeonsdelight.cleaving_board"),
                        Component.translatable("advancement.dungeonsdelight.cleaving_board.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.OR)
                .addCriterion("cleaving_board_bamboo", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(DDBlocks.BAMBOO_CLEAVING_BOARD.get()))
                .addCriterion("cleaving_board_wormwood", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(DDBlocks.WORMWOOD_CLEAVING_BOARD.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":cleaving_board");

        Advancement obtain_slime_noodles = Advancement.Builder.advancement()
                .parent(cleaving_board).display(new DisplayInfo(new ItemStack(DDItems.SLIME_NOODLES.get()),
                        Component.translatable("advancement.dungeonsdelight.obtain_slime_noodles"),
                        Component.translatable("advancement.dungeonsdelight.obtain_slime_noodles.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("obtain_slime_noodles", InventoryChangeTrigger.TriggerInstance.hasItems(DDItems.SLIME_NOODLES.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":obtain_slime_noodles");

        Advancement obtain_sculk_polyp = Advancement.Builder.advancement()
                .parent(cleaving_board).display(new DisplayInfo(new ItemStack(DDItems.SCULK_POLYP.get()),
                        Component.translatable("advancement.dungeonsdelight.obtain_sculk_polyp"),
                        Component.translatable("advancement.dungeonsdelight.obtain_sculk_polyp.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("obtain_sculk_polyp", InventoryChangeTrigger.TriggerInstance.hasItems(DDItems.SCULK_POLYP.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":obtain_sculk_polyp");

        Advancement place_embedded_eggs = Advancement.Builder.advancement()
                .parent(obtain_sculk_polyp).display(new DisplayInfo(new ItemStack(DDBlocks.EMBEDDED_EGGS.get()),
                        Component.translatable("advancement.dungeonsdelight.place_embedded_eggs"),
                        Component.translatable("advancement.dungeonsdelight.place_embedded_eggs.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("place_embedded_eggs", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(DDBlocks.EMBEDDED_EGGS.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":place_embedded_eggs");

        Advancement obtain_candied_sucker = Advancement.Builder.advancement()
                .parent(place_monster_pot).display(new DisplayInfo(new ItemStack(DDItems.CANDIED_VEX_SUCKER.get()),
                        Component.translatable("advancement.dungeonsdelight.obtain_candied_sucker"),
                        Component.translatable("advancement.dungeonsdelight.obtain_candied_sucker.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.OR)
                .addCriterion("obtain_candied_vex_sucker", InventoryChangeTrigger.TriggerInstance.hasItems(DDItems.CANDIED_VEX_SUCKER.get()))
                .addCriterion("obtain_candied_silverfish_sucker", InventoryChangeTrigger.TriggerInstance.hasItems(DDItems.CANDIED_SILVERFISH_SUCKER.get()))
                .addCriterion("obtain_candied_endermite_sucker", InventoryChangeTrigger.TriggerInstance.hasItems(DDItems.CANDIED_ENDERMITE_SUCKER.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":obtain_candied_sucker");

        Advancement obtain_terror_preta = Advancement.Builder.advancement()
                .parent(root).display(new DisplayInfo(new ItemStack(DDBlocks.MORBID_MUSH.get()),
                        Component.translatable("advancement.dungeonsdelight.obtain_terror_preta"),
                        Component.translatable("advancement.dungeonsdelight.obtain_terror_preta.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("obtain_terror_preta", InventoryChangeTrigger.TriggerInstance.hasItems(DDBlocks.MORBID_MUSH.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":obtain_terror_preta");

        Advancement plant_all_rotten_crops = Advancement.Builder.advancement()
                .parent(obtain_terror_preta).display(new DisplayInfo(new ItemStack(DDItems.BLEET_SEEDS.get()),
                        Component.translatable("advancement.dungeonsdelight.plant_all_rotten_crops"),
                        Component.translatable("advancement.dungeonsdelight.plant_all_rotten_crops.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("plant_endelves", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(DDBlocks.ENDELVES.get()))
                .addCriterion("plant_manalliums", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(DDBlocks.MANALLIUMS.get()))
                .addCriterion("plant_bleets", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(DDBlocks.BLEETS.get()))
                .addCriterion("plant_rotbulb", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(DDBlocks.ROTBULB.get()))
                .save(consumer, DungeonsDelight.MOD_ID + ":plant_all_rotten_crops");

        Advancement eat_soul_pepper = Advancement.Builder.advancement()
                .parent(root).display(new DisplayInfo(new ItemStack(DDItems.SOUL_PEPPER.get()),
                        Component.translatable("advancement.dungeonsdelight.eat_soul_pepper"),
                        Component.translatable("advancement.dungeonsdelight.eat_soul_pepper.desc"),
                        RunicLib.customid(DungeonsDelight.MOD_ID, "textures/block/wormwood_planks.png"), FrameType.TASK,
                        true, true, false))
                .requirements(RequirementsStrategy.AND)
                .addCriterion("eat_soul_pepper", ConsumeItemTrigger.TriggerInstance.usedItem(ItemPredicate.Builder.item().of(DDItems.SOUL_PEPPER.get()).build()))
                .save(consumer, DungeonsDelight.MOD_ID + ":eat_soul_pepper");
    }
}