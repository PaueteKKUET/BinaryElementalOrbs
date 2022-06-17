package com.fadedbytes.BinaryElementalOrbs.command.commands;

public enum PermissionRole {

    CONSOLE(999),

    ADMIN(800),

    USER(100),

    NONE(-100);


    private final int weight;
    PermissionRole(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isAtLeast(PermissionRole role) {
        return this.getWeight() >= role.getWeight();
    }

}
