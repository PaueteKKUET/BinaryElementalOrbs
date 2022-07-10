package com.fadedbytes.BinaryElementalOrbs.server.player;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;
import java.util.UUID;

public class LoggingInPlayer implements Player {

    private final UUID uuid;
    private final String name;

    public LoggingInPlayer(@NotNull String name) {
        this.name = name;
        this.uuid = UUID.nameUUIDFromBytes(name.getBytes());
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public boolean isLoggedIn() {
        return BEO.getServer().isLoggedIn(this.getName());
    }

    @Override
    public @NotNull UUID getUUID() {
        return this.uuid;
    }

    @Override
    public OnlinePlayer fromPlayer(@NotNull SocketAddress address) {
        return null;
    }

    @Override
    public void sendMessage(String message) {
        // Can't send messages to a player that isn't logged in
    }
}
