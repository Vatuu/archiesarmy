package dev.vatuu.archiesarmy.client.bedrock.geometry;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.vatuu.archiesarmy.util.Codecs;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;

import java.util.ArrayList;
import java.util.List;

public final class GeometryBone {

    public final String name, parent;
    private final Vector3f pivot, rotation;
    private final boolean mirror;
    private final float inflate;
    private final boolean debug;
    private final int renderGroup;
    private final List<GeometryCube> elements;

    private final List<GeometryBone> children = new ArrayList<>();

    private boolean visible = true;

    private Vector3f additiveRotation = new Vector3f(0, 0, 0);
    private Vector3f additiveTranslation = new Vector3f(0, 0, 0);
    private Vector3f additiveScaling = new Vector3f(1, 1, 1);

    private GeometryBone(String id, String parent, Vector3f pivot, Vector3f rotation,
                         boolean mirror, float inflate, boolean debug, int renderGroup,
                         List<GeometryCube> cubes) {
        this.name = id;
        this.parent = parent;
        this.pivot = pivot;
        this.rotation = rotation;
        this.mirror = mirror;
        this.inflate = inflate;
        this.debug = debug;
        this.renderGroup = renderGroup;
        this.elements = cubes;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setRotation(float pitch, float yaw, float roll, boolean degrees) {
        if (!degrees)
            this.additiveRotation = new Vector3f((float) Math.toDegrees(pitch), (float) Math.toDegrees(yaw), (float) Math.toDegrees(roll));
        else
            this.additiveRotation = new Vector3f(pitch, yaw, roll);
    }

    public void addRotation(float pitch, float yaw, float roll, boolean degrees) {
        if (!degrees)
            this.additiveRotation.add(new Vector3f((float) Math.toDegrees(pitch), (float) Math.toDegrees(yaw), (float) Math.toDegrees(roll)));
        else
            this.additiveRotation.add(new Vector3f(pitch, yaw, roll));
    }

    public void addTranslation(float x, float y, float z) {
        this.additiveTranslation.add(new Vector3f(x, y, z));
    }

    public void addScaling(float x, float y, float z) {
        this.additiveScaling.add(new Vector3f(x, y, z));
    }

    public void setTextureSize(int width, int height) {
        this.elements.forEach(e -> e.defineFaces(width, height, this.inflate));
    }

    public void addChild(GeometryBone bone) {
        children.add(bone);
    }

    public void render(MatrixStack stack, VertexConsumer consumer, int light, int overlay) {
        this.render(stack, consumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void render(MatrixStack stack, VertexConsumer consumer, int light, int overlay, float red, float green, float blue, float alpha) {
        if (!visible)
            return;
        stack.push();
        if (!this.elements.isEmpty() || !this.children.isEmpty()) {
            stack.translate(pivot.getX() / 16D, pivot.getY() / 16D, pivot.getZ() / 16D);
            stack.multiply(Vector3f.NEGATIVE_Z.getDegreesQuaternion(rotation.getZ() + additiveRotation.getZ()));
            stack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(rotation.getY() + additiveRotation.getY()));
            stack.multiply(Vector3f.NEGATIVE_X.getDegreesQuaternion(rotation.getX() + additiveRotation.getX()));
            stack.translate(-(pivot.getX()) / 16D, -(pivot.getY() / 16D), -(pivot.getZ() / 16D));

            stack.translate(additiveTranslation.getX(), additiveTranslation.getY(), additiveTranslation.getZ());
            stack.scale(additiveScaling.getX(), additiveScaling.getY(), additiveScaling.getZ());

            additiveRotation.set(0, 0, 0);
            additiveTranslation.set(0, 0, 0);
            additiveScaling.set(1, 1, 1);

            elements.forEach(c -> c.renderCuboid(stack, consumer, light, overlay, red, green, blue, alpha));
        }

        if (!this.children.isEmpty())
            children.forEach(c -> c.render(stack, consumer, light, overlay, red, green, blue, alpha));

        stack.pop();
    }

    public static final Codec<GeometryBone> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.fieldOf("name").forGetter((GeometryBone o) -> o.name),
            Codec.STRING.optionalFieldOf("parent", "").forGetter((GeometryBone o) -> o.parent),
            Codecs.VEC_3F.optionalFieldOf("pivot", new Vector3f(0, 0, 0)).forGetter((GeometryBone o) -> o.pivot),
            Codecs.VEC_3F.optionalFieldOf("rotation", new Vector3f(0, 0, 0)).forGetter((GeometryBone o) -> o.rotation),
            Codec.BOOL.optionalFieldOf("mirror", false).forGetter((GeometryBone o) -> o.mirror),
            Codec.FLOAT.optionalFieldOf("inflate", 0.0F).forGetter((GeometryBone o) -> o.inflate),
            Codec.BOOL.optionalFieldOf("debug", false).forGetter((GeometryBone o) -> o.debug),
            Codec.INT.optionalFieldOf("render_group_id", 0).forGetter((GeometryBone o) -> o.renderGroup),
            GeometryCube.CODEC.listOf().optionalFieldOf("cubes", ImmutableList.of()).forGetter((GeometryBone o) -> o.elements)
    ).apply(i, GeometryBone::new));
}
