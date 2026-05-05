package cn.chloeprime.aaa_particles_world.fabric.client;

import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.neoforged.fml.config.ModConfig;

import static cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod.MOD_ID;

public final class AAAParticlesWorldModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.CLIENT, ClientConfig.SPEC);
        AAAParticlesWorldClient.init();
    }
}
