package com.fadedbytes.BinaryElementalOrbs.event.events;

import com.fadedbytes.BinaryElementalOrbs.event.EventManager;

public interface Event {

    default void launch() {
        if (this instanceof Cancellable cancellableEvent) {
            if (cancellableEvent.isCancelled()) return;
        }
        EventManager.launchEvent(this);
    }

}
