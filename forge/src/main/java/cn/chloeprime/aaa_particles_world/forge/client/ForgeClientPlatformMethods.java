package cn.chloeprime.aaa_particles_world.forge.client;

import cn.chloeprime.aaa_particles_world.client.ClientPlatformMethods;
import com.google.auto.service.AutoService;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Consumer;

@AutoService(ClientPlatformMethods.class)
public class ForgeClientPlatformMethods extends ClientPlatformMethods {
    public static final IEventBus BUS = MinecraftForge.EVENT_BUS;

    @Override
    public void addClientPreTickCallback(Runnable action) {
        BUS.addListener(EventPriority.NORMAL, false, TickEvent.ClientTickEvent.class, event -> {
            if (event.phase == TickEvent.Phase.START) {
                action.run();
            }
        });
    }

    @Override
    public void addClientEntityJoinLevelCallback(Consumer<Entity> action) {
        BUS.addListener(EventPriority.LOWEST, false, EntityJoinLevelEvent.class, event -> action.accept(event.getEntity()));
    }
}
