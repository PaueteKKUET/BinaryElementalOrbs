package com.fadedbytes.BinaryElementalOrbs.event.listener;

import com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor.PacketType;
import com.fadedbytes.BinaryElementalOrbs.event.EventManager;
import com.fadedbytes.BinaryElementalOrbs.event.events.protocol.PacketProcessEvent;

public class PingPacketListener extends Listener {

    private static int pingCount = 0;

    public PingPacketListener(EventManager eventManager) {
        super(eventManager);
    }

    @EventListener
    public static void onPingPacketReceived(PacketProcessEvent event) {
        System.out.println(event.toString());
        if (!event.packet().getType().equals(PacketType.UPSTREAM_PING)) return;

        event.setCancelled(pingCount % 2 == 0);
        pingCount++;
        System.out.println("Event cancelled: " + event.isCancelled());
    }

}
