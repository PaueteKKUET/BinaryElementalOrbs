package com.fadedbytes.BinaryElementalOrbs.api.network.packet;

import org.json.simple.parser.JSONParser;

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class DefaultPacketProcessor implements PacketProcessor {

    private final Queue<NetworkPacket> PACKET_QUEUE;

    public DefaultPacketProcessor() {
        this.PACKET_QUEUE = new LinkedList<>();
    }

    public void addPacket(NetworkPacket packet) {
        this.PACKET_QUEUE.add(packet);

    }

    @Override
    public boolean isValid(NetworkPacket packet) {
        return true; // TODO
    }

    @Override
    public PacketType getType(NetworkPacket packet) {
        return null; // TODO
    }

    @Override
    public HashMap<String, Object> toPropertyMap(NetworkPacket packet) throws InvalidObjectException {
        if (!seemsValid(packet)) throw new InvalidObjectException("Packet is not valid");



        return null; // TODO implement
    }

    private boolean seemsValid(NetworkPacket packet) {
        String packetContent = packet.readString();
        return packetContent.startsWith("<BEO>") && packetContent.endsWith("</BEO>");
    }
}
