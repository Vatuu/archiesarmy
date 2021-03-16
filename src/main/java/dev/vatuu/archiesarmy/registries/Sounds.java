package dev.vatuu.archiesarmy.registries;

import dev.vatuu.archiesarmy.ArchiesArmy;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Sounds {

    public static final SoundEvent ENTITY_ENCHANTER_IDLE = register("entity", "illagers.enchanter.idle");
    public static final SoundEvent ENTITY_ENCHANTER_PREATTACK = register("entity", "illagers.enchanter.preattack");
    public static final SoundEvent ENTITY_ENCHANTER_ATTACK = register("entity", "illagers.enchanter.attack");
    public static final SoundEvent ENTITY_ENCHANTER_HURT = register("entity", "illagers.enchanter.hurt");
    public static final SoundEvent ENTITY_ENCHANTER_DEATH = register("entity", "illagers.enchanter.death");
    public static final SoundEvent ENTITY_ENCHANTER_SPELL = register("entity", "illagers.enchanter.spell");
    public static final SoundEvent ENTITY_ENCHANTER_BEAM = register("entity", "illagers.enchanter.beam");
    public static final SoundEvent ENTITY_ENCHANTER_BEAM_LOOP = register("entity", "illagers.enchanter.beam_loop");


    private static SoundEvent register(String category, String id) {
        Identifier i = ArchiesArmy.id(String.format("%s.%s.%s", category, ArchiesArmy.MOD_ID, id));
        return Registry.register(Registry.SOUND_EVENT, i.getPath(), new SoundEvent(i));
    }
}
