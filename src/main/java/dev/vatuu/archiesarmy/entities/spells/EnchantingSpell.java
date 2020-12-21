package dev.vatuu.archiesarmy.entities.spells;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.entities.EnchanterEntity;
import dev.vatuu.archiesarmy.extensions.MobEntityExt;
import dev.vatuu.archiesarmy.registries.Sounds;
import dev.vatuu.archiesarmy.spells.Spell;

import java.util.List;
import java.util.Objects;

public class EnchantingSpell extends Spell<EnchanterEntity> {

    public static final Identifier ID = ArchiesArmy.id("enchant");

    private final TargetPredicate enchanterTargetPredicate = (new TargetPredicate()).setBaseMaxDistance(16.0D).includeInvulnerable().setPredicate(livingEntity -> EnchantingSpell.isEnchantable(livingEntity, false));

    public static boolean isEnchantable(LivingEntity target, boolean already) {
        return target instanceof MobEntity &&
                ((MobEntityExt) target).isEnchantable(already);
    }

    public EnchantingSpell() { super(ID); }

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
            List<LivingEntity> list = entity.world.getTargets(LivingEntity.class, this.enchanterTargetPredicate, entity, entity.getBoundingBox().expand(16.0D, 4.0D, 16.0D));
            if (list.isEmpty()) {
                return false;
            } else {
                entity.setEnchantingTarget(list.get(entity.getRandom().nextInt(list.size())));
                return true;
            }
        } return false;
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
