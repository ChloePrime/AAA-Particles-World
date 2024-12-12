package cn.chloeprime.aaa_particles_world.mixin;

import cn.chloeprime.aaa_particles_world.common.internal.EffekLightningBolt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LightningBolt.class)
public abstract class MixinLightningBolt extends Entity implements EffekLightningBolt {
    @Unique private boolean aaaParticles$effekTicket = true;

    @Unique
    @Override
    public boolean aaaParticles$getEffekTicket() {
        if (aaaParticles$effekTicket) {
            aaaParticles$effekTicket = false;
            return true;
        }
        return false;
    }

    public MixinLightningBolt(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }
}
