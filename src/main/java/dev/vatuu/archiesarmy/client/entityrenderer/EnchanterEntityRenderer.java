package dev.vatuu.archiesarmy.client.entityrenderer;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.bedrock.entityrenderer.GeometryLivingEntityRenderer;
import dev.vatuu.archiesarmy.client.models.EnchanterEntityModel;
import dev.vatuu.archiesarmy.entities.EnchanterEntity;

public class EnchanterEntityRenderer extends GeometryLivingEntityRenderer<EnchanterEntity, EnchanterEntityModel> {

    private static final Identifier TEXTURE = ArchiesArmy.id("textures/entities/illagers/enchanter.png");

    protected EnchanterEntityRenderer(EntityRenderDispatcher dispatcher, EnchanterEntityModel model, float shadowRadius) {
        super(dispatcher, model, shadowRadius);
    }

    @Override
    public Identifier getTexture(EnchanterEntity entity) {
        return TEXTURE;
    }

    @Override
    protected boolean hasLabel(EnchanterEntity livingEntity) {
        return false;
    }

    protected void scale(EnchanterEntity entity, MatrixStack matrixStack, float f) {
        float g = 0.9375F;
        matrixStack.scale(g, g, g);
    }
}
