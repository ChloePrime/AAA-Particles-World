package cn.chloeprime.aaa_particles_world;

import net.minecraft.resources.ResourceLocation;

public final class AAAParticlesWorldMod {
    public static final String MOD_ID = "aaa_particles_world";

    public static void init() {
    }

    public static ResourceLocation loc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
