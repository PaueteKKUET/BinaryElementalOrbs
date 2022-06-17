package com.fadedbytes.BinaryElementalOrbs.event.listener;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;

public abstract class EventListener {

    public EventListener() {
        BEO.getServer().getEventManager().addEventListener(new NamespacedKey(NamespacedKey.BEO_NAMESPACE, getClass().getSimpleName()), this.getClass());
    }

}
