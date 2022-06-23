package com.fadedbytes.BinaryElementalOrbs.server;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.console.Console;
import com.fadedbytes.BinaryElementalOrbs.console.logger.Logger;
import com.fadedbytes.BinaryElementalOrbs.event.EventManager;

import java.net.SocketAddress;

public interface BeoServer {

    EventManager getEventManager();

    Console getConsole();

    void receivePacket(Packet packet);

    void sendPacket(Packet packet, SocketAddress address);

    boolean isDebugMode();

    Logger getServerLogger();

}
