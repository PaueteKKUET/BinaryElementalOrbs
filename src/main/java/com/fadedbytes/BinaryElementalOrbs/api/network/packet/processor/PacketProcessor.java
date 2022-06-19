package com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.PacketType;

public interface PacketProcessor {

    boolean canProcess(PacketType type);

    boolean process(Packet packet);

    default boolean isUpstream(Packet packet) {
        return packet.getType().isUpstream();
    }

}
