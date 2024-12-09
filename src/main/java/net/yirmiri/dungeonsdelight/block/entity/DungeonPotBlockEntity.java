package net.yirmiri.dungeonsdelight.block.entity;

import com.google.common.collect.Lists;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Nameable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.block.DungeonPotBlock;
import net.yirmiri.dungeonsdelight.block.entity.container.DungeonPotMenu;
import net.yirmiri.dungeonsdelight.registry.DDBlockEntities;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDParticles;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.block.entity.inventory.CookingPotItemHandler;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.RecipeWrapper;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class DungeonPotBlockEntity extends SyncedBlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, SpawnerHeatableBlockEntity, Nameable, RecipeUnlocker {
    //TODO - Fix recipe remainders and add custom recipe type
    public static final int MEAL_DISPLAY_SLOT = 6;
    public static final int CONTAINER_SLOT = 7;
    public static final int OUTPUT_SLOT = 8;
    public static final int INVENTORY_SIZE = OUTPUT_SLOT + 1;

    public static final Map<Item, Item> INGREDIENT_REMAINDER_OVERRIDES = Map.ofEntries(
            entry(Items.POWDER_SNOW_BUCKET, Items.BUCKET),
            entry(Items.AXOLOTL_BUCKET, Items.BUCKET),
            entry(Items.COD_BUCKET, Items.BUCKET),
            entry(Items.PUFFERFISH_BUCKET, Items.BUCKET),
            entry(Items.SALMON_BUCKET, Items.BUCKET),
            entry(Items.TROPICAL_FISH_BUCKET, Items.BUCKET),
            entry(Items.SUSPICIOUS_STEW, Items.BOWL),
            entry(Items.MUSHROOM_STEW, Items.BOWL),
            entry(Items.RABBIT_STEW, Items.BOWL),
            entry(Items.BEETROOT_SOUP, Items.BOWL),
            entry(Items.POTION, Items.GLASS_BOTTLE),
            entry(Items.SPLASH_POTION, Items.GLASS_BOTTLE),
            entry(Items.LINGERING_POTION, Items.GLASS_BOTTLE),
            entry(Items.EXPERIENCE_BOTTLE, Items.GLASS_BOTTLE)
    );

    private final ItemStackHandlerContainer inventory;
    private final CookingPotItemHandler inputHandler;
    private final CookingPotItemHandler outputHandler;

    private int cookTime;
    private int cookTimeTotal;
    private ItemStack mealContainerStack;
    private Text customName;

    protected final PropertyDelegate cookingPotData;
    private final Object2IntOpenHashMap<Identifier> usedRecipeTracker;

    private final RecipeManager.MatchGetter<RecipeWrapper, CookingPotRecipe> quickCheck;

    public DungeonPotBlockEntity(BlockPos pos, BlockState state) {
        super(DDBlockEntities.DUNGEON_POT, pos, state);
        this.inventory = createHandler();
        this.inputHandler = new CookingPotItemHandler(inventory, Direction.UP);
        this.outputHandler = new CookingPotItemHandler(inventory, Direction.DOWN);
        this.mealContainerStack = ItemStack.EMPTY;
        this.cookingPotData = createIntArray();
        this.usedRecipeTracker = new Object2IntOpenHashMap<>();
        this.quickCheck = RecipeManager.createCachedMatchGetter(ModRecipeTypes.COOKING.get());
    }

    public static void init() {
        ItemStorage.SIDED.registerForBlockEntity(DungeonPotBlockEntity::getStorage, DDBlockEntities.DUNGEON_POT);
    }

	/*
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(
				Capabilities.ItemHandler.BLOCK,
				ModBlockEntityTypes.COOKING_POT.get(),
				(be, context) -> {
					if (context == Direction.UP) {
						return be.inputHandler;
					}
					return be.outputHandler;
				}
		);
	}
	 */

    public static ItemStack getMealFromItem(ItemStack cookingPotStack) {
        if (!cookingPotStack.isOf(DDBlocks.DUNGEON_POT.asItem())) {
            return ItemStack.EMPTY;
        }

        return cookingPotStack.getOrDefault(ModDataComponents.MEAL.get(), ItemStackWrapper.EMPTY).getStack();
    }

    public static void takeServingFromItem(ItemStack cookingPotStack) {
        if (!cookingPotStack.isOf(DDBlocks.DUNGEON_POT.asItem())) {
            return;
        }

        ItemStack mealStack = cookingPotStack.getOrDefault(ModDataComponents.MEAL.get(), ItemStackWrapper.EMPTY).getStack();
        mealStack.decrement(1);
        cookingPotStack.set(ModDataComponents.MEAL.get(), new ItemStackWrapper(mealStack));
    }

    public static ItemStack getContainerFromItem(ItemStack cookingPotStack) {
        if (!cookingPotStack.isOf(DDBlocks.DUNGEON_POT.asItem())) {
            return ItemStack.EMPTY;
        }
        return cookingPotStack.getOrDefault(ModDataComponents.CONTAINER.get(), ItemStackWrapper.EMPTY).getStack();
    }

    @Override
    public void readNbt(NbtCompound compound, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(compound, registries);
        inventory.deserializeNBT(registries, compound.getCompound("Inventory"));
        cookTime = compound.getInt("CookTime");
        cookTimeTotal = compound.getInt("CookTimeTotal");
        mealContainerStack = ItemStack.fromNbtOrEmpty(registries, compound.getCompound("Container"));
        if (compound.contains("CustomName", 8)) {
            customName = Text.Serialization.fromJson(compound.getString("CustomName"), registries);
        }
        NbtCompound compoundRecipes = compound.getCompound("RecipesUsed");
        for (String key : compoundRecipes.getKeys()) {
            usedRecipeTracker.put(Identifier.of(key), compoundRecipes.getInt(key));
        }
    }

    @Override
    public void writeNbt(NbtCompound compound, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(compound, registries);
        compound.putInt("CookTime", cookTime);
        compound.putInt("CookTimeTotal", cookTimeTotal);
        compound.put("Container", mealContainerStack.encodeAllowEmpty(registries));
        if (customName != null) {
            compound.putString("CustomName", Text.Serialization.toJsonString(customName, registries));
        }
        compound.put("Inventory", inventory.serializeNBT(registries));
        NbtCompound compoundRecipes = new NbtCompound();
        usedRecipeTracker.forEach((recipeId, craftedAmount) -> compoundRecipes.putInt(recipeId.toString(), craftedAmount));
        compound.put("RecipesUsed", compoundRecipes);
    }

    private NbtCompound writeItems(NbtCompound compound, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(compound, registries);
        compound.put("Container", mealContainerStack.encodeAllowEmpty(registries));
        compound.put("Inventory", inventory.serializeNBT(registries));
        return compound;
    }

    public NbtCompound writeMeal(NbtCompound compound, RegistryWrapper.WrapperLookup registries) {
        if (getMeal().isEmpty()) return compound;

        ItemStackHandler drops = new ItemStackHandler(INVENTORY_SIZE);
        for (int i = 0; i < INVENTORY_SIZE; ++i) {
            drops.setStackInSlot(i, i == MEAL_DISPLAY_SLOT ? inventory.getStackInSlot(i) : ItemStack.EMPTY);
        }
        if (customName != null) {
            compound.putString("CustomName", Text.Serialization.toJsonString(customName, registries));
        }
        compound.put("Container", mealContainerStack.encode(registries));
        compound.put("Inventory", drops.serializeNBT(registries));
        return compound;
    }

    public ItemStack getAsItem() {
        ItemStack stack = new ItemStack(DDBlocks.DUNGEON_POT.asItem());
        stack.applyComponentsFrom(createComponentMap());
        return stack;
    }

    public static void cookingTick(World level, BlockPos pos, BlockState state, DungeonPotBlockEntity cookingPot) {
        boolean isHeated = cookingPot.isHeated(level, pos);
        boolean didInventoryChange = false;

        if (isHeated && cookingPot.hasInput()) {
            Optional<RecipeEntry<CookingPotRecipe>> recipe = cookingPot.getMatchingRecipe(new RecipeWrapper(cookingPot.inventory));
            if (recipe.isPresent() && cookingPot.canCook(recipe.get().value())) {
                didInventoryChange = cookingPot.processCooking(recipe.get(), cookingPot);
            } else {
                cookingPot.cookTime = 0;
            }
        } else if (cookingPot.cookTime > 0) {
            cookingPot.cookTime = MathHelper.clamp(cookingPot.cookTime - 2, 0, cookingPot.cookTimeTotal);
        }

        ItemStack mealStack = cookingPot.getMeal();
        if (!mealStack.isEmpty()) {
            if (!cookingPot.doesMealHaveContainer(mealStack)) {
                cookingPot.moveMealToOutput();
                didInventoryChange = true;
            } else if (!cookingPot.inventory.getStackInSlot(CONTAINER_SLOT).isEmpty()) {
                cookingPot.useStoredContainersOnMeal();
                didInventoryChange = true;
            }
        }

        if (didInventoryChange) {
            cookingPot.inventoryChanged();
        }
    }


    public static void animationTick(World level, BlockPos pos, BlockState state, DungeonPotBlockEntity cookingPot) {
        if (cookingPot.isHeated(level, pos)) {
            Random random = level.random;
            if (random.nextFloat() < 0.2F) {
                double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.6D - 0.3D);
                double y = (double) pos.getY() + 0.7D;
                double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.6D - 0.3D);
                level.addParticle(DDParticles.DUNGEON_BUBBLE, x, y, z, 0.0D, 0.0D, 0.0D);
            }
            if (random.nextFloat() < 0.05F) {
                double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
                double y = (double) pos.getY() + 0.5D;
                double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
                double motionY = random.nextBoolean() ? 0.015D : 0.005D;
                level.addParticle(ModParticleTypes.STEAM.get(), x, y, z, 0.0D, motionY, 0.0D);
            }
        }

    }

    private Optional<RecipeEntry<CookingPotRecipe>> getMatchingRecipe(RecipeWrapper inventoryWrapper) {
        if (world == null) return Optional.empty();
        return hasInput() ? quickCheck.getFirstMatch(inventoryWrapper, this.world) : Optional.empty();
    }

    public ItemStack getContainer() {
        ItemStack mealStack = getMeal();
        if (!mealStack.isEmpty() && !mealContainerStack.isEmpty()) {
            return mealContainerStack;
//        } else {
//            return mealStack.getRecipeRemainder();
        }
        return mealStack;
    }

    private boolean hasInput() {
        for (int i = 0; i < MEAL_DISPLAY_SLOT; ++i) {
            if (!inventory.getStackInSlot(i).isEmpty()) return true;
        }
        return false;
    }

    protected boolean canCook(CookingPotRecipe recipe) {
        if (hasInput()) {
            ItemStack resultStack = recipe.getResult(this.world.getRegistryManager());
            if (resultStack.isEmpty()) {
                return false;
            } else {
                ItemStack storedMealStack = inventory.getStackInSlot(MEAL_DISPLAY_SLOT);
                if (storedMealStack.isEmpty()) {
                    return true;
                } else if (!ItemStack.areItemsEqual(storedMealStack, resultStack)) {
                    return false;
                } else if (storedMealStack.getCount() + resultStack.getCount() <= inventory.getSlotLimit(MEAL_DISPLAY_SLOT)) {
                    return true;
                } else {
                    return storedMealStack.getCount() + resultStack.getCount() <= resultStack.getMaxCount();
                }
            }
        } else {
            return false;
        }
    }

    private boolean processCooking(RecipeEntry<CookingPotRecipe> recipe, DungeonPotBlockEntity cookingPot) {
        if (world == null) return false;

        ++cookTime;
        cookTimeTotal = recipe.value().getCookTime();
        if (cookTime < cookTimeTotal) {
            return false;
        }

        cookTime = 0;
        mealContainerStack = recipe.value().getOutputContainer();
        ItemStack resultStack = recipe.value().getResult(this.world.getRegistryManager());
        ItemStack storedMealStack = inventory.getStackInSlot(MEAL_DISPLAY_SLOT);
        if (storedMealStack.isEmpty()) {
            inventory.setStackInSlot(MEAL_DISPLAY_SLOT, resultStack.copy());
        } else if (ItemStack.areItemsEqual(storedMealStack, resultStack)) {
            storedMealStack.increment(resultStack.getCount());
        }
        cookingPot.setLastRecipe(recipe);

        for (int i = 0; i < MEAL_DISPLAY_SLOT; ++i) {
            ItemStack slotStack = inventory.getStackInSlot(i);
            //if (!slotStack.getRecipeRemainder().method_7960()) {
                //ejectIngredientRemainder(slotStack.getRecipeRemainder());
           // } else
                if (INGREDIENT_REMAINDER_OVERRIDES.containsKey(slotStack.getItem())) {
                ejectIngredientRemainder(INGREDIENT_REMAINDER_OVERRIDES.get(slotStack.getItem()).getDefaultStack());
            }
            if (!slotStack.isEmpty())
                slotStack.decrement(1);
        }
        return true;
    }

    protected void ejectIngredientRemainder(ItemStack remainderStack) {
        Direction direction = getCachedState().get(DungeonPotBlock.FACING).rotateYCounterclockwise();
        double x = pos.getX() + 0.5 + (direction.getOffsetX() * 0.25);
        double y = pos.getY() + 0.7;
        double z = pos.getZ() + 0.5 + (direction.getOffsetZ() * 0.25);
        ItemUtils.spawnItemEntity(world, remainderStack, x, y, z,
                direction.getOffsetX() * 0.08F, 0.25F, direction.getOffsetZ() * 0.08F);
    }

    @Override
    public void setLastRecipe(@Nullable RecipeEntry<?> recipe) {
        if (recipe != null) {
            Identifier recipeID = recipe.id();
            usedRecipeTracker.addTo(recipeID, 1);
        }
    }

    @Nullable
    @Override
    public RecipeEntry<?> getLastRecipe() {
        return null;
    }

    @Override
    public void unlockLastRecipe(PlayerEntity player, List<ItemStack> items) {
        List<RecipeEntry<?>> usedRecipes = getUsedRecipesAndPopExperience(player.getWorld(), player.getPos());
        player.unlockRecipes(usedRecipes);
        usedRecipeTracker.clear();
    }

    public List<RecipeEntry<?>> getUsedRecipesAndPopExperience(World level, Vec3d pos) {
        List<RecipeEntry<?>> list = Lists.newArrayList();

        for (Object2IntMap.Entry<Identifier> entry : usedRecipeTracker.object2IntEntrySet()) {
            level.getRecipeManager().get(entry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                splitAndSpawnExperience((ServerWorld) level, pos, entry.getIntValue(), ((CookingPotRecipe) recipe.value()).getExperience());
            });
        }

        return list;
    }

    private static void splitAndSpawnExperience(ServerWorld level, Vec3d pos, int craftedAmount, float experience) {
        int expTotal = MathHelper.floor((float) craftedAmount * experience);
        float expFraction = MathHelper.fractionalPart((float) craftedAmount * experience);
        if (expFraction != 0.0F && Math.random() < (double) expFraction) {
            ++expTotal;
        }

        ExperienceOrbEntity.spawn(level, pos, expTotal);
    }

    public boolean isHeated() {
        if (world == null) return false;
        return this.isHeated(world, pos);
    }

    public ItemStackHandlerContainer getInventory() {
        return inventory;
    }

    public ItemStack getMeal() {
        return inventory.getStackInSlot(MEAL_DISPLAY_SLOT);
    }

    public DefaultedList<ItemStack> getDroppableInventory() {
        DefaultedList<ItemStack> drops = DefaultedList.of();
        for (int i = 0; i < INVENTORY_SIZE; ++i) {
            if (i != MEAL_DISPLAY_SLOT) {
                drops.add(inventory.getStackInSlot(i));
            }
        }
        return drops;
    }

    private void moveMealToOutput() {
        ItemStack mealStack = inventory.getStackInSlot(MEAL_DISPLAY_SLOT);
        ItemStack outputStack = inventory.getStackInSlot(OUTPUT_SLOT);
        int mealCount = Math.min(mealStack.getCount(), mealStack.getMaxCount() - outputStack.getCount());
        if (outputStack.isEmpty()) {
            inventory.setStackInSlot(OUTPUT_SLOT, mealStack.split(mealCount));
        } else if (outputStack.getItem() == mealStack.getItem()) {
            mealStack.decrement(mealCount);
            outputStack.increment(mealCount);
        }
    }

    private void useStoredContainersOnMeal() {
        ItemStack mealStack = inventory.getStackInSlot(MEAL_DISPLAY_SLOT);
        ItemStack containerInputStack = inventory.getStackInSlot(CONTAINER_SLOT);
        ItemStack outputStack = inventory.getStackInSlot(OUTPUT_SLOT);

        if (isContainerValid(containerInputStack) && outputStack.getCount() < outputStack.getMaxCount()) {
            int smallerStackCount = Math.min(mealStack.getCount(), containerInputStack.getCount());
            int mealCount = Math.min(smallerStackCount, mealStack.getMaxCount() - outputStack.getCount());
            if (outputStack.isEmpty()) {
                containerInputStack.decrement(mealCount);
                inventory.setStackInSlot(OUTPUT_SLOT, mealStack.split(mealCount));
            } else if (outputStack.getItem() == mealStack.getItem()) {
                mealStack.decrement(mealCount);
                containerInputStack.decrement(mealCount);
                outputStack.increment(mealCount);
            }
        }
    }

    public ItemStack useHeldItemOnMeal(ItemStack container) {
        if (isContainerValid(container) && !getMeal().isEmpty()) {
            container.decrement(1);
            return getMeal().split(1);
        }
        return ItemStack.EMPTY;
    }

    private boolean doesMealHaveContainer(ItemStack meal) {
        //return !mealContainerStack.isEmpty() || !meal.getRecipeRemainder().method_7960();
        return false;
    }

    public boolean isContainerValid(ItemStack containerItem) {
        if (containerItem.isEmpty()) return false;
        if (!mealContainerStack.isEmpty()) {
            return ItemStack.areItemsEqual(mealContainerStack, containerItem);
        } else {
            return ItemStack.areItemsEqual(getMeal(), containerItem);
        }
    }

    @Override
    public Text getName() {
        return customName != null ? customName : TextUtils.getTranslation("container.dungeon_pot");
    }

    @Override
    public Text getDisplayName() {
        return getName();
    }

    @Override
    @Nullable
    public Text getCustomName() {
        return customName;
    }

    @Override
    public ScreenHandler createMenu(int id, PlayerInventory player, PlayerEntity entity) {
        return new DungeonPotMenu(id, player, this, cookingPotData);
    }

    @NotNull
    public Storage<ItemVariant> getStorage(@Nullable Direction side) {
        if (side == null || side.equals(Direction.UP)) {
            return inputHandler;
        } else {
            return outputHandler;
        }
    }

    @Override
    public void markRemoved() {
        super.markRemoved();
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return writeItems(new NbtCompound(), registries);
    }

    @Override
    protected void readComponents(BlockEntity.ComponentsAccess componentInput) {
        super.readComponents(componentInput);
        this.customName = componentInput.get(DataComponentTypes.CUSTOM_NAME);
        getInventory().setStackInSlot(MEAL_DISPLAY_SLOT, componentInput.getOrDefault(ModDataComponents.MEAL.get(), ItemStackWrapper.EMPTY).getStack());
        this.mealContainerStack = componentInput.getOrDefault(ModDataComponents.CONTAINER.get(), ItemStackWrapper.EMPTY).getStack();
    }

    @Override
    protected void addComponents(ComponentMap.Builder components) {
        super.addComponents(components);
        components.add(DataComponentTypes.CUSTOM_NAME, this.customName);
        if (!getMeal().isEmpty()) {
            components.add(ModDataComponents.MEAL.get(), new ItemStackWrapper(getMeal()));
        }
        if (!getContainer().isEmpty()) {
            components.add(ModDataComponents.CONTAINER.get(), new ItemStackWrapper(getContainer()));
        }
    }

    @Override
    public void removeFromCopiedStackNbt(NbtCompound tag) {
        tag.remove("CustomName");
        tag.remove("meal");
        tag.remove("container");
    }

    private ItemStackHandlerContainer createHandler() {
        return new ItemStackHandlerContainer(INVENTORY_SIZE)
        {
            @Override
            public int getSlotLimit(int slot) {
                if (slot == MEAL_DISPLAY_SLOT)
                    return Math.max(64, super.getSlotLimit(slot));
                return super.getSlotLimit(slot);
            }

            @Override
            protected int getStackLimit(int slot, ItemVariant resource) {
                if (slot == MEAL_DISPLAY_SLOT)
                    return Math.max(64, super.getStackLimit(slot, resource));
                return super.getStackLimit(slot, resource);
            }

            @Override
            protected void onContentsChanged(int slot) {
                inventoryChanged();
            }
        };
    }

    private PropertyDelegate createIntArray() {
        return new PropertyDelegate()
        {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DungeonPotBlockEntity.this.cookTime;
                    case 1 -> DungeonPotBlockEntity.this.cookTimeTotal;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DungeonPotBlockEntity.this.cookTime = value;
                    case 1 -> DungeonPotBlockEntity.this.cookTimeTotal = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.getPos();
    }
}
