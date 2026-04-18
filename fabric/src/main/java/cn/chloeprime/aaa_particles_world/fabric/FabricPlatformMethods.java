package cn.chloeprime.aaa_particles_world.fabric;

import cn.chloeprime.aaa_particles_world.common.PlatformMethods;
import com.google.auto.service.AutoService;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

@AutoService(PlatformMethods.class)
public class FabricPlatformMethods implements PlatformMethods {
    @Override
    public TagKey<Biome> getSwampTag() {
        return ConventionalBiomeTags.SWAMP;
    }
}
