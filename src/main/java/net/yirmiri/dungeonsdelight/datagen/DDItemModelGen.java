package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDItems;

public class DDItemModelGen extends ItemModelProvider {
    public DDItemModelGen(PackOutput output, ExistingFileHelper helper) {
        super(output, DungeonsDelight.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        simpleItem(DDItems.LOGO_ITEM);
        simpleItem(DDItems.SLIME_NOODLES);
        simpleItem(DDItems.SLIME_SLAB);
        simpleItem(DDItems.GHOULASH);
        handHeldItem(DDItems.AMETHYST_ROCK_CANDY);
        handHeldItem(DDItems.CANDIED_VEX_SUCKER);
        handHeldItem(DDItems.CANDIED_SILVERFISH_SUCKER);
        simpleItem(DDItems.SILVERFISH_THORAX);
    }

    private ItemModelBuilder simpleItem(RegistryObject<? extends Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DungeonsDelight.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handHeldItem(RegistryObject<? extends Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(DungeonsDelight.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockItem(RegistryObject<Block> block) {
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation(DungeonsDelight.MOD_ID, "block/" + block.getId().getPath()));
    }
}
