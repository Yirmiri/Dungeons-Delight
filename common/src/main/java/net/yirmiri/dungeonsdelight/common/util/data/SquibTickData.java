package net.yirmiri.dungeonsdelight.common.util.data;

import net.azurune.runiclib.common.item.IAlwaysTickingItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.common.item.CreeperillaSquibItem;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;

public class SquibTickData {
    public static final int MAX_TICKS = 200;
    public static final String SQUIB_TAG = "SquibTimer";

    public static void commonTickdown(IAlwaysTickingItem.Context ctx, ItemStack stack, Level level, Entity entity, Container container) {
        if (!level.isClientSide) {
            int timer = getTime(stack);
            if (validate(container, entity)) {
                if (--timer > 0) writeTime(stack, timer);
                else explode(ctx, stack, level, entity, container);
            }
        }
    }

    public static int getTime(ItemStack stack) {
        Item squib = stack.getItem();
        if (squib instanceof CreeperillaSquibItem) {
            CompoundTag tag = stack.getOrCreateTag();

            if (!tag.contains(SQUIB_TAG)) tag.putInt(SQUIB_TAG, MAX_TICKS);
            return tag.getInt(SQUIB_TAG);
        }
        return MAX_TICKS;
    }

    public static void writeTime(ItemStack stack, int newTime) {
        Item squib = stack.getItem();
        if (squib instanceof CreeperillaSquibItem) {
            CompoundTag tag = stack.getOrCreateTag();

            tag.putInt(SQUIB_TAG, newTime);
        }
    }

    public static boolean validate(Container container, Entity entity) {
        if (container != null) {
            if (container instanceof MonsterPotBlockEntity pot) return (pot.getCookingTotalTime() == 0);
        }
        return (entity instanceof Player player) ? (!player.isCreative() && !player.isSpectator()) : true;
    }

    public static void explode(IAlwaysTickingItem.Context ctx, ItemStack stack, Level level, Entity entity, Container container) {
        Vec3 posvec = null;
        DamageSource src = DDDamageTypes.getDamageSource(level, DDDamageTypes.CREEPERILLA_BLAST);
        float owie = 3.0F;

        int radius = 2;
        Level.ExplosionInteraction expInt = Level.ExplosionInteraction.MOB;

        stack.setCount(0);

        if (ctx == null) {
            if (!(entity instanceof Player player)) throw new IllegalStateException("Creeperilla Squib tried to explode, but couldn't find a RL context nor a player");

            posvec = player.position();
            player.hurt(src, owie);
        }
        else {
            switch (ctx) {
                // Item dropped
                case ITEM_IN_WORLD: {
                    if (!(entity instanceof ItemEntity item)) throw new IllegalStateException("Creeperilla Squib tried to explode in ITEM_IN_WORLD ctx, but target ent isn't an ItemEntity");

                    posvec = item.position();
                    item.discard();
                }
                break;
                // Container
                case CONTAINER: {
                    if (!(container instanceof BlockEntity ent) || ent.isRemoved()) throw new IllegalStateException("Creeperilla Squib tried to explode in CONTAINER ctx, but container is null");

                    radius = 3;
                    posvec = ent.getBlockPos().getCenter();
                    expInt = Level.ExplosionInteraction.BLOCK;
                }
                break;
                // Frame + Container Entities
                case CONTAINER_ENTITY:
                case ITEM_FRAME: {
                    if (ctx == IAlwaysTickingItem.Context.ITEM_FRAME) {
                        if (!(entity instanceof ItemFrame)) throw new IllegalStateException("Creeperilla Squib tried to explode in ITEM_FRAME ctx, but target ent isn't an ItemFrame");
                    }
                    else {
                        if (!(entity instanceof ContainerEntity)) throw new IllegalStateException("Creeperilla Squib tried to explode in CONTAINER_ENTITY ctx, but target ent isn't a ContainerEntity");
                        radius = 1;
                    }

                    posvec = entity.position();
                    entity.hurt(src, 69420.0F);
                }
                break;
                // Mob/Armor Stand + Chested Horse
                case CHESTED_HORSE:
                case LIVING_ENTITY: {
                    if (!(entity instanceof LivingEntity live)) throw new IllegalStateException("Creeperilla Squib tried to explode in LIVING_ENTITY or CHESTED_HORSE ctx, but target ent isn't a LivingEntity");

                    posvec = live.position();
                    live.hurt(src, owie);
                }
                break;
            }
        }

        if (posvec != null) level.explode(
                entity,
                posvec.x(),
                posvec.y(),
                posvec.z(),
                radius,
                false,
                expInt
        );
    }
}
