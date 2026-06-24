package net.yirmiri.dungeonsdelight.common.block.banquets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.yirmiri.dungeonsdelight.common.util.data.HomewardData;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

public class TelepotageBlock extends BanquetBlock {
    public TelepotageBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide() && stack.is(DDItems.TELEPOTAGE.get())) {
            ((HomewardData) player).setHomewardPos(pos);
            ((HomewardData) player).setHomewardDimension(level.dimension());

            player.displayClientMessage(Component.translatable("tooltip.dungeonsdelight.homeward.bound"), false);
            return InteractionResult.SUCCESS;
        }
        else return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;

            for (ServerPlayer serverPlayer : serverLevel.getServer().getPlayerList().getPlayers()) {
                HomewardData data = (HomewardData) serverPlayer;

                if (pos.equals(data.getHomewardPos()) && level.dimension().equals(data.getHomewardDimension())) {
                    data.setHomewardPos(null);
                    data.setHomewardDimension(null);
                }
            }
        }
        super.playerWillDestroy(level, pos, state, player);
    }
}
