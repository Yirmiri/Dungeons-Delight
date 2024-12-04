package net.yirmiri.dungeonsdelight.mixin.porting_lib;

import io.github.fabricators_of_create.porting_lib.tool.data.ItemAbilityLootTableProvider;
import net.minecraft.data.server.loottable.LootTableProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@Mixin(ItemAbilityLootTableProvider.class)
public abstract class ItemAbilityLootTableProviderMixin {

    /**
     * @author - Yirmiri
     * @reason - Probably really stupid way of getting around it but this prevents datagen from crashing due to Porting Lib
     */
    @Overwrite(remap = false)
    public List<LootTableProvider.LootTypeGenerator> getTables() {
        return List.of();
    }
}