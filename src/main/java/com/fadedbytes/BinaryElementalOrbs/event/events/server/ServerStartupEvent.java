package com.fadedbytes.BinaryElementalOrbs.event.events.server;

import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;

import java.time.LocalDate;

public class ServerStartupEvent extends ServerEvent {

    private final LocalDate startupDate;

    public ServerStartupEvent(BeoServer server, LocalDate startupDate) {
        super(server);
        this.startupDate = startupDate;
    }

    public LocalDate getStartupDate() {
        return startupDate;
    }
}
