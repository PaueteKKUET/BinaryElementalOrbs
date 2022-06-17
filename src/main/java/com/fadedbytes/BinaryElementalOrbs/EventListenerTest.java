package com.fadedbytes.BinaryElementalOrbs;

import com.fadedbytes.BinaryElementalOrbs.event.events.ServerShutdownEvent;
import com.fadedbytes.BinaryElementalOrbs.event.events.ServerStartupEvent;
import com.fadedbytes.BinaryElementalOrbs.event.listener.EventListener;

public class EventListenerTest extends EventListener {

    @com.fadedbytes.BinaryElementalOrbs.event.EventListener()
    public static void onServerStartup(ServerStartupEvent event) {
        System.out.println("Server startup event received!");
    }

    @com.fadedbytes.BinaryElementalOrbs.event.EventListener()
    public static void onServerShutdown(ServerShutdownEvent event) {
        System.out.println("Server shutdown event received!");
    }

}
