package dev.vatuu.archiesarmy.client.bedrock.geometry;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.vatuu.archiesarmy.util.Codecs;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.*;

import java.util.List;
import java.util.stream.Stream;

//TODO Per-Face UV
public final class GeometryCube {

    private final Vec3f origin, size, rotation, pivot;
    private final float inflate;
    private final boolean mirror;
    private final float u, v;

    private Face[] faces;

    private GeometryCube(Vec3f origin, Vec3f size, Vec3f rotation, Vec3f pivot, float inflate, boolean mirror, List<Float> uv) {
        this.origin = origin;
        this.size = size;
        this.rotation = rotation;
        this.pivot = pivot;
        this.inflate = inflate;
        this.mirror = mirror;
        this.u = uv.get(0);
        this.v = uv.get(1);
    }

    public void defineFaces(int texWidth, int texHeight, float boneInflate) {
        float inflate = this.inflate + boneInflate;

        Vec3f secondCorner = origin.copy();
        secondCorner.add(size);

        this.faces = new Face[6];
        Vec3f inflatedMin = origin.copy();
        inflatedMin.subtract(new Vec3f(inflate, inflate, inflate));
        Vec3f inflatedMax = secondCorner.copy();
        inflatedMax.add(new Vec3f(inflate, inflate, inflate));

        if (mirror) {
            float x = inflatedMax.getX();
            inflatedMax = new Vec3f(inflatedMin.getX(), inflatedMax.getY(), inflatedMax.getZ());
            inflatedMin = new Vec3f(x, inflatedMin.getY(), inflatedMin.getZ());
        }

        TexturedVertex[] vertices = new TexturedVertex[8];
        vertices[0] = new TexturedVertex(new Vec3d(inflatedMin.getX(), inflatedMin.getY(), inflatedMin.getZ()), 0.0F, 0.0F);
        vertices[1] = new TexturedVertex(new Vec3d(inflatedMax.getX(), inflatedMin.getY(), inflatedMin.getZ()), 0.0F, 8.0F);
        vertices[2] = new TexturedVertex(new Vec3d(inflatedMax.getX(), inflatedMax.getY(), inflatedMin.getZ()), 8.0F, 8.0F);
        vertices[3] = new TexturedVertex(new Vec3d(inflatedMin.getX(), inflatedMax.getY(), inflatedMin.getZ()), 8.0F, 0.0F);
        vertices[4] = new TexturedVertex(new Vec3d(inflatedMin.getX(), inflatedMin.getY(), inflatedMax.getZ()), 0.0F, 0.0F);
        vertices[5] = new TexturedVertex(new Vec3d(inflatedMax.getX(), inflatedMin.getY(), inflatedMax.getZ()), 0.0F, 8.0F);
        vertices[6] = new TexturedVertex(new Vec3d(inflatedMax.getX(), inflatedMax.getY(), inflatedMax.getZ()), 8.0F, 8.0F);
        vertices[7] = new TexturedVertex(new Vec3d(inflatedMin.getX(), inflatedMax.getY(), inflatedMax.getZ()), 8.0F, 0.0F);

        double k = u + size.getZ();
        double l = u + size.getZ() + size.getX();
        double m = u + size.getZ() + size.getX() + size.getX();
        double n = u + size.getZ() + size.getX() + size.getZ();
        double o = u + size.getZ() + size.getX() + size.getZ() + size.getX();
        double q = v + size.getZ();
        double r = v + size.getZ() + size.getY();

        this.faces[2] = new Face(new TexturedVertex[]{vertices[1], vertices[0], vertices[4], vertices[5]}, l, q, m, v, texWidth, texHeight, mirror, Direction.DOWN);
        this.faces[3] = new Face(new TexturedVertex[]{vertices[6], vertices[7], vertices[3], vertices[2]}, k, v, l, q, texWidth, texHeight, mirror, Direction.UP);
        this.faces[1] = new Face(new TexturedVertex[]{vertices[3], vertices[7], vertices[4], vertices[0]}, u, q, k, r, texWidth, texHeight, mirror, Direction.WEST);
        this.faces[4] = new Face(new TexturedVertex[]{vertices[2], vertices[3], vertices[0], vertices[1]}, k, q, l, r, texWidth, texHeight, mirror, Direction.NORTH);
        this.faces[0] = new Face(new TexturedVertex[]{vertices[6], vertices[2], vertices[1], vertices[5]}, l, q, n, r, texWidth, texHeight, mirror, Direction.EAST);
        this.faces[5] = new Face(new TexturedVertex[]{vertices[7], vertices[6], vertices[5], vertices[4]}, n, q, o, r, texWidth, texHeight, mirror, Direction.SOUTH);

    }

