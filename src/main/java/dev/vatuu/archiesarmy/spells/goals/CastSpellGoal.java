package dev.vatuu.archiesarmy.spells.goals;

import dev.vatuu.archiesarmy.spells.BetterSpellcastingIllagerEntity;
import dev.vatuu.archiesarmy.spells.Spell;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.sound.SoundEvent;

public class CastSpellGoal<T extends BetterSpellcastingIllagerEntity> extends Goal {

    private final T entity;
    private final Spell<T> spell;
    private int spellCooldown, startTime;

    public CastSpellGoal(T entity, Spell<T> spell) {
        this.entity = entity;
        this.spell = spell;
    }

    @Override
    public boolean canStart() {
        if (entity.isSpellcasting())
            return false;
        if (entity.age < startTime)
            return false;
        return spell.canStart(entity);
    }

    @Override
    public boolean shouldContinue() {
        return this.spellCooldown > 0 && spell.shouldContinue(entity);
    }

    @Override
    public void start() {
        entity.spellTicks = getSpell().getSpellTicks();
        this.spellCooldown = getSpell().getInitialCooldown();
        this.startTime = entity.age + getSpell().getStartTimeDelay();
        SoundEvent soundEvent = entity.getCastPrepareSound();
        if (soundEvent != null)
            entity.playSound(soundEvent, 1.0F, 1.0F);
        entity.setSpell(getSpell());
    }

    @Override
    public void tick() {
        --this.spellCooldown;
        if (this.spellCooldown == 0) {
            spell.castSpell(entity);
            SoundEvent soundEvent = entity.getCastSpellSound();
            if (soundEvent != null)
                entity.playSound(soundEvent, 1.0F, 1.0F);
            soundEvent = spell.getCastSound();
            if (soundEvent != null)
                entity.playSound(soundEvent, 1.0F, 1.0F);
        }
    }

    @Override
    public void stop() {
        spell.stop(entity);
    }

    public Spell<T> getSpell() {
        return spell;
    }
}
