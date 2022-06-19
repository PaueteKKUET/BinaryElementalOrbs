package com.fadedbytes.BinaryElementalOrbs.api.network.packet;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor.PacketType;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;

public interface Packet {

    /**
     * Returns the type of packet this is, as defined in {@link PacketType}.
     * @return The type of packet this is.
     */
    @NotNull
    PacketType getType();

    /**
     * Returns the root tag of this packet.
     * @return The root tag of this packet.
     * @see Tag
     */
    @NotNull
    Tag getRootTag();

    /**
     * Locks this packet, preventing any further modifications. This should be called before sending the packet.
     */
    void lock();

    /**
     * Returns whether this packet is locked.
     * @return true if this packet is locked, false otherwise.
     */
    boolean isLocked();

    SocketAddress getSocketAddress();

    void reply(Packet packet);

}
