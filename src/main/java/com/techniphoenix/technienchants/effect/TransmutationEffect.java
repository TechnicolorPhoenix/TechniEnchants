package com.techniphoenix.technienchants.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class TransmutationEffect extends Effect {

    protected TransmutationEffect() {
        super(EffectType.BENEFICIAL, 0x928E85);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
}
