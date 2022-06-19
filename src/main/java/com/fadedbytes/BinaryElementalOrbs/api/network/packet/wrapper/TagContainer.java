package com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper;

import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.DefaultTagArgument;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.TagAttribute;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public record TagContainer(
        @NotNull String tagName,
        @NotNull HashMap<String, String> attributes,
        @NotNull String content,
        @NotNull String unprocessedText
) {

    public boolean hasAttributes() {
        return this.attributes().size() > 0;
    }

    public boolean hasTextLeft() {
        return !this.unprocessedText().isBlank();
    }

    public boolean hasInnerTags() {
        return this.content.startsWith("<");
    }

    public Collection<TagAttribute> attributesAsTagAttributes() {
        Collection<TagAttribute> argumentCollection = new ArrayList<>();
        for (String key : attributes().keySet()) {
            argumentCollection.add(new DefaultTagArgument(key, attributes().get(key)));
        }
        return argumentCollection;
    }

}