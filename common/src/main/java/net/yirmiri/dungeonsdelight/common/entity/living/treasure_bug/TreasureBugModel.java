package net.yirmiri.dungeonsdelight.common.entity.living.treasure_bug;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class TreasureBugModel<T extends TreasureBugEntity> extends HierarchicalModel<T> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tenna1;
    private final ModelPart tenna2;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart leg5;
    private final ModelPart leg6;

    public TreasureBugModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.tenna1 = this.head.getChild("tenna1");
        this.tenna2 = this.head.getChild("tenna2");
        this.leg1 = this.body.getChild("leg1");
        this.leg2 = this.body.getChild("leg2");
        this.leg3 = this.body.getChild("leg3");
        this.leg4 = this.body.getChild("leg4");
        this.leg5 = this.body.getChild("leg5");
        this.leg6 = this.body.getChild("leg6");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(12, 12).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -4.0F));

        PartDefinition tenna1 = head.addOrReplaceChild("tenna1", CubeListBuilder.create().texOffs(3, 17).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(2, 16).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -1.0F, -1.0F, 0.3927F, 0.3927F, 0.0F));

        PartDefinition tenna2 = head.addOrReplaceChild("tenna2", CubeListBuilder.create().texOffs(3, 17).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(2, 16).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -1.0F, -1.0F, 0.3927F, -0.3927F, 0.0F));

        PartDefinition leg1 = body.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 16).addBox(1.0F, 0.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 16).addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, 1.0F, -2.5F, 0.0F, 0.3927F, 0.0F));

        PartDefinition leg2 = body.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 16).addBox(1.0F, 0.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 16).addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 1.0F, 0.0F));

        PartDefinition leg3 = body.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 16).addBox(1.0F, 0.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 16).addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, 1.0F, 2.5F, 0.0F, -0.3927F, 0.0F));

        PartDefinition leg4 = body.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 16).addBox(1.0F, 0.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 16).addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, 1.0F, -2.5F, 0.0F, 2.7489F, 0.0F));

        PartDefinition leg5 = body.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(0, 16).addBox(1.0F, 0.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 16).addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 1.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition leg6 = body.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(0, 16).addBox(1.0F, 0.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 16).addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, 1.0F, 2.5F, 0.0F, -2.7489F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(TreasureBugEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.yRot = netHeadYaw * 0.0175F;
        this.head.xRot = headPitch * 0.0175F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.body;
    }
}
