package com.fadedbytes.BinaryElementalOrbs.api.network.protocol;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor.PacketType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public interface Tag {

    static int protocolVersion() {
        return 1;
    }

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
     * <br>
     * The parameter can also be a path to a tag, separated by dots. For example, "root.content.value1".
     *
     * @param tagName The name of the tag to get.
     * @return The inner tag with the given name, or null if the tag doesn't contain a tag with the given name.
     */
    Tag getInnerTag(String tagName);

    /**
     * @return the list of inner tags of this tag. If the tag doesn't contain any inner tags, an empty list is returned.
     */
    List<Tag> getInnerTags();

    /**
     * @return the parent tag of this tag, or null if this tag is the root tag.
     */
    @Nullable
    Tag getParent();

    static Tag createTag(@Nullable RegularTag parent, @NotNull String name, @NotNull String value, @Nullable Collection<TagAttribute> attributes) throws MalformedTagException {
        return _createTag(parent, name, value, attributes, false);
    }

    static Tag createTag(@Nullable RegularTag parent, @NotNull String name, @NotNull String value) throws MalformedTagException {
        return createTag(parent, name, value, null);
    }

    static Tag createOuterTag(@Nullable RegularTag parent, @NotNull String name, @Nullable Collection<TagAttribute> attributes) throws MalformedTagException {
        return _createTag(parent, name, null, attributes, true);
    }

    static Tag createOuterTag(@Nullable RegularTag parent, @NotNull String name) throws MalformedTagException {
        return createOuterTag(parent, name, null);
    }

    private static Tag _createTag(@Nullable RegularTag parent, @NotNull String name, String value, @Nullable Collection<TagAttribute> attributes, boolean isOuter) throws MalformedTagException {
        if (isOuter) {
            if (attributes == null) {
                return new RegularTag(parent, name);
            } else {
                return new ComplexRegularTag(parent, name, attributes);
            }
        } else {
            if (value == null) value = "";
            if (attributes == null) {
                return new PlainTag(parent, name, value);
            } else {
                return new ComplexPlainTag(parent, name, value, attributes);
            }
        }

    }

    static RegularTag presetWithType(PacketType packetType) {
        try {
            RegularTag root = (RegularTag) createOuterTag(null, "beo");

            RegularTag headers = (RegularTag) createOuterTag(root, "headers");
            createTag(headers, "type", packetType.simpleName());
            RegularTag serverInfo = (RegularTag) createOuterTag(headers, "serverInfo");
            createTag(serverInfo, "protocolVersion", String.valueOf(protocolVersion()));

            createOuterTag(root, "content");

            return root;

        } catch (MalformedTagException e) {
            return null;
        }
    }

}
