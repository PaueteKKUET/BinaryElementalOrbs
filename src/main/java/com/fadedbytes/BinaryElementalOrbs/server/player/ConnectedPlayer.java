package com.fadedbytes.BinaryElementalOrbs.server.player;

import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;

public class ConnectedPlayer extends LoggingInPlayer implements OnlinePlayer {
    private final SocketAddress address;

    public ConnectedPlayer(@NotNull String name, @NotNull SocketAddress address) {
        super(name);
        this.address = address;
    }

    @Override
    public @NotNull SocketAddress getAddress() {
        return this.address;
    }

    @Override
    public OnlinePlayer fromPlayer(@NotNull SocketAddress address) {
        return this;
    }
}
