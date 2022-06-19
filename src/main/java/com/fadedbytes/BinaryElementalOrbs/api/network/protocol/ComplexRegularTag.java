package com.fadedbytes.BinaryElementalOrbs.api.network.protocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class ComplexRegularTag extends RegularTag implements ComplexTag {

    private final Collection<TagAttribute> arguments;

    public ComplexRegularTag(Tag parentTag, String tagName, Collection<TagAttribute> arguments, Tag... innerTags) throws MalformedTagException {
        super(parentTag, tagName, innerTags);
        this.arguments = arguments;
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
