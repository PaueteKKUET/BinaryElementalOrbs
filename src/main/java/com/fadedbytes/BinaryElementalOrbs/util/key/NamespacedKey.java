package com.fadedbytes.BinaryElementalOrbs.util.key;

import org.jetbrains.annotations.NotNull;

public class NamespacedKey {

    public static final String BEO_NAMESPACE = "beo";

    private final String namespace;
    private final String key;

    public NamespacedKey(@NotNull String namespace, @NotNull String key) {

        namespace = namespace.toLowerCase();
        key = key.toLowerCase();

        if (!(isValidString(namespace) && isValidString(key))) {
            throw new IllegalArgumentException("Invalid namespace or key for " + namespace + ":" + key + ". Values must only contain lower case alphanumeric characters, numbers and underscores.");
        }

        this.namespace = namespace.toLowerCase();
        this.key = key.toLowerCase();
    }

    private boolean isValidString(String value) {
        return value.matches("[a-z\\d_]+");
    }

    @NotNull
    public String getNamespace() {
        return namespace;
    }

    @NotNull
    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", namespace, key);
    }

}
