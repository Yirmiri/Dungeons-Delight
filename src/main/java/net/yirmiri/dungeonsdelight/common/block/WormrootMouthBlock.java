package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.List;

public class WormrootMouthBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(1, 2, 1, 15, 16, 15);

    public static final BooleanProperty FULL = BooleanProperty.create("full");

    public WormrootMouthBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(FULL, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(FULL);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (state.getValue(FULL) && level.getRawBrightness(pos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(level, pos, state, source.nextInt(5) == 0)) {
            BlockState blockstate = state.setValue(FULL, false);
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
            ForgeHooks.onCropsGrowPost(level, pos, state);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        ResourceLocation lootTableId;

        if (!level.isClientSide && heldItem.getFoodProperties(player) != null && !state.getValue(FULL)) {
            if (heldItem.is(DDTags.ItemT.MONSTER_FOODS) && !(heldItem.is(DDTags.ItemT.WORMMOUTH_BLACKLIST))) {
                lootTableId = new ResourceLocation(DungeonsDelight.MOD_ID, "gameplay/preferred_food");
            } else lootTableId = new ResourceLocation(DungeonsDelight.MOD_ID, "gameplay/disliked_food");

            LootParams.Builder builder = new LootParams.Builder((ServerLevel) level).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos)).withParameter(LootContextParams.THIS_ENTITY, player);
            List<ItemStack> lootData = level.getServer().getLootData().getLootTable(lootTableId).getRandomItems(builder.create(LootContextParamSets.EMPTY));

            if (!lootData.isEmpty()) {
                Containers.dropItemStack(level, pos.getX(), pos.getY() - 0.5, pos.getZ(), lootData.get(level.random.nextInt(lootData.size())));
            }

            if (heldItem.hasCraftingRemainingItem()) {
                Containers.dropItemStack(level, pos.getX(), pos.getY() - 0.5, pos.getZ(), new ItemStack(heldItem.getCraftingRemainingItem().getItem()));
            }

            if (!player.isCreative()) {
                heldItem.shrink(1);
            }

            level.playSound(null, pos, DDSounds.MONSTER_YAM_HURT.get(), SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            if (level.random.nextIntBetweenInclusive(1, 3) == 3) {
                BlockState blockstate = state.setValue(FULL, true);
                level.setBlock(pos, blockstate, 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FULL);
    }
}
