package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.function.Supplier;

public class DDLangProvider extends FabricLanguageProvider {
    public DDLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder build) {
        //MISC
        build.add("itemgroup.dungeonsdelight", "Dungeon's Delight");

        //ITEMS
        addWithYT(build, DDItems.LOGO_ITEM.get(), "Logo Item", "you probably aren't meant to have this");
    }

    private void addDamage(TranslationBuilder build, ResourceKey<DamageType> type, String deathMsg, String killMsg) {
        build.add(type.location().toLanguageKey(), deathMsg);
        build.add("death.attack." + type.location().toLanguageKey(), deathMsg);
        build.add("death.attack." + type.location().toLanguageKey() + ".player", killMsg);
    }

    public static void addYT(FabricLanguageProvider.TranslationBuilder build, Block block, String tooltip) {
        build.add("yapping_tooltips." + block.getDescriptionId() + ".desc", tooltip);
    }

    public static void addYT(FabricLanguageProvider.TranslationBuilder build, Supplier<Block> block, String tooltip) {
        build.add("yapping_tooltips." + block.get().getDescriptionId() + ".desc", tooltip);
    }

    public static void addYT(FabricLanguageProvider.TranslationBuilder builder, Item item, String tooltip) {
        builder.add("yapping_tooltips." + item.getDescriptionId() + ".desc", tooltip);
    }

    public static void addWithYT(FabricLanguageProvider.TranslationBuilder build, Item item, String name, String ytDesc) {
        build.add(item, name);
        addYT(build, item, ytDesc);
    }

    public static void addWithYT(FabricLanguageProvider.TranslationBuilder build, Block block, String name, String ytDesc) {
        build.add(block, name);
        addYT(build, block, ytDesc);
    }
}
