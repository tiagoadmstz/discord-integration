package io.github.chatlog.utils;

import java.util.HashMap;

public abstract class InstanceControl {

    private static final HashMap<String, Object> INSTANCES = new HashMap<>();

    public static <T extends Object> T getInstance(String name){
        return (T) INSTANCES.get(name);
    }

    public static void putInstance(String name, Object object) {
        INSTANCES.put(name, object);
    }

}
