package cn.chloeprime.aaa_particles_world.common;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

import static cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod.*;

public class ModSounds {
    private static final DeferredRegister<SoundEvent> REGISTRAR = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);
    public static final RegistrySupplier<SoundEvent> LOOT_DROP = REGISTRAR.register(
            "loot_drop", () -> SoundEvent.createVariableRangeEvent(loc("loot_drop"))
    );

    public static void init() {
        REGISTRAR.register();
    }
}
