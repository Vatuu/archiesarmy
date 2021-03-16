package dev.vatuu.archiesarmy.registries;

import dev.vatuu.archiesarmy.ArchiesArmy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;

public final class Items {

    public static final SpawnEggItem ENCHANTER_SPAWN_EGG = Registry.register(
            Registry.ITEM,
            ArchiesArmy.id("enchanter_spawn_egg"),
            new SpawnEggItem(
                    Entities.TYPE_ENCHANTER,
                    0x797F7F,
                    0x741933,
                    new Item.Settings()
                            .group(ItemGroup.MISC)
            )
    );

    public static final SpawnEggItem ARCH_ILLAGER_SPAWN_EGG = Registry.register(
            Registry.ITEM,
            ArchiesArmy.id("arch_illager_spawn_egg"),
            new SpawnEggItem(
                    Entities.TYPE_ARCHIE,
                    0x8A9090,
                    0xFFC444,
                    new Item.Settings()
                            .group(ItemGroup.MISC)
            )
    );

    public static void init() {
    }
}
