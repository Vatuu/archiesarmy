package dev.vatuu.archiesarmy.spells.goals;

import net.minecraft.entity.ai.goal.Goal;

import dev.vatuu.archiesarmy.entities.EnchanterEntity;
import dev.vatuu.archiesarmy.spells.BetterSpellcastingIllagerEntity;
import dev.vatuu.archiesarmy.spells.SpellRegistry;

import java.util.EnumSet;

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