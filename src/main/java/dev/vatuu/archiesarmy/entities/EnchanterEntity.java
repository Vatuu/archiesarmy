package dev.vatuu.archiesarmy.entities;

import com.google.common.collect.ImmutableSet;

import dev.vatuu.archiesarmy.entities.spells.EnchantingSpell;
import dev.vatuu.archiesarmy.extensions.MobEntityExt;
import dev.vatuu.archiesarmy.registries.Sounds;
import dev.vatuu.archiesarmy.registries.Spells;
import dev.vatuu.archiesarmy.spells.BetterSpellcastingIllagerEntity;
import dev.vatuu.archiesarmy.spells.Spell;
import dev.vatuu.archiesarmy.spells.goals.CastSpellGoal;
import dev.vatuu.archiesarmy.spells.goals.LookAtTargetGoal;
import dev.vatuu.archiesarmy.spells.goals.LookAtTargetOrEnchantingTargetGoal;
import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

public class EnchanterEntity extends BetterSpellcastingIllagerEntity {

    public final List<UUID> enchantingEntities = new ArrayList<>();
    public LivingEntity enchantingTarget;

    public EnchanterEntity(EntityType<EnchanterEntity> type, World world) {
        super(type, world);
        setSpell(Spells.ENCHANTING);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        enchantingEntities.clear();
        ListTag list = tag.getList("Enchanting", 0);
        list.forEach(u -> enchantingEntities.add(NbtHelper.toUuid(u)));
    }

    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        ListTag enchanting = new ListTag();
        for (int i = 0; i < enchantingEntities.size(); i++)
            enchanting.addTag(i, NbtHelper.fromUuid(enchantingEntities.get(i)));
        tag.put("Enchanting", enchanting);
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtTargetOrEnchantingTargetGoal(this));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, PlayerEntity.class, 8.0F, 0.6D, 1.0D));
        this.goalSelector.add(3, new CastSpellGoal<>(this, Spells.ENCHANTING));
        this.goalSelector.add(4, new WanderAroundGoal(this, 0.6d));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(6, new LookAtEntityGoal(this, MobEntity.class, 8.0F));

        this.targetSelector.add(1, (new FollowTargetGoal<>(this, PlayerEntity.class, false)).setMaxTimeWithoutVisibility(100));
    }

    public boolean isTeammate(Entity other) {
        if (other == null)
            return false;
        else if (other == this || super.isTeammate(other) || enchantingEntities.contains(other.getUuid()))
            return true;
        else if (other instanceof LivingEntity && ((LivingEntity)other).getGroup() == EntityGroup.ILLAGER)
            return this.getScoreboardTeam() == null && other.getScoreboardTeam() == null;

        return false;
    }

   public float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 1.62F;
    }

    public void onDeath(DamageSource source) {
        super.onDeath(source);
        ServerWorld w = (ServerWorld)getEntityWorld();
        enchantingEntities.forEach(u -> {
            Entity e = w.getEntity(u);
            if(!(e == null || !e.isAlive()) && e instanceof MobEntity)
                ((MobEntityExt)e).setEnchanted(false);
        });
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        ServerWorld w = (ServerWorld)getEntityWorld();
        enchantingEntities.removeIf(u -> {
            Entity e = w.getEntity(u);
            return e == null || !e.isAlive();
        });
    }

    public void addBonusForWave(int wave, boolean unused) { }

    public SoundEvent getCelebratingSound() { return null; }
    protected SoundEvent getAmbientSound() { return Sounds.ENTITY_ENCHANTER_IDLE; }
    protected SoundEvent getDeathSound() { return Sounds.ENTITY_ENCHANTER_DEATH; }
    protected SoundEvent getHurtSound(DamageSource source) { return Sounds.ENTITY_ENCHANTER_HURT; }
    public SoundEvent getCastPrepareSound() { return Sounds.ENTITY_ENCHANTER_PREATTACK; }
    public SoundEvent getCastSpellSound() { return Sounds.ENTITY_ENCHANTER_SPELL; }

    public boolean canEnchant() {
        return enchantingEntities.size() < 3;
    }

    public void enchant(LivingEntity target) {
        this.enchantingEntities.add(target.getUuid());
        ((MobEntityExt) target).setEnchanted(true);
    }

    public LivingEntity getEnchantingTarget() {
        return enchantingTarget;
    }

    public void setEnchantingTarget(@Nullable LivingEntity target) {
        this.enchantingTarget = target;
    }
}
