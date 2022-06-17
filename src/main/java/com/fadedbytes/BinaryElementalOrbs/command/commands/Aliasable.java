package com.fadedbytes.BinaryElementalOrbs.command.commands;

import com.fadedbytes.BinaryElementalOrbs.command.CommandExecutor;

import java.util.List;

/**
 * Command that can be aliased.
 */
public interface Aliasable {

    List<String> getAliases();

}
