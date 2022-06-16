package com.fadedbytes.BinaryElementalOrbs.api.network.sender;

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

}
