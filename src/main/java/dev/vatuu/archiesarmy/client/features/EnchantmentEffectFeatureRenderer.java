package dev.vatuu.archiesarmy.client.features;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

import dev.vatuu.archiesarmy.extensions.MobEntityExt;

public class EnchantmentEffectFeatureRenderer<T extends MobEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {

    private final M model;

    @SuppressWarnings("rawtypes")
    public EnchantmentEffectFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext, M model) {
        super(featureRendererContext);
        this.model = model;
        if(model instanceof BipedEntityModel) {
            BipedEntityModel overlay = (BipedEntityModel)model;
            overlay.helmet.visible = overlay.leftArm.mirror = overlay.leftLeg.mirror = false;
        }
    }

    protected float getEnergySwirlX(float partialAge) {
        return partialAge * 0.01F;
    }

    protected Identifier getEnergySwirlTexture() {
        return new Identifier("textures/misc/enchanted_item_glint.png");
    }

    protected M getEnergySwirlModel() {
        return this.model;
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (((MobEntityExt)entity).isEnchanted()) {
            float f = (float)entity.age + tickDelta;
            EntityModel<T> entityModel = this.getEnergySwirlModel();
            entityModel.animateModel(entity, limbAngle, limbDistance, tickDelta);
            this.getContextModel().copyStateTo(entityModel);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEnergySwirl(this.getEnergySwirlTexture(), this.getEnergySwirlX(f), f * 0.01F));
            entityModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            entityModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 0.5F, 0.5F, 0.5F, 1.0F);
        }
    }
}
