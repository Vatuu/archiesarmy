package dev.vatuu.archiesarmy.client.bedrock.animation;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import dev.vatuu.archiesarmy.client.ArchiesArmyClient;
import dev.vatuu.archiesarmy.client.bedrock.geometry.GeometryObject;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationManager implements SimpleSynchronousResourceReloadListener, AutoCloseable {

    public static final Identifier MISSING_IDENTIFIER = new Identifier("");

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Identifier ANIMATION_ID = new Identifier("animation");
    private static final String FORMAT_VERSION = "1.8.0";

    private final ResourceManager resourceContainer;
    private final Map<Identifier, AnimationData> animData = new HashMap<>();

    private AnimationData missing;

    public AnimationManager(ResourceManager resourceManager) {
        this.resourceContainer = resourceManager;
    }

    @Override
    public Identifier getFabricId() {
        return ANIMATION_ID;
    }

    public AnimationData getAnimationData(Identifier id) {
        return animData.computeIfAbsent(id, this::loadAnimationData);
    }

    //TODO: Use internal ResourceManager, use Identifier Lookup and register everything.
    private AnimationData loadAnimationData(Identifier identifier) {
        try {
            Resource resource = MinecraftClient.getInstance().getResourceManager().getResource(identifier);
            JsonObject e = new JsonParser().parse(new InputStreamReader(resource.getInputStream())).getAsJsonObject();

            if(!e.has("format_version") || !e.get("format_version").getAsString().equals(FORMAT_VERSION))
                throw new JsonParseException("Unable to verify Format Version!");

            if(!e.has("animations") || e.get("animations").getAsJsonObject().size() < 1)
                throw new JsonParseException("No Animation Data found!");

            Map<String, AnimationData> map = JsonOps.INSTANCE.withDecoder(Codec.unboundedMap(Codec.STRING, AnimationData.CODEC))
                    .apply(e.get("animations")).resultOrPartial(s -> { throw new JsonParseException(s); })
                    .orElse(new Pair<>(ImmutableMap.of("animation.minecraft.missing", getAnimationData(MISSING_IDENTIFIER)), e))
                    .getFirst();

            if(map.isEmpty())
                throw new JsonParseException("No Animation Data has been parsed!");

            return (AnimationData)map.values().toArray()[0];
        } catch (JsonParseException e) {
            if (identifier != MISSING_IDENTIFIER)
                LOGGER.warn("Failed to load Animation Data[{}]: {}", identifier, e);
            return getAnimationData(MISSING_IDENTIFIER);
        } catch (Throwable e) {
            CrashReport crash = CrashReport.create(e, "Registering Animation Data:");
            CrashReportSection section = crash.addElement("ResourceLocation being registered");
            section.add("ResourceLocation", identifier);
            section.add("AnimationData", animData);
            throw new CrashException(crash);
        }
    }

    @Override
    public void apply(ResourceManager manager) {
        Lists.newArrayList(this.animData.keySet()).stream().filter(i -> !i.equals(MISSING_IDENTIFIER)).forEach(k -> animData.replace(k, loadAnimationData(k)));
        animData.put(MISSING_IDENTIFIER, loadMissingData());
    }

    @Override
    public void close() {
        this.animData.clear();
    }

    private AnimationData loadMissingData() {
        if(missing != null)
            return missing;

        JsonObject e = new JsonParser().parse(new StringReader(MISSING_ANIMATION_DATA)).getAsJsonObject();
        Map<String, AnimationData> map = JsonOps.INSTANCE.withDecoder(Codec.unboundedMap(Codec.STRING, AnimationData.CODEC))
                .apply(e.get("animations")).resultOrPartial(s -> { throw new JsonParseException(s); })
                .get()
                .getFirst();
        missing = (AnimationData)map.values().toArray()[0];

        return missing;
    }

    private static final String MISSING_ANIMATION_DATA =
            "{\n" +
                    "\t\"format_version\": \"" + FORMAT_VERSION + "\",\n" +
                    "\t\"animations\": {\n" +
                    "\t\t\"animation.minecraft.missing\": {\n" +
                    "\t\t\t\"animation_length\": 0.0,\n" +
                    "\t\t\t\"override_previous_animation\": true" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}";
}
