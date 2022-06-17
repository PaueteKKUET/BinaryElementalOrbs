package com.fadedbytes.BinaryElementalOrbs.event;

import com.fadedbytes.BinaryElementalOrbs.event.listener.EventListener;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class EventManager {

    private static final Class<?>[] LISTENER_METHOD_PARAMETERS = new Class[] {
            Event.class
    };

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
            if (isValidListenerMethod(method)) {
                validMethods.add(method);
            }
        }

        if (validMethods.isEmpty()) {
            throw new IllegalArgumentException("No valid methods found for class: " + listenerClass.getName());
        } else {
            LISTENERS.put(key, validMethods.toArray(new Method[0]));
            System.out.println("Registered " + validMethods.size() + " methods for key: " + key);
        }
    }

    private boolean isValidListenerMethod(Method listenerCandidate) {
        com.fadedbytes.BinaryElementalOrbs.event.EventListener annotation = listenerCandidate.getAnnotation(com.fadedbytes.BinaryElementalOrbs.event.EventListener.class);
        if (annotation == null) return false;

        Class<?>[] parameterTypes = listenerCandidate.getParameterTypes();
        if (parameterTypes.length != LISTENER_METHOD_PARAMETERS.length) return false;

        for (int i = 0; i < parameterTypes.length; i++) {
            if (!parameterTypes[i].equals(LISTENER_METHOD_PARAMETERS[i])) return false;
        }

        return true;

    }

}
