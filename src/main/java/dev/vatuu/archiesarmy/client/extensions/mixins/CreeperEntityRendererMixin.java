package dev.vatuu.archiesarmy.client.extensions.mixins;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.extensions.EnchantableEntityRenderer;
import dev.vatuu.archiesarmy.client.features.EnchantmentEffectFeatureRenderer;
import dev.vatuu.archiesarmy.client.features.EnchantmentGlowFeatureRenderer;
import dev.vatuu.archiesarmy.extensions.LivingEntityExt;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreeperEntityRenderer.class)
public abstract class CreeperEntityRendererMixin extends MobEntityRenderer<CreeperEntity, CreeperEntityModel<CreeperEntity>> implements EnchantableEntityRenderer<CreeperEntity> {

    private static final Identifier ENCHANTED_TEXTURE = ArchiesArmy.id("textures/entities/enchanting/creeper.png");
    private static final Identifier EMISSIVE_TEXTURE = ArchiesArmy.id("textures/entities/enchanting/creeper_emissive.png");

    public CreeperEntityRendererMixin(EntityRenderDispatcher entityRenderDispatcher, CreeperEntityModel<CreeperEntity> entityModel, float f) {
        super(entityRenderDispatcher, entityModel, f);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void addFeatureRenderer(CallbackInfo info) {
        this.addFeature(new EnchantmentGlowFeatureRenderer<>(this, new CreeperEntityModel<>(), this.getEmissiveTexture()));
        this.addFeature(new EnchantmentEffectFeatureRenderer<>(this, new CreeperEntityModel<>()));
    }

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    public void getTexture(CreeperEntity e, CallbackInfoReturnable<Identifier> info) {
        if (((LivingEntityExt) e).isEnchanted())
            info.setReturnValue(this.getEnchantedTexture(e));
    }

    @Override
    public Identifier getEnchantedTexture(CreeperEntity entity) {
        return ENCHANTED_TEXTURE;
    }

    @Override
    public Identifier getEmissiveTexture() {
        return EMISSIVE_TEXTURE;
    }
}
