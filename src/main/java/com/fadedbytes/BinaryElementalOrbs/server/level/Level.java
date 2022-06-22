package com.fadedbytes.BinaryElementalOrbs.server.level;

import com.fadedbytes.BinaryElementalOrbs.server.level.element.LevelElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface Level {

    /**
     * @return whether the level is currently fully loaded.
     */
    boolean isLoaded();

    /**
     * Returns the space fabric of this level.
     * @return the space fabric of this level.
     */
    @NotNull
    SpaceFabric getSpaceFabric();

    /**
     * Returns all elements present in this level.
     * @return all elements of this level.
     */
    @NotNull
    Collection<LevelElement> getElements();

    /**
     * Returns all elements of the specified type present in this level.
     * @param type the type of elements to return.
     * @return all elements of the specified type present in this level.
     */
    Collection<? extends LevelElement> getElementsOfType(@Nullable Class<? extends LevelElement> type);

    /**
     * Returns the nearest element to the specified location. If there are no elements in a 100 unit radius, this method returns null.
     * @param loc the location to search from.
     * @return the nearest element to the specified location.
     */
    @Nullable
     default LevelElement getNearestElement(@NotNull Location loc) {
        return getNearestElement(loc, 100.0F);
    }

    /**
     * Returns the nearest element to the specified location. If there are no elements in the specified radius, this method returns null.
     * @param loc the location to search from.
     * @param searchRadius the radius to search in.
     * @return the nearest element to the specified location.
     */
    LevelElement getNearestElement(@NotNull Location loc, float searchRadius);

    /**
     * Adds an element to this level.
     * @param newElement the element to add.
     * @param loc the initial location of the element.
     */
    void addElement(@NotNull LevelElement newElement, Location loc);

    /**
     * Removes an element from this level.
     * @param element the element to remove.
     */
    void removeElement(@NotNull LevelElement element);

    /**
     * Immediately sets the location of an element, if it is present in this level.
     * @param element the element to set the location of.
     * @param location the location to set the element to.
     */
    void setElementLocation(@NotNull LevelElement element, @NotNull Location location);

    /**
     * Moves an element an unspecified distance in the specified direction.
     * The speed of the movement is determined by the specified power, the
     * element's weight and the space fabric properties.
     * @param from the element to move.
     * @param direction the direction to move the element in.
     * @param power the power of the movement.
     */
    void moveElement(@NotNull Location from, @NotNull Location direction, float power);

    /**
     * Saves the level data.
     */
    void saveLevel();

}
