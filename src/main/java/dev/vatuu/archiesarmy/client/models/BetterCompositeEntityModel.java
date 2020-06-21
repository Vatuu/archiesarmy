package dev.vatuu.archiesarmy.client.models;

import dev.vatuu.archiesarmy.animation.client.AnimatableModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.Entity;

public abstract class BetterCompositeEntityModel<E extends Entity> extends AnimatableModel<E> {

    protected ModelPart createBone(Model model, float pivotX, float pivotY, float pivotZ) {
        ModelPart part = new ModelPart(model);
        part.setPivot(pivotX, pivotY, pivotZ);
        return part;
    }

    protected ModelPart createBone(Model model, float pivotX, float pivotY, float pivotZ, ModelPart parent) {
        ModelPart part = createBone(model, pivotX, pivotY, pivotZ);
        parent.addChild(part);
        return part;
    }
}
