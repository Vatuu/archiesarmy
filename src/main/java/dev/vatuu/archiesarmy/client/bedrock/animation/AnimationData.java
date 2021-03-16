package dev.vatuu.archiesarmy.client.bedrock.animation;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Collections;
import java.util.Map;

public class AnimationData {

    public static final String FORMAT_VERSION = "1.8.0";

    private final Either<String, Boolean> loop;
    private final float startDelay, loopDelay, animTimeUpdate, blendWeight;
    private final boolean override;

    private final Map<String, AnimationBone> bones;

    private final float animationLength;

    private AnimationData(Either<String, Boolean> loop, float startDelay, float loopDelay,
                          float animTimeUpdate, float blendWeight, boolean override,
                          Map<String, AnimationBone> bones, float animTime) {
        this.loop = loop;
        this.startDelay = startDelay; this.loopDelay = loopDelay; this.animTimeUpdate = animTimeUpdate; this.blendWeight = blendWeight;
        this.override = override;
        this.bones = bones;

        if(animTime == -1)
            this.animationLength = Collections.max(bones.values(), (b1, b2) -> Float.compare(b1.getLastKeyFrame(), b2.getLastKeyFrame())).getLastKeyFrame();
        else
            this.animationLength = animTime;
    }

    public Map<String, AnimationBone> getBones() {
        return bones;
    }

    public float getAnimationLength() {
        return animationLength;
    }

    public boolean shouldLoop() {
        return loop.left().isPresent() || loop.right().get();
    }

    public static Codec<AnimationData> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.either(Codec.STRING, Codec.BOOL).optionalFieldOf("loop", Either.right(false)).forGetter((AnimationData o) -> o.loop),
            Codec.FLOAT.optionalFieldOf("start_delay", 0F).forGetter((AnimationData o) -> o.startDelay),
            Codec.FLOAT.optionalFieldOf("loop_delay", 0F).forGetter((AnimationData o) -> o.loopDelay),
            Codec.FLOAT.optionalFieldOf("anim_time_update", 0F).forGetter((AnimationData o) -> o.animTimeUpdate),
            Codec.FLOAT.optionalFieldOf("blend_weight", 0F).forGetter((AnimationData o) -> o.blendWeight),
            Codec.BOOL.optionalFieldOf("override_previous_animation", false).forGetter((AnimationData o) -> o.override),
            Codec.unboundedMap(Codec.STRING, AnimationBone.CODEC).optionalFieldOf("bones", ImmutableMap.of()).forGetter((AnimationData o) -> o.bones),
            Codec.FLOAT.optionalFieldOf("animation_length", -1F).forGetter((AnimationData o) -> o.animationLength)
    ).apply(i, AnimationData::new));
}
