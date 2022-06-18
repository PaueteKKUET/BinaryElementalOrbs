package com.fadedbytes.BinaryElementalOrbs;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.PacketUnwrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.PacketWrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.SimplePacketUnwrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.SimplePacketWrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.PlainTag;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.RegularTag;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;

public class PacketWrapperTests {

    /**
     *
     * <beo>
     *     <headers>
     *         <type>ping</type>
     *         <clientInfo>
     *             <protocolVersion>1</protocolVersion>
     *             <senderType>console</senderType>
     *         </clientInfo>
     *     </headers>
     *     <content>
     *         <message>This is an example ping</message>
     *     </content>
     * </beo>
     *
     */

    public static void testWrapper() {

        PacketWrapper wrapper = new SimplePacketWrapper();
        try {
            String stringifiedPacket = wrapper.generatePacketContent(generateMainTag());
            testUnwrapper(stringifiedPacket);
        } catch (MalformedTagException e) {
            System.out.println("Failed wrapping the tag :(");
        }

    }

    private static Tag generateMainTag() {
        RegularTag beoTag = null;
        try {
            beoTag = new RegularTag(null, "beo");

            RegularTag headersTag = new RegularTag(beoTag, "headers");

            PlainTag typeTag = new PlainTag(headersTag, "type", "ping");
            RegularTag clientInfoTag = new RegularTag(headersTag, "clientInfo");

            PlainTag protocolVersionTag = new PlainTag(clientInfoTag, "protocolVersion", "1");
            PlainTag senderTypeTag = new PlainTag(clientInfoTag, "senderType", "console");


            RegularTag contentTag = new RegularTag(beoTag, "content");
            PlainTag messageTag = new PlainTag(contentTag, "message", "This is an example ping");
        } catch (MalformedTagException e) {
            System.out.println("No se pudo crear la etiqueta");
        }

        return beoTag;
    }

    public static void testUnwrapper(String wrappedPacket) {
        SimplePacketUnwrapper unwrapper = new SimplePacketUnwrapper();
        try {
            // TODO
        } catch (Exception e) {
            System.out.println("FAIL");
        }

    }

}
