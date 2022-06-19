package com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.DataPacket;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.NetworkPacket;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.SimplePacketWrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.RegularTag;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;

import java.net.DatagramPacket;

public class PingPacketProcessor extends BasePacketProcessor {

    private static Packet PONG_PACKET = null;

    @Override
    public boolean canProcess(PacketType type) {
        return type == PacketType.UPSTREAM_PING || type == PacketType.DOWNSTREAM_PING;
    }

    @Override
    public void process(Packet packet) {
        if (PONG_PACKET == null) {
            try {
                packet.reply(createPacket());
            } catch (MalformedTagException e) {
                e.printStackTrace();
            }
        }

    }

    public static Packet createPacket() throws MalformedTagException {
        Tag pingTag = Tag.presetWithType(PacketType.DOWNSTREAM_PING);
        Tag.createTag((RegularTag) pingTag.getInnerTag("content"), "message", "Pong");
        return DataPacket.createFromString(SimplePacketWrapper.INSTANCE.generatePacketContent(pingTag));
    }
}
