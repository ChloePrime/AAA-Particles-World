package cn.chloeprime.aaa_particles_world.forge.client;

import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public final class AAAParticlesWorldModForgeClient {
    public static void onInitializeClient(ModLoadingContext modLoadingContext) {
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }
}
