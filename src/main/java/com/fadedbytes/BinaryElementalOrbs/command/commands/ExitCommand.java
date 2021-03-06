package com.fadedbytes.BinaryElementalOrbs.command.commands;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutionCode;
import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutor;
import com.fadedbytes.BinaryElementalOrbs.command.CommandSender;

import java.util.List;

public class ExitCommand implements CommandExecutor, Aliasable {

    @Override
    public CommandExecutionCode runCommand(CommandSender sender, String command, String... args) {
        if (sender.getPermission().isAtLeast(requiredPermissionRole())) {

            sender.sendMessage("Exiting...");

            BEO.exit();
            return CommandExecutionCode.SUCCESS;
        } else {
            sendDefaultCodeMessage(sender, CommandExecutionCode.NO_PERMISSION);
            return CommandExecutionCode.NO_PERMISSION;
        }
    }

    @Override
    public PermissionRole requiredPermissionRole() {
        return PermissionRole.ADMIN;
    }

    @Override
    public String getCommandName() {
        return "exit";
    }

    @Override
    public String getCommandDescription() {
        return "Stops the server";
    }

    @Override
    public List<String> getAliases() {
        return List.of(
                "stop",
                "killserver"
        );
    }
}
