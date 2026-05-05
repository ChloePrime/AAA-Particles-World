package cn.chloeprime.aaa_particles_world.mixin;

import cn.chloeprime.aaa_particles_world.client.content.RocketTrailEffek;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractHurtingProjectile.class)
public abstract class MixinAbstractHurtingProjectile extends Entity {
    @Override
    @Intrinsic
    public boolean isOnFire() {
        return super.isOnFire();
    }

    @Dynamic
    @ModifyReturnValue(method = {"isOnFire", "m_6060_", "method_5809"}, at = @At("RETURN"))
    public boolean isNotOnFireForEffekFireballs(boolean original) {
        if (level().isClientSide()) {
            if (RocketTrailEffek.isEnabledFor(this) && RocketTrailEffek.isEnabled()) {
                return false;
            }
        }
        return original;
    }

    @Inject(method = "createParticleTrail", at = @At("HEAD"), cancellable = true)
    private void disableTrailParticleForEffekedFireballs(CallbackInfo ci) {
        if (level().isClientSide()) {
            if (RocketTrailEffek.isEnabledFor(this) && RocketTrailEffek.isEnabled()) {
                ci.cancel();
            }
        }
    }

    @ApiStatus.Internal
    public MixinAbstractHurtingProjectile(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }
}
