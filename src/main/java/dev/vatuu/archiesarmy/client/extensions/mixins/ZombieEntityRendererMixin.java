package dev.vatuu.archiesarmy.client.extensions.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.features.EnchantmentEffectFeatureRenderer;
import dev.vatuu.archiesarmy.client.features.EnchantmentGlowFeatureRenderer;
import dev.vatuu.archiesarmy.extensions.MobEntityExt;

@Mixin(ZombieEntityRenderer.class)
public abstract class ZombieEntityRendererMixin extends ZombieBaseEntityRenderer<ZombieEntity, ZombieEntityModel<ZombieEntity>> {

    private static final Identifier ENCHANTED_TEXTURE = ArchiesArmy.id("textures/entities/enchanting/zombie.png");
    private static final Identifier EMISSIVE_TEXTURE = ArchiesArmy.id("textures/entities/enchanting/zombie_emissive.png");

    protected ZombieEntityRendererMixin(EntityRenderDispatcher dispatcher, ZombieEntityModel<ZombieEntity> zombieEntityModel, ZombieEntityModel<ZombieEntity> zombieEntityModel2, ZombieEntityModel<ZombieEntity> zombieEntityModel3) {
        super(dispatcher, zombieEntityModel, zombieEntityModel2, zombieEntityModel3);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void addFeatureRenderer(CallbackInfo info) {
        this.addFeature(new EnchantmentGlowFeatureRenderer<>(this, new ZombieEntityModel<>(0, false), EMISSIVE_TEXTURE));
        this.addFeature(new EnchantmentEffectFeatureRenderer<>(this, new ZombieEntityModel<>(0F, false)));
    }

    @Override
    public Identifier getTexture(ZombieEntity e) {
        if(((MobEntityExt)e).isEnchanted() && !(e instanceof DrownedEntity))
            return ENCHANTED_TEXTURE;
        else
            return super.getTexture(e);
    }
}
