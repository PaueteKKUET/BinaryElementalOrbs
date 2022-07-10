package com.fadedbytes.BinaryElementalOrbs.command.commands;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutionCode;
import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutor;
import com.fadedbytes.BinaryElementalOrbs.command.CommandSender;

public class SayCommand implements CommandExecutor {
    @Override
    public CommandExecutionCode runCommand(CommandSender sender, String command, String... args) {

        if (!sender.getPermission().isAtLeast(requiredPermissionRole())) return CommandExecutionCode.NO_PERMISSION;

        if (args.length == 0) return CommandExecutionCode.INVALID_ARGUMENTS;

        BEO.getServer().broadcastMessage(() -> String.join(" ", args));
        return CommandExecutionCode.SUCCESS;

    }

    @Override
    public PermissionRole requiredPermissionRole() {
        return PermissionRole.USER;
    }

    @Override
    public String getCommandName() {
        return "say";
    }

    @Override
    public String getCommandDescription() {
        return "Says something globally to the server";
    }

}
