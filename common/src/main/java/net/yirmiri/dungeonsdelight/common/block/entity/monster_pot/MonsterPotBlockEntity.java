package net.yirmiri.dungeonsdelight.common.block.entity.monster_pot;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.common.block.entity.monster_pot.menu.MonsterPotMenu;
import net.yirmiri.dungeonsdelight.common.recipe.MonsterCookingRecipe;
import net.yirmiri.dungeonsdelight.core.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDRecipeTypes;

import javax.annotation.Nullable;
import java.util.List;

public class MonsterPotBlockEntity extends BlockEntity implements MenuProvider, Nameable, RecipeHolder, WorldlyContainer, StackedContentsCompatible {
    public static final int[] INGREDIENT_SLOTS = new int[]{0, 1, 2, 3, 4, 5};
    public static final int BOWL_SLOT = 6;
    public static final int OUTPUT_SLOT = 7;
    public static final int DATA_COOK_PROGRESS = 0;
    public static final int DATA_COOK_TOTAL = 1;
    public static final int MAX_CONT_SIZE = 8;

    private static final int[] DOWN_SLOTS = new int[]{OUTPUT_SLOT};
    private static final int[] SIDE_SLOTS = new int[]{BOWL_SLOT};
    private static final int[] UP_SLOTS = INGREDIENT_SLOTS; // no need to clone this for data saving

    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed;
    private final RecipeManager.CachedCheck<Container, MonsterCookingRecipe> recipeChecker;
    protected final ContainerData containerData;

    private NonNullList<ItemStack> items;
    private int cookingProgress;
    private int cookingTotalTime;
    private Component name;

    public MonsterPotBlockEntity(BlockPos pos, BlockState blockState) {
        super(DDBlockEntities.MONSTER_POT.get(), pos, blockState);
        this.items = NonNullList.withSize(MAX_CONT_SIZE, ItemStack.EMPTY);
        this.containerData = new ContainerData() {
            public int get(int numer) {
                return switch (numer) {
                    case DATA_COOK_PROGRESS -> MonsterPotBlockEntity.this.cookingProgress;
                    case DATA_COOK_TOTAL -> MonsterPotBlockEntity.this.cookingTotalTime;
                    default -> 0;
                };
            }

            public void set(int numer, int val) {
                switch (numer) {
                    case DATA_COOK_PROGRESS -> MonsterPotBlockEntity.this.cookingProgress = val;
                    case DATA_COOK_TOTAL -> MonsterPotBlockEntity.this.cookingTotalTime = val;
                }

            }

            public int getCount() { return 2; }
        };

        this.recipesUsed = new Object2IntOpenHashMap<>();
        this.recipeChecker = RecipeManager.createCheck(DDRecipeTypes.MONSTER_COOKING.get());
    }

    // Main
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
        this.cookingProgress = tag.getShort("CookTime");
        this.cookingTotalTime = tag.getShort("CookTimeTotal");
        CompoundTag recipes = tag.getCompound("RecipesUsed");

        for (String key : recipes.getAllKeys()) this.recipesUsed.put(new ResourceLocation(key), recipes.getInt(key));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putShort("CookTime", (short)this.cookingProgress);
        tag.putShort("CookTimeTotal", (short)this.cookingTotalTime);
        ContainerHelper.saveAllItems(tag, this.items);

