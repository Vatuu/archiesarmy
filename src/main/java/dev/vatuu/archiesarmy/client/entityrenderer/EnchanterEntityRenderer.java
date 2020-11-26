package dev.vatuu.archiesarmy.client.entityrenderer;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.models.EnchanterEntityModel;
import dev.vatuu.archiesarmy.entities.EnchanterEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class EnchanterEntityRenderer extends MobEntityRenderer<EnchanterEntity, EnchanterEntityModel> {

    private static final Identifier TEXTURE = ArchiesArmy.id("textures/entities/illagers/enchanter.png");

    public EnchanterEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new EnchanterEntityModel(), .5f);
    }

    @Override
    public void render(EnchanterEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(EnchanterEntity entity) {
        return TEXTURE;
    }

    protected void scale(EnchanterEntity entity, MatrixStack matrixStack, float f) {
        float g = 0.9375F;
        matrixStack.scale(g, g, g);
    }
}
