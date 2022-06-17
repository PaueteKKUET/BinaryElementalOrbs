package com.fadedbytes.BinaryElementalOrbs;

import com.fadedbytes.BinaryElementalOrbs.command.CommandManager;
import com.fadedbytes.BinaryElementalOrbs.command.commands.PermissionRole;
import com.fadedbytes.BinaryElementalOrbs.console.Console;
import com.fadedbytes.BinaryElementalOrbs.console.ConsoleManager;
import com.fadedbytes.BinaryElementalOrbs.console.ServerConsole;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;

public final class ServerStart {

    public static void main(String[] args) {
        startModules();
    }

    private static void startModules() {
        CommandManager.setupCommands();

    }

    // Public control methods

    public static void exit() {
        System.exit(0);
    }

}
