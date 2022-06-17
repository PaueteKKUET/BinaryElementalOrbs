package com.fadedbytes.BinaryElementalOrbs.command.commands;

import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutionCode;
import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutor;
import com.fadedbytes.BinaryElementalOrbs.command.CommandManager;
import com.fadedbytes.BinaryElementalOrbs.command.CommandSender;

import java.util.ArrayList;
import java.util.Set;

public class HelpCommand implements CommandExecutor {

    @Override
    public CommandExecutionCode runCommand(CommandSender sender, String command, String... args) {

        if (args.length == 0) {
            showHelpPage(sender, 1);
        } else {
            try {
                int page = Integer.parseInt(args[0]);
                showHelpPage(sender, page);
            } catch (NumberFormatException e) {
                showCommandHelp(sender, args[0]);
            }
        }

        return CommandExecutionCode.SUCCESS;
    }

    @Override
    public PermissionRole requiredPermissionRole() {
        return PermissionRole.NONE;
    }

    @Override
    public String getCommandName() {
        return "help";
    }

    @Override
    public String getCommandDescription() {
        return "Shows this help message";
    }

    private void showHelpPage(CommandSender sender, int pageNumber) {
        Set<String> commands = CommandManager.getAllCommandNames();
        ArrayList<CommandExecutor> executors = new ArrayList<>();
        for (String command : commands) {
            executors.add(CommandManager.getCommand(command));
        }

        executors = executors
                .stream()
                .filter(
                        commandExecutor -> sender.getPermission().isAtLeast(commandExecutor.requiredPermissionRole())
                )
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        if (executors.size() < (pageNumber - 1) * 10) {
            sender.sendMessage("No more help pages");
            return;
        }

        sender.sendMessage("Help page " + pageNumber);
        for (int i = (pageNumber - 1) * 10; i < executors.size() && i < pageNumber * 10; i++) {
            showCommandHelp(sender, executors.get(i).getCommandName());
        }

    }

    private void showCommandHelp(CommandSender sender, String command) {
        CommandExecutor executor = CommandManager.getCommand(command);

        if (executor == null) {
            sender.sendMessage("The command you entered is not known.");
            return;
        }

        sender.sendMessage(
                "/" + command +
                ": " + executor.getCommandDescription() +
                (executor instanceof Aliasable aliasableCommand ? " (aliases: " + String.join(", ", aliasableCommand.getAliases()) + ")" : "")
        );

    }
}
