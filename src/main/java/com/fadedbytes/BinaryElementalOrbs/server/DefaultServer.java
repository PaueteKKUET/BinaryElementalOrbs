package com.fadedbytes.BinaryElementalOrbs.server;

import com.fadedbytes.BinaryElementalOrbs.BEO;
import com.fadedbytes.BinaryElementalOrbs.api.network.ServerNetworkManager;
import com.fadedbytes.BinaryElementalOrbs.api.network.SocketManager;
import com.fadedbytes.BinaryElementalOrbs.api.network.listener.NetworkListener;
import com.fadedbytes.BinaryElementalOrbs.api.network.listener.NetworkPacketListener;
import com.fadedbytes.BinaryElementalOrbs.api.network.packet.Packet;
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
import com.fadedbytes.BinaryElementalOrbs.event.events.Event;
import com.fadedbytes.BinaryElementalOrbs.event.events.level.LevelAddedEvent;
import com.fadedbytes.BinaryElementalOrbs.event.events.protocol.PacketLaunchEvent;
import com.fadedbytes.BinaryElementalOrbs.event.events.protocol.PacketProcessEvent;
import com.fadedbytes.BinaryElementalOrbs.event.events.server.ConsoleAttachedEvent;
import com.fadedbytes.BinaryElementalOrbs.event.events.server.ServerStartupEvent;
import com.fadedbytes.BinaryElementalOrbs.server.level.Level;
import com.fadedbytes.BinaryElementalOrbs.server.level.SimpleLevel;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

public class DefaultServer implements BeoServer {
    private static InetAddress host = null;
    private final static int port = 25466;

    private static DefaultServer SERVER_INSTANCE = null;

    private EventManager eventManager;
    private Console defaultConsole;

    private NetworkListener mainListener;
    private NetworkSender mainSender;
    private HashMap<NamespacedKey, Level> levelHolder;

    protected DefaultServer() {
        setupEventManager();

        ServerStartupEvent startupEvent = new ServerStartupEvent(this, LocalDate.now());
        startupEvent.launch();

        setupConsole();
        setupNetworkManagers();

        registerCommands();

        registerLevels();
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
        Event consoleEvent = new ConsoleAttachedEvent(this, defaultConsole);
        consoleEvent.launch();

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

    private void setupNetworkManagers() {
        this.getConsole().sendMessage("Starting network manager");
        ServerNetworkManager.init();

        try {
            if (host == null) {
                host = InetAddress.getLocalHost();
            }
            mainListener = new NetworkPacketListener(host, port);
            mainSender = new NetworkPacketSender(host);
        } catch (IOException e) {
            this.getConsole().sendMessage("Can't bind to address " + host.getHostAddress() + ":" + port);
            this.getConsole().sendMessage("Server shutting down");
            BEO.exit();
        }

        ServerNetworkManager.addSocketManager((SocketManager) mainListener);
        ServerNetworkManager.addSocketManager((SocketManager) mainSender);

        ServerNetworkManager.init();
    }

    private void registerLevels() {
        this.levelHolder = new HashMap<>();

        this.addLevel(new NamespacedKey(NamespacedKey.BEO_NAMESPACE, "test_level"), new SimpleLevel("test_level"));
    }

    @Override
    public @NotNull EventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public @NotNull Console getConsole() {
        return this.defaultConsole;
    }

    @Override
    public void receivePacket(Packet packet) {
        Event packetProcess = new PacketProcessEvent(this, packet);
        if (packetProcess.launch()) {
            packet.getType().getProcessor().process(packet);
        }
    }

    @Override
    public void sendPacket(Packet packet, SocketAddress address) {
        Event packetSend = new PacketLaunchEvent(this, packet, address);
        if (packetSend.launch()) {
            mainSender.send(packet, address);
            getLogger().debug("Sent packet to " + address);
        }
    }

    @Override
    public boolean isDebugMode() {
        return true;
    }

    @Override
    public @NotNull Logger getServerLogger() {
        return LogManager.getGlobalLogger();
    }

    @Override
    public @NotNull Collection<Level> getLevels() {
        return this.levelHolder.values();
    }

    @Override
    public @Nullable Level getLevel(NamespacedKey key) {
        return this.levelHolder.get(key);
    }

    @Override
    public void addLevel(NamespacedKey key, Level level) {
        if (!this.levelHolder.containsKey(key)) {
            this.levelHolder.put(key, level);

            Event levelAdded = new LevelAddedEvent(level, this);
            levelAdded.launch();
        }
    }

    @Override
    public void removeLevel(NamespacedKey key) {
        this.levelHolder.remove(key);
    }

    public static Logger getLogger() {
        return getServer().getServerLogger();
    }
}
