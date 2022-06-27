package com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.DataPacket;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.SimplePacketWrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.RegularTag;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;

public class PingPacketProcessor extends BasePacketProcessor {

    private static Packet PONG_PACKET = null;

    @Override
    public boolean canProcess(PacketType type) {
        return type == PacketType.UPSTREAM_PING || type == PacketType.DOWNSTREAM_PING;
    }

    @Override
    public void process(Packet packet) {

        if (!this.canProcess(packet.getType())) {
            throw new IllegalArgumentException("Cannot process packet of type: " + packet.getType());
        }

        if (PONG_PACKET == null) {
            try {
                PONG_PACKET = createPacket();
            } catch (MalformedTagException e) {
                BEO.getLogger().error("Failed to create PONG packet");
                BEO.getLogger().trace(e.getStackTrace());
            }
        }

        packet.reply(PONG_PACKET);

    }

    public static Packet createPacket() throws MalformedTagException {
        Tag pingTag = Tag.presetWithType(PacketType.DOWNSTREAM_PING);
        Tag.createTag((RegularTag) pingTag.getInnerTag("content"), "message", "Pong");
        return DataPacket.createFromString(SimplePacketWrapper.INSTANCE.generatePacketContent(pingTag));
    }
}
