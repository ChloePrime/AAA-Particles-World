package cn.chloeprime.aaa_particles_world.forge;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.forge.client.AAAParticlesWorldModForgeClient;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;

@Mod(AAAParticlesWorldMod.MOD_ID)
public final class AAAParticlesWorldModForge {
    public AAAParticlesWorldModForge() {
        AAAParticlesWorldMod.init();

        if (FMLLoader.getCurrent().getDist().isClient()) {
            AAAParticlesWorldModForgeClient.onInitializeClient(ModLoadingContext.get());
        }
    }
}
