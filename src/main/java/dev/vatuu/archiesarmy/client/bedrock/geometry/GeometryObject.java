package dev.vatuu.archiesarmy.client.bedrock.geometry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.vatuu.archiesarmy.client.ArchiesArmyClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GeometryObject {

    private final GeometryDescription description;
    private final String capeBone;
    private final ImmutableMap<String, GeometryBone> bones;

    private boolean debugBoundingBox = false;

    private GeometryObject(GeometryDescription desc, String cape, List<GeometryBone> bones) {
        this.description = desc;
        this.capeBone = cape;

        this.bones = ImmutableMap.copyOf(bones.stream().collect(Collectors.toMap((GeometryBone b) -> b.name, (GeometryBone b) -> b)));
        this.bones.forEach((id, bone) -> {
            if(!bone.parent.equals(""))
                this.bones.get(bone.parent).addChild(bone);
            bone.setTextureSize(description.getTexWidth(), description.getTexHeight());
        });
    }

    void setDebug() {
        this.debugBoundingBox = true;
    }

    public GeometryBone getBone(String part) {
        return bones.get(part);
    }


    public List<GeometryBone> getRootBones() {
        return bones.values().stream().filter(b -> b.parent.equals("")).collect(Collectors.toList());
    }

    public List<GeometryBone> getAllBones() {
        return Lists.newArrayList(bones.values());
    }

    public static final Codec<GeometryObject> CODEC = RecordCodecBuilder.create(i -> i.group(
            GeometryDescription.CODEC.fieldOf("description").forGetter((GeometryObject o) -> o.description),
            Codec.STRING.optionalFieldOf("cape", "").forGetter((GeometryObject o) -> o.capeBone),
            GeometryBone.CODEC.listOf().optionalFieldOf("bones", ImmutableList.of()).forGetter((GeometryObject o) -> Lists.newArrayList(o.bones.values()))
    ).apply(i, GeometryObject::new));
}
