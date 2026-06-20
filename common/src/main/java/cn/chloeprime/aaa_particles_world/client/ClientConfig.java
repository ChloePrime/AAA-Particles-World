package cn.chloeprime.aaa_particles_world.client;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue ENABLE_CRIT;
    public static final ForgeConfigSpec.BooleanValue ENABLE_MAGIC_CRIT;
    public static final ForgeConfigSpec.BooleanValue ENABLE_LIGHTNING;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SMALL_EXPLOSION;
    public static final ForgeConfigSpec.BooleanValue ENABLE_BIG_EXPLOSION;
    public static final ForgeConfigSpec.BooleanValue ENABLE_LOOT_BEAM;
    public static final ForgeConfigSpec.BooleanValue ENABLE_LOOT_SOUND;
    public static final ForgeConfigSpec.BooleanValue ENABLE_FIREBALL_TRAIL;
    public static final ForgeConfigSpec.BooleanValue ENABLE_FIREFLIES;

    public static final ForgeConfigSpec.IntValue MAX_LIGHTNING_COUNT;
    public static final ForgeConfigSpec.IntValue MAX_SMALL_EXPLOSION_COUNT;
    public static final ForgeConfigSpec.IntValue MAX_LARGE_EXPLOSION_COUNT;
    public static final ForgeConfigSpec.IntValue MAX_LOOT_BEAM_COUNT;

    static {
        var builder = new ForgeConfigSpec.Builder();

        ENABLE_CRIT = builder
                .comment("Whether to replace critical particles with Effekseer effect")
                .define("enable_crit", true);

        ENABLE_MAGIC_CRIT = builder
                .comment("Whether to replace magical crit particles with Effekseer effect")
                .define("enable_magical_crit", true);

        ENABLE_LIGHTNING = builder
                .comment("Whether to replace lightining renderer with Effekseer effect")
                .define("enable_lightning", true);

        ENABLE_SMALL_EXPLOSION = builder
                .comment("Whether to replace small explosion particles with Effekseer effect")
                .define("enable_small_explosion", true);

        ENABLE_BIG_EXPLOSION = builder
                .comment("""
                        Whether to replace big explosion particles with Effekseer effect.
                        If false, big explosion will be a composite of multiple small explosions.
                        To let vanilla explode partile show, you should turn off `enable_small_explosion` too.""")
                .define("enable_big_explosion", true);

        ENABLE_LOOT_BEAM = builder
                .comment("Whether to play a beam on item entities")
                .define("enable_loot_beam", true);

        ENABLE_LOOT_SOUND = builder
                .comment("""
                        Whether to play a sound when a loot with beam effek drops on ground,
                        Requires "enable_loot_beam" enabled""")
                .define("enable_loot_sound", true);

        ENABLE_FIREBALL_TRAIL = builder
                .comment("Whether to add a trail effek for (red) fireballs")
                .define("enable_fireball_trail", true);

        ENABLE_FIREFLIES = builder
                .comment("Whether to add visual-only fireflies in swamp biomes at night")
                .define("enable_fireflies", true);

        builder.push("limitation");
        {
            MAX_LIGHTNING_COUNT = builder
                    .comment("""
                            [Since 2.0.1]
                            Max amount of lightning effeks that can play at one time""")
                    .defineInRange("max_lightning_emitters", 8, 0, Integer.MAX_VALUE);

            MAX_SMALL_EXPLOSION_COUNT = builder
                    .comment("""
                            [Since 2.0.1]
                            Max amount of small explosion effeks that can play at one time""")
                    .defineInRange("max_small_explosion_emitters", 240, 0, Integer.MAX_VALUE);

            MAX_LARGE_EXPLOSION_COUNT = builder
                    .comment("""
                            [Since 2.0.1]
                            Max amount of big explosion effeks that can play at one time""")
                    .defineInRange("max_big_explosion_emitters", 120, 0, Integer.MAX_VALUE);

            MAX_LOOT_BEAM_COUNT = builder
                    .comment("""
                            [Since 2.0.1]
                            Max amount of loot beam effeks that can play at one time""")
                    .defineInRange("max_loot_beam_emitters", 1024, 0, Integer.MAX_VALUE);
        }
        builder.pop();

        SPEC = builder.build();
    }
}
