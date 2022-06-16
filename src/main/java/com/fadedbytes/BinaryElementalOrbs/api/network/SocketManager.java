package com.fadedbytes.BinaryElementalOrbs.api.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public abstract class SocketManager implements AutoCloseable {

    private Socket SOCKET;

    public SocketManager(InetAddress address) throws IOException {
        initSocket(address, 0);
    }

    public SocketManager(InetAddress address, int port) throws IOException {
        initSocket(address, port);
    }

    private void initSocket(InetAddress address, int port) throws IOException {
        this.SOCKET = new Socket(address, port);
    }

    private void closeSocket() throws IOException {
        this.SOCKET.close();
    }

    public Socket getSocket() {
        return null;
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
