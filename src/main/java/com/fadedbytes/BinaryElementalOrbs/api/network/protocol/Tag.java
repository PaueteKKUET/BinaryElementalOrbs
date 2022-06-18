package com.fadedbytes.BinaryElementalOrbs.api.network.protocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Tag {

    /**
     * @return The name of the current tag.
     */
    @NotNull
    String getName();

    /**
     * @return if the current tag contains a plain value.
     */
    boolean hasValue();

    /**
     * @return the plain value of the tag, or null if the tag doesn't contain a plain value.
     * @see #hasValue()
     */
    @Nullable
    String getValue();

    /**
     * @return if the current tag contains tags as children.
     */
    boolean hasInnerTags();

    /**
     * Returns the inner tag of this tag with the given name, or null if the tag doesn't contain a tag with the given name.
     * @param tagName The name of the tag to get.
     * @return The inner tag with the given name, or null if the tag doesn't contain a tag with the given name.
     */
    @Nullable
    String getInnerTag(String tagName);

    /**
     * @return the list of inner tags of this tag. If the tag doesn't contain any inner tags, an empty list is returned.
     */
    List<Tag> getInnerTags();

    /**
     * @return the parent tag of this tag, or null if this tag is the root tag.
     */
    @Nullable
    Tag getParent();

}
