package com.techniphoenix.technienchants.loot_modifier;

import com.google.gson.JsonObject;
import com.techniphoenix.technienchants.TechniEnchants;
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

public class TransmutationModifier extends LootModifier {

    private static final Random RANDOM = new Random();

    protected TransmutationModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
        TechniEnchants.LOGGER.debug("Instantiated.");
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {

        LivingEntity harvester = null;
        if (context.hasParam(LootParameters.THIS_ENTITY) && context.getParamOrNull(LootParameters.THIS_ENTITY) instanceof LivingEntity) {
            harvester = (LivingEntity) context.getParamOrNull(LootParameters.THIS_ENTITY);
        } else if (context.hasParam(LootParameters.KILLER_ENTITY) && context.getParamOrNull(LootParameters.KILLER_ENTITY) instanceof LivingEntity) {
            harvester = (LivingEntity) context.getParamOrNull(LootParameters.KILLER_ENTITY);
        }

        if (harvester == null || !harvester.hasEffect(ModEffects.TRANSMUTATION.get())) {
            return generatedLoot;
        }

        int transmutationLevel = harvester.getEffect(ModEffects.TRANSMUTATION.get()).getAmplifier() + 1;

        BlockState brokenState = context.getParamOrNull(LootParameters.BLOCK_STATE);

        ItemStack transmutedStack = ItemStack.EMPTY;

        float chance = 0.0f;
        int minCount = 0;
        int maxCount = 0;

        ResourceLocation blockId = brokenState.getBlock().getRegistryName();

        if (blockId.equals(new ResourceLocation("minecraft", "stone"))) {
            chance = 0.05f;
            minCount = 1; maxCount = 3;
            transmutedStack = new ItemStack(Items.COAL);
        } else if (blockId.equals(new ResourceLocation("minecraft", "coal_ore"))) {
            chance = 0.10f;
            minCount = 1; maxCount = 3;
            transmutedStack = new ItemStack(Items.IRON_NUGGET);
        } else if (blockId.equals(new ResourceLocation("minecraft", "iron_ore"))) {
            chance = 0.10f;
            minCount = 12; maxCount = 36;
            transmutedStack = new ItemStack(Items.GOLD_NUGGET);
        } else if (blockId.equals(new ResourceLocation("minecraft", "gold_ore"))) {
            chance = 0.10f;
            minCount = 3; maxCount = 5;
            transmutedStack = new ItemStack(Items.EMERALD);
        } else if (blockId.equals(new ResourceLocation("minecraft", "quartz_ore"))) {
            chance = 0.25f;
            minCount = 1; maxCount = 2;
            transmutedStack = new ItemStack(Items.GOLD_INGOT);
        } else if (blockId.equals(new ResourceLocation("minecraft", "sand"))) {
            chance = 0.01f;
            minCount = 1; maxCount = 3;
            transmutedStack = new ItemStack(Items.GLOWSTONE_DUST);
        } else if (blockId.equals(new ResourceLocation("minecraft", "lapis_ore"))) {
            chance = 0.01f;
            minCount = 1; maxCount = 1;
            transmutedStack = new ItemStack(Items.DIAMOND);
        } else if (blockId.equals(new ResourceLocation("minecraft", "blackstone"))) {
            chance = 0.01f;
            minCount = 1; maxCount = 1;
            transmutedStack = new ItemStack(Items.GILDED_BLACKSTONE);
        } else if (blockId.equals(new ResourceLocation("minecraft", "obsidian"))) {
            chance = 0.10f;
            minCount = 1; maxCount = 1;
            transmutedStack = new ItemStack(Items.CRYING_OBSIDIAN);
        }

        chance *= transmutationLevel;

        if (!transmutedStack.isEmpty() && RANDOM.nextFloat() < chance) {
            int dropCount = minCount + RANDOM.nextInt(maxCount - minCount + 1);
            transmutedStack.setCount(dropCount);

            return Collections.singletonList(transmutedStack);
        }

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<TransmutationModifier> {
        @Override
        public TransmutationModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            return new TransmutationModifier(ailootcondition);
        }

        @Override
        public JsonObject write(TransmutationModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}