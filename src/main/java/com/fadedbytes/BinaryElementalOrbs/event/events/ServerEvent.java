package com.fadedbytes.BinaryElementalOrbs.event.events;

import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;

public abstract class ServerEvent implements Event {

    private final BeoServer server;

    public ServerEvent(BeoServer server) {
        this.server = server;
    }

    public BeoServer getServer() {
        return server;
    }

}