    public void renderCuboid(MatrixStack stack, VertexConsumer consumer, int light, int overlay, float red, float green, float blue, float alpha) {
        stack.push();

        if (rotation != null) {
            stack.translate(pivot.getX() / 16F, pivot.getY() / 16F, pivot.getZ() / 16F);
            stack.multiply(new Quaternion(-rotation.getX(), rotation.getY(), -rotation.getZ(), true));
            stack.translate(-(pivot.getX() / 16F), -(pivot.getY() / 16F), -(pivot.getZ() / 16F));
        }

        Matrix4f modelMatrix = stack.peek().getPositionMatrix();
        Matrix3f normalMatrix = stack.peek().getNormalMatrix();

        Stream.of(faces).forEach(f -> {
            Vec3f normal = f.normal.copy();
            normal.transform(normalMatrix);

            if (normal.getX() < 0)
                normal.multiplyComponentwise(-1, 1, 1);
            if (normal.getY() < 0)
                normal.multiplyComponentwise(1, -1, 1);
            if (normal.getZ() < 0)
                normal.multiplyComponentwise(1, 1, -1);

            Stream.of(f.vertices).forEach(v -> {
                float x = (float) v.pos.getX() / 16.0F;
                float y = (float) v.pos.getY() / 16.0F;
                float z = (float) v.pos.getZ() / 16.0F;
                Vector4f pos = new Vector4f(x, y, z, 1.0F);
                pos.transform(modelMatrix);
                consumer.vertex(
                        pos.getX(), pos.getY(), pos.getZ(),
                        red, green, blue, alpha,
                        (float) v.u, (float) v.v, overlay, light,
                        normal.getX(), normal.getY(), normal.getZ());
            });
        });

        stack.pop();
    }

    public static final Codec<GeometryCube> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codecs.VEC_3F.optionalFieldOf("origin", new Vec3f(0, 0, 0)).forGetter((GeometryCube o) -> o.origin),
            Codecs.VEC_3F.optionalFieldOf("size", new Vec3f(0, 0, 0)).forGetter((GeometryCube o) -> o.size),
            Codecs.VEC_3F.optionalFieldOf("rotation", new Vec3f(0, 0, 0)).forGetter((GeometryCube o) -> o.rotation),
            Codecs.VEC_3F.optionalFieldOf("pivot", new Vec3f(0, 0, 0)).forGetter((GeometryCube o) -> o.pivot),
            Codec.FLOAT.optionalFieldOf("inflate", 0F).forGetter((GeometryCube o) -> o.inflate),
            Codec.BOOL.optionalFieldOf("mirror", false).forGetter((GeometryCube o) -> o.mirror),
            Codec.FLOAT.listOf().optionalFieldOf("uv", Lists.newArrayList(0F, 0F)).forGetter((GeometryCube o) -> Lists.newArrayList(o.u, o.v))
    ).apply(i, GeometryCube::new));

    private static final class Face {
        private final TexturedVertex[] vertices;
        private final Vec3f normal;

        public Face(TexturedVertex[] vertices, double u1, double v1, double u2, double v2, float squishU, float squishV, boolean mirror, Direction d) {
            this.vertices = vertices;
            float uMod = 0.0F / squishU;
            float vMod = 0.0F / squishV;

            vertices[0] = vertices[0].uvRemap(u2 / squishU - uMod, v1 / squishV + vMod);
            vertices[1] = vertices[1].uvRemap(u1 / squishU + uMod, v1 / squishV + vMod);
            vertices[2] = vertices[2].uvRemap(u1 / squishU + uMod, v2 / squishV - vMod);
            vertices[3] = vertices[3].uvRemap(u2 / squishU - uMod, v2 / squishV - vMod);

            this.normal = d.getUnitVector();
            this.normal.multiplyComponentwise(1F, -1F, -1F);

            if (mirror) {
                for (int i = 0; i < vertices.length / 2; ++i) {
                    TexturedVertex vertex = vertices[i];
                    vertices[i] = vertices[vertices.length - 1 - i];
                    vertices[vertices.length - 1 - i] = vertex;
                }
                this.normal.multiplyComponentwise(-1.0F, 1.0F, 1.0F);
            }
        }
    }

    private record TexturedVertex(Vec3d pos, double u, double v) {
        public TexturedVertex uvRemap(double u, double v) {
            return new TexturedVertex(this.pos, u, v);
        }
    }
}
