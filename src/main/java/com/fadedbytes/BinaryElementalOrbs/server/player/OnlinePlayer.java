package com.fadedbytes.BinaryElementalOrbs.server.player;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;

public interface OnlinePlayer extends Player {

    /**
     * Gets the socket address of the player.
     * @return The socket address of the player.
     */
    @NotNull SocketAddress getAddress();

    /**
     * Sends a packet to the player.
     * @param packet The packet to send.
     */
    default void sendPacket(@NotNull Packet packet) {
        BEO.getServer().sendPacket(packet, this.getAddress());
        BEO.getLogger().debug("Sent packet " + packet.getType() + " to " + this.getName() + "(" + this.getAddress() + ")");
    }

    @Override
    default OnlinePlayer fromPlayer(@NotNull SocketAddress address) {
        return this;
    }
}
