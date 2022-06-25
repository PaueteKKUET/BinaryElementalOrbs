package com.fadedbytes.BinaryElementalOrbs.event.events.protocol;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.event.events.Cancellable;
import com.fadedbytes.BinaryElementalOrbs.event.events.server.ServerEvent;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;

/**
 * Fired when a packet is about to be sent by a server.
 * If this event is cancelled, the packet should not be sent.
 */
public class PacketLaunchEvent implements ServerEvent, PacketEvent, Cancellable {

    private boolean cancelled = false;
    private final BeoServer server;
    private final Packet packet;
    private final SocketAddress address;

    /**
     * Constructs a new packet launch event.
     * @param server The server that the packet will be sent by.
     * @param packet The packet that will be sent.
     * @param address The address that the packet will be sent to.
     */
    public PacketLaunchEvent(BeoServer server, Packet packet, SocketAddress address) {
        this.server = server;
        this.packet = packet;
        this.address = address;
    }

    @NotNull public BeoServer server() {
        return server;
    }

    @NotNull public Packet packet() {
        return packet;
    }

    @NotNull
    public SocketAddress address() {
        return address;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
