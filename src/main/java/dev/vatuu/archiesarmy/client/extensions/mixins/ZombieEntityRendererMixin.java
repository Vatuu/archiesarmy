package dev.vatuu.archiesarmy.client.extensions.mixins;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.client.extensions.EnchantableEntityRenderer;
import dev.vatuu.archiesarmy.client.features.EnchantmentEffectFeatureRenderer;
import dev.vatuu.archiesarmy.client.features.EnchantmentGlowFeatureRenderer;
import dev.vatuu.archiesarmy.extensions.LivingEntityExt;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntityRenderer.class)
public abstract class ZombieEntityRendererMixin extends ZombieBaseEntityRenderer<ZombieEntity, ZombieEntityModel<ZombieEntity>> implements EnchantableEntityRenderer<ZombieEntity> {

    private static final Identifier ENCHANTED_TEXTURE = ArchiesArmy.id("textures/entities/enchanting/zombie.png");
    private static final Identifier EMISSIVE_TEXTURE = ArchiesArmy.id("textures/entities/enchanting/zombie_emissive.png");

    protected ZombieEntityRendererMixin(EntityRenderDispatcher dispatcher, ZombieEntityModel<ZombieEntity> zombieEntityModel, ZombieEntityModel<ZombieEntity> zombieEntityModel2, ZombieEntityModel<ZombieEntity> zombieEntityModel3) {
        super(dispatcher, zombieEntityModel, zombieEntityModel2, zombieEntityModel3);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void addFeatureRenderer(CallbackInfo info) {
        this.addFeature(new EnchantmentGlowFeatureRenderer<>(this, new ZombieEntityModel<>(0, false), this.getEmissiveTexture()));
        this.addFeature(new EnchantmentEffectFeatureRenderer<>(this, new ZombieEntityModel<>(0F, false)));
    }

    @Override
    public Identifier getTexture(ZombieEntity e) {
        if (((LivingEntityExt) e).isEnchanted() && !(e instanceof DrownedEntity))
            return this.getEnchantedTexture(e);
        else
            return super.getTexture(e);
    }

    @Override
    public Identifier getEnchantedTexture(ZombieEntity entity) {
        return ENCHANTED_TEXTURE;
    }

    @Override
    public Identifier getEmissiveTexture() {
        return EMISSIVE_TEXTURE;
    }
}
