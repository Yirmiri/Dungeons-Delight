package net.yirmiri.dungeonsdelight.common.block.entity;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.WormouthBlock;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappings;
import net.yirmiri.dungeonsdelight.core.init.DDLootTables;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.List;
import java.util.Objects;

public class WormouthBlockEntity extends BlockEntity implements ContainerSingleItem {
    private int cooldown = 0;
    private int digestTime = 0;
    private int tries = 3;
    private ResourceLocation nextTable;
    private boolean nextExhausts = false;
    private boolean nextWasPlayer = false;
    private ItemStack stack = ItemStack.EMPTY;

    public WormouthBlockEntity(BlockPos pos, BlockState blockState) {
        super(DDBlockEntities.WORMOUTH.get(), pos, blockState);
    }

    @Override public boolean canPlaceItem(int index, ItemStack stack) { return WormouthMappings.test(stack) != null && this.cooldown <= 0 && this.digestTime <= 0; }
    @Override public boolean canTakeItem(Container target, int index, ItemStack stack) { return false; }
    @Override public ItemStack getItem(int i) { return this.stack; }
    @Override public void setItem(int i, ItemStack itemStack) {
        this.stack = itemStack;
        Pair<ResourceLocation, Boolean> dxi = WormouthMappings.test(itemStack);
        if (dxi != null && this.tryEating(this.level, this.worldPosition, this.stack.getItem(), dxi.getFirst(), dxi.getSecond(), false)) {
            this.tryExtraDrop(this.level, this.worldPosition, this.stack);
            this.stack = ItemStack.EMPTY;
            this.setChanged();
        }
    }
    @Override public boolean stillValid(Player player) { return Container.stillValidBlockEntity(this, player); }

    @Override public ItemStack removeItem(int i, int i1) {
        ItemStack stack2 = Objects.requireNonNullElse(this.stack, ItemStack.EMPTY);
        this.stack = ItemStack.EMPTY;
        return stack2;
    }

    public void tick(ServerLevel server, BlockState state, BlockPos pos) {
        if (this.digestTime > -1) this.digestTime--;
        if (this.cooldown > -1 && server.getRawBrightness(pos.above(), 0) >= 9) this.cooldown--;

        if (this.cooldown == 0) {
            if (this.tries <= 0) this.tries = 3;
            server.setBlock(pos, server.getBlockState(pos).setValue(WormouthBlock.EATING, false), Block.UPDATE_ALL_IMMEDIATE);
        }

        if (this.digestTime == 0) {
            Direction rel = state.getValue(WormouthBlock.FACING);
            server.playSound(null, pos, DDSounds.WORMOUTH_OPEN.get(), SoundSource.BLOCKS,
                    1.0F,
                    1.0F + ((server.random.nextFloat() - 0.5F) * 0.5F));
            server.sendParticles(
                    ParticleTypes.POOF,
                    pos.getX() + 0.5 + (rel.getStepX() * 0.6),
                    pos.getY() + 0.5 + (rel.getStepY() * 0.6),
                    pos.getZ() + 0.5 + (rel.getStepZ() * 0.6),
                    5, 0.2D, 0.1D, 0.2D, 0.02D);

            if (this.nextTable != null) {
                this.spitItems(server, pos, rel, false);

                if (this.nextExhausts) {
                    if (this.nextWasPlayer) server.addFreshEntity(new ExperienceOrb(server, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, server.random.nextInt(4) + 1));

                    this.tries--;
                    if (this.tries <= 0 && server.random.nextIntBetweenInclusive(0, 1) == 1) {
                        this.cooldown = 3600;
                    }
                }
            }
        }
    }

    public boolean tryEating(Level level, BlockPos pos, Item item, ResourceLocation table, boolean exhaust, boolean isPlayer) {
        if (level instanceof ServerLevel server) {
            if (this.cooldown <= 0 && this.digestTime <= 0) {
                BlockState state = server.getBlockState(pos);
                this.nextTable = table;
                this.cooldown = 21;
                this.digestTime = 20;
                this.nextExhausts = exhaust;
                this.nextWasPlayer = isPlayer;
                server.setBlock(pos, state.setValue(WormouthBlock.EATING, true), Block.UPDATE_ALL_IMMEDIATE);

                server.playSound(null, pos, DDSounds.WORMOUTH_EAT.get(), SoundSource.BLOCKS,
                        1.0F,
                        1.0F + ((server.random.nextFloat() - 0.5F) * 0.5F));

                Direction rel = state.getValue(WormouthBlock.FACING);
                ItemParticleOption aprtx1 = new ItemParticleOption(ParticleTypes.ITEM, item.getDefaultInstance());
                server.sendParticles(
                        aprtx1,
                        pos.getX() + 0.5 + (rel.getStepX() * 0.6),
                        pos.getY() + 0.5 + (rel.getStepY() * 0.6),
                        pos.getZ() + 0.5 + (rel.getStepZ() * 0.6),
                        5, 0.2D, 0.1D, 0.2D, 0.02D);

                this.setChanged();
                return true;
            }
        }
        return false;
    }

