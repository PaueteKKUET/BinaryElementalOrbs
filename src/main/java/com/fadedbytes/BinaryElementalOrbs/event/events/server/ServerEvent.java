package com.fadedbytes.BinaryElementalOrbs.event.events.server;

import com.fadedbytes.BinaryElementalOrbs.event.events.Event;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import org.jetbrains.annotations.NotNull;

/**
 * Events representing something related to a server.
 */
public interface ServerEvent extends Event {

    /**
     * @return The server that this event is related to.
     */
    @NotNull
    BeoServer server();

}
