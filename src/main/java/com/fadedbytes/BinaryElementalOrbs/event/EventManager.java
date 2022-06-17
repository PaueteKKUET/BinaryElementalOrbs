package com.fadedbytes.BinaryElementalOrbs.event;

import com.fadedbytes.BinaryElementalOrbs.event.listener.EventListener;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class EventManager {

    private final HashMap<NamespacedKey, Method[]> LISTENERS;

    public EventManager() {
        LISTENERS = new HashMap<>();
    }

    public void addEventListener(NamespacedKey key, Class<EventListener> listenerClass) {
        if (LISTENERS.containsKey(key)) {
            throw new IllegalArgumentException("Listener already registered for key: " + key);
        }

        Method[] methods = listenerClass.getMethods();
        Collection<Method> validMethods = new ArrayList<>();

        for (Method method : methods) {
            com.fadedbytes.BinaryElementalOrbs.event.EventListener annotation = method.getAnnotation(com.fadedbytes.BinaryElementalOrbs.event.EventListener.class);
            if (annotation == null) continue;

            validMethods.add(method);
        }

        if (validMethods.isEmpty()) {
            throw new IllegalArgumentException("No valid methods found for class: " + listenerClass.getName());
        } else {
            LISTENERS.put(key, validMethods.toArray(new Method[0]));
        }
    }

    public void removeEventListener() {

    }

}
