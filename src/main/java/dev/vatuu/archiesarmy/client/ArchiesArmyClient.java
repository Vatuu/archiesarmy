package dev.vatuu.archiesarmy.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.vatuu.archiesarmy.animation.client.AnimationRegistry;
import dev.vatuu.archiesarmy.client.geometry.GeometryBone;
import dev.vatuu.archiesarmy.client.geometry.GeometryCuboid;
import dev.vatuu.archiesarmy.client.geometry.GeometryData;
import dev.vatuu.archiesarmy.client.entityrenderer.EntityRenderer;
import dev.vatuu.archiesarmy.client.geometry.GeometryManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceType;

@Environment(EnvType.CLIENT)
public class ArchiesArmyClient implements ClientModInitializer {

    public static ArchiesArmyClient INSTANCE;

    public GeometryManager geometryManager;

    public static Gson GSON_CLIENT = new GsonBuilder()
            .registerTypeAdapter(GeometryData.class, new GeometryData.Deserializer())
            .registerTypeAdapter(GeometryBone.class, new GeometryBone.Deserializer())
            .registerTypeAdapter(GeometryCuboid.class, new GeometryCuboid.Deserializer())
            .create();

    @Override
    public void onInitializeClient() {
        INSTANCE = this;

        geometryManager = new GeometryManager(MinecraftClient.getInstance().getResourceManager());
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(geometryManager);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(AnimationRegistry.INSTANCE);

        EntityRenderer.register();
    }
}
