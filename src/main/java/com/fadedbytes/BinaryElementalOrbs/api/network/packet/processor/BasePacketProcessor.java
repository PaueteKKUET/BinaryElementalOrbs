package com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public abstract class BasePacketProcessor implements PacketProcessor {

    private static final HashMap<Class<? extends BasePacketProcessor>, BasePacketProcessor> PROCESSORS = new HashMap<>();

    protected BasePacketProcessor() {
        PROCESSORS.put(getClass(), this);
    }

    @NotNull
    public static PacketProcessor getInstanceOf(@NotNull Class<? extends BasePacketProcessor> processorClass) {
        if (!PROCESSORS.containsKey(processorClass)) {
            try {
                PROCESSORS.put(processorClass, processorClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException("Can not create instance for packet processor class: " + processorClass.getName(), e);
            }
        }

        return PROCESSORS.get(processorClass);

    }
}
