package cn.chloeprime.aaa_particles_world.util;

import mod.chloeprime.aaaparticles.api.client.EffectDefinition;
import mod.chloeprime.aaaparticles.api.client.EffectHolder;
import mod.chloeprime.aaaparticles.api.client.EffectRegistry;
import net.minecraft.client.particle.Particle;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;

/**
 * @since 2.0.1
 */
public final class EffekLimiter {
    public static int getCurrentPlayingCount(Identifier effekId) {
        return Optional.ofNullable(EffectRegistry.get(effekId))
                .flatMap(EffectHolder::lazyGet)
                .stream()
                .flatMap(EffectDefinition::emitterContainers)
                .mapToInt(Collection::size)
                .sum();
    }

    @ApiStatus.Internal
    public static final Set<Particle> EXPLOSION_EMITTER_MERCY_LIST = Collections.newSetFromMap(new WeakHashMap<>());

    @ApiStatus.Internal
    public static final Deque<Boolean> EXPLOSION_EMITTER_MERCY_CALL_STACK = new ArrayDeque<>();

    private EffekLimiter() {
    }
}
