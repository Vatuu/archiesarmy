package dev.vatuu.archiesarmy.animation.client.extensions.mixin;

import dev.vatuu.archiesarmy.animation.client.extensions.ModelPartExtension;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Quaternion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelPart.class)
@Environment(EnvType.CLIENT)
public abstract class ModelPartMixin implements ModelPartExtension {

    @Unique private Vector3f rotation = new Vector3f(0, 0, 0);
    @Unique private Vector3f translation = new Vector3f(0, 0, 0);

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPart;rotate(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER), method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V")
    private void render(MatrixStack stack, VertexConsumer consumer, int light, int overlay, float r, float g, float b, float a, CallbackInfo info) {
        translateMatrix(stack, translation);
        rotateMatrix(stack, rotation.getX(), rotation.getY(), rotation.getZ());
        this.rotation = new Vector3f(0, 0, 0);
        this.translation = new Vector3f(0, 0, 0);
    }

    public void setRotation(Vector3f vec) {
       this.rotation = vec;
    }

    public void setTranslation(Vector3f vec) {
        this.translation = vec;
    }

    private void rotateMatrix(MatrixStack stack, float pitch, float yaw, float roll) {
        stack.multiply(new Quaternion(pitch, -yaw, roll, true));
    }

    private void translateMatrix(MatrixStack stack, Vector3f trans) {
        stack.translate(trans.getX() / 16F, trans.getY() / 16F, trans.getZ() / 16F);
    }
}
