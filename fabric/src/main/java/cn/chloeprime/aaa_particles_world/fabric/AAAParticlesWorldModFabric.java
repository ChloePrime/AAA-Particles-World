package cn.chloeprime.aaa_particles_world.fabric;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import net.fabricmc.api.ModInitializer;

public final class AAAParticlesWorldModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        AAAParticlesWorldMod.init();
    }
}
