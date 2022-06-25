package com.fadedbytes.BinaryElementalOrbs.event.events.server;

import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

/**
 * Fired when a server just started.
 * @param server The server that just started.
 * @param startupDate The date and time the server just started.
 */
public record ServerStartupEvent(BeoServer server, LocalDate startupDate) implements ServerEvent {
    @Override
    public @NotNull BeoServer server() {
        return this.server;
    }

    public @NotNull LocalDate startupDate() {
        return this.startupDate;
    }
}
