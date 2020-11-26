package dev.vatuu.archiesarmy.registries;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.animation.AnimationManager;
import dev.vatuu.archiesarmy.entities.EnchanterEntity;
import dev.vatuu.archiesarmy.entities.RedstoneMonstrosityEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.tag.EntityTypeTags;
import net.minecraft.util.registry.Registry;

public final class Entities {

    public static final EntityType<RedstoneMonstrosityEntity> TYPE_REDSTONE_MONSTROSITY = Registry.register(
            Registry.ENTITY_TYPE,
            ArchiesArmy.id("redstone_monstrosity"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RedstoneMonstrosityEntity::new)
                    .fireImmune()
                    .dimensions(EntityDimensions.fixed(5, 5))
                    .build()
    );

    public static final EntityType<EnchanterEntity> TYPE_ENCHANTER = Registry.register(
            Registry.ENTITY_TYPE,
            ArchiesArmy.id("enchanter"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EnchanterEntity::new)
                    .dimensions(EntityDimensions.fixed(.6F, 1.95F))
                    .build()
    );

    public static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(TYPE_REDSTONE_MONSTROSITY, RedstoneMonstrosityEntity.createAttributes());
        AnimationManager.INSTANCE.register(TYPE_REDSTONE_MONSTROSITY, RedstoneMonstrosityEntity.ANIMATION_WALK, RedstoneMonstrosityEntity.ANIMATION_AWAKEN);

        FabricDefaultAttributeRegistry.register(TYPE_ENCHANTER, EnchanterEntity.createAttributes());
    }
}
