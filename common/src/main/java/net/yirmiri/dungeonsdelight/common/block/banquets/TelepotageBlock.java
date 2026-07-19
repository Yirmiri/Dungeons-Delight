package net.yirmiri.dungeonsdelight.common.block.banquets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;

import java.util.stream.Stream;

public class TelepotageBlock extends BanquetBlock {
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 3);
    public static final BooleanProperty FULL = BooleanProperty.create("full");
    private static final VoxelShape SHAPE = Stream.of(Block.box(2, 0, 2, 14, 2, 14), Block.box(1, 2, 1, 3, 6, 15), Block.box(3, 2, 1, 15, 6, 3), Block.box(3, 1, 3, 13, 4, 13), Block.box(3, 2, 13, 13, 6, 15), Block.box(13, 2, 3, 15, 6, 15), Block.box(6, 1, 0, 10, 5, 2), Block.box(6, 1, 14, 10, 5, 16), Block.box(0, 1, 6, 2, 5, 10), Block.box(14, 1, 6, 16, 5, 10)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public TelepotageBlock(Properties properties) {
        super(DDItems.TELEPOTAGE, properties);
        registerDefaultState(defaultBlockState()
                .setValue(getServingsProperty(), getMaxServings())
                .setValue(FULL, true)
        );
    }

    @Override
    protected IntegerProperty getServingsProperty() {
        return SERVINGS;
    }

    @Override
    public int getMaxServings() {
        return 3;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(SERVINGS, FULL);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    public static void removePearl(Level level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(TelepotageBlock.FULL, false), 3);
    }

    @Override
    public boolean canTakeServing(BlockState state) {
        return !isEmpty(state) && !state.getValue(FULL);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        if (!state.getValue(FULL) && !isEmpty(state)) {
            if (level.isClientSide()) {
                if (stack.is(Items.ENDER_PEARL)) {
                    return InteractionResult.CONSUME;
                }
                return super.use(state, level, pos, player, hand, hit);
            }
            else if (stack.is(Items.ENDER_PEARL)) {
                level.setBlock(pos, state.setValue(FULL, true), 3);
                stack.shrink(1);
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.playSound(player, pos, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            }
        }

        if (player instanceof ServerPlayer serverPlayer && stack.is(DDTags.ItemT.HOMEWARD_FOODS)) {
            HomewardData data = (HomewardData) serverPlayer;

            data.setHomewardPos(pos);
            data.setHomewardDimension(level.dimension());

            player.displayClientMessage(Component.translatable("tooltip.dungeonsdelight.homeward.bound"), false);
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, hit);
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