package dev.vatuu.archiesarmy.extensions.mixins;

import dev.vatuu.archiesarmy.extensions.EntityExt;
import dev.vatuu.archiesarmy.extensions.LivingEntityExt;
import dev.vatuu.archiesarmy.registries.Tags;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityExt, EntityExt {

    private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected LivingEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker()V", at = @At("TAIL"))
    public void injectDataTracker(CallbackInfo ci) {
        if (this.isEnchantable()) this.dataTracker.startTracking(ENCHANTED, false);
    }

    @Override
    public boolean isEnchantable() {
        return this.getType().isIn(Tags.ENCHANTABLE_ENTITIES) && this.isAlive();
    }

    @Override
    public void setEnchanted(boolean enchanted) {
        if (this.isEnchantable() || !enchanted) this.dataTracker.set(ENCHANTED, enchanted);
    }

    @Override
    public boolean isEnchanted() {
        return this.isEnchantable() && this.dataTracker.get(ENCHANTED);
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        EntityDimensions dimensions = super.getDimensions(pose);
        if (isEnchanted()) dimensions = dimensions.scaled(this.getEnchantedScale());
        return dimensions;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void injectWriteTagData(NbtCompound tag, CallbackInfo ci) {
        if (this.isEnchantable()) tag.putBoolean("Enchanted", this.isEnchanted());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void injectReadTagData(NbtCompound tag, CallbackInfo ci) {
        if (this.isEnchantable()) this.setEnchanted(tag.getBoolean("Enchanted"));
    }
}
