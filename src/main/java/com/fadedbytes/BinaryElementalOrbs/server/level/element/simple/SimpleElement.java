package com.fadedbytes.BinaryElementalOrbs.server.level.element.simple;

import com.fadedbytes.BinaryElementalOrbs.server.level.Location;
import com.fadedbytes.BinaryElementalOrbs.server.level.element.LevelElement;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class SimpleElement implements LevelElement {

    private final UUID uuid;
    private Location location;
    protected float weight;

    public SimpleElement(float weight) {
        this.uuid = UUID.randomUUID();
        this.weight = weight;
    }

    public SimpleElement(Location loc, float weight) {
        this(weight);
        this.setLocation(loc);
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(@NotNull Location location) {
        this.remove();
        this.location = location;
        location.getLevel().addElement(this, location);
    }

    @Override
    public @NotNull UUID getUUID() {
        return this.uuid;
    }

    @Override
    public void remove() {
        if (this.getLocation() == null) return;
        this.getLocation().getLevel().removeElement(this);
    }

    @Override
    public float weight() {
        return this.weight;
    }
}
