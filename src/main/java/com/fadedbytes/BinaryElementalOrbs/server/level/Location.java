package com.fadedbytes.BinaryElementalOrbs.server.level;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Location {

    private final Level level;
    private final float[] coords;

    public Location(Level level, float... coords) {
        this.level = level;
        this.coords = new float[coords.length];

        for (int i = 0; i < coords.length; i++) {
            // round the coordinate to 4 decimals
            this.coords[i] = (float) Math.round(coords[i] * 10000f) / 10000f;
        }
    }

    /**
     * Returns the value of the coordinate at the specified axis position, or 0 if the axis does not exist.
      * @param axisPosition the value of the axis to get.
     * @return the value of the specified axis in the current location.
     */
    public float getCoord(int axisPosition) {
        if (coords.length <= axisPosition) return 0.0f;
        return coords[axisPosition];
    }

    /**
     * Returns the value of the coordinate at the specified axis, or 0 if the axis does not exist.
     * @param axis the axis to get the value of.
     * @return the value of the specified axis in the current location.
     */
    public float getCoord(Axis axis) {
        return getCoord(axis.getPosition());
    }

    /**
     * @return the number of dimensions of the current location.
     */
    public int getDimensions() {
        return coords.length;
    }

    /**
     * @return all the coordinates of the current location, from 0 to {@link #getDimensions()} - 1.
     */
    public float[] getCoords() {
        return Arrays.copyOf(coords, coords.length);
    }

    /**
     * Returns the level that this location is in.
     * @return the level that this location is in.
     */
    @NotNull
    public Level getLevel() {
        return level;
    }

    public float distance(Location other) {
        if (this.getLevel().equals(other.getLevel())) {
            return this.getLevel().getSpaceFabric().distanceBetween(this, other);
        } else {
            throw new IllegalArgumentException("Cannot calculate distance between locations in different levels.");
        }
    }


    /**
     * Some predefined axis positions.
     */
    public enum Axis {
        X(0),
        Y(1),
        Z(2),
        W(3);

        final int axisPosition;

        Axis(int axisPosition) {
            this.axisPosition = axisPosition;
        }

        int getPosition() {
            return this.axisPosition;
        }

    }

}
