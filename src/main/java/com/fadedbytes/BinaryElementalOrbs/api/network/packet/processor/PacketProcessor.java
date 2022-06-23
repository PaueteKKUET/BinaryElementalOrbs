package com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;

public interface PacketProcessor {

    boolean canProcess(PacketType type);

    void process(Packet packet);

    default boolean isUpstream(Packet packet) {
        return packet.getType().isUpstream();
    }

}
