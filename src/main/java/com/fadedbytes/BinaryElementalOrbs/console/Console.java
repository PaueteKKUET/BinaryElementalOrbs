package com.fadedbytes.BinaryElementalOrbs.console;

import com.fadedbytes.BinaryElementalOrbs.command.CommandSender;

/**
 * Classes that implement this interface can be used as a console.
 */
public interface Console extends CommandSender {

    void start();

    void processInput();

    void print(String message);

    void println(String message);

    void printMotd();

}
