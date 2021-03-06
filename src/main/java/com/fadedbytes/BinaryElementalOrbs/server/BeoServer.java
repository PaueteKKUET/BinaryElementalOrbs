package com.fadedbytes.BinaryElementalOrbs.server;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.command.CommandSender;
import com.fadedbytes.BinaryElementalOrbs.console.Console;
import com.fadedbytes.BinaryElementalOrbs.console.logger.Logger;
import com.fadedbytes.BinaryElementalOrbs.event.EventManager;
import com.fadedbytes.BinaryElementalOrbs.server.level.Level;
import com.fadedbytes.BinaryElementalOrbs.server.player.OnlinePlayer;
import com.fadedbytes.BinaryElementalOrbs.server.player.Player;
import com.fadedbytes.BinaryElementalOrbs.server.whitelist.Whitelist;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.SocketAddress;
import java.util.Collection;
import java.util.function.Supplier;

public interface BeoServer {

    /**
     * @return the current server event manager.
     */
    @NotNull
    EventManager getEventManager();

    /**
     * @return the current server console.
     */
    @NotNull
    Console getConsole();

    /**
     * Processes the given incoming packet.
     * @param packet The packet to process.
     */
    void receivePacket(Packet packet);

    /**
     * Sends the given packet to the given address.
     * @param packet The packet to send.
     * @param address The address to send the packet to.
     */
    void sendPacket(Packet packet, SocketAddress address);

    /**
     * @return whether the server is currently running in debug mode.
     */
    boolean isDebugMode();

    /**
     * @return the current server logger.
     */
    @NotNull
    Logger getServerLogger();

    /**
     * @return a collection of all levels currently loaded.
     */
    @NotNull
    Collection<Level> getLevels();

    /**
     * Gets the loaded level with the given namespace key. If no level is loaded with the given key, null is returned.
     * @param key The namespace key of the level to get.
     * @return The loaded level with the given namespace key.
     */
    @Nullable
    Level getLevel(NamespacedKey key);

    /**
     * Adds the given loaded level to the running server.
     * @param level The level to add.
     */
    void addLevel(NamespacedKey key, Level level);

    /**
     * Removes the given level from the running server. If the level is not loaded, nothing happens.
     * @param key The key of the level to remove.
     */
    void removeLevel(NamespacedKey key);

    /**
     * @return the current status of the server.
     */
    ServerStatus getServerStatus();

    /**
     * Changes the server status. This may trigger certain events. If the given status is the same as the current status, nothing happens.
     * @param status The new status of the server.
     */
    void setServerStatus(@NotNull ServerStatus status);

    /**
     * @return whether the server has an enabled whitelist currently.
     */
    boolean whitelistEnabled();

    /**
     * Sets the server whitelist to the given whitelist and enables it.
     * If the given whitelist is null, the server will disable the whitelist,
     * which can also be done by calling {@link #disableWhitelist()}.
     * @param whitelist The whitelist to set.
     */
    void setWhitelist(Whitelist whitelist);

    /**
     * Disables the server whitelist.
     */
    void disableWhitelist();

    /**
     * @return the current message of the day.
     */
    String getMotd();

    /**
     * @return the current number of players online.
     */
    int getCurrentPlayerCount();

    /**
     * @return the maximum number of players allowed on the server.
     */
    int getMaxPlayerCount();

    /**
     * @return true if the server is full, false otherwise.
     */
    boolean isServerFull();

    /**
     * @return the current online players.
     */
    @NotNull Collection<OnlinePlayer> getOnlinePlayers();

    /**
     * Gets the online player with the given name. If no player is online with the given name, null is returned.
     * @param name The name of the player to get.
     * @return The online player with the given name.
     */
    @Nullable OnlinePlayer getPlayer(String name);

    /**
     * Checks if the given player is currently online.
     * @param username The username of the player to check.
     * @return Whether the given player is online.
     */
    boolean isLoggedIn(String username);

    /**
     * Checks if the given player is currently online.
     * @param player The player to check.
     * @return Whether the given player is online.
     */
    default boolean isLoggedIn(Player player) {
        return isLoggedIn(player.getName());
    }

    /**
     * Tries to login the given player. If the player is already online, nothing happens.
     * @param player The player to login.
     * @param password The given password of the player.
     * @param address The address of the player
     */
    void login(Player player, String password, SocketAddress address);

    /**
     * Disconnects the given player.
     * @param player The player to disconnect.
     */
    void disconnect(OnlinePlayer player);

    /**
     * Checks if the player with the given name can join according to the server's whitelist. If there is no whitelist, this method always returns true.
     * @param username The username of the player to check.
     * @return Whether the player with the given name can join according to the server's whitelist.
     */
    boolean whitelistAllowsPlayer(String username);

    /**
     * Checks if the player with the given name is banned from this server.
     * @param username The username of the player to check.
     * @return Whether the player with the given name is banned from this server.
     */
    boolean isBanned(String username);

    /**
     * Sends the given message to the given console.
     * @param console The console to send the message to.
     * @param message The message to send.
     */
    void sendMessage(@NotNull Console console, @NotNull Supplier<String> message);

    /**
     * Sends the given message to the given player.
     * @param playerName The name of the player to send the message to.
     * @param message The message to send.
     */
    void sendMessage(@NotNull String playerName, @NotNull Supplier<String> message);

    /**
     * Broadcasts the given message to all players and consoles.
     * @param message The message to broadcast.
     */
    default void broadcastMessage(@NotNull Supplier<String> message) {
        for (OnlinePlayer player : getOnlinePlayers()) {
            sendMessage(player.getName(), message);
        }

        this.getConsole().sendMessage(message.get());
    }
}
