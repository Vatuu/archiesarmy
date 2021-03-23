package dev.vatuu.archiesarmy.client.bedrock.molang.context;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class MolangEntityContext<E extends Entity> extends MolangContext {

    private E currentEntity;

    public MolangEntityContext(World w, E entity) {
        super(w);
    }

    public E getEntity() {
        return currentEntity;
    }
}
