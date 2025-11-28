package com.techniphoenix.technienchants.item;

import com.techniphoenix.technienchants.TechniEnchants;
import com.techniphoenix.technienchants.effect.ModEffects;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, TechniEnchants.MOD_ID);

    // 1. Regular Potion (Potion of Do Nothing)
    public static final RegistryObject<Potion> STONESKIN = POTIONS.register("stoneskin_potion",
            () -> new Potion(new EffectInstance(ModEffects.STONESKIN.get(), 3600))); // 3600 ticks = 3 minutes

    public static final RegistryObject<Potion> LONG_STONESKIN_POTION = POTIONS.register("long_stoneskin_potion",
            () -> new Potion("long_stoneskin_potion", new EffectInstance(ModEffects.STONESKIN.get(), 9600))); // 9600 ticks = 8 minutes

    public static final RegistryObject<Potion> STRONG_STONESKIN_POTION = POTIONS.register("strong_stoneskin_potion",
            () -> new Potion("strong_stoneskin_potion", new EffectInstance(ModEffects.STONESKIN.get(), 1800, 5))); // 1800 ticks = 1.5 min, level 5 (amplifier +25% damage reduction)

    // 1. Regular Potion (Potion of Do Nothing)
    public static final RegistryObject<Potion> TRANSMUTATION = POTIONS.register("transmutation_potion",
            () -> new Potion(new EffectInstance(ModEffects.TRANSMUTATION.get(), 3600))); // 3600 ticks = 3 minutes
    public static final RegistryObject<Potion> LONG_TRANSMUTATION_POTION = POTIONS.register("long_transmutation_potion",
            () -> new Potion("long_transmutation_potion", new EffectInstance(ModEffects.TRANSMUTATION.get(), 9600))); // 9600 ticks = 8 minutes
    public static final RegistryObject<Potion> STRONG_TRANSMUTATION_POTION = POTIONS.register("strong_transmutation_potion",
            () -> new Potion("strong_transmutation_potion", new EffectInstance(ModEffects.TRANSMUTATION.get(), 1800, 1))); // 1800 ticks = 1.5 min, level 2 (double chances)

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
