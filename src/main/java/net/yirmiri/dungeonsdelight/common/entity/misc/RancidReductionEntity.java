package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.*;
import vectorwing.farmersdelight.common.block.TomatoVineBlock;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;

public class RancidReductionEntity extends ThrowableItemProjectile {
    public RancidReductionEntity(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    public RancidReductionEntity(Level level, LivingEntity entity) {
        super(DDEntities.RANCID_REDUCTION.get(), entity, level);
    }

    public RancidReductionEntity(Level level, double x, double y, double z) {
        super(DDEntities.RANCID_REDUCTION.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return DDItems.RANCID_REDUCTION.get();
    }

    @Override
    public void handleEntityEvent(byte id) {
        ItemStack entityStack = new ItemStack(this.getDefaultItem());
        if (id == 3) {
            for (int i = 0; i < 12; ++i) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, entityStack), this.getX(), this.getY(), this.getZ(),
                        (this.random.nextFloat() * 2.0 - 1.0) * 0.1, (this.random.nextFloat() * 2.0 - 1.0)
                                * 0.1 + 0.1, (this.random.nextFloat() * 2.0 - 1.0) * 0.1);
            }
        }
    }

    @Override
    protected float getGravity() {
        return 0.05F;
    }

    public void rotCrop(BlockPos pos, Block newBlock, Level level, BlockState state) {
        level.setBlock(pos, newBlock.defaultBlockState(), 3);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(this, state));
        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), DDSounds.MONSTER_YAM_AMBIENT.get(), SoundSource.BLOCKS, 1.0F, 0.75F);
        addRotParticles(level, pos, 5);
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

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(ModDamageTypes.getSimpleDamageSource(this.level(), DDDamageTypes.SHATTER), 3.0F);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        //this.level().addParticle(DDParticles.SKULL_HEART_BLAST.get(), this.getX(), this.getY(), this.getZ(), 0, 0.5, 0);
        if (!this.level().isClientSide) {
            this.makeAreaOfEffectCloud();
            this.discard();
        }
    }

    private void makeAreaOfEffectCloud() {
        AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
        Entity owner = this.getOwner();
        if (owner instanceof LivingEntity) {
            cloud.setOwner((LivingEntity)owner);
        }

        cloud.setDuration(300);
        cloud.setRadius(3.0F);
        cloud.setRadiusOnUse(-0.5F);
        cloud.setWaitTime(10);
        cloud.setRadiusPerTick(-cloud.getRadius() / (float)cloud.getDuration());
        cloud.setParticle(DDParticles.ROT_CLOUD.get());
        cloud.addEffect(new MobEffectInstance(DDEffects.EXUDATION.get(), 300, 2));
        cloud.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 300, 0));

        Level level = this.level();
        BlockPos centerPos = new BlockPos((int)Math.floor(cloud.getX()), (int)Math.floor(cloud.getY()), (int)Math.floor(cloud.getZ()));

        playSound(SoundEvents.GLASS_BREAK, 1.5F, -1.0F);

        if (!level.isClientSide) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        BlockPos pos = new BlockPos(centerPos.getX() + dx, centerPos.getY() + dy, centerPos.getZ() + dz);
                        BlockState state = level.getBlockState(pos);

                        if (state.getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(state)) {
                            if (cropBlock instanceof PotatoBlock potatoBlock && potatoBlock.isMaxAge(state)) {
                                rotCrop(pos, DDBlocks.ROTTEN_POTATOES.get(), level, state);
                            } else if (cropBlock instanceof TomatoVineBlock tomatoVineBlock && tomatoVineBlock.isMaxAge(state) && !state.getValue(TomatoVineBlock.ROPELOGGED)) {
                                rotCrop(pos, DDBlocks.ROTTEN_TOMATOES.get(), level, state);
                            } else if (!(cropBlock instanceof TomatoVineBlock)) {
                                rotCrop(pos, DDBlocks.ROTTEN_CROP.get(), level, state);
                            }
                        }
                    }
                }
            }
        }

        this.level().addFreshEntity(cloud);
    }
}
