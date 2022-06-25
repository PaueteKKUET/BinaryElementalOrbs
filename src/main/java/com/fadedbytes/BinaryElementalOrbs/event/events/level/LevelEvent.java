package com.fadedbytes.BinaryElementalOrbs.event.events.level;

import com.fadedbytes.BinaryElementalOrbs.event.events.Event;
import com.fadedbytes.BinaryElementalOrbs.server.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * Events representing something related to a level.
 */
public interface LevelEvent extends Event {

    /**
     * @return The level that this event is related to.
     */
    @NotNull
    Level level();

}
