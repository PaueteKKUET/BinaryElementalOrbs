package com.fadedbytes.BinaryElementalOrbs.server.level;

/**
 * Stores the basic, common properties of a level.
 * <br>
 * Use {@link Builder} to create a new instance.
 */
public final class LevelProperties {

    private LevelProperties(Builder builder) {

    }

    /**
     * Builder for {@link LevelProperties}.
     */
    public static final class Builder {
        public LevelProperties build() {
            return new LevelProperties(this);
        }
    }

}
