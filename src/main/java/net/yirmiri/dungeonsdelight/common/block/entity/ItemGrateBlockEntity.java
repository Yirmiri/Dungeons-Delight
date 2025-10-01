package net.yirmiri.dungeonsdelight.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

public class ItemGrateBlockEntity extends BlockEntity implements ItemGrateTooltip {
    private ItemStack stack = ItemStack.EMPTY;
    private float rotation;
    private boolean waxed;
    private boolean fast;
    private boolean large;

    public ItemGrateBlockEntity(BlockPos pos, BlockState state) {
        super(DDBlockEntities.ITEM_GRATE.get(), pos, state);
    }

    public void insertItem(Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);
        if (!held.isEmpty() && stack.isEmpty()) {
            stack = held.split(1);
            update();
        }
    }

    public void takeItem(Player player) {
        if (!stack.isEmpty()) {
            player.addItem(stack.copy());
            stack = ItemStack.EMPTY;
            update();
        }
    }

    public void dropItem(Level level, BlockPos pos) {
        if (!level.isClientSide && !stack.isEmpty()) {
            ItemStack drop = stack.copy();
            stack = ItemStack.EMPTY;
            Block.popResource(level, pos, drop);
        }
    }

    public void tick(BlockPos pos) {
        if (!isWaxed()) {
            rotation += 2F;
            if (rotation >= 360F) {
                rotation -= 360F;
            }
            setChanged();
        }

        if (!canInsert()) {
            RandomSource randomsource = level.getRandom();
            if (randomsource.nextBoolean()) {
                double d0 = (double) pos.getX() + randomsource.nextDouble();
                double d1 = (double) pos.getY() + randomsource.nextDouble();
                double d2 = (double) pos.getZ() + randomsource.nextDouble();
                level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 0.0, 0.0);
                //TODO: Default particle, will probably add more enhancements to let you customize particles
                level.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0, 0.0, 0.0);
            }
        }
    }

    public float getRotationSpeed(float partialTicks) {
        float baseRotation = rotation + partialTicks * 2F;
        return fast ? baseRotation * 2 : baseRotation;
    }

    public float getRotation() {
        return rotation;
    }

    public ItemStack getStack() {
        return stack;
    }

    public boolean canInsert() {
        return stack == ItemStack.EMPTY;
    }

    public boolean isWaxed() {
        return waxed;
    }

    public boolean isFast() {
        return fast;
    }

    public boolean isLarge() {
        return large;
    }

    public float getRenderScale() {
        if (large) {
            return 1.5F;
        } else return 1;
    }

    public void setWaxed(boolean newValue) {
        waxed = newValue;
        update();
    }

    public void setFast(boolean newValue) {
        fast = newValue;
        update();
    }

    public void setLarge(boolean newValue) {
        large = newValue;
        update();
    }

    public void update() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putFloat("rotation", rotation);
        tag.putBoolean("waxed", waxed);
        tag.putBoolean("fast", fast);
        tag.putBoolean("large", large);
        if (!stack.isEmpty()) {
            tag.put("item", this.stack.save(registries));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        rotation = tag.getFloat("rotation");
        waxed = tag.getBoolean("waxed");
        fast = tag.getBoolean("fast");
        large = tag.getBoolean("large");
        if (tag.contains("item")) {
            stack = ItemStack.parse(registries, tag.getCompound("item")).orElse(ItemStack.EMPTY);
        } else {
            stack = ItemStack.EMPTY;
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
