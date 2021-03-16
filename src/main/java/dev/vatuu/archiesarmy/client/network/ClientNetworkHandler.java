package dev.vatuu.archiesarmy.client.network;

import dev.vatuu.archiesarmy.network.AbstractPacket;
import dev.vatuu.archiesarmy.network.PacketS2CAnimationControl;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.network.PacketByteBuf;

public class ClientNetworkHandler {

    public ClientNetworkHandler() {
        register(new PacketS2CAnimationControl());
    }

    public void sendToServer(AbstractPacket packet) {
        PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());
        packet.encode(buffer);
        ClientSidePacketRegistry.INSTANCE.sendToServer(packet.getId(), buffer);
    }

    private void register(AbstractPacket packet) {
        ClientSidePacketRegistry.INSTANCE.register(packet.getId(), packet);
    }
}
