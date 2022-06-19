package com.fadedbytes.BinaryElementalOrbs.console.logger;

@FunctionalInterface
public interface LoggerSubscriber {
    /**
     * At the moment the message is ready to be shown, this method is called.
     * The method should pipe the message to the receiver, which is, for example, a console.
     * @param message The message to be shown.
     */
    void logTo(String message);
}
