package com.fadedbytes.BinaryElementalOrbs.api.network.protocol;

import org.jetbrains.annotations.NotNull;

public class DefaultTagArgument implements TagArgument {

    private final String name;

    private String value;

    DefaultTagArgument(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public @NotNull String name() {
        return this.name;
    }

    @Override
    public @NotNull String value() {
        return this.value;
    }

    @Override
    public void setValue(@NotNull String value) {
        this.value = value;
    }
}
