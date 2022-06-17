package com.fadedbytes.BinaryElementalOrbs;

import com.fadedbytes.BinaryElementalOrbs.command.CommandManager;
import com.fadedbytes.BinaryElementalOrbs.event.events.ServerShutdownEvent;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import com.fadedbytes.BinaryElementalOrbs.server.DefaultServer;

public final class BEO {

    private static BeoServer server;

    public static void main(String[] args) {

        server = DefaultServer.getServer();
        startModules();
        launchTests();
    }

    private static void startModules() {
        CommandManager.setupCommands();

    }

    // Public control methods

    public static void exit() {
        ServerShutdownEvent shutdownEvent = new ServerShutdownEvent(server);
        shutdownEvent.launch();
        System.exit(0);
    }

    public static BeoServer getServer() {
        return server;
    }

    public static void launchTests() {
        new EventListenerTest();
    }

}
