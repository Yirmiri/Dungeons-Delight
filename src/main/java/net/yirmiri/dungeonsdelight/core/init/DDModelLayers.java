package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDModelLayers {
    public static final ModelLayerLocation ROTTEN_ZOMBIE = createMain("rotten_zombie");
    public static final ModelLayerLocation ROTTEN_ZOMBIE_INNER_ARMOR = createInnerArmor("rotten_zombie");
    public static final ModelLayerLocation ROTTEN_ZOMBIE_OUTER_ARMOR = createOuterArmor("rotten_zombie");

    private DDModelLayers() {

    }

    private static ModelLayerLocation createMain(String model) {
        return create(model, "main");
    }

    private static ModelLayerLocation createInnerArmor(String model) {
        return create(model, "inner_armor");
    }

    private static ModelLayerLocation createOuterArmor(String model) {
        return create(model, "outer_armor");
    }

    private static ModelLayerLocation create(String model, String layer) {
        return new ModelLayerLocation(RunicLib.customid(DungeonsDelight.MOD_ID, model), layer);
    }
}
