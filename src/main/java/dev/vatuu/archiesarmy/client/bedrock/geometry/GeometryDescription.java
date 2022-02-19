package dev.vatuu.archiesarmy.client.bedrock.geometry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.vatuu.archiesarmy.util.Codecs;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

public final class GeometryDescription {

    private final Identifier identifier;
    private final float boundsWidth, boundsHeight;
    private final Vec3f boundsOffset;
    private final int texWidth, texHeight;

    private GeometryDescription(Identifier id, float boundsW, float boundsH, Vec3f boundsOff, int texW, int texH) {
        this.identifier = id;
        this.boundsWidth = boundsW;
        this.boundsHeight = boundsH;
        this.boundsOffset = boundsOff;
        this.texWidth = texW;
        this.texHeight = texH;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public int getTexWidth() {
        return texWidth;
    }

    public int getTexHeight() {
        return texHeight;
    }

    public static final Codec<GeometryDescription> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codecs.IDENTIFIER.fieldOf("identifier").forGetter((GeometryDescription o) -> o.identifier),
            Codec.FLOAT.optionalFieldOf("visible_bounds_width", 1F).forGetter((GeometryDescription o) -> o.boundsWidth),
            Codec.FLOAT.optionalFieldOf("visible_bounds_height", 1F).forGetter((GeometryDescription o) -> o.boundsHeight),
            Codecs.VEC_3F.optionalFieldOf("visible_bounds_offset", new Vec3f(1, 1, 1)).forGetter((GeometryDescription o) -> o.boundsOffset),
            Codec.INT.optionalFieldOf("texture_width", 16).forGetter((GeometryDescription o) -> o.texWidth),
            Codec.INT.optionalFieldOf("texture_height", 16).forGetter((GeometryDescription o) -> o.texHeight)
    ).apply(i, GeometryDescription::new));
}
