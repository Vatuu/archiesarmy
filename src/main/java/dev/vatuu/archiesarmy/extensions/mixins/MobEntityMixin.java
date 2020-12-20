package dev.vatuu.archiesarmy.extensions.mixins;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

import dev.vatuu.archiesarmy.extensions.MobEntityExt;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity implements MobEntityExt {

    @Shadow @Final private static TrackedData<Byte> MOB_FLAGS;

    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    public void setEnchanted(boolean enchanted) {
        byte b = this.dataTracker.get(MOB_FLAGS);
        this.dataTracker.set(MOB_FLAGS, enchanted ? (byte)(b | 128) : (byte)(b & 127));
    }

    @Override
    public boolean isEnchanted() {
        return (this.dataTracker.get(MOB_FLAGS) & 128) != 0;
    }
}
