package com.techniphoenix.technienchants.event;

import com.techniphoenix.technienchants.effect.TransmutationEffect;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModEvents {

    public static void register(IEventBus bus){
        bus.register(new CurseOfRustEventHandler());
        bus.register(new StoneskinEventHandler());
        bus.register(new StasisHandler());
        bus.register(new DeathDelayHandler());
    }
}
