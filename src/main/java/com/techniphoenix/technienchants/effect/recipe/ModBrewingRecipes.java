package com.techniphoenix.technienchants.effect.recipe;

import com.techniphoenix.technienchants.item.ModPotions;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;

public class ModBrewingRecipes {

    public static CustomBrewingRecipe STONESKIN_RECIPE = new CustomBrewingRecipe(
            Potions.AWKWARD,
            Items.GILDED_BLACKSTONE,
            ModPotions.STONESKIN.get()
    );
    public static CustomBrewingRecipe STONESKIN_STRONG_RECIPE = new CustomBrewingRecipe(
            ModPotions.STONESKIN.get(),
            Items.GLOWSTONE_DUST,
            ModPotions.STRONG_STONESKIN_POTION.get()
    );
    public static CustomBrewingRecipe STONESKIN_LONG_RECIPE = new CustomBrewingRecipe(
            ModPotions.STONESKIN.get(),
            Items.REDSTONE,
            ModPotions.LONG_STONESKIN_POTION.get()
    );

    public static CustomBrewingRecipe TRANSMUTATION_RECIPE = new CustomBrewingRecipe(
            Potions.LUCK,
            Items.BREWING_STAND,
            ModPotions.TRANSMUTATION.get()
    );
    public static CustomBrewingRecipe TRANSMUTATION_STRONG_RECIPE = new CustomBrewingRecipe(
            ModPotions.TRANSMUTATION.get(),
            Items.GLOWSTONE_DUST,
            ModPotions.STRONG_TRANSMUTATION_POTION.get()
    );
    public static CustomBrewingRecipe TRANSMUTATION_LONG_RECIPE = new CustomBrewingRecipe(
            ModPotions.TRANSMUTATION.get(),
            Items.REDSTONE,
            ModPotions.LONG_TRANSMUTATION_POTION.get()
    );
}
