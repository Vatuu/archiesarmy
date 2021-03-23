package dev.vatuu.archiesarmy.client.models;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.bedrock.models.EntityGeometryModel;
import dev.vatuu.archiesarmy.entities.EnchanterEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class EnchanterEntityModel extends EntityGeometryModel<EnchanterEntity> {

    private static final Identifier MODEL = ArchiesArmy.id("models/entities/illagers/enchanter.geo");

    public EnchanterEntityModel() {
        super(RenderLayer::getEntityCutoutNoCull, MODEL);
    }

    @Override
    public void setAngles(EnchanterEntity e, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch) {
        getGeometry().getBone("left_arm").setVisible(false);
        getGeometry().getBone("right_arm").setVisible(false);

        getGeometry().getBone("head").setRotation(headPitch * 0.017453292F, headYaw * 0.017453292F, 0.0F, false);

        getGeometry().getBone("left_leg").setRotation(MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance * 0.5F, 0.0F, 0.0F, false);
        getGeometry().getBone("right_leg").setRotation(MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance * 0.5F, 0.0F, 0.0F, false);
    }
}
