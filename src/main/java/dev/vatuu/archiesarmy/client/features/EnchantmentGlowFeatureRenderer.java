package dev.vatuu.archiesarmy.client.features;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import dev.vatuu.archiesarmy.extensions.LivingEntityExt;

public class EnchantmentGlowFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>>  extends FeatureRenderer<T, M> {

    private final M overlay;
    private final Identifier texture;

    public EnchantmentGlowFeatureRenderer(FeatureRendererContext<T, M> context, M overlay, Identifier texture) {
        super(context);
        this.overlay = overlay;
        this.texture = texture;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider provider, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if(((LivingEntityExt)entity).isEnchanted())
            render(this.getContextModel(), overlay, texture, matrixStack, provider, 0x00F000F0, entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, 1.0F, 1.0F, 1.0F);
    }
}
