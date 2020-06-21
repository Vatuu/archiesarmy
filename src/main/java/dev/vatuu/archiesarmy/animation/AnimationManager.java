package dev.vatuu.archiesarmy.animation;

import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

import java.util.*;

public class AnimationManager {

    public static final AnimationManager INSTANCE = new AnimationManager();

    private final Map<EntityType<?>, List<Identifier>> animationRegistry;

    private AnimationManager() {
        this.animationRegistry = new HashMap<>();
    }

    public void register(EntityType<?> type, Identifier... ids) {
        List<Identifier> id = animationRegistry.computeIfAbsent(type, t -> new ArrayList<>());
        id.addAll(Arrays.asList(ids));
    }
}
