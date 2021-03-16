package dev.vatuu.archiesarmy.entities;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.extensions.EntityExt;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class MinionEntity extends HostileEntity {

    public MinionEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 1.1F;
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!((EntityExt) this).getServerAnimationData().isEmpty()) {
            ((EntityExt) this).removeAnimation(ArchiesArmy.id("animations/entities/minion.animation"), false);
        } else
            ((EntityExt) this).addAnimation(ArchiesArmy.id("animations/entities/minion.animation"), false);

        return ActionResult.SUCCESS;
    }
}
