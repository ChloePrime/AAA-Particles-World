package cn.chloeprime.aaa_particles_world;

import cn.chloeprime.aaa_particles_world.common.ModSounds;
import net.minecraft.resources.ResourceLocation;

public final class AAAParticlesWorldMod {
    public static final String MOD_ID = "aaa_particles_world";

    public static void init() {
        ModSounds.init();
    }

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
