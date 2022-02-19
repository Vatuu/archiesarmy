package dev.vatuu.archiesarmy.spells.goals;

import dev.vatuu.archiesarmy.spells.BetterSpellcastingIllagerEntity;
import dev.vatuu.archiesarmy.spells.SpellRegistry;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class LookAtTargetGoal extends Goal {

    BetterSpellcastingIllagerEntity entity;

    public LookAtTargetGoal(BetterSpellcastingIllagerEntity entity) {
        this.entity = entity;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    public boolean canStart() {
        return entity.spellTicks > 0;
    }

    public void start() {
        super.start();
        entity.getNavigation().stop();
    }

    public void stop() {
        super.stop();
        entity.setSpell(SpellRegistry.SPELL_NONE);
    }

    public void tick() {
        if (entity.getTarget() != null) {
            entity.getLookControl().lookAt(entity.getTarget(), entity.getMaxLookYawChange(), (float) entity.getMaxLookPitchChange());
        }

    }
}