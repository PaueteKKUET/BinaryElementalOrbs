package com.fadedbytes.BinaryElementalOrbs.server;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.api.network.ServerNetworkManager;
import com.fadedbytes.BinaryElementalOrbs.api.network.SocketManager;
import com.fadedbytes.BinaryElementalOrbs.api.network.listener.NetworkListener;
import com.fadedbytes.BinaryElementalOrbs.api.network.listener.NetworkPacketListener;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.processor.PacketType;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.PacketWrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.wrapper.SimplePacketWrapper;
import com.fadedbytes.BinaryElementalOrbs.api.network.sender.NetworkPacketSender;
import com.fadedbytes.BinaryElementalOrbs.api.network.sender.NetworkSender;
import com.fadedbytes.BinaryElementalOrbs.command.CommandManager;
import com.fadedbytes.BinaryElementalOrbs.command.commands.PermissionRole;
import com.fadedbytes.BinaryElementalOrbs.console.Console;
import com.fadedbytes.BinaryElementalOrbs.console.ConsoleManager;
import com.fadedbytes.BinaryElementalOrbs.console.ServerConsole;
import com.fadedbytes.BinaryElementalOrbs.console.logger.LogLevel;
import com.fadedbytes.BinaryElementalOrbs.console.logger.LogManager;
import com.fadedbytes.BinaryElementalOrbs.console.logger.Logger;
import com.fadedbytes.BinaryElementalOrbs.event.EventManager;
import com.fadedbytes.BinaryElementalOrbs.event.events.ServerStartupEvent;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;

public class DefaultServer implements BeoServer {
    private static final InetAddress host = null;
    private static final int port = 25466;

    private static DefaultServer SERVER_INSTANCE = null;

    private EventManager eventManager;
    private Console defaultConsole;

    private NetworkListener mainListener;
    private NetworkSender mainSender;

    protected DefaultServer() {
        setupEventManager();

        ServerStartupEvent startupEvent = new ServerStartupEvent(this, LocalDate.now());
        startupEvent.launch();

        setupConsole();
        try {
            setupNetworkManagers();
        } catch (IOException e) {
            this.getConsole().sendMessage("Can't bind to address " + host.getHostAddress() + ":" + port);
            this.getConsole().sendMessage("Server shutting down");
            BEO.exit();
        }

        registerCommands();
    }

    public static DefaultServer getServer() {
        if (SERVER_INSTANCE == null) {
            SERVER_INSTANCE = new DefaultServer();
        }

        return SERVER_INSTANCE;
    }

    private void setupConsole() {
        defaultConsole = ConsoleManager.getConsole(
                new NamespacedKey(NamespacedKey.BEO_NAMESPACE, "main_console"),
                System.in,
                System.out
        );
        ((ServerConsole) defaultConsole).setPermission(PermissionRole.CONSOLE);

        LogManager.getGlobalLogger().subscribe(
                this.isDebugMode() ?
                        LogLevel.DEBUG :
                        LogLevel.WARN,
                getConsole()::sendMessage
        );
    }

    private void registerCommands() {
        this.getConsole().sendMessage("Registering commands");
        CommandManager.setupCommands();
    }

    private void setupEventManager() {
        eventManager = new EventManager();
    }

    private void setupNetworkManagers() throws IOException {
        this.getConsole().sendMessage("Starting network manager");
        ServerNetworkManager.init();

        mainListener = new NetworkPacketListener(host, port);
        mainSender = new NetworkPacketSender(host);

        ServerNetworkManager.addSocketManager((SocketManager) mainListener);
        ServerNetworkManager.addSocketManager((SocketManager) mainSender);

        ServerNetworkManager.init();
    }

    @Override
    public EventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public Console getConsole() {
        return this.defaultConsole;
    }

    @Override
    public void receivePacket(Packet packet) {
        packet.getType().getProcessor().process(packet);
    }

    @Override
    public void sendPacket(Packet packet, SocketAddress address) {
        mainSender.send(packet, address);
        getLogger().debug("Sent packet to " + address);
    }

    @Override
    public boolean isDebugMode() {
        return true;
    }

    @Override
    public Logger getServerLogger() {
        return LogManager.getGlobalLogger();
    }

    public static Logger getLogger() {
        return getServer().getServerLogger();
    }
}
