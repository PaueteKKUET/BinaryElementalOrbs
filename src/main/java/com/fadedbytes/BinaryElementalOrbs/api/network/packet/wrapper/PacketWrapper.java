package com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper;

import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;

public interface PacketWrapper {

    String generatePacketContent(Tag tag) throws MalformedTagException;

}
