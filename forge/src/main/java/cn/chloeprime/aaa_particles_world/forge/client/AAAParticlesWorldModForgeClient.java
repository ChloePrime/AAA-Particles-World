package cn.chloeprime.aaa_particles_world.forge.client;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = AAAParticlesWorldMod.MOD_ID, dist = Dist.CLIENT)
public final class AAAParticlesWorldModForgeClient {
    public AAAParticlesWorldModForgeClient(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        AAAParticlesWorldClient.init();
    }
}
