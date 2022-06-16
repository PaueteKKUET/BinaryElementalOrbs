package com.fadedbytes.BinaryElementalOrbs.api.network.listener;

/**
 * Represents an object that listens for incoming network packets.
 */
public interface NetworkListener {

    void listen();

    boolean isListening();

}
