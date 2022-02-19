package dev.vatuu.archiesarmy.client.bedrock.geometry;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import dev.vatuu.archiesarmy.client.ArchiesArmyClient;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.texture.TextureManager;
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

public class GeometryManager implements SimpleSynchronousResourceReloadListener, AutoCloseable {

    public static final Identifier MISSING_IDENTIFIER = new Identifier("");

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Identifier GEOMETRY_ID = new Identifier("geometry");
    private static final String FORMAT_VERSION = "1.16.4";

    private final ResourceManager resourceContainer;
    private final Map<Identifier, GeometryObject> modelData = new HashMap<>();

    private GeometryObject missing;

    public GeometryManager(ResourceManager resourceManager) {
        this.resourceContainer = resourceManager;
    }

    @Override
    public Identifier getFabricId() {
        return GEOMETRY_ID;
    }

    public GeometryObject getModelData(Identifier id) {
        return modelData.computeIfAbsent(id, this::loadGeometryData);
    }

    //TODO: Use internal ResourceManager, use Identifier Lookup and register everything.
    private GeometryObject loadGeometryData(Identifier identifier) {
        try {
            Resource resource = MinecraftClient.getInstance().getResourceManager().getResource(identifier);
            JsonObject e = JsonParser.parseReader(new InputStreamReader(resource.getInputStream())).getAsJsonObject();

            if(!e.has("format_version") || !e.get("format_version").getAsString().equals(FORMAT_VERSION))
                throw new JsonParseException("Unable to verify Format Version!");

            if(!e.has(GEOMETRY_ID.toString()) || e.get(GEOMETRY_ID.toString()).getAsJsonArray().size() < 1)
                throw new JsonParseException("No Geometry Data found!");

            List<GeometryObject> list = JsonOps.INSTANCE.withDecoder(GeometryObject.CODEC.listOf())
                    .apply(e.get(GEOMETRY_ID.toString())).resultOrPartial(s -> { throw new JsonParseException(s); })
                    .orElse(new Pair<>(Lists.newArrayList(getModelData(MISSING_IDENTIFIER)), e))
                    .getFirst();

            if(list.isEmpty())
                throw new JsonParseException("No Geometry Data has been parsed!");

            if(e.has("debug") && e.get("debug").getAsBoolean())
                list.forEach(GeometryObject::setDebug);

            return list.get(0);
        } catch (JsonParseException e) {
            if (identifier != MISSING_IDENTIFIER)
                LOGGER.warn("Failed to parse Geometry Data[{}]: {}", identifier, e);
            return getModelData(MISSING_IDENTIFIER);
        } catch (Throwable e) {
            CrashReport crash = CrashReport.create(e, "Registering Geometry Data:");
            CrashReportSection section = crash.addElement("ResourceLocation being registered");
            section.add("ResourceLocation", identifier);
            section.add("GeometryData", modelData);
            throw new CrashException(crash);
        }
    }

    @Override
    public void reload(ResourceManager manager) {
        Lists.newArrayList(this.modelData.keySet()).stream().filter(i -> !i.equals(MISSING_IDENTIFIER)).forEach(k -> modelData.replace(k, loadGeometryData(k)));
        modelData.put(MISSING_IDENTIFIER, loadMissingData());
    }

    @Override
    public void close() {
        this.modelData.clear();
    }

    private GeometryObject loadMissingData() {
        if(missing != null)
            return missing;

        JsonObject e = JsonParser.parseReader(new StringReader(MISSING_MODEL_DATA)).getAsJsonObject();
        List<GeometryObject> list = JsonOps.INSTANCE.withDecoder(GeometryObject.CODEC.listOf())
                .apply(e.get(GEOMETRY_ID.toString())).resultOrPartial(s -> { throw new JsonParseException(s); })
                .get()
                .getFirst();
        missing = list.get(0);

        return missing;
    }

    private static final String MISSING_MODEL_DATA =
                    "{\n" +
                    "\t\"format_version\": \"" + FORMAT_VERSION + "\",\n" +
                    "\t\"minecraft:geometry\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"description\": {\n" +
                    "\t\t\t\t\"identifier\": \"geometry.minecraft.missing\",\n" +
                    "\t\t\t\t\"texture_width\": 16,\n" +
                    "\t\t\t\t\"texture_height\": 16,\n" +
                    "\t\t\t\t\"visible_bounds_width\": 2,\n" +
                    "\t\t\t\t\"visible_bounds_height\": 1,\n" +
                    "\t\t\t\t\"visible_bounds_offset\": [0, 0, 0]\n" +
                    "\t\t\t},\n" +
                    "\t\t\t\"bones\": [\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"name\": \"root\",\n" +
                    "\t\t\t\t\t\"pivot\": [0, 8, 0],\n" +
                    "\t\t\t\t\t\"cubes\": [\n" +
                    "\t\t\t\t\t\t{\"origin\": [-7, 1, -7], \"size\": [14, 14, 14], \"uv\": [0, 0]}\n" +
                    "\t\t\t\t\t]\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t]\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}";
}
