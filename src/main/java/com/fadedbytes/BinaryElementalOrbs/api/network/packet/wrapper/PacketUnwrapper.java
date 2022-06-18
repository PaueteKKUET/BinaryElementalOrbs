package com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper;

import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;
import org.jetbrains.annotations.NotNull;

public interface PacketUnwrapper {

    Tag generateTagFromString(@NotNull String plainTextTag) throws MalformedTagException;

}
