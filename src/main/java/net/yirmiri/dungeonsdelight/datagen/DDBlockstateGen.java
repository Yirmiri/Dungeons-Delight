package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import vectorwing.farmersdelight.common.block.CabinetBlock;

public class DDBlockstateGen extends BlockStateProvider {
    public DDBlockstateGen(PackOutput output, ExistingFileHelper helper) {
        super(output, DungeonsDelight.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        cubeAllWithItem(DDBlocks.WORMWOOD_PLANKS.get());
        stairsWithItem((StairBlock) DDBlocks.WORMWOOD_STAIRS.get(), DDBlocks.WORMWOOD_PLANKS.get());
        slabWithItem((SlabBlock) DDBlocks.WORMWOOD_SLAB.get(), DDBlocks.WORMWOOD_PLANKS.get());
        doorBlock((DoorBlock) DDBlocks.WORMWOOD_DOOR.get(), "wormwood", new ResourceLocation("dungeonsdelight:block/wormwood_door_bottom"), new ResourceLocation("dungeonsdelight:block/wormwood_door_top"));
        fenceWithItem((FenceBlock) DDBlocks.WORMWOOD_FENCE.get(), DDBlocks.WORMWOOD_PLANKS.get());
        fenceGateWithItem((FenceGateBlock) DDBlocks.WORMWOOD_FENCE_GATE.get(), DDBlocks.WORMWOOD_PLANKS.get());
        pressurePlateWithItem((PressurePlateBlock) DDBlocks.WORMWOOD_PRESSURE_PLATE.get(), DDBlocks.WORMWOOD_PLANKS.get());
        buttonWithItem((ButtonBlock) DDBlocks.WORMWOOD_BUTTON.get(), DDBlocks.WORMWOOD_PLANKS.get());
        trapdoorWithItem((TrapDoorBlock) DDBlocks.WORMWOOD_TRAPDOOR.get(), DDBlocks.WORMWOOD_TRAPDOOR.get());
        cubeAllWithItem(DDBlocks.WORMWOOD_MOSAIC.get());
        stairsWithItem((StairBlock) DDBlocks.WORMWOOD_MOSAIC_STAIRS.get(), DDBlocks.WORMWOOD_MOSAIC.get());
        slabWithItem((SlabBlock) DDBlocks.WORMWOOD_MOSAIC_SLAB.get(), DDBlocks.WORMWOOD_MOSAIC.get());
        cabinetBlock((CabinetBlock) DDBlocks.WORMWOOD_CABINET.get(), "wormwood");
        cubeAllWithItem(DDBlocks.SCULK_MAYO_BLOCK.get());
        cubeAllWithItem(DDBlocks.WORMROOTS_BLOCK.get());
    }

    private String key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    private void cubeAllWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    private void stairsWithItem(StairBlock block, Block blockTexture) {
        stairsBlock(block, blockTexture(blockTexture));
        simpleBlockItem(block, models().stairs(key(block), blockTexture(blockTexture), blockTexture(blockTexture), blockTexture(blockTexture)));
    }

    private void slabWithItem(SlabBlock block, Block blockTexture) {
        slabBlock(block, blockTexture(blockTexture), blockTexture(blockTexture));
        simpleBlockItem(block, models().slab(key(block), blockTexture(blockTexture), blockTexture(blockTexture), blockTexture(blockTexture)));
    }

    private void fenceWithItem(FenceBlock block, Block blockTexture) {
        fenceBlock(block, blockTexture(blockTexture));
        simpleBlockItem(block, models().fenceInventory(key(block), blockTexture(blockTexture)));
    }

    private void fenceGateWithItem(FenceGateBlock block, Block blockTexture) {
        fenceGateBlock(block, blockTexture(blockTexture));
        simpleBlockItem(block, models().fenceGate(key(block), blockTexture(blockTexture)));
    }

    private void pressurePlateWithItem(PressurePlateBlock block, Block blockTexture) {
        pressurePlateBlock(block, blockTexture(blockTexture));
        simpleBlockItem(block, models().pressurePlate(key(block), blockTexture(blockTexture)));
    }

    private void buttonWithItem(ButtonBlock block, Block blockTexture) {
        buttonBlock(block, blockTexture(blockTexture));
    }

    private void trapdoorWithItem(TrapDoorBlock block, Block blockTexture) {
        trapdoorBlock(block, blockTexture(blockTexture), true);
        simpleBlockItem(block, models().trapdoorOrientableBottom(key(block), blockTexture(blockTexture)));
    }

    public void cabinetBlock(CabinetBlock block, String woodType) {
        this.horizontalBlock(block, state -> {
            String suffix = state.getValue(CabinetBlock.OPEN) ? "_open" : "";
            return models().orientable(ForgeRegistries.BLOCKS.getKey(block) + suffix,
                    new ResourceLocation(DungeonsDelight.MOD_ID, "block/" + (woodType + "_cabinet_side")),
                    new ResourceLocation(DungeonsDelight.MOD_ID, "block/" + (woodType + "_cabinet_front" + suffix)),
                    new ResourceLocation(DungeonsDelight.MOD_ID, "block/" + (woodType + "_cabinet_top")));
        });
    }
}
