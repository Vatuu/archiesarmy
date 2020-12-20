package dev.vatuu.archiesarmy.client.network;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import dev.vatuu.archiesarmy.ArchiesArmy;
import dev.vatuu.archiesarmy.extensions.EntityExt;
import dev.vatuu.archiesarmy.network.AbstractPacket;

public class PacketC2SRemoveAnimation implements AbstractPacket {

    private static final Identifier ID = ArchiesArmy.id("remove_anim_client");

    private Entity targetEntity;
    private Identifier id;
    private boolean all;

    public PacketC2SRemoveAnimation() { }
    public PacketC2SRemoveAnimation(Entity targetEntity, Identifier id, boolean all) {
        this.targetEntity = targetEntity;
        this.id = id;
        this.all = all;
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    public void encode(PacketByteBuf buffer) {
        buffer.writeVarInt(targetEntity.getEntityId());
        buffer.writeIdentifier(id);
        buffer.writeBoolean(all);
    }

    @Override
    public void decode(PacketByteBuf buffer) {
        this.targetEntity = MinecraftClient.getInstance().world.getEntityById(buffer.readVarInt());
        this.id = buffer.readIdentifier();
        this.all = buffer.readBoolean();
    }

    @Override
    public void onReceive() {
        ((EntityExt)targetEntity).removeAnimation(id, all);
    }
}
