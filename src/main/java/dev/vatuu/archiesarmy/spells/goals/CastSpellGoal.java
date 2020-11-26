package dev.vatuu.archiesarmy.spells.goals;

import dev.vatuu.archiesarmy.spells.BetterSpellcastingIllagerEntity;
import dev.vatuu.archiesarmy.spells.Spell;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.sound.SoundEvent;

public class CastSpellGoal<T extends BetterSpellcastingIllagerEntity> extends Goal {

    private final T entity;
    private final Spell<T> spell;
    private int spellWarmup, nextCast;

    public CastSpellGoal(T entity, Spell<T> spell) {
        this.entity = entity;
        this.spell = spell;
    }

    @Override
    public boolean canStart() {
        if(entity.isSpellcasting())
            return false;
        if(entity.age < nextCast)
            return false;
        return spell.canStart(entity);
    }

    @Override
    public boolean shouldContinue() {
        return this.spellWarmup >= 0 && spell.canContinue(entity);
    }

    @Override
    public void start() {
        entity.setSpell(spell);
        entity.spellTicks = spell.getCastTime() + spell.getWarmupTime();
        this.spellWarmup = spell.getWarmupTime();
        this.nextCast = entity.age + (spell.getNextCastTime() + spell.getWarmupTime() + spell.getCastTime());
        SoundEvent soundEvent = entity.getCastPrepareSound();
        if (soundEvent != null)
            entity.playSound(soundEvent, 1.0F, 1.0F);
    }

    @Override
    public void tick() {
        if (this.spellWarmup == 0) {
            spell.castSpell(entity);
            SoundEvent soundEvent = entity.getCastSpellSound();
            if (soundEvent != null)
                entity.playSound(soundEvent, 1.0F, 1.0F);
            soundEvent = spell.getCastSound();
            if(soundEvent != null)
                entity.playSound(soundEvent, 1.0F, 1.0F);
        }

        this.spellWarmup--;
    }
}
