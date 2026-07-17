package net.yirmiri.dungeonsdelight.common.effect;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.banquets.BanquetBlock;
import net.yirmiri.dungeonsdelight.common.block.banquets.TelepotageBlock;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.common.util.data.HomewardData;
import net.yirmiri.dungeonsdelight.core.registry.DDStats;

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
                    HomewardData data = (HomewardData) player;
                    ServerLevel targetLevel = player.server.getLevel(data.getHomewardDimension());

                    if (data.getHomewardPos() == null || data.getHomewardDimension() == null || targetLevel == null) {
                        player.displayClientMessage(Component.translatable("tooltip.dungeonsdelight.homeward.no_spawn"), false);
                        return;
                    }

                    BlockPos homewardBlockPos = data.getHomewardPos();
                    BlockState targetBlock = targetLevel.getBlockState(homewardBlockPos);

                    if (!(targetBlock.getBlock() instanceof TelepotageBlock)) {
                        player.displayClientMessage(Component.translatable("tooltip.dungeonsdelight.homeward.missing_telepotage")
                                .withStyle(ChatFormatting.RED), false);
                        return;
                    }

                    if (targetBlock.getBlock() instanceof TelepotageBlock && BanquetBlock.isEmpty(targetBlock) || !targetBlock.getValue(TelepotageBlock.FULL)) {
                        player.displayClientMessage(Component.translatable("tooltip.dungeonsdelight.homeward.empty_or_no_pearl"), false);
                        return;
                    }

                    Vec3 pos = new Vec3(homewardBlockPos.getX() + 0.5D, homewardBlockPos.getY() + 1.0D, homewardBlockPos.getZ() + 0.5D);

                    if (targetLevel != player.level()) {
                        if (DungeonsDelight.CONFIG.getHomewardCrossDimensional()) {
                            player.teleportTo(targetLevel, pos.x, pos.y, pos.z, player.getYRot(), player.getXRot());
                        } else {
                            player.displayClientMessage(Component.translatable("tooltip.dungeonsdelight.homeward.no_spawn_point_in_dimension"), false);
                        }
                    } else {
                        player.teleportTo(pos.x, pos.y, pos.z);
                        player.hurt(player.damageSources().fall(), 4);
                        targetLevel.playSound(player, homewardBlockPos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                        BanquetBlock.removeServing(targetLevel, homewardBlockPos, targetBlock);
                        TelepotageBlock.removePearl(targetLevel, homewardBlockPos, targetBlock);
                        player.awardStat(DDStats.HOMEWARD.get());
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
