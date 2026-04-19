package cn.chloeprime.aaa_particles_world.client;

import cn.chloeprime.aaa_particles_world.client.content.AtmosphereEffekManager;
import mod.chloeprime.aaaparticles.client.installer.NativePlatform;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;

public class AAAParticlesWorldClient {
    public static boolean isEffekReduced() {
        return particleOption() != ParticleStatus.ALL;
    }

    public static boolean isEffekEnabled() {
        // 粒子效果为最少时关闭 Effek
        if (particleOption() == ParticleStatus.MINIMAL) {
            return false;
        }
        boolean badOS = NativePlatform.isRunningOnUnsupportedPlatform();
        return !badOS && Minecraft.getInstance().options.graphicsMode().get() != GraphicsStatus.FABULOUS;
    }

    private static ParticleStatus particleOption() {
        return Minecraft.getInstance().options.particles().get();
    }

    public static void init() {
        AtmosphereEffekManager.init();
    }
}
