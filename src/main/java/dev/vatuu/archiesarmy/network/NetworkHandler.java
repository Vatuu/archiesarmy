package dev.vatuu.archiesarmy.network;

import dev.vatuu.archiesarmy.client.network.PacketC2SRemoveAnimation;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public final class NetworkHandler {

    public NetworkHandler() {
        register(new PacketC2SRemoveAnimation());
    }

    public void sendToClient(ServerPlayerEntity entity, AbstractPacket packet) {
        PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());
        packet.encode(buffer);
        ServerPlayNetworking.send(entity, packet.getId(), buffer);
    }

    private void register(AbstractPacket packet) {
        ClientPlayNetworking.registerGlobalReceiver(packet.getId(), packet);
    }
}
