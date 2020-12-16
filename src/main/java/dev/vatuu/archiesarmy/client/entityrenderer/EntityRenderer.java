package dev.vatuu.archiesarmy.client.entityrenderer;

import dev.vatuu.archiesarmy.client.models.ArchieEntityModel;
import dev.vatuu.archiesarmy.client.models.MinionEntityModel;
import dev.vatuu.archiesarmy.client.models.EnchanterEntityModel;
import dev.vatuu.archiesarmy.registries.Entities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public final class EntityRenderer {

    public static void register() {
        EntityRendererRegistry.INSTANCE.register(Entities.TYPE_ENCHANTER, (d, ctx) -> new EnchanterEntityRenderer(d, new EnchanterEntityModel(), 0.5F));
        EntityRendererRegistry.INSTANCE.register(Entities.TYPE_ARCHIE, (d, ctx) -> new ArchIllagerEntityRenderer(d, new ArchieEntityModel(), 0.5F));
        EntityRendererRegistry.INSTANCE.register(Entities.TYPE_MINION, (d, ctx) -> new MinionEntityRenderer(d, new MinionEntityModel(), 1F));
    }
}
