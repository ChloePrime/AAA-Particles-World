package cn.chloeprime.aaa_particles_world.client;

import net.minecraft.world.entity.Entity;

import java.util.ServiceLoader;
import java.util.function.Consumer;

public abstract class ClientPlatformMethods {
    public static ClientPlatformMethods get() {
        return INSTANCE;
    }

    public abstract void addClientPreTickCallback(Runnable action);
    public abstract void addClientEntityJoinLevelCallback(Consumer<Entity> action);

    private static final ClientPlatformMethods INSTANCE = ServiceLoader.load(ClientPlatformMethods.class)
            .findFirst()
            .orElseThrow(ExceptionInInitializerError::new);
}
