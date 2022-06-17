package com.fadedbytes.BinaryElementalOrbs.command;

import com.fadedbytes.BinaryElementalOrbs.command.commands.PermissionRole;

/**
 * Classes that implement this interface can be used to register commands. They should be able to handle the command.
 */
public interface CommandExecutor {

    CommandExecutionCode runCommand(CommandSender sender, String command, String... args);

    PermissionRole requiredPermissionRole();

    String getCommandName();

    String getCommandDescription();

    default void sendDefaultCodeMessage(CommandSender sender, CommandExecutionCode code) {
        sender.sendMessage(code.getDefaultMessage());
    }
}
