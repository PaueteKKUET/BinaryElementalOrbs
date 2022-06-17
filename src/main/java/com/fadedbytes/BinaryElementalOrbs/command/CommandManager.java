package com.fadedbytes.BinaryElementalOrbs.command;

import com.fadedbytes.BinaryElementalOrbs.command.commands.ExitCommand;
import com.fadedbytes.BinaryElementalOrbs.command.commands.HelpCommand;

import java.util.HashMap;
import java.util.Set;

public class CommandManager {

    private static final HashMap<String, CommandExecutor> EXECUTORS = new HashMap<>();

    public static void setupCommands() {
        register(new ExitCommand());
        register(new HelpCommand());
    }

    public static void register(CommandExecutor executor) {
        EXECUTORS.put(executor.getCommandName(), executor);
    }

    public static CommandExecutionCode runCommand(CommandSender sender, String command, String... args) {
        CommandExecutor executor = EXECUTORS.get(command);
        if (executor != null) {
            return executor.runCommand(sender, command, args);
        } else {
            sender.sendMessage(CommandExecutionCode.UNKNOWN_COMMAND.getDefaultMessage());
            return CommandExecutionCode.UNKNOWN_COMMAND;
        }
    }

    public static Set<String> getAllCommandNames() {
        return EXECUTORS.keySet();
    }

    public static CommandExecutor getCommand(String command) {
        return EXECUTORS.get(command);
    }
}
