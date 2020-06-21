package dev.vatuu.archiesarmy.client.entityrenderer;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.animation.client.AnimatableEntityRenderer;
import dev.vatuu.archiesarmy.animation.client.Animation;
import dev.vatuu.archiesarmy.client.models.RedstoneMonstrosityModel;
import dev.vatuu.archiesarmy.entities.RedstoneMonstrosityEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class RedstoneMonstrosityRenderer extends AnimatableEntityRenderer<RedstoneMonstrosityEntity, RedstoneMonstrosityModel> {

    public static final Identifier TEXTURE = ArchiesArmy.id("textures/entities/redstone_monstrosity.png");

    public RedstoneMonstrosityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new RedstoneMonstrosityModel(), 3F);
    }

    @Override
    public Identifier getTexture(RedstoneMonstrosityEntity entity) {
        return TEXTURE;
    }

    @Override
    public Map<Identifier, Animation> getAnimationRegisterMap() {
        Map<Identifier, Animation> map = new HashMap<>();
        map.put(RedstoneMonstrosityEntity.ANIMATION_WALK, new Animation(ArchiesArmy.id("animations/redstone_monstrosity/walk.anim"), this.getModel()));
        map.put(RedstoneMonstrosityEntity.ANIMATION_AWAKEN, new Animation(ArchiesArmy.id("animations/redstone_monstrosity/awaken.anim"), this.getModel()));
        return map;
    }
}
