package com.fadedbytes.BinaryElementalOrbs.event.events.level;

import com.fadedbytes.BinaryElementalOrbs.server.level.Level;

/**
 * Fired when a level is fully loaded.
 */
public class LevelLoadedEvent extends LevelEvent {
    public LevelLoadedEvent(Level level) {
        super(level);
    }
}
