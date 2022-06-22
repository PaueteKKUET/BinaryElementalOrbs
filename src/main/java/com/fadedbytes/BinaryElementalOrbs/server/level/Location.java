package com.fadedbytes.BinaryElementalOrbs.server.level;

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

    public Level getLevel() {
        return level;
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
