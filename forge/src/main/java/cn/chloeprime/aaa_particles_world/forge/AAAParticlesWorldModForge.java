package cn.chloeprime.aaa_particles_world.forge;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.forge.client.AAAParticlesWorldModForgeClient;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.Environment;

@Mod(AAAParticlesWorldMod.MOD_ID)
public final class AAAParticlesWorldModForge {
    public AAAParticlesWorldModForge() {
        EventBuses.registerModEventBus(AAAParticlesWorldMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        AAAParticlesWorldMod.init();

        if (Environment.get().getDist().isClient()) {
            AAAParticlesWorldModForgeClient.onInitializeClient(ModLoadingContext.get());
        }
    }
}
