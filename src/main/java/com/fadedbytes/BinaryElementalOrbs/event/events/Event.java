package com.fadedbytes.BinaryElementalOrbs.event.events;

import com.fadedbytes.BinaryElementalOrbs.event.EventManager;

public interface Event {

    /**
     * @return true if the event has been launched properly, false otherwise.
     */
    default boolean launch() {
        return EventManager.launchEvent(this);
    }

}
