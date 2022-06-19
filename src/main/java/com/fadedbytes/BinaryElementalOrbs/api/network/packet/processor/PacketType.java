package com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor;

import org.jetbrains.annotations.NotNull;

public enum PacketType {

    UPSTREAM_PING(PingPacketProcessor.class),
    UPSTREAM_SERVER_STATUS(ServerStatusPacketProcessor.class),
    UPSTREAM_CONNECTION_REQUEST(ConnectionRequestPacketProcessor.class),

    DOWNSTREAM_PING(PingPacketProcessor.class),
    DOWNSTREAM_SERVER_STATUS(ServerStatusPacketProcessor.class),
    DOWNSTREAM_CONNECTION_RESPONSE(ConnectionRequestPacketProcessor.class);

    private final PacketProcessor processor;

    private PacketType(@NotNull Class<? extends BasePacketProcessor> processorClass) {
        this.processor = BasePacketProcessor.getInstanceOf(processorClass);
    }

    public boolean isUpstream() {
        return this.name().contains("UPSTREAM");
    }

    public boolean isDownstream() {
        return this.name().contains("DOWNSTREAM");
    }

    public PacketProcessor getProcessor() {
        return processor;
    }

    public String simpleName() {
        return this.name().replace("UPSTREAM_", "").replace("DOWNSTREAM_", "");
    }

    public static PacketType getPacketType(String name) {
        for (PacketType type : PacketType.values()) {
            if (type.name().equals(name)) {
                return type;
            }
        }

        throw new IllegalArgumentException("No packet type found with name: " + name);
    }

    public static PacketType getUpstreamPacket(String name) {
        return getPacketType("UPSTREAM_" + name.toUpperCase());
    }

    public static PacketType getDownstreamPacket(String name) {
        return getPacketType("DOWNSTREAM_" + name.toUpperCase());
    }

}
