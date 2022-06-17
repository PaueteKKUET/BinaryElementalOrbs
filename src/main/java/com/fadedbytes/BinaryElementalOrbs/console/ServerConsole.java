package com.fadedbytes.BinaryElementalOrbs.console;

import com.fadedbytes.BinaryElementalOrbs.command.commands.PermissionRole;
import com.fadedbytes.BinaryElementalOrbs.util.key.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public final class ServerConsole implements Console {

    private final NamespacedKey key;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private PermissionRole permission;

    ServerConsole(@NotNull NamespacedKey key, @NotNull InputStream inputStream, @NotNull OutputStream outputStream) {
        this.key = key;
        this.inputStream = inputStream;
        this.outputStream = outputStream;

        this.permission = PermissionRole.USER;

        this.start();
        this.printMotd();
    }

    public void start() {
        Thread consoleThread = new Thread(ConsoleManager.CONSOLE_THREAD_GROUP, this::processInput, this.toString());
        consoleThread.start();
    }

    public void processInput() {
        while (true) {
            byte[] buffer = new byte[0];
            byte lastInput;

            try {
                do {
                    lastInput = (byte) inputStream.read();
                    if (lastInput == '\n') continue;

                    byte[] newBuffer = Arrays.copyOf(buffer, buffer.length + 1);
                    newBuffer[buffer.length] = lastInput;
                    buffer = newBuffer;
                } while (lastInput != '\n');

                ConsoleManager.executeCommand(
                        new String(buffer, StandardCharsets.UTF_8),
                        this
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void print(String message) {
        try {
            this.outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void println(String message) {
        print(message + "\n");
    }

    @Override
    public void printMotd() {
        println("Server starting...");
        println("Console started");
    }


    @Override
    public String toString() {
        return "ConsoleManager{" +
                "namespace=" + key +
                "inputStream=" + inputStream +
                ", outputStream=" + outputStream +
                '}';
    }

    public void setPermission(PermissionRole permission) {
        this.permission = permission;
    }

    @Override
    public @NotNull String getName() {
        return "Server Console";
    }

    @Override
    public @NotNull PermissionRole getPermission() {
        return this.permission;
    }

    @Override
    public void sendMessage(String message) {
        println(message);
    }
}
