package cn.chloeprime.aaa_particles_world.fabric.client;

import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.neoforged.fml.config.ModConfig;

import static cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod.MOD_ID;

public final class AAAParticlesWorldModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NeoForgeConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }
}
