package com.fadedbytes.BinaryElementalOrbs.command;

import com.fadedbytes.BinaryElementalOrbs.command.commands.PermissionRole;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a sender of a command.
 */
public interface CommandSender {

    @NotNull String getName();

    @NotNull PermissionRole getPermission();

    void sendMessage(String message);

}
