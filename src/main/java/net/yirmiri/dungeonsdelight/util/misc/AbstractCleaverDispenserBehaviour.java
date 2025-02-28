package net.yirmiri.dungeonsdelight.util.misc;

import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.yirmiri.dungeonsdelight.entity.misc.CleaverEntity;

public class AbstractCleaverDispenserBehaviour extends AbstractProjectileDispenseBehavior {
    @Override
    public ItemStack execute(BlockSource source, ItemStack stack) {
        Level level = source.getLevel();
        Position pos = DispenserBlock.getDispensePosition(source);
        Direction dir = source.getBlockState().getValue(DispenserBlock.FACING);
        Projectile projectile = this.getProjectile(level, pos, stack);
        projectile.shoot(dir.getStepX(), (float) dir.getStepY() + 0.1F, dir.getStepZ(), this.getPower(), this.getUncertainty());
        level.addFreshEntity(projectile);
        stack.setDamageValue(stack.getDamageValue() + 1);
        return stack;
    }

    @Override
    protected Projectile getProjectile(Level level, Position pos, ItemStack stack) {
        return new CleaverEntity(level, pos, stack);
    }
}
