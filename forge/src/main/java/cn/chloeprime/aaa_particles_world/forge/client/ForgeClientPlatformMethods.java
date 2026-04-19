package cn.chloeprime.aaa_particles_world.forge.client;

import cn.chloeprime.aaa_particles_world.client.ClientPlatformMethods;
import com.google.auto.service.AutoService;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.function.Consumer;

@AutoService(ClientPlatformMethods.class)
public class ForgeClientPlatformMethods extends ClientPlatformMethods {
    public static final IEventBus BUS = NeoForge.EVENT_BUS;

    @Override
    public void addClientPreTickCallback(Runnable action) {
        BUS.addListener(ClientTickEvent.Pre.class,event -> action.run());
    }

    @Override
    public void addClientEntityJoinLevelCallback(Consumer<Entity> action) {
        BUS.addListener(EventPriority.LOWEST, false, EntityJoinLevelEvent.class, event -> action.accept(event.getEntity()));
    }
}
