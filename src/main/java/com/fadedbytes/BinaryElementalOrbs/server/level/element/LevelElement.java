package com.fadedbytes.BinaryElementalOrbs.server.level.element;

import com.fadedbytes.BinaryElementalOrbs.server.level.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface LevelElement {

    /**
     * Gets the weight of the element.
     * @return The weight of the element.
     */
    float weight();

    /**
     * Gets the current location of the element.
     * @return The location of the element.
     */
    @Nullable
    Location getLocation();

    /**
     * Sets the location of the element to the given location. To remove the element from the level, use {@link #remove()}.
     * @param location The location to set the element to.
     */
    void setLocation(@Nullable Location location);

    /**
     * Gets the unique identifier of the element.
     * @return The unique identifier of the element.
     */
    @NotNull
    UUID getUUID();

    /**
     * Removes the element from the level, if it has been added to one.
     */
    void remove();

}
