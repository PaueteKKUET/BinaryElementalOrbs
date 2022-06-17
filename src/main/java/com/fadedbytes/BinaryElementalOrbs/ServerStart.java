package com.fadedbytes.BinaryElementalOrbs;

import com.fadedbytes.BinaryElementalOrbs.command.CommandManager;
import com.fadedbytes.BinaryElementalOrbs.command.commands.PermissionRole;
import com.fadedbytes.BinaryElementalOrbs.console.Console;
import com.fadedbytes.BinaryElementalOrbs.console.ConsoleManager;
import com.fadedbytes.BinaryElementalOrbs.console.ServerConsole;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;

public final class ServerStart {

    private static Console consoleManager;

    public static void main(String[] args) {
        startConsole();
        startCommands();

        consoleManager.println("Console started.");
    }

    private static void startConsole() {
        consoleManager = ConsoleManager.getConsoleManager(new NamespacedKey(NamespacedKey.BEO_NAMESPACE, "main_console"), System.in, System.out);
        ((ServerConsole) consoleManager).setPermission(PermissionRole.ADMIN);
    }

    private static void startCommands() {
        CommandManager.setupCommands();
    }

    private static void startApi() {

    }

    public static void exit() {
        System.exit(0);
    }

}
