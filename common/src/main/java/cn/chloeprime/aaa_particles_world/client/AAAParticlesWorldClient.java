package cn.chloeprime.aaa_particles_world.client;

import cn.chloeprime.aaa_particles_world.client.content.AtmosphereEffekManager;
import cn.chloeprime.aaa_particles_world.client.content.RocketTrailEffek;
import mod.chloeprime.aaaparticles.client.installer.NativePlatform;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ParticleStatus;

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
        return !badOS;
    }

    private static net.minecraft.server.level.ParticleStatus particleOption() {
        return Minecraft.getInstance().options.particles().get();
    }

    public static void init() {
        AtmosphereEffekManager.init();
        RocketTrailEffek.init();
    }
}
