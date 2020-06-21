package dev.vatuu.archiesarmy.entities;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.animation.AnimationManager;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.VindicatorEntity;
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

    public static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(TYPE_REDSTONE_MONSTROSITY, RedstoneMonstrosityEntity.createAttributes());
        AnimationManager.INSTANCE.register(TYPE_REDSTONE_MONSTROSITY, RedstoneMonstrosityEntity.ANIMATION_WALK, RedstoneMonstrosityEntity.ANIMATION_AWAKEN);
    }
}
