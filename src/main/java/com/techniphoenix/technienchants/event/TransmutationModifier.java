package com.techniphoenix.technienchants.event;

import com.google.gson.JsonObject;
import com.techniphoenix.technienchants.effect.ModEffects;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TransmutationModifier extends LootModifier {

    // Static definition of the transmutation rules
    // Using a map would be cleaner, but for simplicity, we keep the rules defined in the apply method.
    private static final Random RANDOM = new Random();

    protected TransmutationModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {

        // --- 1. Condition Check: Entity must have the MobEffect ---

        // Get the entity that is responsible for the loot (the block harvester)
        LivingEntity harvester = null;
        if (context.hasParam(LootParameters.THIS_ENTITY) && context.getParamOrNull(LootParameters.THIS_ENTITY) instanceof LivingEntity) {
            harvester = (LivingEntity) context.getParamOrNull(LootParameters.THIS_ENTITY);
        } else if (context.hasParam(LootParameters.KILLER_ENTITY) && context.getParamOrNull(LootParameters.KILLER_ENTITY) instanceof LivingEntity) {
            // Fallback/alternative context parameter for the entity causing the drop
            harvester = (LivingEntity) context.getParamOrNull(LootParameters.KILLER_ENTITY);
        }

        // If there is no harvester, or the harvester does not have the transmutation effect, return the original loot.
        if (harvester == null || !harvester.hasEffect(ModEffects.TRANSMUTATION.get())) {
            return generatedLoot;
        }

        // --- 2. Transmutation Logic ---

        // This loot modifier is only applied to the blocks listed in the JSON file.
        // If we reach here, the block is one of the target blocks (Stone, Coal, Sand, etc.).

        // The block state is guaranteed to be available in the BLOCK context used by GLMs for blocks.
        BlockState brokenState = context.getParamOrNull(LootParameters.BLOCK_STATE);

        ItemStack transmutedStack = ItemStack.EMPTY;

        // Use a Stream to collect the new loot, starting with the original loot
        Stream<ItemStack> lootStream = generatedLoot.stream();

        // --- Transmutation Rules (Replicated from your request) ---
        // Note: The blocks targeted by this rule are defined in the accompanying JSON file.

        // The `generatedLoot` list contains the default drops (e.g., Cobblestone, Coal).
        // Since transmutation replaces the drop entirely, we clear the original loot later if successful.

        float chance = 0.0f;
        int minCount = 0;
        int maxCount = 0;

        ResourceLocation blockId = brokenState.getBlock().getRegistryName();

        if (blockId.equals(new ResourceLocation("minecraft", "stone"))) {
            // Stone breaks, 1-3 Gold Nugget drops, 5%
            chance = 1.00f;
            minCount = 1; maxCount = 3;
            transmutedStack = new ItemStack(Items.GOLD_NUGGET);
        } else if (blockId.equals(new ResourceLocation("minecraft", "coal_ore"))) {
            // Coal breaks, 1-3 Iron Nugget drops, 10%
            chance = 0.10f;
            minCount = 1; maxCount = 3;
            transmutedStack = new ItemStack(Items.IRON_NUGGET);
        } else if (blockId.equals(new ResourceLocation("minecraft", "sand"))) {
            // Sand breaks, 1-3 Glowstone Dust drops, 1%
            chance = 0.01f;
            minCount = 1; maxCount = 3;
            transmutedStack = new ItemStack(Items.GLOWSTONE_DUST);
        } else if (blockId.equals(new ResourceLocation("minecraft", "lapis_ore"))) {
            // Lapis Lazuli Ore breaks, Diamond drops, 1%
            chance = 0.01f;
            minCount = 1; maxCount = 1;
            transmutedStack = new ItemStack(Items.DIAMOND);
        } else if (blockId.equals(new ResourceLocation("minecraft", "blackstone"))) {
            // Blackstone breaks, Gilded Blackstone drops, 1%
            chance = 0.01f;
            minCount = 1; maxCount = 1;
            transmutedStack = new ItemStack(Items.GILDED_BLACKSTONE);
        } else if (blockId.equals(new ResourceLocation("minecraft", "obsidian"))) {
            // Obsidian breaks, crying obsidian drops, 10%
            chance = 0.10f;
            minCount = 1; maxCount = 1;
            transmutedStack = new ItemStack(Items.CRYING_OBSIDIAN);
        }

        // Apply chance check
        if (!transmutedStack.isEmpty() && RANDOM.nextFloat() < chance) {
            // SUCCESS: Replace the original loot with the transmuted item
            int dropCount = minCount + RANDOM.nextInt(maxCount - minCount + 1);
            transmutedStack.setCount(dropCount);

            // Return only the transmuted item
            return Collections.singletonList(transmutedStack);
        }

        // If the chance fails, or no rule matched (which shouldn't happen based on the JSON),
        // we return the original generated loot. Since the JSON targets blocks that usually drop an item,
        // this keeps the original drops. However, for a complete replacement effect, you might want to return
        // an empty list here if the block was matched but the chance failed.
        // For now, we return original loot to be safe.
        return generatedLoot;
    }

    // Serializer is required to load the JSON
    public static class Serializer extends GlobalLootModifierSerializer<TransmutationModifier> {
        @Override
        public TransmutationModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            // The modifier itself takes no special JSON properties, only standard conditions.
            return new TransmutationModifier(ailootcondition);
        }

        @Override
        public JsonObject write(TransmutationModifier instance) {
            // Writes out the necessary conditions (which are handled by LootModifier superclass)
            return makeConditions(instance.conditions);
        }
    }
}