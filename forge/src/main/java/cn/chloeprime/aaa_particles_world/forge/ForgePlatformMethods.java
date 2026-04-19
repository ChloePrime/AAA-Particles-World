package cn.chloeprime.aaa_particles_world.forge;

import cn.chloeprime.aaa_particles_world.common.PlatformMethods;
import com.google.auto.service.AutoService;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.Tags;

@AutoService(PlatformMethods.class)
public class ForgePlatformMethods implements PlatformMethods {
    @Override
    public TagKey<Biome> getSwampTag() {
        return Tags.Biomes.IS_SWAMP;
    }
}
