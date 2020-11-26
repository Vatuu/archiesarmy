package dev.vatuu.archiesarmy.spells;

import dev.vatuu.archiesarmy.ArchiesArmy;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public abstract class Spell<T extends BetterSpellcastingIllagerEntity> {

    private final Identifier id;

    public Spell(Identifier id) { this.id = id; }

    public Identifier getId() { return id; }

    public abstract int getWarmupTime();
    public abstract int getCastTime();
    public abstract int getNextCastTime();
    public SoundEvent getCastSound() { return null; }

    public abstract boolean canStart(T entity);
    public abstract boolean canContinue(T entity);
    public abstract void castSpell(T entity);

    public static class None extends Spell<BetterSpellcastingIllagerEntity> {

        public static Identifier ID = ArchiesArmy.id("none");

        public None() { super(ID); }

        public int getWarmupTime() { return 0; }
        public int getCastTime() { return 0; }
        public int getNextCastTime() { return Integer.MAX_VALUE; }
        public boolean canContinue(BetterSpellcastingIllagerEntity entity) { return false; }
        public boolean canStart(BetterSpellcastingIllagerEntity entity) { return false; }
        public void castSpell(BetterSpellcastingIllagerEntity entity) { }
    }
}
