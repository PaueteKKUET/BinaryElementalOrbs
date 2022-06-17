package com.fadedbytes.BinaryElementalOrbs;

import com.fadedbytes.BinaryElementalOrbs.command.CommandManager;
import com.fadedbytes.BinaryElementalOrbs.command.commands.PermissionRole;
import com.fadedbytes.BinaryElementalOrbs.console.Console;
import com.fadedbytes.BinaryElementalOrbs.console.ConsoleManager;
import com.fadedbytes.BinaryElementalOrbs.console.ServerConsole;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;

public final class ServerStart {

    /**
     * The main console of the server.
     */
    private static Console consoleManager;

    public static void main(String[] args) {
        startConsole();
        startModules();

        consoleManager.println("Console started.");
    }

    private static void startModules() {
        CommandManager.setupCommands();
    }

    private static void startConsole() {
        consoleManager = ConsoleManager.getConsoleManager(new NamespacedKey(NamespacedKey.BEO_NAMESPACE, "main_console"), System.in, System.out);
        ((ServerConsole) consoleManager).setPermission(PermissionRole.CONSOLE);
    }

    // Public control methods

    public static void exit() {
        System.exit(0);
    }

}
