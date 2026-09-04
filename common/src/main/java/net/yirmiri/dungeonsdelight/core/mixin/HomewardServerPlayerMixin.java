package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.util.data.HomewardData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class HomewardServerPlayerMixin implements HomewardData {
    ServerPlayer player = (ServerPlayer) (Object) this;

    @Unique
    private BlockPos homewardPos = null;

    @Unique
    private ResourceKey<Level> homewardDimension = null;

    @Unique
    private int lastSelectedSlot = -1;

    @Override
    public void setHomewardPos(BlockPos pos) {
        this.homewardPos = pos;
    }

    @Override
    public BlockPos getHomewardPos() {
        return this.homewardPos;
    }

    @Override
    public void setHomewardDimension(ResourceKey<Level> level) {
        this.homewardDimension = level;
    }

    @Override
    public ResourceKey<Level> getHomewardDimension() {
        return this.homewardDimension;
    }

//    @Inject(method = "tick", at = @At("TAIL")) //todo (move out serverplayerhomeward) make on mouse hover this is just a temp as it requires a packet (should take a second or two to hatch from the item and also should hatch no matter what if the inv gets closed or the item is dropped)
//    private void dungeonsdelight$tick(CallbackInfo ci) {
//        int slot = player.getInventory().selected;
//        if (slot == lastSelectedSlot) return;
//
//        lastSelectedSlot = slot;
//
//        ItemStack stack = player.getInventory().getItem(slot);
//
//        if (stack.isEmpty()) return;
//        if (!stack.hasTag() || !stack.getTag().getBoolean("TreasureBugInfested")) return;
//
//        int count = stack.getCount();
//        player.getInventory().setItem(slot, ItemStack.EMPTY);
//
//        for (int i = 0; i < count; i++) {
//            TreasureBugEntity bug = DDEntities.TREASURE_BUG.get().create(player.serverLevel());
//
//            if (bug != null) {
//                bug.moveTo(player.getX(), player.getY(), player.getZ());
//
//                ItemStack copiedStack = stack.copy();
//                copiedStack.setCount(1);
//                bug.setStoredItem(copiedStack);
//                player.serverLevel().addFreshEntity(bug);
//            }
//        }
//    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void dungeonsdelight$addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        if (this.homewardPos != null) {
            tag.putLong("HomewardPos", this.homewardPos.asLong());
        }

        if (this.homewardDimension != null) {
            tag.putString("HomewardDimension", this.homewardDimension.location().toString());
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void dungeonsdelight$readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("HomewardPos")) {
            this.homewardPos = BlockPos.of(tag.getLong("HomewardPos"));
        } else {
            this.homewardPos = null;
        }

        if (tag.contains("HomewardDimension")) {
            this.homewardDimension = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(tag.getString("HomewardDimension")));
        } else {
            this.homewardDimension = null;
        }
    }

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void dungeonsdelight$restoreFrom(ServerPlayer oldPlayer, boolean keepEverything, CallbackInfo ci) {
        this.homewardPos = ((HomewardData) oldPlayer).getHomewardPos();
        this.homewardDimension = ((HomewardData) oldPlayer).getHomewardDimension();
    }
}