package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDItems;

public class DDItemModelGen extends ItemModelProvider {
    public DDItemModelGen(PackOutput output, ExistingFileHelper helper) {
        super(output, DungeonsDelight.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        basicItem(DDItems.LOGO_ITEM.get());
        basicItem(DDItems.SLIME_NOODLES.get());
        basicItem(DDItems.SLIME_SLAB.get());
        basicItem(DDItems.GHOULASH.get());
        handHeldItem(DDItems.AMETHYST_ROCK_CANDY);
        handHeldItem(DDItems.CANDIED_VEX_SUCKER);
        handHeldItem(DDItems.CANDIED_SILVERFISH_SUCKER);
        basicItem(DDItems.SILVERFISH_THORAX.get());
        basicItem(DDItems.STAINED_SCRAP.get());
        basicItem(DDItems.GHAST_CALAMARI.get());
        basicItem(DDItems.GHAST_TENTACLE.get());
        basicItem(DDItems.FRIED_GHAST_CALAMARI.get());
        basicItem(DDItems.SILVERFISH_FRIED_RICE.get());
        basicItem(DDItems.SPIDER_EXTRACT.get());
        basicItem(DDItems.SPIDER_MEAT.get());
        basicItem(DDItems.SMOKED_SPIDER_MEAT.get());
        handHeldItem(DDItems.SPIDER_TANGHULU);
        basicItem(DDItems.SPIDER_EYE_SALMAGUNDI.get());
        basicItem(DDItems.MONSTER_BURGER.get());
        basicItem(DDBlocks.WORMWOOD_DOOR.get().asItem());
        buttonInventory(DDBlocks.WORMWOOD_BUTTON.get().asItem(), "wormwood_planks");
        basicItem(DDBlocks.WORMROOTS.get().asItem());
    }

    private String key(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    private ItemModelBuilder handHeldItem(RegistryObject<? extends Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(DungeonsDelight.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void buttonInventory(Item item, String texture) {
        withExistingParent(key(item), BLOCK_FOLDER + "/button_inventory").texture("texture", new ResourceLocation(DungeonsDelight.MOD_ID, ItemModelProvider.BLOCK_FOLDER + "/" + texture));
    }
}
