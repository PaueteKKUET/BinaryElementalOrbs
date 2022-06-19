package com.fadedbytes.BinaryElementalOrbs.console.logger;

public class LogManager {

    private static final Logger GLOBAL_LOGGER = new SimpleLogger();

    public static Logger getGlobalLogger() {
        return GLOBAL_LOGGER;
    }

}
