package com.fadedbytes.BinaryElementalOrbs.api.network;

import com.fadedbytes.BinaryElementalOrbs.api.network.listener.NetworkListener;

import java.util.HashSet;
import java.util.Set;

public final class ServerNetworkManager {

    private static ThreadGroup NETWORK_THREAD_GROUP;

    private static Set<SocketManager> SOCKET_MANAGERS;

    public static void init() {
        NETWORK_THREAD_GROUP = new ThreadGroup("Network Thread Group");
        SOCKET_MANAGERS = new HashSet<>();
    }

    private static ThreadGroup getNetworkThreadGroup() {
        return NETWORK_THREAD_GROUP;
    }

    private static Set<SocketManager> getSocketManagers() {
        return SOCKET_MANAGERS;
    }

    public static void addSocketManager(SocketManager manager) {
        SOCKET_MANAGERS.add(manager);
        startListeners();
    }

    /**
     * Makes the listeners start listening for incoming data.
     */
    private static void startListeners() {
        for (SocketManager manager : getSocketManagers()) {
            if (manager instanceof NetworkListener listener) {

                if (listener.isListening()) continue;

                Thread thread = new Thread(
                        getNetworkThreadGroup(),
                        listener::listen,
                        manager.toString()
                );

                thread.start();

            }
        }
    }

}
