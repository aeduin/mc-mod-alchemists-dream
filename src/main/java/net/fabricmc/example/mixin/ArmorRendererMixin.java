package net.fabricmc.example.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorRendererMixin {
    // @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelPart;visible:Z"), method = "setVisible", locals = LocalCapture.CAPTURE_FAILSOFT)
    protected void setVisible(BipedEntityModel bipedModel, EquipmentSlot slot, CallbackInfo ci) {
        bipedModel.setVisible(false);
        bipedModel.leftArm.visible = false;
        bipedModel.rightArm.visible = false;
        bipedModel.head.visible = false;
        bipedModel.hat.visible = false;
        bipedModel.body.visible = false;
        bipedModel.rightLeg.visible = false;
        bipedModel.leftLeg.visible = false;
    }

    // @Inject(at = @At(value = "TAIL"), method="setVisible")
    protected void setVisibleTail(BipedEntityModel bipedModel, EquipmentSlot slot, CallbackInfo ci) {
        bipedModel.setVisible(false);
        bipedModel.leftArm.visible = false;
        bipedModel.rightArm.visible = false;
        bipedModel.head.visible = false;
        bipedModel.hat.visible = false;
        bipedModel.body.visible = false;
        bipedModel.rightLeg.visible = false;
        bipedModel.leftLeg.visible = false;
    }

    // @Inject(method="renderArmor", at = @At(value = "INVOKE", target="Lnet/minecraft/client/render/entity/feature/ArmorFeatureRenderer;setVisible(Lnet/minecraft/client/render/entity/model/BipedEntityModel;Lnet/minecraft/entity/EquipmentSlot;)V", shift = At.Shift.AFTER))
    protected void setInvisible(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity entity, EquipmentSlot armorSlot, int light, BipedEntityModel model, CallbackInfo ci) {
        if(entity.isInvisible()) {
            model.leftArm.visible = false;
            model.rightArm.visible = false;
            model.head.visible = false;
            model.hat.visible = false;
            model.body.visible = false;
            model.rightLeg.visible = false;
            model.leftLeg.visible = false;
        }
    }
}
