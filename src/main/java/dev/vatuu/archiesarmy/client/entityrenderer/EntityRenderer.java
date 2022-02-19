package dev.vatuu.archiesarmy.client.entityrenderer;

import dev.vatuu.archiesarmy.client.models.ArchieEntityModel;
import dev.vatuu.archiesarmy.client.models.EnchanterEntityModel;
import dev.vatuu.archiesarmy.client.models.MinionEntityModel;
import dev.vatuu.archiesarmy.registries.Entities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public final class EntityRenderer {

    public static void register() {
        EntityRendererRegistry.register(Entities.TYPE_ENCHANTER, ctx -> new EnchanterEntityRenderer(ctx, new EnchanterEntityModel(), 0.5F));
        EntityRendererRegistry.register(Entities.TYPE_ARCHIE, ctx -> new ArchIllagerEntityRenderer(ctx, new ArchieEntityModel(), 0.5F));
        EntityRendererRegistry.register(Entities.TYPE_MINION, ctx -> new MinionEntityRenderer(ctx, new MinionEntityModel(), 1F));
    }
}
