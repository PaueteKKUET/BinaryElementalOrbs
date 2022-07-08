package com.fadedbytes.BinaryElementalOrbs;

import com.fadedbytes.BinaryElementalOrbs.console.logger.LogManager;
import com.fadedbytes.BinaryElementalOrbs.console.logger.Logger;
import com.fadedbytes.BinaryElementalOrbs.event.events.Event;
import com.fadedbytes.BinaryElementalOrbs.event.events.server.PlayerLoginEvent;
import com.fadedbytes.BinaryElementalOrbs.event.events.server.ServerShutdownEvent;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import com.fadedbytes.BinaryElementalOrbs.server.DefaultServer;
import com.fadedbytes.BinaryElementalOrbs.server.player.LoggingInPlayer;

import java.time.LocalDateTime;

public final class BEO {

    private static BeoServer server;

    public static void main(String[] args) {

        server = DefaultServer.getServer();

        Event fakeLogin = new PlayerLoginEvent(server, new LoggingInPlayer("uwu"), LocalDateTime.now());
        fakeLogin.launch();

    }

    // Public control methods

    public static void exit() {
        ServerShutdownEvent shutdownEvent = new ServerShutdownEvent(server, LocalDateTime.now());
        shutdownEvent.launch();
        System.exit(0);
    }

    public static BeoServer getServer() {
        return server;
    }

    public static Logger getLogger() {
        return LogManager.getGlobalLogger();
    }

}
