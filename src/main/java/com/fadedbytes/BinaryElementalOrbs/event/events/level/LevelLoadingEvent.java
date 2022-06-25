package com.fadedbytes.BinaryElementalOrbs.event.events.level;

import com.fadedbytes.BinaryElementalOrbs.server.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a level starts loading.
 * @param level The level that is being loaded.
 */
public record LevelLoadingEvent(Level level) implements LevelEvent {
    @Override
    public @NotNull Level level() {
        return this.level;
    }
}
