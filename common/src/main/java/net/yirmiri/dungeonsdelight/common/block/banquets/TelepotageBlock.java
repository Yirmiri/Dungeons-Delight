package net.yirmiri.dungeonsdelight.common.block.banquets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.common.util.data.HomewardData;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.stream.Stream;

public class TelepotageBlock extends BanquetBlock { //todo finish banquet and make telepotage/ender pearls not use the item when clicked on block
    private static final VoxelShape SHAPE = Stream.of(Block.box(2, 0, 2, 14, 2, 14), Block.box(1, 2, 1, 3, 6, 15), Block.box(3, 2, 1, 15, 6, 3), Block.box(3, 1, 3, 13, 4, 13), Block.box(3, 2, 13, 13, 6, 15), Block.box(13, 2, 3, 15, 6, 15), Block.box(6, 1, 0, 10, 5, 2), Block.box(6, 1, 14, 10, 5, 16), Block.box(0, 1, 6, 2, 5, 10), Block.box(14, 1, 6, 16, 5, 10)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final BooleanProperty FULL = BooleanProperty.create("full");
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 3);

    public TelepotageBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(FULL, true)
                .setValue(SERVINGS, 3)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FULL);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) return super.use(state, level, pos, player, hand, hit);

        if (stack.is(Items.ENDER_PEARL) && !state.getValue(FULL)) {
            state.setValue(FULL, true);
            return InteractionResult.CONSUME;
        }

        if (stack.is(DDItems.TELEPOTAGE.get())) {
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
