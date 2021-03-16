package dev.vatuu.archiesarmy.client.bedrock.animation;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.vatuu.archiesarmy.util.Codecs;
import dev.vatuu.archiesarmy.util.Transformation;
import net.minecraft.client.util.math.Vector3f;

import java.util.Collections;
import java.util.Map;

public class AnimationBone {

    private final String relativeTo;
    public final Map<Float, Vector3f> rotationData, positionData, scaleData;

    public boolean hasRotation, hasTranslation, hasScale = false;

    private AnimationBone(String relativeTo, Map<Float, Vector3f> position, Map<Float, Vector3f> rotation, Map<Float, Vector3f> scale) {
        this.relativeTo = relativeTo;
        this.positionData = position; this.rotationData = rotation; this.scaleData = scale;

        if(!positionData.isEmpty())
            this.hasTranslation = true;
        if(!rotationData.isEmpty())
            this.hasTranslation = true;
        if(!scaleData.isEmpty())
            this.hasTranslation = true;
    }

    public boolean hasDataForFrame(float timestamp, Transformation type) {
        switch (type) {
            case POSITION:
                return hasTranslation && positionData.containsKey(timestamp);
            case ROTATION:
                return hasRotation && rotationData.containsKey(timestamp);
            case SCALE:
                return hasScale && scaleData.containsKey(timestamp);
        }
        return false;
    }

    public Vector3f getDataForFrame(float timestamp, Transformation type) {
        switch (type) {
            case POSITION:
                return positionData.get(timestamp);
            case ROTATION:
                return rotationData.get(timestamp);
            case SCALE:
                return scaleData.get(timestamp);
        }
        return new Vector3f();
    }

    public float getLastKeyFrame() {
        float keyframe = 0;

        if(!positionData.isEmpty()) {
            float last = Collections.max(positionData.keySet(), Float::compareTo);
            keyframe = Math.max(last, keyframe);
        }

        if(!rotationData.isEmpty()) {
            float last = Collections.max(rotationData.keySet(), Float::compareTo);
            keyframe = Math.max(last, keyframe);
        }

        if(!scaleData.isEmpty()) {
            float last = Collections.max(scaleData.keySet(), Float::compareTo);
            keyframe = Math.max(last, keyframe);
        }

        return keyframe;
    }

    public static Codec<AnimationBone> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.optionalFieldOf("relative_to", "").forGetter((AnimationBone o) -> o.relativeTo),
            Codec.unboundedMap(Codec.STRING.xmap(Float::parseFloat, String::valueOf), Codecs.VEC_3F).optionalFieldOf("position", ImmutableMap.of()).forGetter((AnimationBone o) -> o.positionData),
            Codec.unboundedMap(Codec.STRING.xmap(Float::parseFloat, String::valueOf), Codecs.VEC_3F).optionalFieldOf("rotation", ImmutableMap.of()).forGetter((AnimationBone o) -> o.rotationData),
            Codec.unboundedMap(Codec.STRING.xmap(Float::parseFloat, String::valueOf), Codecs.VEC_3F).optionalFieldOf("scale", ImmutableMap.of()).forGetter((AnimationBone o) -> o.scaleData)
    ).apply(i, AnimationBone::new));
}
