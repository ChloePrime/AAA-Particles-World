package cn.chloeprime.aaa_particles_world.forge;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.forge.client.AAAParticlesWorldModForgeClient;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforgespi.Environment;

@Mod(AAAParticlesWorldMod.MOD_ID)
public final class AAAParticlesWorldModForge {
    public AAAParticlesWorldModForge() {
        AAAParticlesWorldMod.init();

        if (Environment.get().getDist().isClient()) {
            AAAParticlesWorldModForgeClient.onInitializeClient(ModLoadingContext.get());
        }
    }
}
