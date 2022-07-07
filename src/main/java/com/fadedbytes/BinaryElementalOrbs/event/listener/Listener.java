package com.fadedbytes.BinaryElementalOrbs.event.listener;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.event.EventManager;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;

public abstract class Listener {

    public Listener(EventManager eventManager) {
        eventManager.addEventListener(new NamespacedKey(NamespacedKey.BEO_NAMESPACE, getClass().getSimpleName()), this.getClass());
    }

}
