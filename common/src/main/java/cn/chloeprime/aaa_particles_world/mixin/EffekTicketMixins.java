package cn.chloeprime.aaa_particles_world.mixin;

import cn.chloeprime.aaa_particles_world.common.internal.EffekTicketHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({LightningBolt.class, ItemEntity.class})
public abstract class EffekTicketMixins extends Entity implements EffekTicketHolder {
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

    public EffekTicketMixins(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }
}
