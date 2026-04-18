package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import dev.architectury.event.events.client.ClientTickEvent;
import mod.chloeprime.aaaparticles.api.client.effekseer.ParticleEmitter;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public enum AtmosphereEffekManager {
    INSTANCE;

    public static void init() {
        ClientTickEvent.CLIENT_PRE.register(INSTANCE::onClientTick);
    }

    public static boolean isEnabled() {
        return AAAParticlesWorldClient.isEffekEnabled();
    }

    private @Nullable ParticleEmitter emitter;
    private @Nullable AtmosphereEffek current;
    private int fadingOutTicks;
    private boolean loading;

    private void onClientTick(Minecraft client) {
        var level = client.level;
        if (level == null) {
            if (emitter != null) {
                if (emitter.exists()) {
                    emitter.stop();
                }
                emitter = null;
            }
            current = null;
            fadingOutTicks = 0;
            return;
        }
        Optional.ofNullable(emitter).ifPresent(em -> updateEmitter(em, client));
        if (loading) {
            return;
        }
        if (fadingOutTicks > 0) {
            tickFadeOut();
            return;
        }
        if (current != null) {
            if (!current.isEnabled(client)) {
                fadeOut(current.getFadeOutTicks());
            }
            return;
        }
        var matching = matchEntry(client).orElse(null);
        if (matching == null) {
            return;
        }
        current = matching;
        loading = true;
        matching.play(client).thenAccept(emitter -> {
            this.emitter = emitter;
            loading = false;
        });
    }

    private void updateEmitter(ParticleEmitter emitter, Minecraft client) {
        var player = client.player;
        if (player != null) {
            emitter.setPosition((float) player.getX(), (float) player.getY(), (float) player.getZ());
        }
    }

    private void fadeOut(int fadeOutTicks) {
        fadingOutTicks = fadeOutTicks;
        if (emitter != null) {
            emitter.sendTrigger(3);
        }
    }

    private void tickFadeOut() {
        fadingOutTicks--;
        if (fadingOutTicks == 0) {
            if (emitter != null) {
                emitter.stop();
                emitter = null;
                current = null;
            }
        }
    }

    private static Optional<AtmosphereEffek> matchEntry(Minecraft client) {
        for (AtmosphereEffek entry : REGISTRY) {
            if (entry.isEnabled(client)) {
                return Optional.of(entry);
            }
        }
        return Optional.empty();
    }

    public static final Set<AtmosphereEffek> REGISTRY = new LinkedHashSet<>(List.of(
            AtmosphereEffeks.FIREFLIES));
}
