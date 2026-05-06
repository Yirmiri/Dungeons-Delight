package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

public class DDModelProvider extends FabricModelProvider {
    public DDModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        // MISC
        generator.generateFlatItem(DDItems.LOGO_ITEM.get().asItem(), ModelTemplates.FLAT_ITEM);
        // DISCS
        generator.generateFlatItem(DDItems.MUSIC_DISC_MALADY.get().asItem(), ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(DDItems.MUSIC_DISC_MALADY_B_SIDE.get().asItem(), ModelTemplates.FLAT_ITEM);
    }
}
