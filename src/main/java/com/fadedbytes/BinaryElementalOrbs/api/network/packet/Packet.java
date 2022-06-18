package com.fadedbytes.BinaryElementalOrbs.api.network.packet;

import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Packet {

    @NotNull
    PacketType getType();

    @Nullable
    Tag getTopTag();

    void lock();

    boolean isLocked();

}
