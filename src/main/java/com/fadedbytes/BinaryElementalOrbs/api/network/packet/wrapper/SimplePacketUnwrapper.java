package com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper;

import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimplePacketUnwrapper implements PacketUnwrapper {


    @Override
    public Tag generateTagFromString(@NotNull String plainTextTag) throws MalformedTagException {

        return null;
    }

    /**
     * Clears all line breaks and tabs from the string
     * @param unFormattedString The string to be formatted
     * @return The formatted string
     */
    private String preFormat(String unFormattedString) {
        return unFormattedString.replace("\t", "").replace("\n", "");
    }
}
