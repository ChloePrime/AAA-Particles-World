package cn.chloeprime.aaa_particles_world.client;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LightningEffek {
    public static final ResourceLocation LIGHTNING_EFFEK = AAAParticlesWorldMod.loc("lightning");

    public static void playLightningEffek(Entity bolt) {
        var info = ParticleEmitterInfo.create(bolt.level(), LIGHTNING_EFFEK).position(bolt.position());
        AAALevel.addParticle(bolt.level(), true, info);
    }
}
