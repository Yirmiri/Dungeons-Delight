package net.yirmiri.dungeonsdelight.core.mixin.client;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin <T extends LivingEntity> {
    HumanoidModel<T> model = (HumanoidModel<T>) (Object) this;

    @Inject(method = "setupAnim*", at = @At("HEAD"))
    private void dungeonsdelight$setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (entity.isUsingItem()) {
            boolean firstPerson = Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON;

            if (!(entity instanceof Player)) {
                firstPerson = false;
            }

            if (entity.getMainHandItem().getItem() instanceof CleaverItem) {
                model.rightArmPose = firstPerson ? HumanoidModel.ArmPose.BOW_AND_ARROW : HumanoidModel.ArmPose.THROW_SPEAR;

            } else if (entity.getOffhandItem().getItem() instanceof CleaverItem) {
                model.leftArmPose = firstPerson ? HumanoidModel.ArmPose.BOW_AND_ARROW : HumanoidModel.ArmPose.THROW_SPEAR;
            }
        }
    }
}