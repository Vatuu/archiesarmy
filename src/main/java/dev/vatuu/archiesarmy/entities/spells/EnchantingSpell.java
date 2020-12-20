package dev.vatuu.archiesarmy.entities.spells;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.entities.EnchanterEntity;
import dev.vatuu.archiesarmy.extensions.MobEntityExt;
import dev.vatuu.archiesarmy.registries.Sounds;
import dev.vatuu.archiesarmy.spells.Spell;

import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Objects;

public class EnchantingSpell extends Spell<EnchanterEntity> {

    public static final Identifier ID = ArchiesArmy.id("enchant");

    public static final ImmutableSet<EntityType<?>> ENHANCEABLE_ENTITIES = ImmutableSet.of(EntityType.ZOMBIE, EntityType.CREEPER, EntityType.SKELETON);

    private final TargetPredicate enchanterTargetPredicate = (new TargetPredicate()).setBaseMaxDistance(16.0D).includeInvulnerable().setPredicate(livingEntity -> {
        System.out.println("haha yes "+livingEntity);
        return ENHANCEABLE_ENTITIES.stream().anyMatch(t -> t.equals(livingEntity.getType()) &&
                EnchantingSpell.canEnchant(livingEntity));
    });

    public static boolean canEnchant(LivingEntity target) {
        return !(target instanceof PlayerEntity) &&
                ENHANCEABLE_ENTITIES.contains(target.getType()) &&
                target.isAlive() &&
                target instanceof MobEntityExt &&
                !((MobEntityExt) target).isEnchanted();
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
