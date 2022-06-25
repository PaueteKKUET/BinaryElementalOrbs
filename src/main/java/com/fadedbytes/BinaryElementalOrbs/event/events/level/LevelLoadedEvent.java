package com.fadedbytes.BinaryElementalOrbs.event.events.level;

import com.fadedbytes.BinaryElementalOrbs.server.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a level is fully loaded.
 * @param level The level that was loaded.
 */
public record LevelLoadedEvent(Level level) implements LevelEvent {

    @Override
    public @NotNull Level level() {
        return this.level;
    }
}
