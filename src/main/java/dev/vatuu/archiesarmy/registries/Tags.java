package dev.vatuu.archiesarmy.registries;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;

import dev.vatuu.archiesarmy.ArchiesArmy;

public class Tags {

	public static final Tag<EntityType<?>> ENCHANTABLE_ENTITIES = TagRegistry.entityType(ArchiesArmy.id("enchantable"));

	public static void init() {
		// NO-OP
	}
}
