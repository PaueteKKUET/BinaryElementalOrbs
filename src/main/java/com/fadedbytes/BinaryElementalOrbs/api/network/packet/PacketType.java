package com.fadedbytes.BinaryElementalOrbs.api.network.packet;

public enum PacketType {

    UPSTREAM_PING,
    UPSTREAM_SERVER_STATUS,
    UPSTREAM_CONNECTION_REQUEST,

    DOWNSTREAM_PING,
    DOWNSTREAM_SERVER_STATUS,
    DOWNSTREAM_CONNECTION_RESPONSE;


    public boolean isUpstream() {
        return this.name().contains("UPSTREAM");
    }

    public boolean isDownstream() {
        return this.name().contains("DOWNSTREAM");
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
