package com.fadedbytes.BinaryElementalOrbs.server.level;

/**
 * A GameLevel is an instance of a game. It should be able to perform the following actions:
 * <ul>
 *     <li>Store the elements present on the level</li>
 *     <li>Provide the information about the level</li>
 *     <li>Update the elements of the game without lose information</li>
 */
public abstract class GameLevel {

    private final LevelProperties properties;

    public GameLevel(LevelProperties properties) {
        this.properties = properties;
    }

}
