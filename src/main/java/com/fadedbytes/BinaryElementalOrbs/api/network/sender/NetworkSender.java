package com.fadedbytes.BinaryElementalOrbs.api.network.sender;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;

import java.net.InetAddress;
import java.net.SocketAddress;

public interface NetworkSender {

    void send(Packet packet, SocketAddress address);

}
