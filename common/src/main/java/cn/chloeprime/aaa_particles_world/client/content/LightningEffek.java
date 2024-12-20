package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LightningEffek {
    public static final ResourceLocation LIGHTNING_EFFEK = AAAParticlesWorldMod.loc("lightning");

    public static boolean isEnabled() {
        return ClientConfig.ENABLE_LIGHTNING.get() && AAAParticlesWorldClient.isEffekEnabled();
    }

    public static void playLightningEffek(Entity bolt) {
        var info = ParticleEmitterInfo.create(bolt.level(), LIGHTNING_EFFEK).position(bolt.position());
        AAALevel.addParticle(bolt.level(), true, info);
    }
}
