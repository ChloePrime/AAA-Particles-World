package cn.chloeprime.aaa_particles_world.client;


import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue ENABLE_CRIT;
    public static final ModConfigSpec.BooleanValue ENABLE_MAGIC_CRIT;
    public static final ModConfigSpec.BooleanValue ENABLE_LIGHTNING;
    public static final ModConfigSpec.BooleanValue ENABLE_EXPLOSION;
    public static final ModConfigSpec.BooleanValue ENABLE_LOOT_BEAM;
    public static final ModConfigSpec.BooleanValue ENABLE_LOOT_SOUND;
    public static final ModConfigSpec.BooleanValue ENABLE_FIREBALL_TRAIL;
    public static final ModConfigSpec.BooleanValue ENABLE_FIREFLIES;

    static {
        var builder = new ModConfigSpec.Builder();

        ENABLE_CRIT = builder
                .comment("Whether to replace critical particles with Effekseer effect")
                .define("enable_crit", true);

        ENABLE_MAGIC_CRIT = builder
                .comment("Whether to replace magical crit particles with Effekseer effect")
                .define("enable_magical_crit", true);

        ENABLE_LIGHTNING = builder
                .comment("Whether to replace lightining renderer with Effekseer effect")
                .define("enable_lightning", true);

        ENABLE_EXPLOSION = builder
                .comment("Whether to replace explosion particles with Effekseer effect")
                .define("enable_explosion", true);

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

        SPEC = builder.build();
    }
}
