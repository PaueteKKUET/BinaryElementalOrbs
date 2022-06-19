package com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;

public class ServerStatusPacketProcessor extends BasePacketProcessor {
    @Override
    public boolean canProcess(PacketType type) {
        return false;
    }

    @Override
    public void process(Packet packet) {
        BEO.getLogger().debug("Server status packet received");
    }
}
