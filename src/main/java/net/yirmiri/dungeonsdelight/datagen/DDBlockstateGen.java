package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDBlockstateGen extends BlockStateProvider {
    public DDBlockstateGen(PackOutput output, ExistingFileHelper helper) {
        super(output, DungeonsDelight.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
