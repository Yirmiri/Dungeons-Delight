package net.yirmiri.dungeonsdelight.common.block.entity.cleaving_board;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.common.resources.cleaving_board.CleavingBoardMappings;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.Objects;

public class CleavingBoardBlockEntity extends BlockEntity implements ContainerSingleItem {
    private ItemStack stack = ItemStack.EMPTY;

    public CleavingBoardBlockEntity(BlockPos pos, BlockState blockState) {
        super(DDBlockEntities.CLEAVING_BOARD.get(), pos, blockState);
    }

    public void tryCleaving(CleaverEntity cleaver, ServerLevel level, ServerPlayer player, BlockState state) {
        if (!this.stack.isEmpty()) {
            Pair<ResourceLocation, Integer> pair = CleavingBoardMappings.test(this.stack);

            if (pair != null) {
                BlockPos pos = this.getBlockPos();
                level.playSound(null, this.getBlockPos(), DDSounds.CLEAVER_CLEAVE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

                Direction rel = state.getValue(CleavingBoardBlock.FACING);
                ItemParticleOption aprtx1 = new ItemParticleOption(ParticleTypes.ITEM, this.stack);
                level.sendParticles(
                        aprtx1,
                        pos.getX() + 0.5 - (rel.getStepX() * 0.3),
                        pos.getY() + 0.5 - (rel.getStepY() * 0.3),
                        pos.getZ() + 0.5 - (rel.getStepZ() * 0.3),
                        5, 0.2D, 0.1D, 0.2D, 0.02D);

            } else {
                level.playSound(null, this.getBlockPos(), DDSounds.CLEAVING_BOARD_REMOVE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            this.setChanged();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!stack.isEmpty()) {
            tag.put("item", this.stack.save(new CompoundTag()));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("item")) {
            stack = ItemStack.of(tag.getCompound("item"));
        } else {
            stack = ItemStack.EMPTY;
        }
    }

    @Override public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }
    @Override public Packet<ClientGamePacketListener> getUpdatePacket() { return ClientboundBlockEntityDataPacket.create(this);}

    @Override public ItemStack removeItem(int i, int i1) {
        ItemStack stack2 = Objects.requireNonNullElse(this.stack, ItemStack.EMPTY);
        this.stack = ItemStack.EMPTY;
        return stack2;
    }

    public boolean canInsert() {
        return stack == ItemStack.EMPTY;
    }

    @Override public ItemStack getItem(int i) { return this.stack; }
    @Override public void setItem(int i, ItemStack itemStack) { this.stack = itemStack; }
    @Override public boolean stillValid(Player player) { return false; }
    @Override public boolean canPlaceItem(int index, ItemStack stack) { return false; }
    @Override public boolean canTakeItem(Container target, int index, ItemStack stack) { return false; }
}