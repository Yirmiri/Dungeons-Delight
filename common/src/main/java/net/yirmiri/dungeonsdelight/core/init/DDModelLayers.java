package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDModelLayers {
    public static final Supplier<LayerDefinition> MAIN = () -> LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64);
    //todo aw and at
    //public static final Supplier<LayerDefinition> INNER_ARMOR = () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION), 64, 32);
    //public static final Supplier<LayerDefinition> OUTER_ARMOR = () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32);

    public static final ModelLayerLocation CAMEL_HUSK = new ModelLayerLocation(RunicLib.customid(DungeonsDelight.MOD_ID, "camel_husk"), "main");
    public static final ModelLayerLocation VEXING_FANGS = new ModelLayerLocation(RunicLib.customid(DungeonsDelight.MOD_ID, "vexing_fangs"), "main");
    public static final ModelLayerLocation MONSTER_YAM = new ModelLayerLocation(RunicLib.customid(DungeonsDelight.MOD_ID, "monster_yam"), "main");
}