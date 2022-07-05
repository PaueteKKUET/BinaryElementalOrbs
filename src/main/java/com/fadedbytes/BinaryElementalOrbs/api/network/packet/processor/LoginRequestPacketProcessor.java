package com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.DataPacket;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.SimplePacketWrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.MalformedTagException;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.RegularTag;
import com.fadedbytes.BinaryElementalOrbs.api.network.protocol.Tag;
import com.fadedbytes.BinaryElementalOrbs.server.player.LoggingInPlayer;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;

public class LoginRequestPacketProcessor extends BasePacketProcessor {
    @Override
    public boolean canProcess(PacketType type) {
        return type.equals(PacketType.UPSTREAM_LOGIN_REQUEST);
    }

    @Override
    public void process(Packet packet) {

        if (!this.canProcess(packet.getType())) {
            throw new IllegalArgumentException("Cannot process packet of type: " + packet.getType());
        }

        SocketAddress   userAddress = packet.getSocketAddress();
        String          username    = packet.getRootTag().getInnerTag("content").getInnerTag("username").getValue();
        String          password    = packet.getRootTag().getInnerTag("content").getInnerTag("password").getValue();

        if (username == null ) username = "";
        if (password == null ) password = "";

        if (username.isEmpty() || username.isBlank()) {
            BEO.getLogger().debug("Received login request from " + userAddress + " with no username");

            try {
                packet.reply(createPacket(LoginResponse.USER_NOT_FOUND));
            } catch (MalformedTagException e) {
                throw new RuntimeException(e);
            }

            return;
        }

        BEO.getServer().login(new LoggingInPlayer(username), password, userAddress);
    }

    public static Packet createPacket(@NotNull LoginResponse response) throws MalformedTagException {
        Tag loginTag = Tag.presetWithType(PacketType.DOWNSTREAM_LOGIN_RESPONSE);
        Tag.createTag((RegularTag) loginTag.getInnerTag("content"), "status", String.valueOf(response.getCode()));
        return DataPacket.createFromString(SimplePacketWrapper.INSTANCE.generatePacketContent(loginTag));
    }

    /**
     * Possible login response codes
     */
    public enum LoginResponse {

        // Correct codes

        /**
         * Login successful
         */
        SUCCESS(200),

        // User related codes

        /**
         * The password is incorrect
         */
        INCORRECT_PASSWORD(401),
        /**
         * The user is not registered
         */
        USER_NOT_FOUND(402),

        // Server related codes

        /**
         * The user is banned
         */
        BANNED(501),
        /**
         * The user is not whitelisted
         */
        NOT_WHITELISTED(502),
        /**
         * The server is full
         */
        SERVER_FULL(503),

        /**
         * Other error
         */
        OTHER(900);

        private final int code;

        LoginResponse(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
