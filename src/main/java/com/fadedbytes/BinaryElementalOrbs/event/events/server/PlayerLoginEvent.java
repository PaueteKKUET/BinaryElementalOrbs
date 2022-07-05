package com.fadedbytes.BinaryElementalOrbs.event.events.server;

import com.fadedbytes.BinaryElementalOrbs.event.events.TimestampedEvent;
import com.fadedbytes.BinaryElementalOrbs.event.events.player.PlayerEvent;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import com.fadedbytes.BinaryElementalOrbs.server.player.Player;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public record PlayerLoginEvent(BeoServer server, Player player, LocalDateTime timestamp) implements ServerEvent, PlayerEvent, TimestampedEvent {

    @Override
    public @NotNull BeoServer server() {
        return server;
    }

    @Override
    public @NotNull Player player() {
        return player;
    }

    @Override
    public @NotNull LocalDateTime timestamp() {
        return timestamp;
    }

}
