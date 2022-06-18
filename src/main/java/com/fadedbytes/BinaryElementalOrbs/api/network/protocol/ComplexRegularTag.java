package com.fadedbytes.BinaryElementalOrbs.api.network.protocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class ComplexRegularTag extends RegularTag implements ComplexTag {

    private final Collection<TagArgument> arguments;

    public ComplexRegularTag(Tag parentTag, String tagName, Collection<TagArgument> arguments, Tag... innerTags) throws MalformedTagException {
        super(parentTag, tagName, innerTags);
        this.arguments = arguments;
    }

    @Override
    public @NotNull Collection<TagArgument> getArguments() {
        return arguments;
    }

    @Override
    public @Nullable TagArgument getArgumentByName(@NotNull String name) {
        TagArgument argument = null;
        for (TagArgument arg : getArguments()) {
            if (arg.name().equals(name)) {
                argument = arg;
                break;
            }
        }
        return argument;
    }

    @Override
    public void addArgument(@NotNull TagArgument argument) {
        arguments.add(argument);
    }

    @Override
    public void setArgumentByName(@NotNull String name, @NotNull String value) {
        TagArgument argument = getArgumentByName(name);
        if (argument != null) {
            argument.setValue(value);
            return;
        }
        addArgument(new DefaultTagArgument(name, value));
    }

    @Override
    public void removeArgumentByName(String name) {
        TagArgument argument = getArgumentByName(name);
        if (argument != null) {
            arguments.remove(argument);
        }
    }
}
