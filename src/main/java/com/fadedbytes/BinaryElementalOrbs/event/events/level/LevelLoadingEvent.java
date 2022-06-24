package com.fadedbytes.BinaryElementalOrbs.event.events.level;

import com.fadedbytes.BinaryElementalOrbs.server.level.Level;

/**
 * Fired when a level starts loading.
 */
public class LevelLoadingEvent extends LevelEvent {
    public LevelLoadingEvent(Level level) {
        super(level);
    }
}
