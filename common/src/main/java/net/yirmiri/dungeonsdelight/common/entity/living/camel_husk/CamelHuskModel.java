package net.yirmiri.dungeonsdelight.common.entity.living.camel_husk;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.definitions.CamelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CamelHuskModel<T extends CamelHuskEntity> extends HierarchicalModel<T> {
   private final ModelPart root;
   private final ModelPart head;
   private final ModelPart[] saddleParts;
   private final ModelPart[] ridingParts;

   public CamelHuskModel(ModelPart root) {
      this.root = root;
      ModelPart modelpart = root.getChild("body");
      this.head = modelpart.getChild("head");
      this.saddleParts = new ModelPart[]{modelpart.getChild("saddle"), this.head.getChild("bridle")};
      this.ridingParts = new ModelPart[]{this.head.getChild("reins")};
   }

   public static LayerDefinition createBodyLayer() {
       MeshDefinition meshdefinition = new MeshDefinition();
       PartDefinition partdefinition = meshdefinition.getRoot();
       CubeDeformation cubedeformation = new CubeDeformation(0.1F);
       PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 25).addBox(-7.5F, -12.0F, -23.5F, 15.0F, 12.0F, 27.0F), PartPose.offset(0.0F, 4.0F, 9.5F));
       partdefinition1.addOrReplaceChild("hump", CubeListBuilder.create().texOffs(74, 0).addBox(-4.5F, -5.0F, -5.5F, 9.0F, 5.0F, 11.0F), PartPose.offset(0.0F, -12.0F, -10.0F));
       partdefinition1.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(122, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 14.0F, 0.0F), PartPose.offset(0.0F, -9.0F, 3.5F));
       PartDefinition partdefinition2 = partdefinition1.addOrReplaceChild("head", CubeListBuilder.create().texOffs(60, 24).addBox(-3.5F, -7.0F, -15.0F, 7.0F, 8.0F, 19.0F).texOffs(21, 0).addBox(-3.5F, -21.0F, -15.0F, 7.0F, 14.0F, 7.0F).texOffs(50, 0).addBox(-2.5F, -21.0F, -21.0F, 5.0F, 5.0F, 6.0F), PartPose.offset(0.0F, -3.0F, -19.5F));
       partdefinition2.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(45, 0).addBox(-0.5F, 0.5F, -1.0F, 3.0F, 1.0F, 2.0F), PartPose.offset(3.0F, -21.0F, -9.5F));
       partdefinition2.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(67, 0).addBox(-2.5F, 0.5F, -1.0F, 3.0F, 1.0F, 2.0F), PartPose.offset(-3.0F, -21.0F, -9.5F));
       partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(58, 16).addBox(-2.5F, 2.0F, -2.5F, 5.0F, 21.0F, 5.0F), PartPose.offset(4.9F, 1.0F, 9.5F));
       partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(94, 16).addBox(-2.5F, 2.0F, -2.5F, 5.0F, 21.0F, 5.0F), PartPose.offset(-4.9F, 1.0F, 9.5F));
       partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 2.0F, -2.5F, 5.0F, 21.0F, 5.0F), PartPose.offset(4.9F, 1.0F, -10.5F));
       partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 26).addBox(-2.5F, 2.0F, -2.5F, 5.0F, 21.0F, 5.0F), PartPose.offset(-4.9F, 1.0F, -10.5F));
       partdefinition1.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(74, 64).addBox(-4.5F, -17.0F, -15.5F, 9.0F, 5.0F, 11.0F, cubedeformation).texOffs(92, 114).addBox(-3.5F, -20.0F, -15.5F, 7.0F, 3.0F, 11.0F, cubedeformation).texOffs(0, 89).addBox(-7.5F, -12.0F, -23.5F, 15.0F, 12.0F, 27.0F, cubedeformation), PartPose.offset(0.0F, 0.0F, 0.0F));
       partdefinition2.addOrReplaceChild("reins", CubeListBuilder.create().texOffs(98, 42).addBox(3.51F, -18.0F, -17.0F, 0.0F, 7.0F, 15.0F).texOffs(84, 57).addBox(-3.5F, -18.0F, -2.0F, 7.0F, 7.0F, 0.0F).texOffs(98, 42).addBox(-3.51F, -18.0F, -17.0F, 0.0F, 7.0F, 15.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
       partdefinition2.addOrReplaceChild("bridle", CubeListBuilder.create().texOffs(60, 87).addBox(-3.5F, -7.0F, -15.0F, 7.0F, 8.0F, 19.0F, cubedeformation).texOffs(21, 64).addBox(-3.5F, -21.0F, -15.0F, 7.0F, 14.0F, 7.0F, cubedeformation).texOffs(50, 64).addBox(-2.5F, -21.0F, -21.0F, 5.0F, 5.0F, 6.0F, cubedeformation).texOffs(74, 70).addBox(2.5F, -19.0F, -18.0F, 1.0F, 2.0F, 2.0F).texOffs(74, 70).mirror().addBox(-3.5F, -19.0F, -18.0F, 1.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
       return LayerDefinition.create(meshdefinition, 128, 128);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.root().getAllParts().forEach(ModelPart::resetPose);
      this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);
      this.toggleInvisibleParts(entity);
      this.animateWalk(CamelAnimation.CAMEL_WALK, limbSwing, limbSwingAmount, 2.0F, 2.5F);
      this.animate(entity.sitAnimationState, CamelAnimation.CAMEL_SIT, ageInTicks, 1.0F);
      this.animate(entity.sitPoseAnimationState, CamelAnimation.CAMEL_SIT_POSE, ageInTicks, 1.0F);
      this.animate(entity.sitUpAnimationState, CamelAnimation.CAMEL_STANDUP, ageInTicks, 1.0F);
      this.animate(entity.idleAnimationState, CamelAnimation.CAMEL_IDLE, ageInTicks, 1.0F);
      this.animate(entity.dashAnimationState, CamelAnimation.CAMEL_DASH, ageInTicks, 1.0F);
   }

   private void applyHeadRotation(T entity, float netHeadYaw, float headPitch, float ageInTicks) {
      netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
      headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);
      if (entity.getJumpCooldown() > 0) {
         float f = ageInTicks - (float)entity.tickCount;
         float f1 = 45.0F * ((float)entity.getJumpCooldown() - f) / 55.0F;
         headPitch = Mth.clamp(headPitch + f1, -25.0F, 70.0F);
      }

      this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
      this.head.xRot = headPitch * ((float) Math.PI / 180F);
   }

   private void toggleInvisibleParts(T entity) {
      boolean hasSaddle = entity.isSaddled();
      boolean isRidden = entity.isVehicle();

      for (ModelPart modelpart : this.saddleParts) {
         modelpart.visible = hasSaddle;
      }

      for (ModelPart modelpart1 : this.ridingParts) {
         modelpart1.visible = isRidden && hasSaddle;
      }
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.young) {
            poseStack.pushPose();
            poseStack.scale(0.45F, 0.45F, 0.45F);
            poseStack.translate(0.0F, 1.834375F, 0.0F);
            this.root().render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.popPose();
        } else {
            this.root().render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        }
    }

   @Override
   public ModelPart root() {
        return this.root;
    }
}