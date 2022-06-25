package com.fadedbytes.BinaryElementalOrbs.event.events.protocol;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.event.events.Event;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event related to a network packet.
 */
public interface PacketEvent extends Event {

    @NotNull
    Packet packet();

}
