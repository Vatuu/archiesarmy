package dev.vatuu.archiesarmy.extensions.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

import dev.vatuu.archiesarmy.extensions.EntityExt;
import dev.vatuu.archiesarmy.extensions.LivingEntityExt;
import dev.vatuu.archiesarmy.registries.Tags;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityExt, EntityExt {

    private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected LivingEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker()V", at = @At("TAIL"))
    public void injectDataTracker(CallbackInfo ci) {
        if(this.isEnchantable()) this.dataTracker.startTracking(ENCHANTED, false);
    }

    @Override
    public boolean isEnchantable() {
        return this.getType().isIn(Tags.ENCHANTABLE_ENTITIES) && this.isAlive();
    }

    @Override
    public void setEnchanted(boolean enchanted) {
        if(this.isEnchantable() || !enchanted) this.dataTracker.set(ENCHANTED, enchanted);
    }

    @Override
    public boolean isEnchanted() {
        return this.isEnchantable() && this.dataTracker.get(ENCHANTED);
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        EntityDimensions dimensions = super.getDimensions(pose);
        if(isEnchanted()) dimensions = dimensions.scaled(this.getEnchantedScale());
        return dimensions;
    }

    @Inject(method = "writeCustomDataToTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
    public void injectWriteTagData(CompoundTag tag, CallbackInfo ci) {
        if(this.isEnchantable()) tag.putBoolean("Enchanted", this.isEnchanted());
    }

    @Inject(method = "readCustomDataFromTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
    public void injectReadTagData(CompoundTag tag, CallbackInfo ci) {
        if(this.isEnchantable()) this.setEnchanted(tag.getBoolean("Enchanted"));
    }
}
