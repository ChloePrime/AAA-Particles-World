package cn.chloeprime.aaa_particles_world.fabric.client;

import cn.chloeprime.aaa_particles_world.client.ClientPlatformMethods;
import com.google.auto.service.AutoService;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.world.entity.Entity;

import java.util.function.Consumer;

@AutoService(ClientPlatformMethods.class)
public class FabricClientPlatformMethods extends ClientPlatformMethods {
    @Override
    public void addClientPreTickCallback(Runnable action) {
        ClientTickEvents.START_CLIENT_TICK.register(mc -> action.run());
    }

    @Override
    public void addClientEntityJoinLevelCallback(Consumer<Entity> action) {
        ClientEntityEvents.ENTITY_LOAD.register((entity, world) -> action.accept(entity));
    }
}
