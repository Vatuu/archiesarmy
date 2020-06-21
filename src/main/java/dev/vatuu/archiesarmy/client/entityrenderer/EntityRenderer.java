package dev.vatuu.archiesarmy.client.entityrenderer;

import dev.vatuu.archiesarmy.entities.Entities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public final class EntityRenderer {

    public static void register() {
        EntityRendererRegistry.INSTANCE.register(Entities.TYPE_REDSTONE_MONSTROSITY, (d, ctx) -> new RedstoneMonstrosityRenderer(d));
    }
}
