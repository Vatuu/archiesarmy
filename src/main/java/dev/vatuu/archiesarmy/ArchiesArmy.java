package dev.vatuu.archiesarmy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.vatuu.archiesarmy.animation.TesseractComponent;
import dev.vatuu.archiesarmy.animation.client.BoneFrameData;
import dev.vatuu.archiesarmy.animation.client.Keyframe;
import dev.vatuu.archiesarmy.registries.Entities;
import dev.vatuu.archiesarmy.registries.Spells;
import dev.vatuu.archiesarmy.spells.SpellRegistry;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ArchiesArmy implements ModInitializer {

    public static final String MOD_ID = "archiesarmy";
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(BoneFrameData.class, new BoneFrameData.Deserializer())
            .registerTypeAdapter(Keyframe.class, new Keyframe.Deserializer())
            .create();

    public static final ComponentType<TesseractComponent> COMPONENT_ANIMATION = ComponentRegistry.INSTANCE.registerIfAbsent(id("tesseract_animation"), TesseractComponent.class).attach(EntityComponentCallback.event(Entity.class), TesseractComponent::new);

    @Override
    @SuppressWarnings("unchecked")
    public void onInitialize() {
        Entities.registerEntityAttributes();
        Registry.register((Registry<Registry<?>>)Registry.REGISTRIES, SpellRegistry.REGISTRY_KEY.getValue(), SpellRegistry.REGISTRY);
        Spells.init();
    }

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }
}
