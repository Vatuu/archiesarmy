package dev.vatuu.archiesarmy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.vatuu.archiesarmy.animation.TesseractComponent;
import dev.vatuu.archiesarmy.animation.client.BoneFrameData;
import dev.vatuu.archiesarmy.animation.client.Keyframe;
import dev.vatuu.archiesarmy.entities.Entities;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class ArchiesArmy implements ModInitializer {

    public static final String MOD_ID = "archiesarmy";
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(BoneFrameData.class, new BoneFrameData.Deserializer())
            .registerTypeAdapter(Keyframe.class, new Keyframe.Deserializer())
            .create();

    public static final ComponentType<TesseractComponent> COMPONENT_ANIMATION = ComponentRegistry.INSTANCE.registerIfAbsent(id("tesseract_animation"), TesseractComponent.class).attach(EntityComponentCallback.event(Entity.class), TesseractComponent::new);

    @Override
    public void onInitialize() {
        Entities.registerEntityAttributes();
    }

    public static final Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }
}
