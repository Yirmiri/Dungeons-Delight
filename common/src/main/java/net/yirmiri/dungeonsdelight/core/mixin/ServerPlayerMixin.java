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
public class ServerPlayerMixin implements HomewardData {
    @Unique
    private BlockPos homewardPos = null;

    @Unique
    private ResourceKey<Level> homewardDimension = null;

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
    public Level getHomewardDimension() {
        return this.homewardDimension == null ? null : ((ServerPlayer) (Object) this).server.getLevel(this.homewardDimension);
    }

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

        Level level = ((HomewardData) oldPlayer).getHomewardDimension();
        this.homewardDimension = level != null ? level.dimension() : null;
    }
}