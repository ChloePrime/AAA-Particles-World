package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class CritEffek {
    public static final ResourceLocation CRIT_EFFEK = AAAParticlesWorldMod.loc("essentials/critical");
    public static final float INTRINSIC_SCALE = 10;
    public static final float TARGET_SIZE = 2;

    public static boolean isEnabled() {
        return ClientConfig.ENABLE_CRIT.get() && AAAParticlesWorldClient.isEffekEnabled();
    }

    public static void playCritEffek(Entity victim) {
        var info = ParticleEmitterInfo.create(victim.level(), CRIT_EFFEK)
                .position(victim.getBoundingBox().getCenter())
                .scale(TARGET_SIZE / INTRINSIC_SCALE);
        AAALevel.addParticle(victim.level(), 128, info);
    }
}
