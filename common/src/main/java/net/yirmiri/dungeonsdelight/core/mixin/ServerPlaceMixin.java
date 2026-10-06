package net.yirmiri.dungeonsdelight.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.MonsterPotBlockEntity;
import net.yirmiri.dungeonsdelight.common.menu.monster_pot.MonsterPotMenu;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Iterator;
import java.util.List;

@Mixin(ServerPlaceRecipe.class)
public abstract class ServerPlaceMixin<C extends Container> {
    @Shadow protected Inventory inventory;
    @Shadow protected RecipeBookMenu<C> menu;

    @Shadow public abstract void addItemToSlot(Iterator<Integer> ingredients, int p_slot, int maxAmount, int y, int x);

    @WrapOperation(method = "moveItemToGrid", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Inventory;findSlotMatchingUnusedItem(Lnet/minecraft/world/item/ItemStack;)I")
    )
    private int dungeonsdelight$ignoreCreeperillaForGoodGameDesign(Inventory instance, ItemStack ingredient, Operation<Integer> original) {
        if (ingredient.is(DDItems.CREEPERILLA_SQUIB.get())) {
            return DDUtil.searchInventoryForAnyOfThis(this.inventory, ingredient.getItem());
        }
        return original.call(instance, ingredient);
    }

    @WrapOperation(method = "handleRecipeClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipebook/ServerPlaceRecipe;placeRecipe(IIILnet/minecraft/world/item/crafting/Recipe;Ljava/util/Iterator;I)V"))
    // Boilerplate as sin, but handles moving the bowl item to the bowl slot automatically
    private void dungeonsdelight$monsterPotOrdinate(
            ServerPlaceRecipe instance,
            int width,
            int height,
            int slot,
            Recipe<?> recipe,
            Iterator<Integer> iterator,
            int max,
            Operation<Void> original,
            @Local(ordinal = 0)IntList intlist
    ) {
        Iterator<Integer> itr = iterator;
        if (this.menu instanceof MonsterPotMenu men && recipe instanceof MonsterCookingRecipe cookRecipe && !cookRecipe.getContainer().isEmpty()) {
            ItemStack stack = cookRecipe.getContainer();
            intlist.removeInt(intlist.size() - 1);
            itr = intlist.iterator();

            int exi = StackedContents.getStackingIndex(stack);
            this.addItemToSlot(List.of(exi).iterator(), MonsterPotBlockEntity.BOWL_SLOT, max, 0, 0);
        }
        original.call(instance, width, height, slot, recipe, itr, max);
    }
}
