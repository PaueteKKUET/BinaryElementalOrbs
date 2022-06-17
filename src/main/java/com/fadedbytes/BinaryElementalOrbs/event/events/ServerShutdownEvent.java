package com.fadedbytes.BinaryElementalOrbs.event.events;

import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;

public class ServerShutdownEvent extends ServerEvent {

    public ServerShutdownEvent(BeoServer server) {
        super(server);
    }
}
