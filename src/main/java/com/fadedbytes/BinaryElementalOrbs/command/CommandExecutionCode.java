package com.fadedbytes.BinaryElementalOrbs.command;

public enum CommandExecutionCode {

    /**
     * The command was executed successfully.
     */
    SUCCESS("Command executed successfully."),

    /**
     * The execution could not be completed because of an error, but the command was able to be executed.
     */
    CATCHED_ERROR("Command executed, but an error occurred."),

    /**
     * The arguments were invalid.
     */
    INVALID_ARGUMENTS("Invalid arguments."),

    /**
     * The command was not found.
     */
    UNKNOWN_COMMAND("Unknown command. Use the help command to see a list of commands."),

    /**
     * The command was executed successfully, but it is deprecated.
     */
    DEPRECATED("Watch out! This command is deprecated."),

    /**
     * The command cannot be executed under the given circumstances.
     */
    INVALID_CONTEXT("This command cannot be executed right now."),

    /**
     * The sender does not have the required permission to execute the command.
     */
    NO_PERMISSION("You do not have permission to execute this command."),

    /**
     * The command was empty.
     */
    BLANK_COMMAND("");

    private final String defaultMessage;

    CommandExecutionCode(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

}
