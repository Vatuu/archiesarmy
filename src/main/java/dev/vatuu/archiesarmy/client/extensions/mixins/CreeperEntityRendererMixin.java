package dev.vatuu.archiesarmy.client.extensions.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.features.EnchantmentEffectFeatureRenderer;
import dev.vatuu.archiesarmy.client.features.EnchantmentGlowFeatureRenderer;
import dev.vatuu.archiesarmy.extensions.MobEntityExt;

@Mixin(CreeperEntityRenderer.class)
public abstract class CreeperEntityRendererMixin extends MobEntityRenderer<CreeperEntity, CreeperEntityModel<CreeperEntity>> {

    private static final Identifier ENCHANTED_TEXTURE = ArchiesArmy.id("textures/entities/enchanting/creeper.png");
    private static final Identifier EMISSIVE_TEXTURE = ArchiesArmy.id("textures/entities/enchanting/creeper_emissive.png");

    public CreeperEntityRendererMixin(EntityRenderDispatcher entityRenderDispatcher, CreeperEntityModel<CreeperEntity> entityModel, float f) {
        super(entityRenderDispatcher, entityModel, f);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void addFeatureRenderer(CallbackInfo info) {
        this.addFeature(new EnchantmentGlowFeatureRenderer<>(this, new CreeperEntityModel<>(), EMISSIVE_TEXTURE));
        this.addFeature(new EnchantmentEffectFeatureRenderer<>(this, new CreeperEntityModel<>()));
    }

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    public void getTexture(CreeperEntity e, CallbackInfoReturnable<Identifier> info) {
        if(((MobEntityExt)e).isEnchanted())
            info.setReturnValue(ENCHANTED_TEXTURE);
    }
}
