package com.fadedbytes.BinaryElementalOrbs.server.whitelist;

import com.fadedbytes.BinaryElementalOrbs.server.player.Player;

import java.util.Collection;

/**
 * A whitelist is a list of players that determines which players are allowed to connect to the server.
 */
public interface Whitelist {

    /**
     * Checks if the player is listed in the whitelist.
     * @param player The player to check.
     * @return True if the player is listed in the whitelist, false otherwise.
     */
    boolean includes(String player);

    /**
     * Checks if the player can join the server according to the whitelist.
     * @param player The player to check.
     * @return True if the player can join the server, false otherwise.
     */
    boolean canJoin(String player);

    /**
     * Adds a player to the whitelist.
     * @param player The player to add.
     */
    void addPlayer(String player);

    /**
     * Removes a player from the whitelist.
     * @param player The player to remove.
     */
    void removePlayer(String player);

    /**
     * Adds a collection of players to the whitelist.
     * @param players The players to add.
     */
    void addAllPlayers(Collection<String> players);

    /**
     * Returns a collection of all players in the whitelist.
     * @return A collection of all players in the whitelist.
     */
    Collection<String> getPlayers();

    /**
     * Gets the mode of this whitelist.
     * @return The mode of this whitelist.
     * @see WhitelistMode
     */
    WhitelistMode getMode();

    /**
     * Whitelists can operate in one of the enumerated modes.
     */
    enum WhitelistMode {

        /**
         * The listed players will be allowed to join the server.
         */
        WHITELIST,

        /**
         * The listed players will be denied from joining the server.
         */
        BLACKLIST

    }

}
