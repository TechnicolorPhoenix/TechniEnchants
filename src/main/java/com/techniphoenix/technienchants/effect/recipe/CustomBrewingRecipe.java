package com.techniphoenix.technienchants.effect.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class CustomBrewingRecipe implements IBrewingRecipe {

    private final Potion inputPotion;
    private final Ingredient ingredient;
    private final ItemStack outputStack;

    public CustomBrewingRecipe(Potion inputPotion, Item inputItem, Potion outputPotion){
        this.inputPotion = inputPotion;

        this.ingredient = Ingredient.of(inputItem);

        this.outputStack = PotionUtils.setPotion(new ItemStack(Items.POTION), outputPotion);
    }

    @Override
    public boolean isInput(ItemStack input) {
        if (input.getItem() != Items.POTION) {
            return false;
        }

        return PotionUtils.getPotion(input) == inputPotion;
    }

    @Override
    public boolean isIngredient(ItemStack ingredientStack) {
        return this.ingredient.test(ingredientStack);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        boolean inputMatches = this.isInput(input);

        boolean ingredientMatches = this.isIngredient(ingredient);

        if (inputMatches && ingredientMatches) {
            return this.outputStack.copy();
        }

        return ItemStack.EMPTY;
    }
}
