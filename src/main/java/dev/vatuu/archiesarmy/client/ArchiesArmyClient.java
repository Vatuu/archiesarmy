package dev.vatuu.archiesarmy.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceType;

import dev.vatuu.archiesarmy.client.bedrock.animation.AnimationBone;
import dev.vatuu.archiesarmy.client.bedrock.animation.AnimationData;
import dev.vatuu.archiesarmy.client.bedrock.animation.AnimationManager;
import dev.vatuu.archiesarmy.client.bedrock.geometry.GeometryBone;
import dev.vatuu.archiesarmy.client.bedrock.geometry.GeometryCuboid;
import dev.vatuu.archiesarmy.client.bedrock.geometry.GeometryData;
import dev.vatuu.archiesarmy.client.bedrock.geometry.GeometryManager;
import dev.vatuu.archiesarmy.client.entityrenderer.EntityRenderer;
import dev.vatuu.archiesarmy.client.network.ClientNetworkHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Environment(EnvType.CLIENT)
public class ArchiesArmyClient implements ClientModInitializer {

    public static ArchiesArmyClient INSTANCE;

    public GeometryManager geometryManager;
    public AnimationManager animationManager;

    public ClientNetworkHandler networkHandler;

    public static Gson GSON_CLIENT = new GsonBuilder()
            .registerTypeAdapter(GeometryData.class, new GeometryData.Deserializer())
            .registerTypeAdapter(GeometryBone.class, new GeometryBone.Deserializer())
            .registerTypeAdapter(GeometryCuboid.class, new GeometryCuboid.Deserializer())
            .registerTypeAdapter(AnimationBone.class, new AnimationBone.Deserializer())
            .registerTypeAdapter(AnimationData.class, new AnimationData.Deserializer())
            .create();

    @Override
    public void onInitializeClient() {
        INSTANCE = this;

        geometryManager = new GeometryManager(MinecraftClient.getInstance().getResourceManager());
        animationManager = new AnimationManager(MinecraftClient.getInstance().getResourceManager());
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(geometryManager);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(animationManager);

        EntityRenderer.register();

        networkHandler = new ClientNetworkHandler();
    }
}
