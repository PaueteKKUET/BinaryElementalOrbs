package com.fadedbytes.BinaryElementalOrbs.event.events.level;

import com.fadedbytes.BinaryElementalOrbs.event.events.Event;
import com.fadedbytes.BinaryElementalOrbs.server.level.Level;

public abstract class LevelEvent implements Event {

    private final Level level;

    public LevelEvent(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return this.level;
    }

}
