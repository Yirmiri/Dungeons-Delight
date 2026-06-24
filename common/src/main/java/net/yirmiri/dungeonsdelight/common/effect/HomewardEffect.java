package net.yirmiri.dungeonsdelight.common.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.Optional;

public class HomewardEffect extends PureMonsterEffect {
    public HomewardEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (!living.level().isClientSide()) {
            if (living instanceof ServerPlayer player && !living.isSpectator()) {
                MobEffectInstance effect = player.getEffect(this);

                if (effect != null && effect.getDuration() == 1) {
                    if (player.getRespawnPosition() != null) {
                        ServerLevel respawnLevel = player.server.getLevel(player.getRespawnDimension());

                        if (respawnLevel == null) {
                            player.displayClientMessage(Component.translatable("tooltip.dungeonsdelight.homeward.no_spawn"), true);
                            return;
                        }

                        Optional<Vec3> respawnPos = Player.findRespawnPositionAndUseSpawnBlock(respawnLevel, player.getRespawnPosition(),
                                player.getRespawnAngle(), player.isRespawnForced(), false);

                        if (respawnPos.isPresent()) {
                            Vec3 pos = respawnPos.get();

                            if (respawnLevel != player.level()) {
                                if (DungeonsDelight.CONFIG.getHomewardCrossDimensional()) {
                                    player.teleportTo(respawnLevel, pos.x, pos.y, pos.z, player.getYRot(), player.getXRot());
                                } else {
                                    player.displayClientMessage(Component.translatable("tooltip.dungeonsdelight.homeward.no_spawn_point_in_dimension"), true);
                                }
                            } else {
                                player.teleportTo(pos.x, pos.y, pos.z);
                                respawnLevel.playSound(player, player.getRespawnPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F); //todo arty sound
                            }
                        } else {
                            player.displayClientMessage(Component.translatable("tooltip.dungeonsdelight.homeward.no_spawn"), true);
                        }
                    } else {
                        player.displayClientMessage(Component.translatable("tooltip.dungeonsdelight.homeward.no_spawn"), true);
                    }
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration == 1;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getDescriptionId()).withStyle(style -> style.withColor(DDUtil.MONSTER_COLOR));
    }
}
