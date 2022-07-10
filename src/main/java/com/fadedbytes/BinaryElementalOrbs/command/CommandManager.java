package com.fadedbytes.BinaryElementalOrbs.command;

import com.fadedbytes.BinaryElementalOrbs.command.commands.*;

import java.util.HashMap;
import java.util.Set;

public class CommandManager {

    private static final HashMap<String, CommandExecutor> EXECUTORS = new HashMap<>();
    private static final HashMap<String, String> ALIASES = new HashMap<>();

    public static void setupCommands() {
        register(new ExitCommand());
        register(new HelpCommand());
        register(new PacketCommand());
        register(new SayCommand());
    }

    public static void register(CommandExecutor executor) {
        EXECUTORS.put(executor.getCommandName(), executor);

        if (executor instanceof Aliasable aliasableCommand) {
            for (String alias : aliasableCommand.getAliases()) {
                ALIASES.put(alias, executor.getCommandName());
            }
        }

    }

    public static void runCommand(CommandSender sender, String command, String... args) {
        CommandExecutor executor = EXECUTORS.get(command);
        if (executor != null) {
            CommandExecutionCode code = executor.runCommand(sender, command, args);
            if (code != CommandExecutionCode.SUCCESS) {
                sender.sendMessage(code.getDefaultMessage());
            }
        } else {

            String originalCommand = ALIASES.get(command);
            executor = EXECUTORS.get(originalCommand);
            if (executor == null) {
                sender.sendMessage(CommandExecutionCode.UNKNOWN_COMMAND.getDefaultMessage());
            } else {
                runCommand(sender, originalCommand, args);
            }
        }
    }

    public static Set<String> getAllCommandNames() {
        return EXECUTORS.keySet();
    }

    public static CommandExecutor getCommand(String command) {
        return EXECUTORS.get(command);
    }
}

