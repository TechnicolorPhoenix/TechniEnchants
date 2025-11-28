package com.techniphoenix.technienchants;

import com.techniphoenix.technienchants.effect.ModEffects;
import com.techniphoenix.technienchants.effect.recipe.GildedBlackstoneBrewingRecipe;
import com.techniphoenix.technienchants.effect.recipe.TransmutationBrewingRecipe;
import com.techniphoenix.technienchants.enchantment.ModEnchantments;
import com.techniphoenix.technienchants.event.ModEvents;
import com.techniphoenix.technienchants.loot_modifier.ModLootModifiers;
import com.techniphoenix.technienchants.item.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TechniEnchants.MOD_ID)
public class TechniEnchants
{
    public static final String MOD_ID = "technienchants";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public TechniEnchants() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEffects.register(eventBus);
        ModEnchantments.register(eventBus);
        ModPotions.register(eventBus);
        ModLootModifiers.register(eventBus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the event handler instance on the FORGE bus
        ModEvents.register(MinecraftForge.EVENT_BUS);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

        ItemStack stoneSkinPotionStack = PotionUtils.setPotion(
                new ItemStack(Items.POTION), ModPotions.STONESKIN.get()
        );
        ItemStack transmutationPotionStack = PotionUtils.setPotion(
                new ItemStack(Items.POTION), ModPotions.TRANSMUTATION.get()
        );

        event.enqueueWork(() -> {

            BrewingRecipeRegistry.addRecipe(new GildedBlackstoneBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(
                    Ingredient.of(stoneSkinPotionStack.getItem()),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(
                            new ItemStack(Items.POTION), ModPotions.LONG_STONESKIN_POTION.get()
                    )
            );
            BrewingRecipeRegistry.addRecipe(
                    Ingredient.of(stoneSkinPotionStack.getItem()),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(
                            new ItemStack(Items.POTION), ModPotions.STRONG_STONESKIN_POTION.get()
                    )
            );

            BrewingRecipeRegistry.addRecipe(new TransmutationBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(
                    Ingredient.of(transmutationPotionStack.getItem()),
                    Ingredient.of(Items.REDSTONE),
                    PotionUtils.setPotion(
                            new ItemStack(Items.POTION), ModPotions.LONG_TRANSMUTATION_POTION.get()
                    )
            );
            BrewingRecipeRegistry.addRecipe(
                    Ingredient.of(transmutationPotionStack.getItem()),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    PotionUtils.setPotion(
                            new ItemStack(Items.POTION), ModPotions.STRONG_TRANSMUTATION_POTION.get()
                    )
            );

        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo(MOD_ID, "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {

    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts

    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here

        }
    }
}
