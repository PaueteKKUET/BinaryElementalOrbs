package com.fadedbytes.BinaryElementalOrbs.event;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.event.events.Event;
import com.fadedbytes.BinaryElementalOrbs.event.listener.EventListener;
import com.fadedbytes.BinaryElementalOrbs.event.listener.Listener;
import com.fadedbytes.BinaryElementalOrbs.event.listener.PingPacketListener;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EventManager {

    private static final Set<EventManager> EVENT_MANAGERS = new HashSet<>();

    private final HashMap<NamespacedKey, Method[]> LISTENERS;
    private final HashMap<Class<? extends Event>, ArrayList<Method>> LISTENERS_BY_EVENT;


    public EventManager() {
        LISTENERS = new HashMap<>();
        LISTENERS_BY_EVENT = new HashMap<>();
        EVENT_MANAGERS.add(this);
    }

    public void addEventListener(NamespacedKey key, Class<? extends Listener> listenerClass) {
        if (LISTENERS.containsKey(key)) {
            throw new IllegalArgumentException("Listener already registered for key: " + key);
        }

        Method[] methods = listenerClass.getMethods();
        Collection<Method> validMethods = new ArrayList<>();

        for (Method method : methods) {
            if (isValidListenerMethod(method)) {
                validMethods.add(method);

                Class<? extends Event> eventClass = getEventClass(method);

                if (LISTENERS_BY_EVENT.containsKey(eventClass)) {
                    LISTENERS_BY_EVENT.get(eventClass).add(method);
                } else {
                    ArrayList<Method> methodsByEvent = new ArrayList<>();
                    methodsByEvent.add(method);
                    LISTENERS_BY_EVENT.put(eventClass, methodsByEvent);
                }

            }
        }

        if (validMethods.isEmpty()) {
            throw new IllegalArgumentException("No valid methods found for class: " + listenerClass.getName());
        } else {
            LISTENERS.put(key, validMethods.toArray(new Method[0]));
        }
    }

    private boolean isValidListenerMethod(Method listenerCandidate) {
        EventListener annotation = listenerCandidate.getAnnotation(EventListener.class);
        if (annotation == null) return false;

        Class<?>[] parameterTypes = listenerCandidate.getParameterTypes();
        if (parameterTypes.length != 1) return false;

        return Event.class.isAssignableFrom(parameterTypes[0]);
    }

    /**
     * Gets the class of the event parameter of a method.
     * @param listenerMethod the checked method to get the event parameter class from.
     * @return the class of the event parameter of the method.
     */
    private Class<? extends Event> getEventClass(Method listenerMethod) {
        try {
            Class<?> eventClass = listenerMethod.getParameterTypes()[0];

            if (Event.class.isAssignableFrom(eventClass)) {
                return (Class<? extends Event>) eventClass;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Method is not a valid listener method: " + listenerMethod.getName());
        }
    }

    public static void launchEvent(Event event) {
        Class<? extends Event> eventType = event.getClass();

        for (EventManager eventManager : EVENT_MANAGERS) {

            ArrayList<Class<? extends Event>> validEventTypes = new ArrayList<>();

            for (Class<? extends Event> listenerClass : eventManager.LISTENERS_BY_EVENT.keySet()) {
                if (eventType.isAssignableFrom(listenerClass)) {
                    validEventTypes.add(listenerClass);
                }
            }

            for (Class<? extends Event> validEventType : validEventTypes) {
                for (Method listenerMethod : eventManager.LISTENERS_BY_EVENT.get(validEventType)) {
                    try {
                        listenerMethod.invoke(null, event);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        BEO.getLogger().error("Error while invoking listener method for event: " + validEventType.getName(), e.getStackTrace());
                    }
                }
            }

        }
    }

    // All created event listeners must be registered here.
    public void registerListeners() {
        new PingPacketListener(this);
    }
}
