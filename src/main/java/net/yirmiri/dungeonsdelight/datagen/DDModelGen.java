package net.yirmiri.dungeonsdelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.yirmiri.dungeonsdelight.registry.DDItems;

public class DDModelGen extends FabricModelProvider {
    public DDModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(DDItems.LOGO_ITEM, Models.GENERATED);
        generator.register(DDItems.BREEZE_CREAM_CONE, Models.HANDHELD);
        generator.register(DDItems.TRIAL_FREAKSHAKE, Models.GENERATED);
        generator.register(DDItems.SLIME_SLAB, Models.GENERATED);
        generator.register(DDItems.SLIME_NOODLES, Models.GENERATED);
        generator.register(DDItems.AMETHYST_ROCK_CANDY, Models.HANDHELD);
        generator.register(DDItems.CANDIED_SILVERFISH_SUCKER, Models.HANDHELD);
        generator.register(DDItems.CANDIED_VEX_SUCKER, Models.HANDHELD);
        generator.register(DDItems.SILVERFISH_THORAX, Models.GENERATED);
        generator.register(DDItems.GHOULASH, Models.GENERATED);
    }
}
