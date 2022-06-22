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

    public float getCoord(int i) {
        if (coords.length <= i) return 0.0f;
        return coords[i];
    }



    public static enum AXIS {
        X,
        Y,
        Z,
        W
    }

}
