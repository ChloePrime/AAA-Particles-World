package cn.chloeprime.aaa_particles_world.client;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue ENABLE_LIGHTNING;
    public static final ForgeConfigSpec.BooleanValue ENABLE_EXPLOSION;
    public static final ForgeConfigSpec.BooleanValue ENABLE_LOOT_BEAM;
    public static final ForgeConfigSpec.BooleanValue ENABLE_LOOT_SOUND;

    static {
        var builder = new ForgeConfigSpec.Builder();

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


        SPEC = builder.build();
    }
}
