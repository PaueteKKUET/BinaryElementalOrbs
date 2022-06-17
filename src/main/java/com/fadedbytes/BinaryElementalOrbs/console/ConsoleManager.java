package com.fadedbytes.BinaryElementalOrbs.console;

import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutionCode;
import com.fadedbytes.BinaryElementalOrbs.command.CommandManager;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class ConsoleManager {

    public static final ThreadGroup CONSOLE_THREAD_GROUP = new ThreadGroup("Console");
    private static final HashMap<NamespacedKey, Console> CONSOLE_MANAGERS = new HashMap<>();

    public static Console getConsoleManager(@NotNull NamespacedKey key, @NotNull InputStream inputStream, OutputStream outputStream) {
        if (!CONSOLE_MANAGERS.containsKey(key)) {
            Console newConsole = new ServerConsole(key, inputStream, outputStream);
            CONSOLE_MANAGERS.put(key, newConsole);
            return newConsole;
        } else {
            return CONSOLE_MANAGERS.get(key);
        }
    }

    public static void executeCommand(@NotNull String command, @NotNull Console console) {

        if (command.isBlank()) {
            console.println("");
        }

        String[] commandParts = command.split(" ");

        String commandName = commandParts[0];
        String[] commandArgs = Arrays.copyOfRange(commandParts, 1, commandParts.length);

        CommandManager.runCommand(console, commandName, commandArgs);
    }

    public static Console getConsoleManager(@NotNull NamespacedKey key) {
        return CONSOLE_MANAGERS.get(key);
    }
}
