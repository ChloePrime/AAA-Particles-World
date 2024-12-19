package cn.chloeprime.aaa_particles_world.client;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue ENABLE_LIGHTNING;
    public static final ForgeConfigSpec.BooleanValue ENABLE_EXPLOSION;

    static {
        var builder = new ForgeConfigSpec.Builder();

        ENABLE_LIGHTNING = builder
                .comment("Whether to replace lightining renderer with Effekseer effect")
                .define("enable_lightning", true);

        ENABLE_EXPLOSION = builder
                .comment("Whether to replace explosion particles with Effekseer effect")
                .define("enable_explosion", true);

        SPEC = builder.build();
    }
}
