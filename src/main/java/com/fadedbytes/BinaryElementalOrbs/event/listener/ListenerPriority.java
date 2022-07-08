package com.fadedbytes.BinaryElementalOrbs.event.listener;

/**
 * Defines the priority of an event listener method. The higher the priority, the later the method is called.
 * High priority methods can modify the event later in time.
 */
public enum ListenerPriority {

    LOWEST(100),
    LOW(300),
    NORMAL(500),
    HIGH(700),
    HIGHEST(900),
    MONITOR(1000);

    private final int priority;

    ListenerPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

}
