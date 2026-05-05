package cn.chloeprime.aaa_particles_world;

import cn.chloeprime.aaa_particles_world.common.ModSounds;
import net.minecraft.resources.Identifier;

public final class AAAParticlesWorldMod {
    public static final String MOD_ID = "aaa_particles_world";

    public static void init() {
        ModSounds.init();
    }

    public static Identifier loc(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
