package cn.chloeprime.aaa_particles_world.util;

import mod.chloeprime.aaaparticles.api.client.EffectDefinition;
import mod.chloeprime.aaaparticles.api.client.EffectHolder;
import mod.chloeprime.aaaparticles.api.client.EffectRegistry;
import net.minecraft.client.particle.Particle;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;
import java.util.stream.Stream;

/**
 * @since 2.0.1
 */
public final class EffekLimiter {
    public static int getCurrentPlayingCount(ResourceLocation effekId) {
        return Optional.ofNullable(EffectRegistry.get(effekId))
                .flatMap(EffectHolder::lazyGet)
                .stream()
                .flatMap(EffekLimiter::getAllVariants)
                .flatMap(EffectDefinition::emitterContainers)
                .mapToInt(Collection::size)
                .sum();
    }

    private static Stream<EffectDefinition> getAllVariants(EffectDefinition main) {
        var subs = main.getMetadata().getRoutingSettings()
                .map(Map::values)
                .stream()
                .flatMap(Collection::stream)
                .flatMap(routing -> routing.targetId().stream())
                .map(EffectRegistry::get)
                .filter(Objects::nonNull)
                .flatMap(holder -> holder.lazyGet().stream());
        return Stream.concat(Stream.of(main), subs);
    }

    @ApiStatus.Internal
    public static final Set<Particle> EXPLOSION_EMITTER_MERCY_LIST = Collections.newSetFromMap(new WeakHashMap<>());

    @ApiStatus.Internal
    public static final Deque<Boolean> EXPLOSION_EMITTER_MERCY_CALL_STACK = new ArrayDeque<>();

    private EffekLimiter() {
    }
}
