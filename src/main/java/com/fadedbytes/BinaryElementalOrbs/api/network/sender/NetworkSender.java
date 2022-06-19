package com.fadedbytes.BinaryElementalOrbs.api.network.sender;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;

import java.net.InetAddress;

public interface NetworkSender {

    void send(Packet packet, InetAddress address, int port);

}
