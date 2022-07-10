package com.fadedbytes.BinaryElementalOrbs.server.player;

import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;
import java.util.UUID;

public interface Player {

    /**
     * Gets the name of the player.
     * @return The name of the player.
     */
    @NotNull String getName();

    /**
     * Checks if the player is online.
     * @return True if the player is online, false otherwise.
     */
    boolean isLoggedIn();

    /**
     * @return the UUID of the player.
     */
    @NotNull UUID getUUID();

    /**
     * Gets an OnlinePlayer instance for this player.
     * @param address The socket address of the player.
     * @return An OnlinePlayer instance for this player.
     */
    OnlinePlayer fromPlayer(@NotNull SocketAddress address);

    /**
     * Sends a chat message to the player.
     * @param message The message to send.
     */
    void sendMessage(String message);

}
