package dev.vatuu.archiesarmy.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.vatuu.archiesarmy.client.bedrock.animation.AnimationBone;
import dev.vatuu.archiesarmy.client.bedrock.animation.AnimationData;
import dev.vatuu.archiesarmy.client.bedrock.animation.AnimationManager;
import dev.vatuu.archiesarmy.client.bedrock.geometry.GeometryManager;
import dev.vatuu.archiesarmy.client.bedrock.geometry.GeometryObject;
import dev.vatuu.archiesarmy.client.entityrenderer.EntityRenderer;
import dev.vatuu.archiesarmy.client.network.ClientNetworkHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceType;

@Environment(EnvType.CLIENT)
public class ArchiesArmyClient implements ClientModInitializer {

    public static ArchiesArmyClient INSTANCE;

    public GeometryManager geometryManager;
    public AnimationManager animationManager;

    public ClientNetworkHandler networkHandler;

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
