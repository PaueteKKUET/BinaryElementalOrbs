package com.fadedbytes.BinaryElementalOrbs.api.network.protocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegularTag implements Tag {

    private final Tag parent;
    private final List<Tag> innerTags;
    private final String name;


    public RegularTag(@Nullable Tag parentTag, @NotNull String tagName, Tag... innerTags) throws MalformedTagException {
        this.parent = parentTag;
        this.name = tagName;
        this.innerTags = new ArrayList<>();

        this.addInnerTags(innerTags);

        if (parent != null) {
            if (this.parent instanceof RegularTag regularParent) {
                regularParent.addInnerTags(this);
            } else {
                throw new MalformedTagException("Parent tag is not a complex tag");
            }
        }
    }

    public void addInnerTags(@NotNull Tag... innerTags) {
        this.innerTags.addAll(Arrays.asList(innerTags));
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public boolean hasValue() {
        return false;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public boolean hasInnerTags() {
        return innerTags.size() > 0;
    }

    @Override
    public String getInnerTag(String tagName) {
        for (Tag tag : innerTags) {
            if (tag.getName().equals(tagName)) {
                return tag.getValue();
            }
        }
        return null;
    }

    @Override
    public List<Tag> getInnerTags() {
        return innerTags;
    }

    @Override
    @Nullable
    public Tag getParent() {
        return this.parent;
    }
}