    public void panic(Level level, BlockPos pos, BlockState state) {
        if (level instanceof ServerLevel serverLevel && this.cooldown <= -1 && this.digestTime <= -1) {
            this.nextTable = DDLootTables.WORMOUTH_GENERIC_PANIC;
            this.cooldown = 7200;
            this.digestTime = -1;
            this.nextWasPlayer = false;

            Direction dir = state.getOptionalValue(WormouthBlock.FACING).orElse(Direction.DOWN);

            this.spitItems(serverLevel, pos, dir, false);

            serverLevel.playSound(null, pos, DDSounds.WORMOUTH_PANIC.get(), SoundSource.BLOCKS,
                    1.0F,
                    1.0F + ((serverLevel.random.nextFloat() - 0.5F) * 0.5F));
            serverLevel.sendParticles(
                    ParticleTypes.POOF,
                    pos.getX() + 0.5 + (dir.getStepX() * 0.6),
                    pos.getY() + 0.5 + (dir.getStepY() * 0.6),
                    pos.getZ() + 0.5 + (dir.getStepZ() * 0.6),
                    5, 0.2D, 0.1D, 0.2D, 0.02D);

            serverLevel.setBlock(pos, state.setValue(WormouthBlock.EATING, true), Block.UPDATE_ALL_IMMEDIATE);
        }
    }

    public void tryExtraDrop(Level level, BlockPos pos, ItemStack stack) {
        // TODO: 1.21.1 removes bowlitem so check food remainder or something
        if (level instanceof ServerLevel server) {
            BlockState state = server.getBlockState(pos);
            Direction rel = state.getOptionalValue(WormouthBlock.FACING).orElse(Direction.DOWN);
            BlockPos goingto = pos.relative(rel, 2);

            ItemStack checkMe = null;
            if (stack.getItem() instanceof BowlFoodItem || stack.getItem() instanceof SuspiciousStewItem) checkMe = new ItemStack(Items.BOWL);
            else if (stack.getItem().getCraftingRemainingItem() != null) checkMe = new ItemStack(stack.getItem().getCraftingRemainingItem());

            if (checkMe != null) {
                Vec3 spawnP = new Vec3(
                        pos.getX() + 0.5 + (rel.getStepX() * 0.8),
                        pos.getY() + 0.5 + (rel.getStepY() * 0.8),
                        pos.getZ() + 0.5 + (rel.getStepZ() * 0.8)
                );
                ItemEntity itementity = new ItemEntity(
                        server,
                        spawnP.x() + ((server.random.nextFloat() - 0.5F) * 0.5F),
                        spawnP.y() + ((server.random.nextFloat() - 0.5F) * 0.5F),
                        spawnP.z() + ((server.random.nextFloat() - 0.5F) * 0.5F),
                        checkMe
                );

                double p0 = goingto.getX() - pos.getX();
                double p1 = goingto.getY() - pos.getY();
                double p2 = goingto.getZ() - pos.getZ();
                double p3 = 0.1;
                itementity.setDeltaMovement(p0 * p3, p1 * p3, p2 * p3);

                server.addFreshEntity(itementity);
            }
        }
    }

    public void emergencyDrop(Level level, BlockPos pos, BlockState state) {
        if (level instanceof ServerLevel server && this.nextTable != null && this.digestTime > 0) {
            this.spitItems(server, pos, Direction.DOWN, true);
        }
    }

    private void spitItems(ServerLevel server, BlockPos pos, Direction rel, boolean emergency) {
        LootParams lootparams = new LootParams.Builder(server).withParameter(LootContextParams.ORIGIN, pos.getCenter()).create(LootContextParamSets.CHEST);
        LootTable lootTable = server.getServer().getLootData().getLootTable(this.nextTable);
        List<ItemStack> list = lootTable.getRandomItems(lootparams);
        BlockPos goingto = pos.relative(rel, 2);

        this.nextTable = null;

        Vec3 spawnP = (emergency)
                ? new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ())
                : new Vec3(pos.getX() + 0.5 + (rel.getStepX() * 0.8), pos.getY() + 0.5 + (rel.getStepY() * 0.8), pos.getZ() + 0.5 + (rel.getStepZ() * 0.8));
        for (ItemStack stack : list) {
            float offsetY = (emergency) ? ((server.random.nextFloat() - 0.5F) * 0.5F) : 0;
            ItemEntity itementity = new ItemEntity(
                    server,
                    spawnP.x() + ((server.random.nextFloat() - 0.5F) * 0.5F),
                    spawnP.y() + offsetY,
                    spawnP.z() + ((server.random.nextFloat() - 0.5F) * 0.5F),
                    stack
            );

            if (emergency) {
                double evilNumber = 0.11485000171139836;
                itementity.setDeltaMovement(level.random.triangle(0.0, evilNumber), level.random.triangle(0.2, evilNumber), level.random.triangle(0.0, evilNumber));
            }
            else {
                double p0 = goingto.getX() - pos.getX();
                double p1 = goingto.getY() - pos.getY();
                double p2 = goingto.getZ() - pos.getZ();
                double p3 = 0.1;
                itementity.setDeltaMovement(p0 * p3, p1 * p3, p2 * p3);
            }

            server.addFreshEntity(itementity);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("cooldown", this.cooldown);
        tag.putInt("digestTime", this.digestTime);
        tag.putInt("tries", this.tries);
        tag.putBoolean("exhausts", this.nextExhausts);
        tag.putBoolean("wasPlayer", this.nextWasPlayer);

        if (!this.stack.isEmpty()) tag.put("item", this.stack.save(new CompoundTag()));
        if (this.nextTable != null) tag.putString("nextTable", this.nextTable.toString());
    }
    @Override
    public void load(CompoundTag tag) {
        this.cooldown = tag.getInt("cooldown");
        this.digestTime = tag.getInt("digestTime");
        this.tries = tag.getInt("tries");
        this.nextExhausts = tag.getBoolean("exhausts");
        this.nextWasPlayer = tag.getBoolean("wasPlayer");

        if (tag.contains("item")) this.stack = ItemStack.of(tag.getCompound("item"));
        else this.stack = ItemStack.EMPTY;

        if (tag.contains("nextTable")) this.nextTable = ResourceLocation.tryParse(tag.getString("nextTable"));
        else this.nextTable = null;
    }
}