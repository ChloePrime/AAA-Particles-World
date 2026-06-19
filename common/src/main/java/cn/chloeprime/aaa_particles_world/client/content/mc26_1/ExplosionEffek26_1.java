package cn.chloeprime.aaa_particles_world.client.content.mc26_1;

import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import cn.chloeprime.aaa_particles_world.client.content.ExplosionEffek;

public class ExplosionEffek26_1 {
    public static boolean isRemovingPoofForSmallExplosion() {
        return ExplosionEffek.isReplacingSmallExplosion() && ClientConfig.MC26_1_REMOVE_POOF_FOR_SMALL_EXPLOSION.get();
    }

    public static boolean isRemovingPoofForLargeExplosion() {
        // 只替换小爆炸时，大爆炸会被分解为多个小爆炸的组合。
        // 此时也需要移除大爆炸的原版白烟。
        return ExplosionEffek.isEnabled() && ClientConfig.MC26_1_REMOVE_POOF_FOR_LARGE_EXPLOSION.get();
    }

    private ExplosionEffek26_1() {
    }
}
