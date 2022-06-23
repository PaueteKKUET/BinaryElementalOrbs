package com.fadedbytes.BinaryElementalOrbs.api.network.protocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlainTag implements Tag {

    private final Tag parent;
    private final String name;
    private String value;

    public PlainTag(Tag parentTag, @NotNull String name, @NotNull String value) throws MalformedTagException {
        this.parent = parentTag;

        this.name = name;
        this.value = value;

        if (this.parent instanceof RegularTag regularParent) {
            regularParent.addInnerTags(this);
        } else {
            throw new MalformedTagException("Parent tag is not a complex tag");
        }
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public boolean hasValue() {
        return true;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean hasInnerTags() {
        return false;
    }

    @Override
    public Tag getInnerTag(String tagName) {
        return null;
    }

    @Override
    public List<Tag> getInnerTags() {
        return List.of();
    }

    @Override
    public Tag getParent() {
        return this.parent;
    }

    /**
     * Sets the value of this tag.
     * @param value The new value of this tag.
     */
    public void setValue(@Nullable String value) {
        this.value = value == null ? "" : value;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.value;
    }
}
