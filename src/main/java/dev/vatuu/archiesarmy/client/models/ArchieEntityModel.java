package dev.vatuu.archiesarmy.client.models;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.geometry.models.EntityGeometryModel;
import dev.vatuu.archiesarmy.entities.ArchIllagerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class ArchieEntityModel extends EntityGeometryModel<ArchIllagerEntity> {

    private static final Identifier MODEL = ArchiesArmy.id("models/entities/illagers/archie.geo");

    public ArchieEntityModel() {
        super(RenderLayer::getEntityCutoutNoCull, MODEL);
    }

    @Override
    public void setAngles(ArchIllagerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
}
