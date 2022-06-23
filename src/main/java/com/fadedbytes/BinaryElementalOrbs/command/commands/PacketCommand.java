package com.fadedbytes.BinaryElementalOrbs.command.commands;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor.PacketType;
import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutionCode;
import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutor;
import com.fadedbytes.BinaryElementalOrbs.command.CommandSender;

import java.util.Arrays;

public class PacketCommand implements CommandExecutor {
    @Override
    public CommandExecutionCode runCommand(CommandSender sender, String command, String... args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /packet <list | send>");
            return CommandExecutionCode.INVALID_ARGUMENTS;
        }
        switch (args[0]) {
            case "list" -> {
                return list(sender, Arrays.copyOfRange(args, 1, args.length));
            }
            case "send" -> {
                return send(sender, Arrays.copyOfRange(args, 1, args.length));
            }
            default -> {
                sender.sendMessage("Usage: /packet <list | send>");
                return CommandExecutionCode.INVALID_ARGUMENTS;
            }
        }
    }

    @Override
    public PermissionRole requiredPermissionRole() {
        return PermissionRole.CONSOLE;
    }

    @Override
    public String getCommandName() {
        return "packet";
    }

    @Override
    public String getCommandDescription() {
        return "Can perform packet operations, such as sending debug packets or listing the last received packets.";
    }

    private CommandExecutionCode list(CommandSender sender, String... args) {

        switch (args.length) {
            case 0 -> {
                sender.sendMessage("Usage: /packet list <types | last>");
                return CommandExecutionCode.INVALID_ARGUMENTS;
            }
            case 1 -> {
                if (args[0].equals("types")) {
                    printPacketTypesTo(sender, false);
                    return CommandExecutionCode.SUCCESS;
                } else if (args[0].equals("last")) {
                    printLastReceivedPacketsTo(sender, 10);
                    return CommandExecutionCode.SUCCESS;
                } else {
                    sender.sendMessage("Usage: /packet list <types | last>");
                    return CommandExecutionCode.INVALID_ARGUMENTS;
                }
            }
            case 2 -> {
                if (args[0].equals("types")) {
                    printPacketTypesTo(sender, args[1].equals("all"));
                    return CommandExecutionCode.SUCCESS;
                } else if (args[0].equals("last")) {
                    try {
                        printLastReceivedPacketsTo(sender, Integer.parseInt(args[1]));
                        return CommandExecutionCode.SUCCESS;
                    } catch (NumberFormatException e) {
                        sender.sendMessage("Usage: /packet list last <number>");
                        return CommandExecutionCode.INVALID_ARGUMENTS;
                    }
                } else {
                    sender.sendMessage("Usage: /packet list <types | last>");
                    return CommandExecutionCode.INVALID_ARGUMENTS;
                }
            }
        }

        return CommandExecutionCode.INVALID_ARGUMENTS;
    }

    private CommandExecutionCode send(CommandSender sender, String... args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /packet send <type> <data> <address>");
            return CommandExecutionCode.INVALID_ARGUMENTS;
        }

        sender.sendMessage("Not implemented yet, sorry!");
        return CommandExecutionCode.SUCCESS;
    }

    private void printPacketTypesTo(CommandSender sender, boolean alsoUpstream) {
        sender.sendMessage("Packet types:");
        for (PacketType type : PacketType.values()) {
            if (alsoUpstream || type.isDownstream()) {
                sender.sendMessage(" " + (type.isUpstream() ? "-" : "+") +  " " + type.name());
            }
        }

    }

    private void printLastReceivedPacketsTo(CommandSender sender, int limit) {
        sender.sendMessage("Showing last " + limit + " packets:");
        sender.sendMessage("   ---   Unimplemented, coming soon   ---");

    }
}
