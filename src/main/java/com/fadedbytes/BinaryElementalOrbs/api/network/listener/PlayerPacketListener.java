package com.fadedbytes.BinaryElementalOrbs.api.network.listener;

import com.fadedbytes.BinaryElementalOrbs.api.network.SocketManager;

import java.io.IOException;
import java.net.InetAddress;

public class PlayerPacketListener extends SocketManager implements NetworkListener {

    private boolean listening;

    public PlayerPacketListener(InetAddress address, int port) throws IOException {
        super(address, port);
    }

    @Override
    public void listen() {
        listening = true;
    }

    @Override
    public boolean isListening() {
        return this.listening;
    }
}
