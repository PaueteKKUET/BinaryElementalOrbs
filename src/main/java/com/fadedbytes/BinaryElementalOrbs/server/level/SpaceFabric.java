package com.fadedbytes.BinaryElementalOrbs.server.level;

public interface SpaceFabric {

    Location getCenter();

    boolean isValidLocation(Location loc);

    float distanceBetween(Location loc1, Location loc2);

    int getFabricRadius();

}
