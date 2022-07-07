package com.fadedbytes.BinaryElementalOrbs.event.events.server;

import com.fadedbytes.BinaryElementalOrbs.event.events.Cancellable;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import com.fadedbytes.BinaryElementalOrbs.server.ServerStatus;
import org.jetbrains.annotations.NotNull;


/**
 * This event is fired when the server status changes.
 */
public class ServerStatusChangedEvent implements ServerEvent, Cancellable {

    private final BeoServer server;
    private final ServerStatus status;
    private boolean cancelled;

    public ServerStatusChangedEvent(@NotNull BeoServer server, @NotNull ServerStatus status) {
        this.server = server;
        this.status = status;
    }

    @Override
    public @NotNull BeoServer server() {
        return this.server;
    }

    /**
     * @return the new server status.
     */
    public @NotNull ServerStatus status() {
        return this.status;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
