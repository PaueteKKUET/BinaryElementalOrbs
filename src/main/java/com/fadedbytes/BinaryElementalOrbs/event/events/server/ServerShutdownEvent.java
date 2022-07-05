package com.fadedbytes.BinaryElementalOrbs.event.events.server;

import com.fadedbytes.BinaryElementalOrbs.event.events.TimestampedEvent;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

/**
 * Fired when a server is about to shut down.
 * @param server The server that is about to shut down.
 * @param timestamp The date and time the server is about to shut down.
 */
public record ServerShutdownEvent(BeoServer server, LocalDateTime timestamp) implements ServerEvent, TimestampedEvent {
    @Override
    public @NotNull BeoServer server() {
        return this.server;
    }

    @Override
    public @NotNull LocalDateTime timestamp() {
        return this.timestamp;
    }
}
