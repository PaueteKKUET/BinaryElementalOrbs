package com.fadedbytes.BinaryElementalOrbs.console.logger;

import java.util.HashMap;
import java.util.function.Supplier;

public abstract class BaseLogger implements Logger {

    private final HashMap<LoggerSubscriber, LogLevel> SUBSCRIBERS = new HashMap<>();

    @Override
    public void log(LogLevel level, String message) {
        log(level, () -> message);
    }

    @Override
    public void debug(Supplier<String> messageSupplier) {
        this.log(LogLevel.DEBUG, messageSupplier);
    }

    @Override
    public void debug(String message) {
        this.debug(() -> message);
    }

    @Override
    public void error(Supplier<String> messageSupplier) {
        this.log(LogLevel.ERROR, messageSupplier);
    }

    @Override
    public void error(String message) {
        this.error(() -> message);
    }

    @Override
    public void warn(Supplier<String> messageSupplier) {
        this.log(LogLevel.WARN, messageSupplier);
    }

    @Override
    public void warn(String message) {
        this.warn(() -> message);
    }

    @Override
    public void info(Supplier<String> messageSupplier) {
        this.log(LogLevel.INFO, messageSupplier);
    }

    @Override
    public void info(String message) {
        this.info(() -> message);
    }

    @Override
    public void event(Supplier<String> messageSupplier) {
        this.log(LogLevel.EVENT, messageSupplier);
    }

    @Override
    public void event(String message) {
        this.event(() -> message);
    }

    @Override
    public void trace(StackTraceElement[] elements) {
        for (StackTraceElement element : elements) {
            this.error(element::toString);
        }
    }

    @Override
    public void debugTrace(StackTraceElement[] elements) {
        for (StackTraceElement element : elements) {
            this.debug(element::toString);
        }
    }

    @Override
    public void subscribe(LogLevel maxLevel, LoggerSubscriber subscriber) {
        SUBSCRIBERS.put(subscriber, maxLevel);
    }

    protected HashMap<LoggerSubscriber, LogLevel> getSubscribers() {
        return SUBSCRIBERS;
    }

    /**
     * Generates a string with the current timestamp in the format: "HH:mm:ss"
     * @return a string with the current timestamp
     */
    protected String timestamp() {
        return "[" +
                String.format("%02d", System.currentTimeMillis() / 1000 / 60 / 60) + ":" +
                String.format("%02d", System.currentTimeMillis() / 1000 / 60 % 60) + ":" +
                String.format("%02d", System.currentTimeMillis() / 1000 % 60) +
                "]";
    }

    /**
     * Generates a string with the current timestamp in the format: "HH:mm:ss.SSS"
     * @return a string with the current timestamp
     */
    protected String precisionTimestamp() {
        return "[" +
                String.format("%02d", System.currentTimeMillis() / 1000 / 60 / 60) + ":" +
                String.format("%02d", System.currentTimeMillis() / 1000 / 60 % 60) + ":" +
                String.format("%02d", System.currentTimeMillis() / 1000 % 60) + "." +
                String.format("%03d", System.currentTimeMillis() % 1000) +
                "]";
    }
}
