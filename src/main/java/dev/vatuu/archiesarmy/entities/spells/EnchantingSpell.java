package dev.vatuu.archiesarmy.entities.spells;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.entities.EnchanterEntity;
import dev.vatuu.archiesarmy.extensions.LivingEntityExt;
import dev.vatuu.archiesarmy.registries.Sounds;
import dev.vatuu.archiesarmy.spells.Spell;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

public class EnchantingSpell extends Spell<EnchanterEntity> {

    public static final Identifier ID = ArchiesArmy.id("enchant");

    private final TargetPredicate enchanterTargetPredicate = TargetPredicate.DEFAULT.setBaseMaxDistance(16.0D).setPredicate(livingEntity -> EnchantingSpell.isEnchantable(livingEntity, false));

    public static boolean isEnchantable(LivingEntity target, boolean already) {
        return target != null &&
                ((LivingEntityExt) target).isEnchantable(already);
    }

    public EnchantingSpell() {
        super(ID);
    }

    @Override
    public int getInitialCooldown() {
        return 30;
    }

    @Override
    public int getSpellTicks() {
        return 40;
    }

    @Override
    public int getStartTimeDelay() {
        return 140;
    }

    @Override
    public SoundEvent getCastSound() {
        return Sounds.ENTITY_ENCHANTER_SPELL;
    }

    @Override
    public boolean canStart(EnchanterEntity entity) {
        if (entity.isSpellcasting()) {
            return false;
        } else if (entity.age < this.getInitialCooldown()) {
            return false;
        } else if (entity.canEnchant()) {
            List<LivingEntity> list = entity.world.getTargets(LivingEntity.class, this.enchanterTargetPredicate, entity, entity.getBoundingBox().expand(entity.getEnchantingRange(), 4.0D, entity.getEnchantingRange()));
            if (list.isEmpty()) {
                return false;
            } else {
                entity.setEnchantingTarget(list.get(entity.getRandom().nextInt(list.size())));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldContinue(EnchanterEntity entity) {
        return entity.getEnchantingTarget() != null && entity.getEnchantingTarget().isAlive();
    }

    @Override
    public void castSpell(EnchanterEntity entity) {
        entity.enchant(Objects.requireNonNull(entity.getEnchantingTarget()));
    }

    @Override
    public void stop(EnchanterEntity entity) {
        super.stop(entity);
        entity.setEnchantingTarget(null);
    }
}
