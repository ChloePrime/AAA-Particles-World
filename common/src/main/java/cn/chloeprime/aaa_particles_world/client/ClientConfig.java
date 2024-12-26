package cn.chloeprime.aaa_particles_world.client;


import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue ENABLE_LIGHTNING;
    public static final ModConfigSpec.BooleanValue ENABLE_EXPLOSION;

    static {
        var builder = new ModConfigSpec.Builder();

        ENABLE_LIGHTNING = builder
                .comment("Whether to replace lightining renderer with Effekseer effect")
                .define("enable_lightning", true);

        ENABLE_EXPLOSION = builder
                .comment("Whether to replace explosion particles with Effekseer effect")
                .define("enable_explosion", true);

        SPEC = builder.build();
    }
}
