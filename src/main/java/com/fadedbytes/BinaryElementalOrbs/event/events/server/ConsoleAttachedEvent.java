package com.fadedbytes.BinaryElementalOrbs.event.events.server;

import com.fadedbytes.BinaryElementalOrbs.console.Console;
import com.fadedbytes.BinaryElementalOrbs.server.BeoServer;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a console is attached to a server.
 * @param server The server that the console is attached to.
 * @param console The console that was attached.
 */
public record ConsoleAttachedEvent(BeoServer server, Console console) implements ServerEvent {

    @NotNull public BeoServer server() {
        return server;
    }

    @NotNull public Console console() {
        return console;
    }
}
