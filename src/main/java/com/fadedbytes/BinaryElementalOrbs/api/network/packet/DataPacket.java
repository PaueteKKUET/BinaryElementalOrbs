package com.fadedbytes.BinaryElementalOrbs.api.network.packet;

import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;

import java.net.DatagramPacket;
import java.net.SocketAddress;

public class DataPacket extends NetworkPacket {

    public DataPacket(DatagramPacket packet, boolean createdByServer) throws MalformedTagException {
        super(packet, createdByServer);
    }
}
