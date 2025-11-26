package com.techniphoenix.technienchants.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class TransmutationEffect extends Effect {
    protected TransmutationEffect() {
        super(EffectType.BENEFICIAL, 0x928E85);
    }

    // Since the effect is passive (damage reduction),
    // we don't need logic here. The event listener handles it.
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
}
