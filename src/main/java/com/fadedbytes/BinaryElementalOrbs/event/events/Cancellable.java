package com.fadedbytes.BinaryElementalOrbs.event.events;

/**
 * Events implementing this interface can be cancelled before launching. Listeners can modify the event's cancel property to cancel the event.
 */
public interface Cancellable {

    /**
     * @return true if the event is cancelled, false otherwise.
     */
    boolean isCancelled();

    /**
     * Sets the cancelled state of the event.
     * @param cancelled true if the event is to be cancelled, false otherwise.
     */
    void setCancelled(boolean cancelled);

}
