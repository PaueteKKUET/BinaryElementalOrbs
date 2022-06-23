package com.fadedbytes.BinaryElementalOrbs.api.network.listener;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.api.network.SocketManager;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.DataPacket;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.NetworkPacket;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.server.DefaultServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NetworkPacketListener extends SocketManager implements NetworkListener {

    private static final int BUFFER_SIZE = 1024;
    private boolean listening;

    public NetworkPacketListener(InetAddress address, int port) throws IOException {
        super(address, port);
    }

    @Override
    public void listen() {
        listening = true;

        while (listening) {
            try {
                DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
                DatagramSocket socket = this.getSocket();
                socket.receive(packet);

                receivePacket(packet);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() throws Exception {
        this.listening = false;
        super.close();
    }

    @Override
    public boolean isListening() {
        return this.listening;
    }

    public static void receivePacket(DatagramPacket packet) {
        try {
            NetworkPacket newPacket = new DataPacket(packet, false);
            DefaultServer.getServer().receivePacket(newPacket);
        } catch (MalformedTagException e) {
            BEO.getLogger().debug("Error while reading a received packet");
        }
    }
}
