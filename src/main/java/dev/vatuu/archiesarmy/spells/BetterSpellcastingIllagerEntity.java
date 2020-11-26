package dev.vatuu.archiesarmy.spells;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public abstract class BetterSpellcastingIllagerEntity extends IllagerEntity {

    public int spellTicks;

    private Spell<? extends BetterSpellcastingIllagerEntity> spell = SpellRegistry.SPELL_NONE;

    public BetterSpellcastingIllagerEntity(EntityType<? extends BetterSpellcastingIllagerEntity> type, World w) {
        super(type, w);
    }

    public abstract SoundEvent getCastPrepareSound();
    public abstract SoundEvent getCastSpellSound();

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SPELL, SpellRegistry.SPELL_NONE);
    }

    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.spellTicks = tag.getInt("SpellTicks");
    }

    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putInt("SpellTicks", this.spellTicks);
    }

    public boolean isSpellcasting() {
        if (this.world.isClient) {
            return !this.dataTracker.get(SPELL).equals(SpellRegistry.SPELL_NONE);
        } else {
            return this.spellTicks > 0;
        }
    }

    @Environment(EnvType.CLIENT)
    public IllagerEntity.State getState() {
        if (this.isSpellcasting()) {
            return IllagerEntity.State.SPELLCASTING;
        } else {
            return this.isCelebrating() ? IllagerEntity.State.CELEBRATING : IllagerEntity.State.CROSSED;
        }
    }

    public void setSpell(Spell<? extends BetterSpellcastingIllagerEntity> spell) {
        this.spell = spell;
        this.dataTracker.set(SPELL, spell);
    }

    public Spell<? extends BetterSpellcastingIllagerEntity> getSpell() {
        System.out.println("Getting spell");
        System.out.printf("This.Spell: %s | DataTracker: %s%n", this.spell, this.dataTracker.get(SPELL));
        return !this.world.isClient ? this.spell : this.dataTracker.get(SPELL);
    }

    protected void mobTick() {
        super.mobTick();
        if (this.spellTicks > 0)
            --this.spellTicks;
    }

    public void tick() {
        super.tick();
    }

    private static final TrackedDataHandler<Spell<? extends BetterSpellcastingIllagerEntity>> TRACKED_SPELL = new TrackedDataHandler<Spell<? extends BetterSpellcastingIllagerEntity>>() {
        @Override
        public void write(PacketByteBuf data, Spell spell) {
            data.writeString(spell.getId().toString());
        }

        @Override
        public Spell<? extends BetterSpellcastingIllagerEntity> read(PacketByteBuf buf) {
            return SpellRegistry.REGISTRY.get(new Identifier(buf.readString()));
        }

        @Override
        public Spell<? extends BetterSpellcastingIllagerEntity> copy(Spell<? extends BetterSpellcastingIllagerEntity> spell) {
            return spell;
        }
    };
    private static final TrackedData<Spell<? extends BetterSpellcastingIllagerEntity>> SPELL = DataTracker.registerData(BetterSpellcastingIllagerEntity.class, TRACKED_SPELL);

    static {
        TrackedDataHandlerRegistry.register(TRACKED_SPELL);
    }
}
