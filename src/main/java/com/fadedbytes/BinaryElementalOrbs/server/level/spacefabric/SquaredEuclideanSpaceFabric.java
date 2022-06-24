package com.fadedbytes.BinaryElementalOrbs.server.level.spacefabric;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.server.level.Level;
import com.fadedbytes.BinaryElementalOrbs.server.level.Location;
import com.fadedbytes.BinaryElementalOrbs.server.level.element.LevelElement;
import org.jetbrains.annotations.NotNull;

public class SquaredEuclideanSpaceFabric implements SpaceFabric {

    private final Level level;
    private final Location center;
    private final int radius;

    public SquaredEuclideanSpaceFabric(Level level, int dimensions, int radius) {
        this.center = new Location(level, new float[dimensions]);

        this.level = level;
        this.radius = radius > 0 ? radius : 1;
    }

    @Override
    public Location getCenter() {
        return this.center;
    }

    @Override
    public boolean isValidLocation(Location loc) {
        int radius = getFabricRadius();
        for (float coord : loc.getCoords()) {
            if (coord < -radius || coord > radius) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Location nearestValidLocation(Location loc) {
        if (isValidLocation(loc)) {
            return loc;
        }

        float[] coords = loc.getCoords();
        for (int i = 0; i < coords.length; i++) {
            if (coords[i] < -getFabricRadius()) {
                coords[i] = -getFabricRadius();
            } else if (coords[i] > getFabricRadius()) {
                coords[i] = getFabricRadius();
            }
        }

        return new Location(level, coords);
    }

    @Override
    public float distanceBetween(Location loc1, Location loc2) {
        return 0;
    }

    @Override
    public int getFabricRadius() {
        return this.radius;
    }

    @Override
    public int getDimensions() {
        return this.center.getDimensions();
    }

    @Override
    public void moveElement(@NotNull LevelElement element, Location direction, float power) {
        BEO.getLogger().debug("An element should be moved by a space fabric, but it's not implemented yet.");
    }
}
