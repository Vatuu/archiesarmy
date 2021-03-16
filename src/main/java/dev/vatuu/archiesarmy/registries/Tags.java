package dev.vatuu.archiesarmy.registries;

import dev.vatuu.archiesarmy.ArchiesArmy;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;

public class Tags {

    public static final Tag<EntityType<?>> ENCHANTABLE_ENTITIES = TagRegistry.entityType(ArchiesArmy.id("enchantable"));

    public static void init() {
    }
}
