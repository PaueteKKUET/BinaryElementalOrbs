package com.fadedbytes.BinaryElementalOrbs.api.network.protocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;

/**
 * A complex tag is a tag that contains arguments inside the tag.
 * <br>
 * For example: <wood>Hey</wood> is not a complex tag, but <wood type="cherry">Hey</wood> is.
 */
public interface ComplexTag extends Tag {

    /**
     * @return a map with the keys and values of each argument of this tag.
     */
    @NotNull
    Collection<TagArgument> getArguments();

    /**
     * @return the value of the argument with the given name.
     * @param name The name of the argument to get.
     * @return The value of the argument with the given name, or null if it doesn't exist.
     */
    @Nullable
    TagArgument getArgumentByName(@NotNull String name);

    /**
     * Adds an argument to this tag.
     * @param argument The argument to add.
     */
    void addArgument(@NotNull TagArgument argument);

    /**
     * Sets the value of the argument with the given name. If the argument doesn't exist, it will be created.
     * @param name The name of the argument to set.
     * @param value The value of the argument to set.
     */
    void setArgumentByName(@NotNull String name, @NotNull String value);

    /**
     * Removes the argument with the given name.
     * @param name The name of the argument to remove.
     */
    void removeArgumentByName(String name);

}
