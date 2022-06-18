package com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper;

import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.ComplexTag;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.TagArgument;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SimplePacketWrapper implements PacketWrapper {

    private static final String TAG_OPEN = "<%s%s>";
    private static final String TAG_CLOSE = "</%s>\n";

    @Override
    public String generatePacketContent(Tag tag) throws MalformedTagException {

        StringBuilder builder = new StringBuilder();

        return processTag(builder, tag, 0).toString();
    }

    private @NotNull StringBuilder processTag(StringBuilder builder, Tag tag, int level) throws MalformedTagException {
        if (tag.hasValue()) {
            return builder
                    .append("\t".repeat(level))
                    .append(
                        String.format(
                                TAG_OPEN,
                                tag.getName(),
                                generateTagAttributes(tag)
                        )
                    )
                    .append(tag.getValue())
                    .append(
                        String.format(
                                TAG_CLOSE,
                                tag.getName()
                        )
                    );
        } else if (tag.hasInnerTags()){

            builder.append("\t".repeat(level));
            builder.append(
                    String.format(
                            TAG_OPEN,
                            tag.getName(),
                            generateTagAttributes(tag)
                    )
            );

            builder.append("\n");

            List<Tag> innerTags = tag.getInnerTags();
            for (Tag innerTag : innerTags) {
                processTag(builder, innerTag, level + 1);
            }

            builder.append("\t".repeat(level));
            builder.append(
                    String.format(
                            TAG_CLOSE,
                            tag.getName()
                    )
            );

            return builder;
        } else {
            throw new MalformedTagException("Tag has no value or inner tags");
        }
    }

    private String generateTagAttributes(Tag tag) {
        if (tag instanceof ComplexTag complexTag) {
            StringBuilder attributes = new StringBuilder();
            for (TagArgument attribute : complexTag.getArguments()) {
                attributes
                        .append(" ")
                        .append(attribute.name())
                        .append("=\"")
                        .append(attribute.value())
                        .append("\"");
            }
            return attributes.toString();
        } else {
            return "";
        }
    }
}
