package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ExplosionEffek {
    public enum Type {
        BIG,
        SMALL,
    }
    public static final ResourceLocation BIG_EXPLOSION_EFFEK = AAAParticlesWorldMod.loc("explosion");
    public static final ResourceLocation SMALL_EXPLOSION_EFFEK = AAAParticlesWorldMod.loc("explosion_small");
    public static final float INTRINSIC_RADIUS_OF_BIG_EXPLOSION = 3.5F;
    public static final float INTRINSIC_RADIUS_OF_SMALL_EXPLOSION = 3;

    public static boolean isEnabled() {
        return ClientConfig.ENABLE_EXPLOSION.get() && !AAAParticlesWorldClient.isEffeksDisabled();
    }

    public static void playExplosion(Type type, Level level, double x, double y, double z, float radius) {
        var info = switch (type) {
            case BIG -> ParticleEmitterInfo.create(level, BIG_EXPLOSION_EFFEK)
                    .position(x, y, z)
                    .scale(radius / INTRINSIC_RADIUS_OF_BIG_EXPLOSION);
            case SMALL -> ParticleEmitterInfo.create(level, SMALL_EXPLOSION_EFFEK)
                    .position(x, y, z)
                    .scale(radius / INTRINSIC_RADIUS_OF_SMALL_EXPLOSION);
        };
        AAALevel.addParticle(level, false, info);
    }
}
