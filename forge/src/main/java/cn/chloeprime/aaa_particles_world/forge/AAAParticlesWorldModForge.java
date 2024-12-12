package cn.chloeprime.aaa_particles_world.forge;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AAAParticlesWorldMod.MOD_ID)
public final class AAAParticlesWorldModForge {
    public AAAParticlesWorldModForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(AAAParticlesWorldMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        AAAParticlesWorldMod.init();
    }
}
