package com.fadedbytes.BinaryElementalOrbs.event.events.player;

import com.fadedbytes.BinaryElementalOrbs.event.events.Event;
import com.fadedbytes.BinaryElementalOrbs.server.player.Player;
import org.jetbrains.annotations.NotNull;

public interface PlayerEvent extends Event {

    /**
     * Gets the involved player.
     * @return The involved player.
     */
    @NotNull Player player();

}
