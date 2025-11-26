package com.techniphoenix.technienchants.effect.recipe;

import com.techniphoenix.technienchants.item.ModPotions;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class GildedBlackstoneBrewingRecipe implements IBrewingRecipe {

    private final ItemStack inputStack;
    private final Ingredient inputIngredient;

    private final Ingredient ingredient;

    private final ItemStack outputStack;

    public GildedBlackstoneBrewingRecipe(){
        this.inputStack = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD);
        this.inputIngredient = Ingredient.of(this.inputStack);

        this.ingredient = Ingredient.of(Items.GILDED_BLACKSTONE);

        this.outputStack = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.STONESKIN.get());
    }

    @Override
    public boolean isInput(ItemStack input) {
        return this.inputIngredient.test(input);
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return this.ingredient.test(ingredient);
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
