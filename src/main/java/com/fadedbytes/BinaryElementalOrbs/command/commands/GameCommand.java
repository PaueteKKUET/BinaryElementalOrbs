package com.fadedbytes.BinaryElementalOrbs.command.commands;

import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutionCode;
import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutor;
import com.fadedbytes.BinaryElementalOrbs.command.CommandSender;

import java.util.List;

public class GameCommand implements CompletableCommandExecutor {
    @Override
    public CommandExecutionCode runCommand(CommandSender sender, String command, String... args) {

        if (args.length < 2) {

        }

        return CommandExecutionCode.SUCCESS;
    }

    @Override
    public PermissionRole requiredPermissionRole() {
        return PermissionRole.ADMIN;
    }

    @Override
    public String getCommandName() {
        return "game";
    }

    @Override
    public String getCommandDescription() {
        return "Manages the server games.";
    }

    @Override
    public List<String> getNextArguments(CommandSender sender, String... currentArgs) {
        // TODO implement
        return List.of();
    }
}
