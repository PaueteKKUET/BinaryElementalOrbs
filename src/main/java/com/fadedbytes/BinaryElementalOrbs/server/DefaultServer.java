package com.fadedbytes.BinaryElementalOrbs.server;

import com.fadedbytes.BinaryElementalOrbs.command.commands.PermissionRole;
import com.fadedbytes.BinaryElementalOrbs.console.Console;
import com.fadedbytes.BinaryElementalOrbs.console.ConsoleManager;
import com.fadedbytes.BinaryElementalOrbs.console.ServerConsole;
import com.fadedbytes.BinaryElementalOrbs.event.EventManager;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;

public class DefaultServer implements BeoServer {

    private static DefaultServer SERVER_INSTANCE = null;

    private EventManager eventManager;
    private Console defaultConsole;

    protected DefaultServer() {
        setupConsole();
    }

    public static DefaultServer getServer() {
        if (SERVER_INSTANCE == null) {
            SERVER_INSTANCE = new DefaultServer();
        }

        return SERVER_INSTANCE;
    }

    private void setupConsole() {
        defaultConsole = ConsoleManager.getConsole(
                new NamespacedKey(NamespacedKey.BEO_NAMESPACE, "main_console"),
                System.in,
                System.out
        );
    }

    private void setupEventManager() {
        eventManager = new EventManager();
    }

    @Override
    public EventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public Console getConsole() {
        return this.defaultConsole;
    }
}
