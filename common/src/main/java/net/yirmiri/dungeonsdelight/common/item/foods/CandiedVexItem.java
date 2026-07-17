package net.yirmiri.dungeonsdelight.common.item.foods;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.misc.vexing_fangs.VexingFangsEntity;
import net.yirmiri.dungeonsdelight.common.item.food_type.BiteableFoodItem;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;

import java.util.List;

public class CandiedVexItem extends BiteableFoodItem {
    public CandiedVexItem(boolean hasEffectTooltip, Properties properties) {
        super(hasEffectTooltip, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if (DungeonsDelight.CONFIG.getItemEffectTooltips()) {
            DDUtil.addConsumeTooltip(tooltipComponents);
            tooltipComponents.add(Component.translatable("tooltip.dungeonsdelight.effect.vexing_fangs")
                    .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        Vec3 horizontal = new Vec3(consumer.getLookAngle().x, 0, consumer.getLookAngle().z).normalize();
        double minY = Math.min(consumer.getY(), consumer.getY() - 5);
        double maxY = consumer.getY() + 1;

        for (int i = 0; i < DungeonsDelight.CONFIG.getVexingFangsCount(); ++i) {
            double distance = DungeonsDelight.CONFIG.getVexingFangsDistance() * (i + 1);
            createSpellEntity(consumer, consumer.getX() + horizontal.x * distance, consumer.getZ() + horizontal.z * distance, minY, maxY, i / DungeonsDelight.CONFIG.getVexingFangsSpeed());
        }
        return super.finishUsingItem(stack, level, consumer);
    }

    private void createSpellEntity(LivingEntity consumer, double x, double z, double minY, double maxY, int warmupDelay) {
        BlockPos blockpos = BlockPos.containing(x, maxY, z);
        boolean flag = false;
        double voxelShapeY = 0.0D;
        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = consumer.level().getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(consumer.level(), blockpos1, Direction.UP)) {
                if (!consumer.level().isEmptyBlock(blockpos)) {
                    VoxelShape voxelshape = consumer.level().getBlockState(blockpos).getCollisionShape(consumer.level(), blockpos);
                    if (!voxelshape.isEmpty()) {
                        voxelShapeY = voxelshape.max(Direction.Axis.Y);
                    }
                }
                flag = true;
                break;
            }
            blockpos = blockpos.below();
        } while (blockpos.getY() >= Mth.floor(minY) - 1);
        if (flag) {
            consumer.level().addFreshEntity(new VexingFangsEntity(consumer.level(), x, (double) blockpos.getY() + voxelShapeY, z, consumer.getYRot() * ((float) Math.PI / 180F), warmupDelay, consumer));
        }
    }
}
