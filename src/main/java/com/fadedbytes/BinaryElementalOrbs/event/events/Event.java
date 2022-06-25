package com.fadedbytes.BinaryElementalOrbs.event.events;

import com.fadedbytes.BinaryElementalOrbs.event.EventManager;

public interface Event {

    /**
     * @return true if the event has been launched properly, false otherwise.
     */
    default boolean launch() {
        if (this instanceof Cancellable cancellableEvent) {
            if (cancellableEvent.isCancelled()) return false;
        }
        EventManager.launchEvent(this);
        return true;
    }

}
