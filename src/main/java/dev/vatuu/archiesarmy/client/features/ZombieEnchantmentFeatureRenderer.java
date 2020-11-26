package dev.vatuu.archiesarmy.client.features;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.extensions.MobEntityExt;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

public class ZombieEnchantmentFeatureRenderer<T extends ZombieEntity, M extends EntityModel<T>>  extends FeatureRenderer<T, M> {

    private static final Identifier OVERLAY = ArchiesArmy.id("textures/entities/enchanting/zombie.png");
    private final ZombieEntityModel<T> overlay = new ZombieEntityModel<>(0, false);

    public ZombieEnchantmentFeatureRenderer(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider provider, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if(((MobEntityExt)entity).isEnchanted())
            render(this.getContextModel(), this.overlay, OVERLAY, matrixStack, provider, light, entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, 1.0F, 1.0F, 1.0F);
    }
}
