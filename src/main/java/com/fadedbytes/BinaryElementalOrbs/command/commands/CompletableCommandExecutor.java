package com.fadedbytes.BinaryElementalOrbs.command.commands;

import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutor;
import com.fadedbytes.BinaryElementalOrbs.command.CommandSender;

import java.util.List;

/**
 * If a command executor is completable, it can be queried to get the next arguments.
 */
public interface CompletableCommandExecutor extends CommandExecutor {

    List<String> getNextArguments(CommandSender sender, String... currentArgs);

}
