package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import cn.chloeprime.aaa_particles_world.common.ModSounds;
import cn.chloeprime.aaa_particles_world.util.BooleanField;
import net.minecraft.world.entity.item.ItemEntity;

public class LootDropSound {
    public static boolean isEnabled() {
        return LootBeamEffek.isEnabled() && ClientConfig.ENABLE_LOOT_SOUND.get();
    }

    /**
     * 防止丢到草径边上之类的场合，由于草径和周围完整方块细微的高度差
     * 而导致音效连续播放两次，所以给播放音效添加一个最小下落速度需求。
     */
    public static final double Y_SPEED_THRESHOLD = -0.1;

    @SuppressWarnings("ConstantValue")
    public static void tryPlay(ItemEntity item, BooleanField hasPlayed) {
        if (item.level() == null || !item.level().isClientSide) {
            return;
        }

        boolean onGround = item.onGround();
        if (hasPlayed.get()) {
            // 物品不再在地上时重置是否需要播放音效的flag
            if (!onGround && item.getDeltaMovement().y() <= Y_SPEED_THRESHOLD) {
                hasPlayed.set(false);
            }
        } else if (onGround) {
            hasPlayed.set(true);
            // 普通品质物品不播放音效
            if (LootBeamEffek.getColor(item.getItem()).isEmpty()) {
                return;
            }
            // 掉地上时播放音效
            item.level().playLocalSound(
                    item.getX(), item.getY(), item.getZ(),
                    ModSounds.LOOT_DROP.get(), item.getSoundSource(),
                    1, 1, false);
        }
    }
}
