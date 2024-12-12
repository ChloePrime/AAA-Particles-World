package cn.chloeprime.aaa_particles_world.client;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ExplosionEffek {
    public static final ResourceLocation LIGHTNING_EFFEK = AAAParticlesWorldMod.loc("explosion");
    public static final float INTRINSIC_RADIUS_OF_EFFEK = 3;

    public static void playExplosion(Level level, double x, double y, double z, float radius) {
        var info = ParticleEmitterInfo.create(level, LIGHTNING_EFFEK)
                .position(x, y, z)
                .scale(radius / INTRINSIC_RADIUS_OF_EFFEK);
        AAALevel.addParticle(level, false, info);
    }
}