package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import cn.chloeprime.aaa_particles_world.client.EffekParticle;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ExplosionEffek {
    public record Type(
            ResourceLocation effekId,
            float intrinsicRadius
    ) {
        public static final Type BIG = new Type(BIG_EXPLOSION_EFFEK, 3.5F);
        public static final Type SMALL = new Type(SMALL_EXPLOSION_EFFEK, 3);
    }

    public static final float LARGE_VANILLA_EXPLOSION_SIZE = 5;
    public static final float SMALL_VANILLA_EXPLOSION_SIZE = 1;

    public static final ResourceLocation BIG_EXPLOSION_EFFEK = AAAParticlesWorldMod.loc("explosion");
    public static final ResourceLocation SMALL_EXPLOSION_EFFEK = AAAParticlesWorldMod.loc("explosion_small");

    public static boolean isEnabled() {
        return ClientConfig.ENABLE_EXPLOSION.get() && AAAParticlesWorldClient.isEffekEnabled();
    }

    public static void playExplosion(Type type, Level level, double x, double y, double z, float radius) {
        var info = ParticleEmitterInfo.create(level, type.effekId())
                .position(x, y, z)
                .scale(radius / type.intrinsicRadius());
        AAALevel.addParticle(level, false, info);
    }

    public static Particle createParticleWrapper(
            Type type, ClientLevel level,
            double x, double y, double z,
            double dx, double dy, double dz,
            float radius
    ) {
        var scale = radius / type.intrinsicRadius();
        var particle = new EffekParticle(level, type.effekId(), x, y, z, dx, dy, dz);
        if (particle.getEmitter().isPresent()) {
            particle.getEmitter().get().setScale(scale, scale, scale);
        }
        return particle;
    }
}
