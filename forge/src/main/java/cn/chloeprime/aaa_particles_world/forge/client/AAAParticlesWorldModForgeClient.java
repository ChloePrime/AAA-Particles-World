package cn.chloeprime.aaa_particles_world.forge.client;

import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;

public final class AAAParticlesWorldModForgeClient {
    public static void onInitializeClient(ModLoadingContext context) {
        context.getActiveContainer().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }
}
