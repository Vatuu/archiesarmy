package dev.vatuu.archiesarmy.extensions.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

import dev.vatuu.archiesarmy.extensions.EntityExt;
import dev.vatuu.archiesarmy.extensions.MobEntityExt;
import dev.vatuu.archiesarmy.registries.Tags;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity implements MobEntityExt, EntityExt {

    private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(MobEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker()V", at = @At("TAIL"))
    public void injectDataTracker(CallbackInfo ci) {
        if(isEnchantable()) this.dataTracker.startTracking(ENCHANTED, false);
    }

    @Override
    public boolean isEnchantable() {
        return this.getType().isIn(Tags.ENCHANTABLE_ENTITIES) && this.isAlive();
    }

    @Override
    public void setEnchanted(boolean enchanted) {
        if(isEnchantable()) this.dataTracker.set(ENCHANTED, enchanted);
    }

    @Override
    public boolean isEnchanted() {
        return isEnchantable() && this.dataTracker.get(ENCHANTED);
    }

    @Override
    public float getScaleFactor() {
        return super.getScaleFactor() * (isEnchanted() ? 1.2F : 1.0F);
    }

    @Inject(method = "writeCustomDataToTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
    public void injectWriteTagData(CompoundTag tag, CallbackInfo ci) {
        if(isEnchantable()) tag.putBoolean("Enchanted", this.isEnchanted());
    }

    @Inject(method = "readCustomDataFromTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
    public void injectReadTagData(CompoundTag tag, CallbackInfo ci) {
        if(isEnchantable()) this.setEnchanted(tag.getBoolean("Enchanted"));
    }
}
