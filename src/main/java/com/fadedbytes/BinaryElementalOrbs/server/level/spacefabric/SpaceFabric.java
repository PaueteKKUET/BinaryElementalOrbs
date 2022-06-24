package com.fadedbytes.BinaryElementalOrbs.server.level.spacefabric;

import com.fadedbytes.BinaryElementalOrbs.server.level.Location;
import com.fadedbytes.BinaryElementalOrbs.server.level.element.LevelElement;
import org.jetbrains.annotations.NotNull;

public interface SpaceFabric {

    /**
     * Gets the center location of the space fabric.
     * @return The center location of the space fabric.
     */
    Location getCenter();

    /**
     * A location is valid if it is within the limits of the space fabric.
     * @param loc The location to check.
     * @return True if the location is valid, false otherwise.
     */
    boolean isValidLocation(Location loc);

    /**
     * Gets the nearest valid location to the given invalid location. This method will return the same location if the given location is valid.
     * @param loc The location to get the nearest valid location to.
     * @return The nearest valid location to the given invalid location.
     */
    Location nearestValidLocation(Location loc);

    /**
     * Calculates the straight line distance between two locations.
     * @param loc1 The first location.
     * @param loc2 The second location.
     * @return The straight line distance between the two locations.
     */
    float distanceBetween(Location loc1, Location loc2);

    /**
     * Gets the radius of the space fabric. This radius does not have to be applied to a sphere, necessarily.
     * @return The radius of the space fabric.
     */
    int getFabricRadius();

    /**
     * Gets the number of dimensions of the space fabric.
     * @return The number of dimensions of the space fabric.
     */
    int getDimensions();

    /**
     * Moves the given element in the direction of the given location
     * This method will not move the element if the location is invalid.
     * The element will be moved following the space fabric's rules.
     * @param element The element to move.
     * @param direction The direction to move the element.
     * @param power The power of the movement.
     */
    void moveElement(@NotNull LevelElement element, Location direction, float power);

}
