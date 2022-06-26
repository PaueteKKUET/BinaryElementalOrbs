package com.fadedbytes.BinaryElementalOrbs.event.events.level;

import com.fadedbytes.BinaryElementalOrbs.event.events.server.ServerEvent;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import com.fadedbytes.BinaryElementalOrbs.server.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a level is added to a server.
 * @param level The level that was added.
 * @param server The server that the level was added to.
 */
public record LevelAddedEvent(Level level, BeoServer server) implements LevelEvent, ServerEvent {

    @Override
    public @NotNull Level level() {
        return this.level;
    }

    @Override
    public @NotNull BeoServer server() {
        return this.server;
    }
}

