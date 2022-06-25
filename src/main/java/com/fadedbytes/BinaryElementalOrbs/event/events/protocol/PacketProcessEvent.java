package com.fadedbytes.BinaryElementalOrbs.event.events.protocol;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.event.events.Cancellable;
import com.fadedbytes.BinaryElementalOrbs.event.events.server.ServerEvent;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a received packet is about to be processed by a server.
 * If this event is cancelled, the packet should not be processed.
 */
public class PacketProcessEvent implements ServerEvent, PacketEvent, Cancellable {

    private boolean cancelled = false;
    private final BeoServer server;
    private final Packet packet;

    /**
     * Constructs a new packet process event.
     * @param server The server that the packet was processed by.
     * @param packet The packet that will be processed.
     */
    public PacketProcessEvent(BeoServer server, Packet packet) {
        this.server = server;
        this.packet = packet;
    }

    @NotNull
    public BeoServer server() {
        return server;
    }

    @NotNull public Packet packet() {
        return packet;
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
