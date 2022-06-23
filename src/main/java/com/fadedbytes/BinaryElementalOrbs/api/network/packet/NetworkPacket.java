package com.fadedbytes.BinaryElementalOrbs.api.network.packet;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor.PacketType;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.SimplePacketUnwrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;
import org.jetbrains.annotations.NotNull;

import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

public abstract class NetworkPacket implements Packet {

    private final DatagramPacket packet;
    private final Tag rootTag;
    private final PacketType type;
    private boolean locked;
    private final boolean createdByServer;

    public NetworkPacket(DatagramPacket packet, boolean createdByServer) throws MalformedTagException {
        this.packet = packet;
        this.createdByServer = createdByServer;

        this.rootTag = this.unwrap();

        String assertedPacketType = this.rootTag.getInnerTag("headers.type").getValue();

        if (assertedPacketType == null) {
            throw new MalformedTagException("Packet type not found");
        }

        try {
            this.type =
                    this.createdByServer ?
                            PacketType.getDownstreamPacket(assertedPacketType) :
                            PacketType.getUpstreamPacket(assertedPacketType);
        } catch (IllegalArgumentException e) {
            throw new MalformedTagException("Invalid packet type: " + this.rootTag.getName());
        }
    }

    private byte[] read() {
        return packet.getData();
    }

    private String readString() {
        return new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
    }

    @Override
    public @NotNull PacketType getType() {
        return this.type;
    }

    private @NotNull Tag unwrap() throws MalformedTagException {
        return SimplePacketUnwrapper.INSTANCE.generateTagFromString(this.readString());
    }

    @Override
    public void lock() {
        locked = true;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public @NotNull Tag getRootTag() {
        return this.rootTag;
    }

    @Override
    public SocketAddress getSocketAddress() {
        return this.packet.getSocketAddress();
    }

    @Override
    public void reply(Packet packet) {
        BEO.getServer().sendPacket(packet, this.getSocketAddress());
    }

    @Override
    public boolean isCreatedByServer() {
        return this.createdByServer;
    }
}
