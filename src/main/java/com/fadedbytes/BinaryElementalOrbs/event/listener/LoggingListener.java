package com.fadedbytes.BinaryElementalOrbs.event.listener;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.event.EventManager;
import com.fadedbytes.BinaryElementalOrbs.event.events.Event;

public class LoggingListener extends Listener {


    public LoggingListener(EventManager eventManager) {
        super(eventManager);
    }

    @EventListener(priority = ListenerPriority.MONITOR)
    public static void beforeEventLaunch(Event event) {
        BEO.getLogger().debug(event.toLogString());
    }

}
