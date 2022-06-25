package com.fadedbytes.BinaryElementalOrbs.event.events.server;

import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a server is about to shut down.
 * @param server The server that is about to shut down.
 */
public record ServerShutdownEvent(BeoServer server) implements ServerEvent {
    @Override
    public @NotNull BeoServer server() {
        return this.server;
    }
}
