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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;
import net.yirmiri.dungeonsdelight.common.resources.cleaving_board.CleavingBoardMappings;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDCriteriaTriggers;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CleavingBoardBlockEntity extends BlockEntity implements ContainerSingleItem {
    private ItemStack stack = ItemStack.EMPTY;

    public CleavingBoardBlockEntity(BlockPos pos, BlockState blockState) {
        super(DDBlockEntities.CLEAVING_BOARD.get(), pos, blockState);
    }

    public void tryCleaving(CleaverEntity cleaver, Level level, Player player, BlockState state) {
        if (!this.stack.isEmpty() && (cleaver.getDeltaMovement().length() > DungeonsDelight.CONFIG.cleaverVelocityForCleavingBoard.getValue())) {
            Pair<ResourceLocation, Integer> pair = CleavingBoardMappings.test(this.stack);

            if (pair != null) {
                BlockPos pos = this.getBlockPos();
                level.playSound(null, this.getBlockPos(), DDSounds.CLEAVER_CLEAVE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

                if (level instanceof ServerLevel server) {
                    Direction rel = state.getValue(CleavingBoardBlock.FACING);
                    ItemParticleOption aprtx1 = new ItemParticleOption(ParticleTypes.ITEM, this.stack.getItem().getDefaultInstance());
                    server.sendParticles(
                            aprtx1,
                            pos.getX() + 0.5 - (rel.getStepX() * 0.3),
                            pos.getY() + 0.5 - (rel.getStepY() * 0.3),
                            pos.getZ() + 0.5 - (rel.getStepZ() * 0.3),
                            5, 0.2D, 0.1D, 0.2D, 0.02D
                    );

                    LootTable lootTable = server.getServer().getLootData().getLootTable(pair.getFirst());
                    int expBase = pair.getSecond();
                    int times = this.stack.getCount();

                    int expTotal = 0;
                    List<ItemStack> stacks = new ArrayList<>();

                    LootParams lootparams = new LootParams.Builder(server)
                            .withParameter(LootContextParams.ORIGIN, pos.getCenter())
                            .withParameter(LootContextParams.TOOL, cleaver.getCleaverStack())
                            .withParameter(LootContextParams.THIS_ENTITY, player)
                            .create(LootContextParamSets.FISHING);

                    for (int i = 0; i < times; ++i) {
                        expTotal += expBase;
                        lootTable.getRandomItems(lootparams, (itemStack) -> {
                            boolean canStack = true;
                            int c1 = itemStack.getCount();

                            for (ItemStack stk : stacks) {
                                if (ItemStack.isSameItemSameTags(stk, itemStack) && (c1 + stk.getCount() < stk.getMaxStackSize())) {
                                    canStack = false;
                                    stk.grow(c1);
                                    break;
                                }
                            }

                            if (canStack) stacks.add(itemStack);
                        });
                    }

                    Vec3 pos1 = pos.getCenter();
                    for (ItemStack stk : stacks) {
                        ItemEntity itementity = new ItemEntity(level, pos1.x(), pos1.y() , pos1.z(), stk);
                        itementity.setDefaultPickUpDelay();
                        level.addFreshEntity(itementity);
                    }

                    if (player instanceof ServerPlayer player2) DDCriteriaTriggers.CLEAVING_BOARD.trigger(player2);
                }

                this.setFirstItem(ItemStack.EMPTY);
                this.setChanged();
            }
            //else {
            //    level.playSound(null, this.getBlockPos(), DDSounds.CLEAVING_BOARD_REMOVE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            //
            //    if (level instanceof ServerLevel server) {
            //        Vec3 pos = this.getBlockPos().getCenter();
            //
            //        ItemEntity itementity = new ItemEntity(level, pos.x(), pos.y() , pos.z(), this.stack);
            //        itementity.setDefaultPickUpDelay();
            //        level.addFreshEntity(itementity);
            //    }
            //
            //    this.setFirstItem(ItemStack.EMPTY);
            //    this.setChanged();
            //}
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