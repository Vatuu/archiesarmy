package dev.vatuu.archiesarmy.client.models;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.bedrock.models.EntityGeometryModel;
import dev.vatuu.archiesarmy.entities.MinionEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class MinionEntityModel extends EntityGeometryModel<MinionEntity> {

    private static final Identifier MODEL = ArchiesArmy.id("models/entities/minion.geo");

    public MinionEntityModel() {
        super(RenderLayer::getEntityCutoutNoCull, MODEL);
    }

    @Override
    public void setAngles(MinionEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
}
