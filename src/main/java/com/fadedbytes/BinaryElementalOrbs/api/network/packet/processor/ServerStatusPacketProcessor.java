package com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor;

import static com.fadedbytes.BinaryElementalOrbs.BEO.getServer;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.DataPacket;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.SimplePacketWrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.RegularTag;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;

public class ServerStatusPacketProcessor extends BasePacketProcessor {
    @Override
    public boolean canProcess(PacketType type) {
        return type.equals(PacketType.UPSTREAM_SERVER_STATUS) || type.equals(PacketType.DOWNSTREAM_SERVER_STATUS);
    }

    @Override
    public void process(Packet packet) {
        if (!this.canProcess(packet.getType())) {
            throw new IllegalArgumentException("Cannot process packet of type: " + packet.getType());
        }

        try {
            packet.reply(getStatusPacket());
        } catch (MalformedTagException e) {
            BEO.getLogger().error("Failed to create status packet");
            BEO.getLogger().trace(e.getStackTrace());
        }

    }

    public static Packet getStatusPacket() throws MalformedTagException {
        RegularTag root = Tag.presetWithType(PacketType.DOWNSTREAM_SERVER_STATUS);
        RegularTag content = (RegularTag) root.getInnerTag("content");
        Tag.createTag(content, "status", getServer().getServerStatus().getDescription());
        Tag.createTag(content, "whitelist", getServer().whitelistEnabled() ? "on" : "off");
        Tag.createTag(content, "players", String.valueOf(getServer().getCurrentPlayerCount()));
        Tag.createTag(content, "maxPlayers", String.valueOf(getServer().getMaxPlayerCount()));
        Tag.createTag(content, "motd", getServer().getMotd());

        return DataPacket.createFromString(SimplePacketWrapper.INSTANCE.generatePacketContent(root));
    }
}
