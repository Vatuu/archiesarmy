package dev.vatuu.archiesarmy.client.entityrenderer;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.geometry.entityrenderer.GeometryLivingEntityRenderer;
import dev.vatuu.archiesarmy.client.models.ArchieEntityModel;
import dev.vatuu.archiesarmy.entities.ArchIllagerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

public class ArchIllagerEntityRenderer extends GeometryLivingEntityRenderer<ArchIllagerEntity, ArchieEntityModel> {

    private static final Identifier TEXTURE = ArchiesArmy.id("textures/entities/illagers/arch_illager.png");

    protected ArchIllagerEntityRenderer(EntityRenderDispatcher dispatcher, ArchieEntityModel model, float shadowRadius) {
        super(dispatcher, model, shadowRadius);
    }

    @Override
    public Identifier getTexture(ArchIllagerEntity entity) {
        return TEXTURE;
    }

    @Override
    protected boolean hasLabel(ArchIllagerEntity livingEntity) {
        return false;
    }
}
