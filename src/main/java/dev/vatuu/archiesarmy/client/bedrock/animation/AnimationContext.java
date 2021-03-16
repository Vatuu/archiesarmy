package dev.vatuu.archiesarmy.client.bedrock.animation;

import com.google.common.collect.Lists;
import dev.vatuu.archiesarmy.client.ArchiesArmyClient;
import dev.vatuu.archiesarmy.client.bedrock.models.GeometryModel;
import dev.vatuu.archiesarmy.util.Transformation;
import dev.vatuu.archiesarmy.util.Tuple;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationContext {

    public final Identifier id;
    private final AnimationData animation;

    private final Map<Tuple<String, Transformation>, Vector3f> prevTransform = new HashMap<>();
    private final Map<Tuple<String, Transformation>, List<Float>> keyframes = new HashMap<>();
    private final Map<Tuple<String, Transformation>, float[]> currentTimestamp = new HashMap<>();

    private boolean isPaused = false, isFinished = false;

    public AnimationContext(Identifier id) {
        this.id = id;
        this.animation = ArchiesArmyClient.INSTANCE.animationManager.getAnimationData(id);
        populateStartData(true);
    }

    public void applyData(GeometryModel model, int animationAge, float tickDelta) {
        if (isPaused || isDone())
            return;

        float time = animationAge + tickDelta;

        for (Map.Entry<String, AnimationBone> entry : animation.getBones().entrySet()) {
            AnimationBone bone = entry.getValue();
            String id = entry.getKey();

            if (bone.hasRotation) {
                Vector3f target = applyTransformation(bone, id, Transformation.ROTATION, time, tickDelta);
                model.getModelData().getBone(id).addRotation(target.getX(), target.getY(), target.getZ(), true);
            }

            if (bone.hasTranslation) {
                Vector3f target = applyTransformation(bone, id, Transformation.POSITION, time, tickDelta);
                model.getModelData().getBone(id).addTranslation(target.getX(), target.getY(), target.getZ());
            }

            if (bone.hasScale) {
                Vector3f target = applyTransformation(bone, id, Transformation.SCALE, time, tickDelta);
                model.getModelData().getBone(id).addScaling(target.getX(), target.getY(), target.getZ());
            }
        }

        if (animationAge > animation.getAnimationLength() * 20)
            this.isFinished = true;
    }

    public void loop() {
        this.isFinished = false;
        populateStartData(false);
    }

    public boolean shouldLoop() {
        return animation.shouldLoop() && isFinished;
    }

    public boolean isDone() {
        return !animation.shouldLoop() && isFinished;
    }

    private Vector3f applyTransformation(AnimationBone bone, String id, Transformation type, float time, float tickDelta) {
        Tuple<String, Transformation> key = new Tuple<>(id, type);

        float timestamp0 = currentTimestamp.get(key)[0];
        float timestamp1 = currentTimestamp.get(key)[1];

        if (time >= (timestamp1 * 20))
            currentTimestamp.replace(key, getKeyframesTimestamps(keyframes.get(key), time));

        if (!bone.hasDataForFrame(timestamp0, type) || !bone.hasDataForFrame(timestamp1, type))
            return new Vector3f();

        float startTime = timestamp0 * 20;
        float endTime = timestamp1 * 20 - startTime;

        float progressDelta = (float) Math.min((time - startTime) / endTime, 1.0);

        Vector3f minPeriod = bone.getDataForFrame(timestamp0, type);
        Vector3f maxPeriod = bone.getDataForFrame(timestamp1, type);
        Vector3f previous = prevTransform.get(key);

        Vector3f target, transform;

        if (type == Transformation.ROTATION) {
            transform = new Vector3f(
                    MathHelper.lerpAngleDegrees(progressDelta, minPeriod.getX(), maxPeriod.getX()),
                    MathHelper.lerpAngleDegrees(progressDelta, minPeriod.getY(), maxPeriod.getY()),
                    MathHelper.lerpAngleDegrees(progressDelta, minPeriod.getZ(), maxPeriod.getZ()));
            target = new Vector3f(
                    MathHelper.lerpAngleDegrees(tickDelta, previous.getX(), transform.getX()),
                    MathHelper.lerpAngleDegrees(tickDelta, previous.getY(), transform.getY()),
                    MathHelper.lerpAngleDegrees(tickDelta, previous.getZ(), transform.getZ()));
        } else {
            transform = new Vector3f(
                    MathHelper.lerp(progressDelta, minPeriod.getX(), maxPeriod.getX()),
                    MathHelper.lerp(progressDelta, minPeriod.getY(), maxPeriod.getY()),
                    MathHelper.lerp(progressDelta, minPeriod.getZ(), maxPeriod.getZ()));
            target = new Vector3f(
                    MathHelper.lerp(tickDelta, previous.getX(), transform.getX()),
                    MathHelper.lerp(tickDelta, previous.getY(), transform.getY()),
                    MathHelper.lerp(tickDelta, previous.getZ(), transform.getZ()));
        }

        prevTransform.replace(key, transform);
        if (type == Transformation.POSITION)
            System.out.printf("TimeStamps: %f/%f/%f | Delta: %f | Min: %s | Max: %s | Current: %s%n", currentTimestamp.get(key)[0] * 20, currentTimestamp.get(key)[1] * 20, time, progressDelta, minPeriod.toString(), maxPeriod.toString(), transform.toString());

        return target;
    }

    private void setStartData(String id, AnimationBone b, Transformation type, boolean full) {
        Tuple<String, Transformation> key = new Tuple<>(id, type);
        if (full) {
            List<Float> values = Lists.newArrayList(b.positionData.keySet());
            values.sort(Double::compare);
            keyframes.put(key, values);
        }
        currentTimestamp.put(key, getKeyframesTimestamps(keyframes.get(key), 0));
        prevTransform.put(key, b.hasDataForFrame(0.0F, type) ? b.getDataForFrame(0.0F, type) : new Vector3f(0, 0, 0));
    }

    private void populateStartData(boolean full) {
        animation.getBones().forEach((id, b) -> {
            if (b.hasRotation)
                setStartData(id, b, Transformation.ROTATION, full);
            if (b.hasTranslation)
                setStartData(id, b, Transformation.POSITION, full);
            if (b.hasScale)
                setStartData(id, b, Transformation.SCALE, full);
        });
    }

    private float[] getKeyframesTimestamps(List<Float> keyframes, float animationAge) {
        float start = 0.0F, end = keyframes.get(keyframes.size() - 1);
        for (float d : Lists.reverse(keyframes)) {
            float adjusted = d * 20;
            if (adjusted > animationAge)
                end = d;
            if (adjusted <= animationAge) {
                start = d;
                break;
            }
        }

        return new float[]{start, end};
    }
}
