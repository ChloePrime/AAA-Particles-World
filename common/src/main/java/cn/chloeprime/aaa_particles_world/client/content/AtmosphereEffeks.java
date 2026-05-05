package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.common.PlatformMethods;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public final class AtmosphereEffeks {
    public static final AtmosphereEffek FIREFLIES = new AtmosphereEffek(AAAParticlesWorldMod.loc("atmosphere/fireflies")) {
        public static final int TIME_NIGHT_BEGIN = 13000;
        public static final int TIME_NIGHT_END = 23500;

        @Override
        public boolean isEnabled(Minecraft mc) {
            var player = mc.player;
            if (player == null) {
                return false;
            }
            return super.isEnabled(mc) && isInNight(player) && !isWorldRaining(player) && isInSwamp(player);
        }

        public boolean isInNight(Entity entity) {
            var dayTime = entity.level().getDefaultClockTime() % 24000L;
            return dayTime >= TIME_NIGHT_BEGIN && dayTime < TIME_NIGHT_END;
        }

        public boolean isInSwamp(Entity entity) {
            return entity.level().getBiome(entity.blockPosition()).is(PlatformMethods.get().getSwampTag());
        }

        public boolean isWorldRaining(Entity entity) {
            return entity.level().isRaining();
        }
    };
}
