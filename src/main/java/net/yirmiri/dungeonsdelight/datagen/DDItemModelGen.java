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
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

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
        genericItem(DDItems.SLIME_BAR, DungeonsDelight.MOD_ID);
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
        genericItem(DDItems.BUBBLEGUNK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SCULK_POLYP, DungeonsDelight.MOD_ID);
        genericItem(DDItems.ANCIENT_EGG, DungeonsDelight.MOD_ID);
        genericItem(DDItems.CLEAVED_ANCIENT_EGG, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SCULK_MAYO, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.FLINT_CLEAVER, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.IRON_CLEAVER, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.GOLDEN_CLEAVER, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.DIAMOND_CLEAVER, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.NETHERITE_CLEAVER, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GLOW_BERRY_GELATIN, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.GLOW_BERRY_GELATIN_BLOCK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.ROTTEN_TRIPE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GELLED_SALAD, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.ROTBULB_CROP, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.ROTBULB_PLANT, DungeonsDelight.MOD_ID);
        genericItem(DDItems.ROTBULB, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GUNK, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.STAINED_SCRAP_BARS, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.SLICORICE, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.COB_N_CANDY, DungeonsDelight.MOD_ID);
        genericItem(DDItems.BRINED_FLESH, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GRITTY_FLESH, DungeonsDelight.MOD_ID);
        genericItem(DDItems.DEVILISH_EGGS, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GHAST_ROLL, DungeonsDelight.MOD_ID);
        genericItem(DDItems.TOKAYAKI, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SALT_SOAKED_STEW, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.SOAKED_SKEWER, DungeonsDelight.MOD_ID);
        genericItem(DDItems.POI, DungeonsDelight.MOD_ID);
        genericItem(DDItems.MONSTER_MUFFIN, DungeonsDelight.MOD_ID);
        genericItem(DDItems.RANCID_REDUCTION, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SCULK_TART_SLICE, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.SCULK_TART, DungeonsDelight.MOD_ID);
        genericItem(DDItems.MONSTER_CAKE_SLICE, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.MONSTER_CAKE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.OSSOBUSCO, DungeonsDelight.MOD_ID);
        genericItem(DDItems.OSSOBUSCO_BLOCK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SPIDER_PIE_SLICE, DungeonsDelight.MOD_ID);
        blockItem(DDBlocks.SPIDER_PIE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SCULK_APPLE, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SHIOKARA_BOWL, DungeonsDelight.MOD_ID);
        genericItem(DDItems.BLOODY_MARY, DungeonsDelight.MOD_ID);
        genericItem(DDItems.WARDENZOLA, DungeonsDelight.MOD_ID);
        genericItem(DDItems.WARDENZOLA_CRUMBLES, DungeonsDelight.MOD_ID);
        genericItem(DDItems.MALICIOUS_SANDWICH, DungeonsDelight.MOD_ID);
        genericItem(DDItems.TARO_MILK_TEA, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.SNIFFER_SHANK, DungeonsDelight.MOD_ID);
        handheldItem(DDItems.COOKED_SNIFFER_SHANK, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SOFT_SERVE_SNIFFER_EGG, DungeonsDelight.MOD_ID);
        genericItem(DDItems.SNIFFERWURST, DungeonsDelight.MOD_ID);
        genericItem(DDItems.COOKED_SNIFFERWURST, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GYUDON, DungeonsDelight.MOD_ID);
        genericItem(DDItems.TERRINE_LOAF, DungeonsDelight.MOD_ID);
        genericItem(DDItems.GHASTLY_SPIRITS, DungeonsDelight.MOD_ID);
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
