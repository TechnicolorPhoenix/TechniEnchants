package com.techniphoenix.technienchants.event;

import com.techniphoenix.technienchants.TechniEnchants;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModLootModifiers {
    // DeferredRegister for GlobalLootModifierSerializers
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, TechniEnchants.MOD_ID);

    // Register our custom serializer
    public static final RegistryObject<TransmutationModifier.Serializer> TRANSMUTATION = GLM_SERIALIZERS.register("transmutation_modifier",
            TransmutationModifier.Serializer::new);

    public static void register(IEventBus eventBus) {
        GLM_SERIALIZERS.register(eventBus);
    }
}
