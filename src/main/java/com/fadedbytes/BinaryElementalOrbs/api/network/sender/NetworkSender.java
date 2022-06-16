package com.fadedbytes.BinaryElementalOrbs.api.network.sender;

import org.json.simple.JSONObject;

public interface NetworkSender {

    void sendPacket(PacketType type, String data);

    default void sendPacket(PacketType type, JSONObject data) {
        sendPacket(type, data.toJSONString());
    }

}
