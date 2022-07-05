package com.fadedbytes.BinaryElementalOrbs.event.events;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

/**
 * Events which have a timestamp.
 */
public interface TimestampedEvent extends Event {

    /**
     * Gets the timestamp of the event.
     * @return The timestamp of the event.
     */
    @NotNull LocalDateTime timestamp();
}
