package cn.chloeprime.aaa_particles_world.client;


import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue ENABLE_CRIT;
    public static final ModConfigSpec.BooleanValue ENABLE_MAGIC_CRIT;
    public static final ModConfigSpec.BooleanValue ENABLE_LIGHTNING;
    public static final ModConfigSpec.BooleanValue ENABLE_SMALL_EXPLOSION;
    public static final ModConfigSpec.BooleanValue ENABLE_BIG_EXPLOSION;
    public static final ModConfigSpec.BooleanValue ENABLE_LOOT_BEAM;
    public static final ModConfigSpec.BooleanValue ENABLE_LOOT_SOUND;
    public static final ModConfigSpec.BooleanValue ENABLE_FIREBALL_TRAIL;
    public static final ModConfigSpec.BooleanValue ENABLE_FIREFLIES;

    // MC26.1 stuff
    public static final ModConfigSpec.BooleanValue MC26_1_REMOVE_POOF_FOR_SMALL_EXPLOSION;
    public static final ModConfigSpec.BooleanValue MC26_1_REMOVE_POOF_FOR_LARGE_EXPLOSION;

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

        builder.push("poofs");
        {
            MC26_1_REMOVE_POOF_FOR_SMALL_EXPLOSION = builder
                    .comment("""
                            [Since Minecraft 26.1.2]
                            Whether to remove white smoke for small explosions. Requires enable_small_explosion set to true.""")
                    .define("remove_poof_for_small_explosion", true);

            MC26_1_REMOVE_POOF_FOR_LARGE_EXPLOSION = builder
                    .comment("""
                            [Since Minecraft 26.1.2]
                            Whether to remove white smoke for large explosions. Requires enable_big_explosion set to true.""")
                    .define("remove_poof_for_big_explosion", true);
        }
        builder.pop();

        SPEC = builder.build();
    }
}
