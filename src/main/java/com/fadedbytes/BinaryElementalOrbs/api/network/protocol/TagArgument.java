package com.fadedbytes.BinaryElementalOrbs.api.network.protocol;

import org.jetbrains.annotations.NotNull;

/**
 * Arguments are clarifications on the meaning of a comlpex tag.
 */

public interface TagArgument {

    /**
     * @return the name of the argument.
     */
    @NotNull
    String name();

    /**
     * @return the value of the argument.
     */
    @NotNull
    String value();

    /**
     * Sets the value of the argument.
     * @param value The value of the argument.
     */
    void setValue(@NotNull String value);

}