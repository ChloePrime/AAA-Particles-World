package cn.chloeprime.aaa_particles_world.client;

import cn.chloeprime.aaa_particles_world.client.content.AtmosphereEffekManager;
import mod.chloeprime.aaaparticles.client.installer.NativePlatform;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.Minecraft;

public class AAAParticlesWorldClient {
    public static boolean isEffekEnabled() {
        boolean badOS = NativePlatform.isRunningOnUnsupportedPlatform();
        return !badOS && Minecraft.getInstance().options.graphicsMode().get() != GraphicsStatus.FABULOUS;
    }

    public static void init() {
        AtmosphereEffekManager.init();
    }
}
