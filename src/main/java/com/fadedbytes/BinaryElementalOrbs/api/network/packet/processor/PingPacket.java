package com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.PacketType;

public class PingPacket extends BasePacketProcessor {


    @Override
    public boolean canProcess(PacketType type) {
        return type == PacketType.UPSTREAM_PING || type == PacketType.DOWNSTREAM_PING;
    }

    @Override
    public boolean process(Packet packet) {
        return false;
    }
}
