package com.buncord.tiltifybuns.events;

import com.buncord.tiltifybuns.TiltifyBuns;
import com.buncord.tiltifybuns.capabilities.ITiltifyBunsCapability;
import com.buncord.tiltifybuns.entities.ScalableRabbit;
import com.buncord.tiltifybuns.init.EntityInit;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TiltifyBuns.MODID, bus = Bus.MOD)
public class ModCommonEvents {

  @SubscribeEvent
  public static void capabilityRegister(RegisterCapabilitiesEvent event) {
    event.register(ITiltifyBunsCapability.class);
  }

  @SubscribeEvent
  public static void entityAttributes(EntityAttributeCreationEvent event) {
    event.put(EntityInit.SCALABUN.get(), ScalableRabbit.createAttributes().build());
  }

}
