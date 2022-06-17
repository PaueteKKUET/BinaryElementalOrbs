package com.fadedbytes.BinaryElementalOrbs.api.network.packet;

import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;

public abstract class NetworkPacket {

    private final DatagramPacket packet;

    public NetworkPacket(DatagramPacket packet) {
        this.packet = packet;
    }

    public byte[] read() {
        return packet.getData();
    }

    public String readString() {
        return new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
    }



}
