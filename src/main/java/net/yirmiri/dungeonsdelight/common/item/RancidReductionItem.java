package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
import vectorwing.farmersdelight.common.block.TomatoVineBlock;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class RancidReductionItem extends DrinkableItem {
    public RancidReductionItem(Item.Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasPotionEffectTooltip, hasCustomTooltip);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof PotatoBlock potatoBlock && potatoBlock.isMaxAge(state)) {
            rotCrop(DDBlocks.ROTTEN_POTATOES.get(), level, pos, state, ctx);
            return InteractionResult.SUCCESS;
        } if (state.getBlock() instanceof TomatoVineBlock tomatoVineBlock && tomatoVineBlock.isMaxAge(state) && !state.getValue(TomatoVineBlock.ROPELOGGED)) {
            rotCrop(DDBlocks.ROTTEN_TOMATOES.get(), level, pos, state, ctx);
            return InteractionResult.SUCCESS;
        } else if (!(state.getBlock() instanceof TomatoVineBlock) && state.getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(state)) {
            rotCrop(DDBlocks.ROTTEN_CROP.get(), level, pos, state, ctx);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(ctx);
    }

    public void rotCrop(Block newBlock, Level level, BlockPos pos, BlockState state, UseOnContext ctx) {
        Player player = ctx.getPlayer();
        level.setBlock(pos, newBlock.defaultBlockState(), 11);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), DDSounds.MONSTER_YAM_AMBIENT.get(), SoundSource.BLOCKS, 1.0F, 0.75F);
        addRotParticles(level, pos, 5);

        if (player != null && !player.getAbilities().instabuild) {
            ctx.getItemInHand().shrink(1);

            if (!player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE))) {
                player.drop(new ItemStack(Items.GLASS_BOTTLE), false);
            }
        }
    }

    //taken from the BoneMealItem class
    public static void addRotParticles(LevelAccessor accessor, BlockPos pos, int i1) {
        if (i1 == 0) {
            i1 = 15;
        }

        BlockState blockstate = accessor.getBlockState(pos);
        if (!blockstate.isAir()) {
            double d0 = 0.5;
            double d1;
            if (blockstate.is(Blocks.WATER)) {
                i1 *= 3;
                d1 = 1.0;
                d0 = 3.0;
            } else if (blockstate.isSolidRender(accessor, pos)) {
                pos = pos.above();
                i1 *= 3;
                d0 = 3.0;
                d1 = 1.0;
            } else {
                d1 = blockstate.getShape(accessor, pos).max(Direction.Axis.Y);
            }

            accessor.addParticle(DDParticles.ROTTEN_GLINT.get(), (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 0.0, 0.0, 0.0);
            RandomSource randomsource = accessor.getRandom();

            for(int i = 0; i < i1; ++i) {
                double d2 = randomsource.nextGaussian() * 0.02;
                double d3 = randomsource.nextGaussian() * 0.02;
                double d4 = randomsource.nextGaussian() * 0.02;
                double d5 = 0.5 - d0;
                double d6 = (double)pos.getX() + d5 + randomsource.nextDouble() * d0 * 2.0;
                double d7 = (double)pos.getY() + randomsource.nextDouble() * d1;
                double d8 = (double)pos.getZ() + d5 + randomsource.nextDouble() * d0 * 2.0;
                if (!accessor.getBlockState(BlockPos.containing(d6, d7, d8).below()).isAir()) {
                    accessor.addParticle(DDParticles.ROTTEN_GLINT.get(), d6, d7, d8, d2, d3, d4);
                }
            }
        }
    }
}
