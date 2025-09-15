package net.yirmiri.dungeonsdelight.common.block;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDCriteriaTriggers;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.List;

public class WormouthBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(1, 2, 1, 15, 16, 15);

    public static final BooleanProperty FULL = BooleanProperty.create("full");
    public static final BooleanProperty COOLDOWN = BooleanProperty.create("cooldown");
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 3);

    public WormouthBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(FULL, false).setValue(COOLDOWN, false));
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
        if (state.getValue(FULL) && level.getRawBrightness(pos.above(), 0) >= 9 && CommonHooks.canCropGrow(level, pos, state, source.nextInt(5) == 0)) {
            BlockState blockstate = state.setValue(FULL, false);
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
            CommonHooks.fireCropGrowPost(level, pos, state);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack heldStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        int currentBites = state.getValue(BITES);
        ItemStack heldItem = player.getItemInHand(hand);
        ResourceLocation lootTableId;

        if (!level.isClientSide && heldItem.getFoodProperties(player) != null && !state.getValue(FULL) && !state.getValue(COOLDOWN)) {
            if (heldItem.is(DDTags.ItemT.MONSTER_FOODS) && heldItem.is(DDTags.ItemT.WORMOUTH_FAVORITES) && !heldItem.is(DDTags.ItemT.WORMOUTH_BLACKLIST)) {
                lootTableId = RunicLib.customid(DungeonsDelight.MOD_ID, "gameplay/preferred_food");
            } else lootTableId = RunicLib.customid(DungeonsDelight.MOD_ID, "gameplay/disliked_food");

            LootParams.Builder builder = new LootParams.Builder((ServerLevel) level).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos)).withParameter(LootContextParams.THIS_ENTITY, player);
            ResourceKey<LootTable> lootTableKey = ResourceKey.create(Registries.LOOT_TABLE, lootTableId);
            LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(lootTableKey);
            List<ItemStack> lootData = lootTable.getRandomItems(builder.create(LootContextParamSets.EMPTY), level.random.nextLong());

            if (!lootData.isEmpty()) {
                spitItemStack(level, pos.getX(), pos.getY() - 0.6, pos.getZ(), lootData.get(level.random.nextInt(lootData.size())));

                if (heldItem.hasCraftingRemainingItem()) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY() - 0.6, pos.getZ(), new ItemStack(heldItem.getCraftingRemainingItem().getItem()));
                }
            }

            if (!player.isCreative()) {
                heldItem.shrink(1);
            }

            if (player instanceof ServerPlayer serverPlayer) {
                DDCriteriaTriggers.FEED_WORMOUTH.get().trigger(serverPlayer);
            }

            level.playSound(null, pos, DDSounds.MONSTER_YAM_HURT.get(), SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            ((ServerLevel) level).sendParticles(ParticleTypes.POOF, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 5, 0.2D, 0.1D, 0.2D, 0.02D);

            if (!lootData.isEmpty()) {
                BlockState cooldownState;
                if (currentBites >= 3 && level.random.nextIntBetweenInclusive(1, 2) == 2) {
                    cooldownState = state.setValue(FULL, true).setValue(BITES, 0);
                } else {
                    cooldownState = state.setValue(COOLDOWN, true).setValue(BITES, Math.min(currentBites + 1, 3));
                    level.scheduleTick(pos, this, 10);
                }
                level.setBlock(pos, cooldownState, 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, cooldownState));
            }
            return ItemInteractionResult.CONSUME;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static void spitItemStack(Level level, double v, double v1, double v2, ItemStack stack) {
        double width = EntityType.ITEM.getWidth();
        double v3 = 1.0 - width;
        double v4 = width / 2.0;
        double v5 = Math.floor(v) + level.random.nextDouble() * v3 + v4;
        double v6 = Math.floor(v1) + level.random.nextDouble() * v3;
        double v7 = Math.floor(v2) + level.random.nextDouble() * v3 + v4;

        while(!stack.isEmpty()) {
            ItemEntity itemEntity = new ItemEntity(level, v5, v6, v7, stack.split(level.random.nextInt(21) + 10));
            itemEntity.setDeltaMovement(level.random.triangle(0.0, 0.005), 0, level.random.triangle(0.0, 0.005));
            level.addFreshEntity(itemEntity);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, state.setValue(COOLDOWN, false), 2);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FULL, COOLDOWN, BITES);
    }
}
