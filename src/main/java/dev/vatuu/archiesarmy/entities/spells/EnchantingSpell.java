package dev.vatuu.archiesarmy.entities.spells;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.entities.EnchanterEntity;
import dev.vatuu.archiesarmy.extensions.MobEntityExt;
import dev.vatuu.archiesarmy.registries.Sounds;
import dev.vatuu.archiesarmy.spells.BetterSpellcastingIllagerEntity;
import dev.vatuu.archiesarmy.spells.Spell;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.core.lookup.SystemPropertiesLookup;

import java.util.Objects;

public class EnchantingSpell extends Spell<EnchanterEntity> {

    public static final Identifier ID = ArchiesArmy.id("enchant");

    public EnchantingSpell() { super(ID); }

    @Override
    public int getWarmupTime() {
        return 30;
    }

    @Override
    public int getCastTime() {
        return 40;
    }

    @Override
    public int getNextCastTime() {
        return 50;
    }

    @Override
    public SoundEvent getCastSound() {
        return Sounds.ENTITY_ENCHANTER_SPELL;
    }

    @Override
    public boolean canStart(EnchanterEntity entity) {
        if(entity.getTarget() != null && entity.getTarget().getType() != EntityType.PLAYER)
            return entity.getTarget().isAlive() && entity.enchantingEntities.size() < 3;
        return false;
    }

    @Override
    public boolean canContinue(EnchanterEntity entity) {
        return entity.getTarget() != null && entity.getTarget().isAlive();
    }

    @Override
    public void castSpell(EnchanterEntity entity) {
        entity.enchantingEntities.add(Objects.requireNonNull(entity.getTarget()).getUuid());
        ((MobEntityExt) entity.getTarget()).setEnchanted(true);
    }
}
