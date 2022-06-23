package com.fadedbytes.BinaryElementalOrbs.api.network.packet;

import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;

import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;

public class DataPacket extends NetworkPacket {

    public DataPacket(DatagramPacket packet, boolean createdByServer) throws MalformedTagException {
        super(packet, createdByServer);
    }

    public static Packet createFromString(String data) throws MalformedTagException {
        byte[] rawData = data.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet = new DatagramPacket(rawData, rawData.length);

        return new DataPacket(packet, true);
    }
}
