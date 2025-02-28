package net.yirmiri.dungeonsdelight.entity.monster_yam;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import org.joml.Vector3f;


public class MonsterYamEntityModel<T extends MonsterYamEntity> extends HierarchicalModel<T> {
    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();
    public static final ModelLayerLocation LAYER_LOC = new ModelLayerLocation(new ResourceLocation(DungeonsDelight.MOD_ID, "monster_yam"), "main");
    private final ModelPart root;
    private final ModelPart center;
    private final ModelPart flower;
    private final ModelPart leftarm;
    private final ModelPart rightarm;
    private final ModelPart lower;

    public MonsterYamEntityModel(ModelPart root) {
        this.root = root.getChild("root");
        this.center = this.root.getChild("center");
        this.flower = this.center.getChild("flower");
        this.leftarm = this.center.getChild("leftarm");
        this.leftarm.getChild("forearm");
        this.rightarm = this.center.getChild("rightarm");
        this.rightarm.getChild("forearm2");
        this.rightarm.getChild("forearm3");
        this.center.getChild("flower");
        this.lower = this.root.getChild("lower");
        this.lower.getChild("rightleg");
        this.lower.getChild("leftleg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition center = root.addOrReplaceChild("center", CubeListBuilder.create().texOffs(46, 40).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(46, 15).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.5F))
                .texOffs(0, 25).addBox(-7.0F, -18.0F, -7.0F, 14.0F, 11.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-7.0F, -18.0F, -7.0F, 14.0F, 11.0F, 14.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -17.0F, 0.0F));

        PartDefinition leftarm = center.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(66, 0).addBox(1.0F, 18.0F, -1.5F, 2.0F, 12.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(1.0F, 18.0F, -1.5F, 2.0F, 12.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(7.0F, -14.0F, 0.5F));

        leftarm.addOrReplaceChild("forearm", CubeListBuilder.create().texOffs(50, 58).addBox(-1.0F, -2.0F, -3.0F, 4.0F, 20.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(32, 53).addBox(-1.0F, -2.0F, -3.0F, 4.0F, 20.0F, 5.0F, new CubeDeformation(0.2F)), PartPose.offset(1.0F, 0.0F, 0.5F));

        PartDefinition rightarm = center.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(66, 0).mirror().addBox(-3.0F, 18.0F, -1.5F, 2.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(56, 0).mirror().addBox(-3.0F, 18.0F, -1.5F, 2.0F, 12.0F, 3.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(-7.0F, -14.0F, 0.5F));

        rightarm.addOrReplaceChild("forearm2", CubeListBuilder.create().texOffs(50, 58).mirror().addBox(-3.0F, -2.0F, -3.0F, 4.0F, 20.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 0.0F, 0.5F));

        rightarm.addOrReplaceChild("forearm3", CubeListBuilder.create().texOffs(32, 53).mirror().addBox(-3.0F, -2.0F, -3.0F, 4.0F, 20.0F, 5.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(-1.0F, 0.0F, 0.5F));

        PartDefinition flower = center.addOrReplaceChild("flower", CubeListBuilder.create(), PartPose.offset(0.0F, -17.0F, -0.2929F));

        flower.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 50).addBox(-7.2929F, -25.0F, 0.2929F, 16.0F, 25.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -0.7071F, 0.0F, -0.7854F, 0.0F));

        flower.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(-8.7071F, -25.0F, 0.2929F, 16.0F, 25.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 0.0F, -0.7071F, 0.0F, 0.7854F, 0.0F));

        PartDefinition lower = root.addOrReplaceChild("lower", CubeListBuilder.create(), PartPose.offset(0.0F, -17.0F, -0.5F));

        lower.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 75).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 17.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(68, 58).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 17.0F, 3.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(-2.5F, 0.0F, 0.0F));

        lower.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 75).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 17.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(68, 58).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 17.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(2.5F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(MonsterYamEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animateWalk(MonsterYamAnimations.walking, limbSwing, limbSwingAmount, 1.0F, 2.5F);

        this.flower.yRot = netHeadYaw * 0.017453292F;
        this.flower.xRot = headPitch * 0.017453292F;
    }

    protected void animateWalk(AnimationDefinition definition, float limbSwing, float limbSwingAmount, float maxAnimationSpeed, float scaleFactor) {
        long i = (long) (limbSwing * 50.0F * maxAnimationSpeed);
        float f = Math.min(limbSwingAmount * scaleFactor, 1.0F);
        KeyframeAnimations.animate(this, definition, i, f, ANIMATION_VECTOR_CACHE);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
