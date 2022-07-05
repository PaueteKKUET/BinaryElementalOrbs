package com.fadedbytes.BinaryElementalOrbs.event.events.server;

import com.fadedbytes.BinaryElementalOrbs.event.events.TimestampedEvent;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

/**
 * Fired when a server just started.
 * @param server The server that just started.
 * @param timestamp The date and time the server just started.
 */
public record ServerStartupEvent(BeoServer server, LocalDateTime timestamp) implements ServerEvent, TimestampedEvent {
    @Override
    public @NotNull BeoServer server() {
        return this.server;
    }

    @Override
    public @NotNull LocalDateTime timestamp() {
        return this.timestamp;
    }
}
