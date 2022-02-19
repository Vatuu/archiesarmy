package dev.vatuu.archiesarmy.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public interface AbstractPacket extends ClientPlayNetworking.PlayChannelHandler {

    Identifier getId();

    void encode(PacketByteBuf buffer);

    void decode(PacketByteBuf buffer);

    void onReceive();

    default void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        this.decode(buf);
        client.execute(this::onReceive);
    }
}
