package dev.vatuu.archiesarmy.client;

import dev.vatuu.archiesarmy.animation.client.AnimationRegistry;
import dev.vatuu.archiesarmy.client.entityrenderer.EntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

@Environment(EnvType.CLIENT)
public class ArchiesArmyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRenderer.register();
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(AnimationRegistry.INSTANCE);
    }
}
