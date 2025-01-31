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
import net.yirmiri.dungeonsdelight.registry.compat.DDCItems;
import net.yirmiri.dungeonsdelight.registry.compat.DDCTFKnives;
import net.yirmiri.dungeonsdelight.util.DDUtil;

import java.util.Objects;

public class DDItemModelGen extends ItemModelProvider {
    public DDItemModelGen(PackOutput output, ExistingFileHelper helper) {
        super(output, DungeonsDelight.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        //DUNGEONS DELIGHT
        genericItem(DDItems.LOGO_ITEM, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SLIME_NOODLES, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SLIME_SLAB, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GHOULASH, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.AMETHYST_ROCK_CANDY, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.CANDIED_VEX_SUCKER, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.CANDIED_SILVERFISH_SUCKER, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SILVERFISH_THORAX, DungeonsDelight.MOD_ID);
        genericItem(DDItems.STAINED_SCRAP, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GHAST_CALAMARI, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GHAST_TENTACLE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.FRIED_GHAST_CALAMARI, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SILVERFISH_FRIED_RICE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SPIDER_EXTRACT, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SPIDER_MEAT, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SMOKED_SPIDER_MEAT, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.SPIDER_TANGHULU, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SPIDER_EYE_SALMAGUNDI, DungeonsDelight.MOD_ID);
        genericItem(DDItems.MONSTER_BURGER, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.WORMWOOD_DOOR, DungeonsDelight.MOD_ID);
        buttonInventory(DDBlocks.WORMWOOD_BUTTON.get().asItem(), "wormwood_planks");
        blockItem(DDBlocks.WORMROOTS, DungeonsDelight.MOD_ID);
        
        //TWILIGHT FOREST COMPAT
        handheldItem(DDCTFKnives.STEELEAF_KNIFE, DDUtil.TF_ID);
        handheldItem(DDCTFKnives.KNIGHTMETAL_KNIFE, DDUtil.TF_ID);
        handheldItem(DDCTFKnives.IRONWOOD_KNIFE, DDUtil.TF_ID);
        genericItem(DDCItems.LIVEROOT_BEER, DDUtil.TF_ID);
        genericItem(DDCItems.TORCHBERRY_RAISINS, DDUtil.TF_ID);
        genericItem(DDCItems.MEEF_WELLINGTON, DDUtil.TF_ID);
        genericItem(DDCItems.BRAISED_GLOWWORM_QUEEN, DDUtil.TF_ID);
    }

    private String key(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    private ItemModelBuilder genericItem(RegistryObject<? extends Item> item, String modid) {
        if (Objects.equals(modid, "dungeonsdelight")) {
            return withExistingParent(item.getId().getPath(),
                    new ResourceLocation("item/generated")).texture("layer0",
                    new ResourceLocation(DungeonsDelight.MOD_ID, "item/" + item.getId().getPath()));
        } else {
            return withExistingParent(item.getId().getPath(),
                    new ResourceLocation("item/generated")).texture("layer0",
                    new ResourceLocation(DungeonsDelight.MOD_ID, "item/" + modid + "/" + item.getId().getPath()));
        }
    }

    private ItemModelBuilder handheldItem(RegistryObject<? extends Item> item, String modid) {
        if (Objects.equals(modid, "dungeonsdelight")) {
            return withExistingParent(item.getId().getPath(),
                    new ResourceLocation("item/handheld")).texture("layer0",
                    new ResourceLocation(DungeonsDelight.MOD_ID, "item/" + item.getId().getPath()));
        } else {
            return withExistingParent(item.getId().getPath(),
                    new ResourceLocation("item/handheld")).texture("layer0",
                    new ResourceLocation(DungeonsDelight.MOD_ID, "item/" + modid + "/" + item.getId().getPath()));
        }
    }

    private ItemModelBuilder blockItem(RegistryObject<? extends Block> block, String modid) {
        if (Objects.equals(modid, "dungeonsdelight")) {
            return withExistingParent(block.getId().getPath(),
                    new ResourceLocation("item/generated")).texture("layer0",
                    new ResourceLocation(DungeonsDelight.MOD_ID, "item/" + block.getId().getPath()));
        } else {
            return withExistingParent(block.getId().getPath(),
                    new ResourceLocation("item/generated")).texture("layer0",
                    new ResourceLocation(DungeonsDelight.MOD_ID, "item/" + modid + "/" + block.getId().getPath()));
        }
    }

    public void buttonInventory(Item item, String texture) {
        withExistingParent(key(item), BLOCK_FOLDER + "/button_inventory").texture("texture", 
                new ResourceLocation(DungeonsDelight.MOD_ID, ItemModelProvider.BLOCK_FOLDER + "/" + texture));
    }
}
