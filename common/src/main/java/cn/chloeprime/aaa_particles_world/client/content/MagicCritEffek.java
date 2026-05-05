package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

public class MagicCritEffek {
    public static final Identifier MAGIC_CRIT_EFFEK = AAAParticlesWorldMod.loc("explosion_mini/blue");
    public static final float INTRINSIC_SCALE = 5;
    public static final float TARGET_SIZE = 1.5F;

    public static boolean isEnabled() {
        return ClientConfig.ENABLE_MAGIC_CRIT.get() && AAAParticlesWorldClient.isEffekEnabled();
    }

    public static void playMagicCritEffek(Entity victim) {
        var info = ParticleEmitterInfo.create(victim.level(), MAGIC_CRIT_EFFEK)
                .position(victim.getBoundingBox().getCenter())
                .scale(TARGET_SIZE / INTRINSIC_SCALE);
        AAALevel.addParticle(victim.level(), 128, info);
    }
}
