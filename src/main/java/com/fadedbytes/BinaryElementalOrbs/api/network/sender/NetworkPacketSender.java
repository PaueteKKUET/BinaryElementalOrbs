package com.fadedbytes.BinaryElementalOrbs.api.network.sender;

import com.fadedbytes.BinaryElementalOrbs.api.network.SocketManager;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.PacketWrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.SimplePacketWrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

public class NetworkPacketSender extends SocketManager implements NetworkSender {

    private static final PacketWrapper WRAPPER = new SimplePacketWrapper();

    public NetworkPacketSender(InetAddress address) throws IOException {
        super(address);
    }

    public NetworkPacketSender(InetAddress address, int port) throws IOException {
        super(address, port);
    }

    @Override
    public void send(Packet packet, SocketAddress address) {
        byte[] data;
        try {
            data = WRAPPER.generatePacketContent(packet.getRootTag()).getBytes(StandardCharsets.UTF_8);
            this.getSocket().send(new DatagramPacket(data, data.length, address));
        } catch (MalformedTagException | IOException e) {

        }
    }
}
