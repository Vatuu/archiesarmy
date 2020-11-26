package dev.vatuu.archiesarmy.entities;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.animation.TesseractComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class RedstoneMonstrosityEntity extends HostileEntity {

    public static final Identifier ANIMATION_WALK = ArchiesArmy.id("redstone_monstrosity_walk");
    public static final Identifier ANIMATION_AWAKEN = ArchiesArmy.id("redstone_monstrosity_awaken");

    public RedstoneMonstrosityEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /*protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.targetSelector.add(0, new FollowTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.add(1, new WanderAroundGoal(this, 0.2D));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 15.0F, 1.0F));
        this.goalSelector.add(3, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
    }*/

    public static DefaultAttributeContainer.Builder createAttributes() {
        return createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 20.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.world.isClient) {
            TesseractComponent comp = TesseractComponent.get(this);
            if(comp.getCurrentAnimation() == null) {
                comp.startAnimation(ANIMATION_WALK, 24, false);
            }
        }
    }
}
