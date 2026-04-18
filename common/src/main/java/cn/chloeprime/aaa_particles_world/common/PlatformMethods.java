package cn.chloeprime.aaa_particles_world.common;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public interface PlatformMethods {
    static PlatformMethods get() {
        return PlatformMethodsImpl.INSTANCE;
    }

    TagKey<Biome> getSwampTag();
}
