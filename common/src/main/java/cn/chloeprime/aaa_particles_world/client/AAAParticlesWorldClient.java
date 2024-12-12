package cn.chloeprime.aaa_particles_world.client;

import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.Minecraft;

public class AAAParticlesWorldClient {
    public static boolean isEffeksEnabled() {
        return Minecraft.getInstance().options.graphicsMode().get() != GraphicsStatus.FABULOUS;
    }
}
