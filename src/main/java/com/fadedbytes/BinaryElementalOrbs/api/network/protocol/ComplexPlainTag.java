package com.fadedbytes.BinaryElementalOrbs.api.network.protocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;

public class ComplexPlainTag extends PlainTag implements ComplexTag {

    private final Collection<TagAttribute> arguments;

    public ComplexPlainTag(Tag parentTag, @NotNull String name, @NotNull String value, @Nullable Collection<TagAttribute> arguments) throws MalformedTagException {
        super(parentTag, name, value);
        this.arguments = arguments == null ? Collections.emptyList() : arguments;
    }

    @Override
    public @NotNull Collection<TagAttribute> getArguments() {
        return arguments;
    }

    @Override
    public @Nullable TagAttribute getArgumentByName(@NotNull String name) {
        TagAttribute argument = null;
        for (TagAttribute arg : getArguments()) {
            if (arg.name().equals(name)) {
                argument = arg;
                break;
            }
        }
        return argument;
    }

    @Override
    public void addArgument(@NotNull TagAttribute argument) {
        arguments.add(argument);
    }

    @Override
    public void setArgumentByName(@NotNull String name, @NotNull String value) {
        TagAttribute argument = getArgumentByName(name);
        if (argument != null) {
            argument.setValue(value);
            return;
        }
        addArgument(new DefaultTagArgument(name, value));
    }

    @Override
    public void removeArgumentByName(String name) {
        TagAttribute argument = getArgumentByName(name);
        if (argument != null) {
            arguments.remove(argument);
        }
    }
}
