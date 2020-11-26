package dev.vatuu.archiesarmy.client.extensions.mixins;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import dev.vatuu.archiesarmy.client.features.EnchantmentEffectFeatureRenderer;
import dev.vatuu.archiesarmy.client.features.ZombieEnchantmentFeatureRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.StrayEntityRenderer;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.feature.StrayOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntityRenderer.class)
public abstract class ZombieEntityRendererMixin extends ZombieBaseEntityRenderer<ZombieEntity, ZombieEntityModel<ZombieEntity>> {

    protected ZombieEntityRendererMixin(EntityRenderDispatcher dispatcher, ZombieEntityModel<ZombieEntity> zombieEntityModel, ZombieEntityModel<ZombieEntity> zombieEntityModel2, ZombieEntityModel<ZombieEntity> zombieEntityModel3) {
        super(dispatcher, zombieEntityModel, zombieEntityModel2, zombieEntityModel3);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void addFeatureRenderer(CallbackInfo info) {
        this.addFeature(new ZombieEnchantmentFeatureRenderer<>(this));
        this.addFeature(new EnchantmentEffectFeatureRenderer<>(this, new ZombieEntityModel<>(0F, false)));
    }
}
