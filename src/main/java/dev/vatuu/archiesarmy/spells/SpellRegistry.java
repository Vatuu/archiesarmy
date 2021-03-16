package dev.vatuu.archiesarmy.spells;

import com.mojang.serialization.Lifecycle;
import dev.vatuu.archiesarmy.ArchiesArmy;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.fabric.impl.registry.sync.FabricRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

import java.util.Collections;

public final class SpellRegistry {

    public static final RegistryKey<Registry<Spell<? extends BetterSpellcastingIllagerEntity>>> REGISTRY_KEY = RegistryKey.ofRegistry(ArchiesArmy.id("spells"));
    public static final MutableRegistry<Spell<? extends BetterSpellcastingIllagerEntity>> REGISTRY = new SimpleRegistry<>(REGISTRY_KEY, Lifecycle.experimental());

    public static final Spell<BetterSpellcastingIllagerEntity> SPELL_NONE = Registry.register(REGISTRY, ArchiesArmy.id("none"), new Spell.None());

    static {
        //noinspection ConstantConditions
        ((FabricRegistry) REGISTRY).build(Collections.singleton(RegistryAttribute.SYNCED));
    }
}
