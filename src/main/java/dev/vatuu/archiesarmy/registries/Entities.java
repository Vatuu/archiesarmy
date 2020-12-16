package dev.vatuu.archiesarmy.registries;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.entities.ArchIllagerEntity;
import dev.vatuu.archiesarmy.entities.MinionEntity;
import dev.vatuu.archiesarmy.entities.EnchanterEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public final class Entities {

    public static final EntityType<EnchanterEntity> TYPE_ENCHANTER = Registry.register(
            Registry.ENTITY_TYPE,
            ArchiesArmy.id("enchanter"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EnchanterEntity::new)
                    .dimensions(EntityDimensions.fixed(.6F, 1.95F))
                    .build()
    );

    public static final EntityType<ArchIllagerEntity> TYPE_ARCHIE = Registry.register(
            Registry.ENTITY_TYPE,
            ArchiesArmy.id("archie"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ArchIllagerEntity::new)
                    .dimensions(EntityDimensions.fixed(.6F, 1.4F))
                    .build()
    );

    public static final EntityType<MinionEntity> TYPE_MINION = Registry.register(
            Registry.ENTITY_TYPE,
            ArchiesArmy.id("am_minion"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MinionEntity::new)
                    .dimensions(EntityDimensions.fixed(1F, 1F))
                    .build()
    );

    public static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(TYPE_ENCHANTER, EnchanterEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(TYPE_ARCHIE, ArchIllagerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(TYPE_MINION, MinionEntity.createAttributes());
    }
}
