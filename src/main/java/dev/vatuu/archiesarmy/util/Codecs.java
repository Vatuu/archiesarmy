package dev.vatuu.archiesarmy.util;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

public final class Codecs {

    public static final Codec<Identifier> IDENTIFIER = Codec.STRING.xmap(Identifier::new, Identifier::toString);
    public static final Codec<Vec3f> VEC_3F = Codec.FLOAT.listOf().xmap(l -> new Vec3f(l.get(0), l.get(1), l.get(2)), v -> Lists.newArrayList(v.getX(), v.getY(), v.getZ()));
}
