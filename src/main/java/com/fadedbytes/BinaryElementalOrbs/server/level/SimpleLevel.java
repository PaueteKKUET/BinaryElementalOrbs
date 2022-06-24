package com.fadedbytes.BinaryElementalOrbs.server.level;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.event.events.Event;
import com.fadedbytes.BinaryElementalOrbs.event.events.level.LevelLoadedEvent;
import com.fadedbytes.BinaryElementalOrbs.event.events.level.LevelLoadingEvent;
import com.fadedbytes.BinaryElementalOrbs.server.level.element.LevelElement;
import com.fadedbytes.BinaryElementalOrbs.server.level.spacefabric.SpaceFabric;
import com.fadedbytes.BinaryElementalOrbs.server.level.spacefabric.SquaredEuclideanSpaceFabric;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleLevel implements Level {

    private boolean loaded;
    private final SpaceFabric spaceFabric;
    private final Collection<LevelElement> elements;

    public SimpleLevel(String levelName) {
        Event loadingEvent = new LevelLoadingEvent(this);
        loadingEvent.launch();

        this.loaded = false;
        this.spaceFabric = new SquaredEuclideanSpaceFabric(this, 3, 50);
        this.elements = new ArrayList<>();

        this.loaded = true;
        Event loadedEvent = new LevelLoadedEvent(this);
        loadedEvent.launch();
    }

    @Override
    public boolean isLoaded() {
        return this.loaded;
    }

    @Override
    public @NotNull SpaceFabric getSpaceFabric() {
        return this.spaceFabric;
    }

    @Override
    public @NotNull Collection<LevelElement> getElements() {
        return new ArrayList<>(this.elements);
    }

    @Override
    public @NotNull Collection<? extends LevelElement> getElementsOfType(@Nullable Class<? extends LevelElement> type) {
        return this.getElements()
                .stream()
                .filter(
                        element -> element.getClass().equals(type)
                )
                .collect(Collectors.toList());
    }

    @Override
    public LevelElement getNearestElement(@NotNull Location loc, float searchRadius) {
        if (loc.getLevel() != this) {
            throw new IllegalArgumentException("Location must be in this level.");
        }

        Optional<LevelElement> element = this.getElements()
                .stream()
                .sorted(
                        (element1, element2) -> {
                            float distance1 = element1.getLocation().distance(loc);
                            float distance2 = element2.getLocation().distance(loc);
                            return Float.compare(distance1, distance2);
                        }
                )
                .findFirst();

        if (element.isPresent()) {
            if (element.get().getLocation().distance(loc) <= searchRadius) {
                return element.get();
            }
        }

        return null;

    }

    @Override
    public void addElement(@NotNull LevelElement newElement, Location loc) {
        newElement.remove();
        newElement.setLocation(loc);
        this.elements.add(newElement);
    }

    @Override
    public void removeElement(@NotNull LevelElement element) {
        this.elements.remove(element);
        element.setLocation(null);
    }

    @Override
    public void setElementLocation(@NotNull LevelElement element, @NotNull Location location) {
        element.setLocation(location);
    }

    @Override
    public void moveElement(@NotNull LevelElement element, @NotNull Location direction, float power) {
        this.getSpaceFabric().moveElement(element, direction, power);
    }

    @Override
    public void saveLevel() {
        BEO.getLogger().debug("Level save not implemented, but called.");
    }
}
