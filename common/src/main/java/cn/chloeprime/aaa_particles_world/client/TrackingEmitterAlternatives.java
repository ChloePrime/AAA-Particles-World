package cn.chloeprime.aaa_particles_world.client;

import cn.chloeprime.aaa_particles_world.client.content.CritEffek;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;

public final class TrackingEmitterAlternatives {
    public static void onCreatingTrackingEmitter(Entity target, ParticleOptions particleData, Runnable canceller) {
        if (particleData == ParticleTypes.CRIT && CritEffek.isEnabled()) {
            CritEffek.playCritEffek(target);
        } else {
            return;
        }
        canceller.run();
    }

    private TrackingEmitterAlternatives() {
    }
}
