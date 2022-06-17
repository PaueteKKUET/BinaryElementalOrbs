package com.fadedbytes.BinaryElementalOrbs.api.network;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class SocketManager implements AutoCloseable {

    private DatagramSocket SOCKET;

    public SocketManager(InetAddress address) throws IOException {
        initSocket(address, 0);
    }

    public SocketManager(InetAddress address, int port) throws IOException {
        initSocket(address, port);
    }

    private void initSocket(InetAddress address, int port) throws IOException {
        this.SOCKET = new DatagramSocket(port, address);
    }

    private void closeSocket() throws IOException {
        this.SOCKET.close();
    }

    public DatagramSocket getSocket() {
        return SOCKET;
    }

    @Override
    public void close() throws Exception {
        closeSocket();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + this.SOCKET.getInetAddress().getHostAddress() + ":" + this.SOCKET.getPort() + "]";
    }

}
