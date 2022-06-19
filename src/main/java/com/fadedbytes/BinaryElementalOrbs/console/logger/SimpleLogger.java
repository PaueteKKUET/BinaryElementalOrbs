package com.fadedbytes.BinaryElementalOrbs.console.logger;

import java.util.HashMap;
import java.util.function.Supplier;

public class SimpleLogger extends BaseLogger {
    @Override
    public void log(LogLevel level, Supplier<String> messageSupplier) {
        HashMap<LoggerSubscriber, LogLevel> currentSubscribers = this.getSubscribers();
        for (LoggerSubscriber subscriber : currentSubscribers.keySet()) {
            LogLevel subscriberLevel = currentSubscribers.get(subscriber);
            if (subscriberLevel.isAtLeast(level)) {
                subscriber.logTo(
                        (
                                subscriberLevel.equals(LogLevel.DEBUG) ?
                                this.precisionTimestamp() :
                                this.timestamp()
                        )
                        + "> " + messageSupplier.get()
                );
            }
        }
    }
}
