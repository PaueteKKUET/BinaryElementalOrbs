package com.fadedbytes.BinaryElementalOrbs.api.network.packet;
import java.io.InvalidObjectException;
import java.util.HashMap;

public interface PacketProcessor {

    boolean isValid(NetworkPacket packet);

    PacketType getType(NetworkPacket packet);

    HashMap<String, Object> toPropertyMap(NetworkPacket packet) throws InvalidObjectException;

}
