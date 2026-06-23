package net.yirmiri.dungeonsdelight.common.block.entity.monster_pot;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;

public class MonsterPotBlockEntity extends BlockEntity implements MenuProvider, Nameable, RecipeHolder {
    public MonsterPotBlockEntity(BlockPos pos, BlockState blockState) {
        super(DDBlockEntities.MONSTER_POT.get(), pos, blockState);
    }

    @Override
    public Component getName() {
        return null;
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return null;
    }

    @Override
    public void setRecipeUsed(Recipe<?> recipe) {

    }

    @Override
    public Recipe<?> getRecipeUsed() {
        return null;
    }
}
