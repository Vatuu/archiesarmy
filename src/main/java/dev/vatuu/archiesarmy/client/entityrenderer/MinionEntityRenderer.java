package dev.vatuu.archiesarmy.client.entityrenderer;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.bedrock.entityrenderer.AnimatableEntityRenderer;
import dev.vatuu.archiesarmy.client.models.MinionEntityModel;
import dev.vatuu.archiesarmy.entities.MinionEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

public class MinionEntityRenderer extends AnimatableEntityRenderer<MinionEntity, MinionEntityModel> {

    private static final Identifier TEXTURE = ArchiesArmy.id("textures/entities/minion.png");

    protected MinionEntityRenderer(EntityRenderDispatcher dispatcher, MinionEntityModel model, float shadowRadius) {
        super(dispatcher, model, shadowRadius);
    }

    @Override
    protected boolean hasLabel(MinionEntity livingEntity) {
        return false;
    }

    @Override
    public Identifier getTexture(MinionEntity entity) {
        return TEXTURE;
    }
}
