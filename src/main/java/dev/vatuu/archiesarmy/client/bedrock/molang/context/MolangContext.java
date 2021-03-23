package dev.vatuu.archiesarmy.client.bedrock.molang.context;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public abstract class MolangContext {

    private World world;

    public MolangContext(World w) {
        this.world = w;
    }
}