        CompoundTag recipes = new CompoundTag();
        this.recipesUsed.forEach((recipe, integ) -> recipes.putInt(recipe.toString(), integ));
        tag.put("RecipesUsed", recipes);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, MonsterPotBlockEntity blockEntity) {

    }

    // RecipeHolder / Recipes
    public void setRecipeUsed(@Nullable Recipe<?> recipe) { if (recipe != null) this.recipesUsed.addTo(recipe.getId(), 1); }
    @Nullable public Recipe<?> getRecipeUsed() { return null; }
    public void awardUsedRecipes(Player player, List<ItemStack> items) { }

    public void doAwardsAndExp(ServerPlayer player) {
        List<Recipe<?>> awards = this.getRecipesAndExp(player.serverLevel(), player.position());
        player.awardRecipes(awards);

        for (Recipe<?> recipe : awards) {
            if (recipe != null) player.triggerRecipeCrafted(recipe, this.items);
        }

        this.recipesUsed.clear();
    }

    public List<Recipe<?>> getRecipesAndExp(ServerLevel level, Vec3 goPos) {
        List<Recipe<?>> list = Lists.newArrayList();

        for (Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            level.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                createExperience(level, goPos, entry.getIntValue(), ((MonsterCookingRecipe)recipe).getExperience());
            });
        }

        return list;
    }

    private static void createExperience(ServerLevel level, Vec3 popVec, int recipeIndex, float experience) {
        int i = Mth.floor((float)recipeIndex * experience);
        float f = Mth.frac((float)recipeIndex * experience);
        if (f != 0.0F && Math.random() < (double)f) {
            ++i;
        }

        ExperienceOrb.award(level, popVec, i);
    }

    // Nameable
    public void setCustomName(Component name) { this.name = name; }
    @Override public Component getName() { return this.name != null ? this.name : this.getDefaultName(); }
    @Override public Component getDisplayName() { return this.getName(); }
    @Nullable public Component getCustomName() { return this.name; }
    protected Component getDefaultName() { return Component.translatable("container.dungeonsdelight.monster_pot"); }

    // Hopper
    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) return DOWN_SLOTS;
        else return (side == Direction.UP) ? UP_SLOTS : SIDE_SLOTS;
    }

    @Override public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) { return this.canPlaceItem(index, itemStack); }
    @Override public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) { return true; }

    // MenuProvider
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new MonsterPotMenu(id, inventory, this, this.containerData, ContainerLevelAccess.create(this.getLevel(), this.getBlockPos()));
    }

    // Container
    @Override
    public boolean isEmpty() {
        boolean ret = true;
        for (ItemStack stack : this.items) if (!stack.isEmpty() && ret) ret = false;
        return ret;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        ItemStack itemstack = this.items.get(index);
        boolean matches = !stack.isEmpty() && ItemStack.isSameItemSameTags(itemstack, stack);
        this.items.set(index, stack);
        if (stack.getCount() > this.getMaxStackSize()) stack.setCount(this.getMaxStackSize());

        boolean ingSlot = false;
        for (int val : INGREDIENT_SLOTS) {
            if (index == val) {
                ingSlot = true;
                break;
            }
        }
        if (!ingSlot) ingSlot = index == BOWL_SLOT;

        if (ingSlot && !matches) {
            this.cookingTotalTime = getTotalCookTime(this.level, this);
            this.cookingProgress = 0;
            this.setChanged();
        }
    }

    @Override public boolean canPlaceItem(int index, ItemStack stack) { return (index != OUTPUT_SLOT); }
    @Override public boolean stillValid(Player player) { return Container.stillValidBlockEntity(this, player); }
    @Override public ItemStack getItem(int index) { return this.items.get(index); }
    @Override public ItemStack removeItem(int index, int count) { return ContainerHelper.removeItem(this.items, index, count); }
    @Override public ItemStack removeItemNoUpdate(int index) { return ContainerHelper.takeItem(this.items, index); }
    @Override public int getContainerSize() { return this.items.size(); }
    @Override public void clearContent() { this.items.clear(); }

    // StackedContents
    @Override
    public void fillStackedContents(StackedContents stacker) {
        for (ItemStack stack : this.items) stacker.accountStack(stack);
    }

    // Unique
    private static float getSuccessChance(Level level, MonsterPotBlockEntity pot) {
        return pot.recipeChecker.getRecipeFor(pot, level).map(MonsterCookingRecipe::getSuccessChance).orElse(MonsterCookingRecipe.DEFAULT_SUCCESS);
    }

    private static int getTotalCookTime(Level level, MonsterPotBlockEntity pot) {
        return pot.recipeChecker.getRecipeFor(pot, level).map(MonsterCookingRecipe::getCookTime).orElse(MonsterCookingRecipe.DEFAULT_COOKING_TIME);
    }
}