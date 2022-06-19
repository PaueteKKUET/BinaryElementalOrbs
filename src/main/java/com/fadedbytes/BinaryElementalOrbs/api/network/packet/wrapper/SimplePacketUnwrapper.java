package com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper;

import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimplePacketUnwrapper implements PacketUnwrapper {

    public static final SimplePacketUnwrapper INSTANCE = new SimplePacketUnwrapper();
    private static final Pattern ATTRIBUTE_PATTERN = Pattern.compile("[a-zA-Z]+?=\".*?\"");

    @Override
    public Tag generateTagFromString(@NotNull String plainTextTag) throws MalformedTagException {
        RegularTag rootTag = createRootContainer();
        formatChildTagFor(rootTag, preFormat(plainTextTag));
        return removeRootContainer(rootTag);
    }

    private RegularTag createRootContainer() throws MalformedTagException {
        return new RegularTag(null, "root");
    }

    private RegularTag removeRootContainer(RegularTag rootTag) throws MalformedTagException {
        if (rootTag.getName().equals("root")) {
            RegularTag beoTag = (RegularTag) rootTag.getInnerTags().get(0);
            if (beoTag == null) {
                throw new MalformedTagException("Missing main tag");
            }
            return beoTag;
        } else {
            throw new MalformedTagException("Root tag is not the first tag in the packet.");
        }
    }

    private void formatChildTagFor(RegularTag parentTag, String rawString) throws MalformedTagException {
        TagContainer rootContainer = transformIntoContainer(rawString);

        if (!rootContainer.hasInnerTags()) {
            Tag.createTag(
                    parentTag,
                    rootContainer.tagName(),
                    rootContainer.content(),
                    rootContainer.attributesAsTagAttributes()
            );
        } else {
            formatChildTagFor(
                    (RegularTag) Tag.createOuterTag(parentTag, rootContainer.tagName(), rootContainer.attributesAsTagAttributes()),
                    rootContainer.content()
            );
        }

        if (rootContainer.hasTextLeft()) {
            if (rootContainer.hasTextLeft()) {
                formatChildTagFor(parentTag, rootContainer.unprocessedText());
            }
        }
    }

    /**
     * Clears all line breaks and tabs from the string
     * @param unFormattedString The string to be formatted
     * @return The formatted string
     */
    private String preFormat(String unFormattedString) {
        return unFormattedString.replace("\t", "").replace("\n", "").trim();
    }



    private TagContainer transformIntoContainer(final String rawString) throws MalformedTagException {
        if (rawString.charAt(0) != '<') throw new MalformedTagException("The raw string is malformed ('<' was not the first character): " + rawString);

        int lastTagNameIndex = -1;
        int lastOpenTagIndex = -1;

        // Calculate the index of the last tag name character and the last open tag symbol
        for (int i = 1; i < rawString.length() && lastOpenTagIndex == -1; i++) {
            switch (rawString.charAt(i)) {
                case ' ' -> {
                    if (lastTagNameIndex == -1) lastTagNameIndex = i - 1;
                }
                case '>' -> lastOpenTagIndex = i;
                case '<' -> throw new MalformedTagException("The raw string is malformed (tried to open a new tag inside a tag declaration at index " + i + " ): " + rawString);
            }
        }

        // Check some conditions before continue:

        if (lastOpenTagIndex == -1) throw new MalformedTagException("The raw string is malformed (open tag end not found): " + rawString);   // If the tag never got closed.
        if (lastTagNameIndex == -1) lastTagNameIndex = lastOpenTagIndex -1;     //If there were no spaces inside the tag, the last index of the name is recalculated.
        if (lastTagNameIndex == 0 ) throw new MalformedTagException("The raw string is malformed (found an unnamed tag): " + rawString);    // If there was an empty tag.

        final String tagName = rawString.substring(1, lastTagNameIndex + 1).trim();

        final String openTag = rawString.substring(0, lastOpenTagIndex + 1);
        final String closeTag = "</" + tagName + ">";

        // get the index of the close tag
        int closeTagIndex = rawString.indexOf(closeTag);

        // if not found, the data is malformed
        if (closeTagIndex == -1) throw new MalformedTagException("The raw string is malformed (close tag not found): " + rawString);

        // get the content of the tag
        final String content = rawString
                .replace(openTag, "")
                .replace(closeTag, "")
                .substring(0, closeTagIndex - openTag.length())
                .trim();

        // calculate the part of the raw string we will not process
        int unprocessedStringIndex = closeTagIndex + closeTag.length();

        final String unprocessedString = unprocessedStringIndex >= rawString.length() ? "" : rawString.substring(unprocessedStringIndex).trim();

        // parse the tag attributes, if exists
        final String rawOpenTag = rawString.substring(0, lastOpenTagIndex + 1);
        String rawAttributess =  rawOpenTag
                .replace("<" + tagName, "")
                .replace(">", "")
                .trim();

        final HashMap<String, String> attributeMap = new HashMap<>();
        if (rawAttributess.length() > 0) {
            Matcher matcher = ATTRIBUTE_PATTERN.matcher(rawAttributess);

            while (matcher.find()) {
                String attribute = matcher.group();
                attributeMap.put(attribute.substring(0, attribute.indexOf('=')), attribute.substring(attribute.indexOf('"') + 1, attribute.length() - 1));
            }
        }

        return new TagContainer(tagName, attributeMap, content, unprocessedString);
    }

}
