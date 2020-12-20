package dev.vatuu.archiesarmy.spells.goals;

import dev.vatuu.archiesarmy.entities.EnchanterEntity;

public class LookAtTargetOrEnchantingTargetGoal extends LookAtTargetGoal {

    EnchanterEntity entity;

    public LookAtTargetOrEnchantingTargetGoal(EnchanterEntity entity) {
        super(entity);
        this.entity = entity;
    }

    public void tick() {
        if (entity.getEnchantingTarget() != null) {
            entity.getLookControl().lookAt(entity.getEnchantingTarget(), (float)entity.getBodyYawSpeed(), (float)entity.getLookPitchSpeed());
        } else super.tick();
    }
}