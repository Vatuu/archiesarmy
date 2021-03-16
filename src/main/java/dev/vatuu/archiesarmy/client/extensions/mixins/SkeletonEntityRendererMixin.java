package dev.vatuu.archiesarmy.client.extensions.mixins;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.extensions.EnchantableEntityRenderer;
import dev.vatuu.archiesarmy.client.features.EnchantmentEffectFeatureRenderer;
import dev.vatuu.archiesarmy.client.features.EnchantmentGlowFeatureRenderer;
import dev.vatuu.archiesarmy.extensions.LivingEntityExt;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkeletonEntityRenderer.class)
public abstract class SkeletonEntityRendererMixin extends BipedEntityRenderer<AbstractSkeletonEntity, SkeletonEntityModel<AbstractSkeletonEntity>> implements EnchantableEntityRenderer<AbstractSkeletonEntity> {

    private static final Identifier ENCHANTED_TEXTURE = ArchiesArmy.id("textures/entities/enchanting/skeleton.png");
    private static final Identifier EMISSIVE_TEXTURE = ArchiesArmy.id("textures/entities/enchanting/skeleton_emissive.png");

    public SkeletonEntityRendererMixin(EntityRenderDispatcher dispatcher, SkeletonEntityModel<AbstractSkeletonEntity> model, float f) {
        super(dispatcher, model, f);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void addFeatureRenderer(CallbackInfo info) {
        this.addFeature(new EnchantmentGlowFeatureRenderer<>(this, new SkeletonEntityModel<>(), this.getEmissiveTexture()));
        this.addFeature(new EnchantmentEffectFeatureRenderer<>(this, new SkeletonEntityModel<>(0F, false)));
    }

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    public void getTexture(AbstractSkeletonEntity e, CallbackInfoReturnable<Identifier> info) {
        if (((LivingEntityExt) e).isEnchanted())
            info.setReturnValue(this.getEnchantedTexture(e));
    }

    @Override
    public Identifier getEnchantedTexture(AbstractSkeletonEntity entity) {
        return ENCHANTED_TEXTURE;
    }

    @Override
    public Identifier getEmissiveTexture() {
        return EMISSIVE_TEXTURE;
    }
}
