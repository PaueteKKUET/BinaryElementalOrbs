package com.fadedbytes.BinaryElementalOrbs.console.logger;

import java.util.function.Supplier;

public interface Logger {

    /**
     * Logs a message with the given level. The message is generated by the given supplier.
     * @param level The level of the message.
     * @param messageSupplier The supplier of the message.
     */
    void log(LogLevel level, Supplier<String> messageSupplier);

    /**
     * Logs a given message with the given level.
     * @param level The level of the message.
     * @param message The message to log.
     */
    void log(LogLevel level, String message);

    /**
     * Logs a message as debug level. The message is generated by the given supplier.
     * @param messageSupplier The supplier of the message.
     */
    void debug(Supplier<String> messageSupplier);

    /**
     * Logs a given message as debug level.
     * @param message The message to log.
     */
    void debug(String message);

    /**
     * Logs a message as info level. The message is generated by the given supplier.
     * @param messageSupplier The supplier of the message.
     */
    void error(Supplier<String> messageSupplier);

    /**
     * Logs a given message as info level.
     * @param message The message to log.
     */
    void error(String message);

    /**
     * Logs an error message and the specified stack trace
     * @param message The message to log.
     * @param elements The stack trace elements.
     */
    default void error(String message, StackTraceElement[] elements) {
        this.error(message);
        this.trace(elements);
    }

    /**
     * Logs a message as warning level. The message is generated by the given supplier.
     * @param messageSupplier The supplier of the message.
     */
    void warn(Supplier<String> messageSupplier);

    /**
     * Logs a given message as warning level.
     * @param message The message to log.
     */
    void warn(String message);

    /**
     * Logs a message as info level. The message is generated by the given supplier.
     * @param messageSupplier The supplier of the message.
     */
    void info(Supplier<String> messageSupplier);

    /**
     * Logs a given message as info level.
     * @param message The message to log.
     */
    void info(String message);

    /**
     * Logs a message as event level. The message is generated by the given supplier.
     * @param messageSupplier The supplier of the message.
     */
    void event(Supplier<String> messageSupplier);

    /**
     * Logs a given message as event level.
     * @param message The message to log.
     */
    void event(String message);

    /**
     * Logs the given stack trace as error level.
     * @param elements The stack trace elements to log.
     */
    void trace(StackTraceElement[] elements);

    /**
     * Logs the given stack trace as debug level.
     * @param elements The stack trace elements to log.
     */
    void debugTrace(StackTraceElement[] elements);

    /**
     * Subscribes the given method to this logger. The method will be called when a message with lower or equal level is logged.
     * @param maxLevel The maximum level of the message to subscribe to.
     * @param subscriber The subscriber to call.
     */
    void subscribe(LogLevel maxLevel, LoggerSubscriber subscriber);

}