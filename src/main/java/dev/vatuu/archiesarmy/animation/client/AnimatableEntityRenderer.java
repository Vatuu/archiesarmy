package dev.vatuu.archiesarmy.animation.client;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.animation.client.Animation;
import dev.vatuu.archiesarmy.animation.client.AnimationData;
import dev.vatuu.archiesarmy.animation.client.AnimationRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

import java.util.Map;

public abstract class AnimatableEntityRenderer<E extends MobEntity, M extends EntityModel<E>> extends MobEntityRenderer<E, M> {

    public abstract Map<Identifier, Animation> getAnimationRegisterMap();
    
    public AnimatableEntityRenderer(EntityRenderDispatcher entityRenderDispatcher, M entityModel, float f) {
        super(entityRenderDispatcher, entityModel, f);
        AnimationRegistry.INSTANCE.addAnimationMap(getAnimationRegisterMap());
    }

    public void render(E mobEntity, float yaw, float delta, MatrixStack matrixStack, VertexConsumerProvider provider, int i) {
        AnimationData data = ArchiesArmy.COMPONENT_ANIMATION.get(mobEntity).getData();
        if(data != null) {
            matrixStack.push();
            data.processAnimation(delta);
            super.render(mobEntity, yaw, delta, matrixStack, provider, i);
            matrixStack.pop();
        } else {
            super.render(mobEntity, yaw, delta, matrixStack, provider, i);
        }

    }

}
