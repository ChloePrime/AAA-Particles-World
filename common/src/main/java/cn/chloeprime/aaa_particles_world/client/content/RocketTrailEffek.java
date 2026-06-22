package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import cn.chloeprime.aaa_particles_world.client.ClientPlatformMethods;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayDeque;
import java.util.Queue;

public final class RocketTrailEffek {
    public static final Identifier ID = AAAParticlesWorldMod.loc("missile_boost");
    public static final float SCALE = 0.3F;

    public static boolean isEnabled() {
        if (!ClientConfig.ENABLE_FIREBALL_TRAIL.get()) {
            return false;
        }
        return !AAAParticlesWorldClient.isEffekReduced() && AAAParticlesWorldClient.isEffekEnabled();
    }

    public static boolean isEnabledFor(Entity entity) {
        return entity instanceof Fireball;
    }

    public static void play(Entity fireball) {
        var pei = ParticleEmitterInfo.create(fireball.level(), ID)
                .position(0, fireball.getBbHeight() * 0.25, 0)
                .scale((float) (SCALE * fireball.getBoundingBox().getSize()))
                .bindOnEntity(fireball)
                .useEntityVelocityAsRotation()
                .entitySpaceRelativePosition(0, 0, 0);
        AAALevel.addParticle(fireball.level(), true, pei);
    }

    private static final Queue<Runnable> DELAYED_CALLS = new ArrayDeque<>();

    @ApiStatus.Internal
    public static void init() {
        ClientPlatformMethods p = ClientPlatformMethods.get();
        p.addClientEntityJoinLevelCallback(RocketTrailEffek::onEntityJoinLevel);
        p.addClientPreTickCallback(RocketTrailEffek::runDelayedEvents);
    }

    @ApiStatus.Internal
    public static void runDelayedEvents() {
        DELAYED_CALLS.forEach(Runnable::run);
        DELAYED_CALLS.clear();
    }

    private static void onEntityJoinLevel(Entity entity) {
        if (entity == null || !entity.level().isClientSide()) {
            return;
        }
        if (entity.isInvisible() || !isEnabledFor(entity)) {
            return;
        }
        if (!isEnabled()) {
            return;
        }
        DELAYED_CALLS.add(() -> play(entity));
    }

    private RocketTrailEffek() {
    }
}
