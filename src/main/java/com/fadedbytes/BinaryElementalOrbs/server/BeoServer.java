package com.fadedbytes.BinaryElementalOrbs.server;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.console.Console;
import com.fadedbytes.BinaryElementalOrbs.console.logger.Logger;
import com.fadedbytes.BinaryElementalOrbs.event.EventManager;
import com.fadedbytes.BinaryElementalOrbs.server.level.Level;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.SocketAddress;
import java.util.Collection;

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

}
