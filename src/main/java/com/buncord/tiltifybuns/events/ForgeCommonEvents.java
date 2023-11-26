package com.buncord.tiltifybuns.events;

import com.buncord.tiltifybuns.TiltifyBuns;
import com.buncord.tiltifybuns.capabilities.TiltifyBunsCapabilityProvider;
import com.buncord.tiltifybuns.commands.TiltifyBunsCommand;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TiltifyBuns.MODID, bus = Bus.FORGE)
public class ForgeCommonEvents {

  @SubscribeEvent
  public static void onAttachCapabilitiesEvent(final AttachCapabilitiesEvent<Level> event) {
    final Level level = event.getObject();

    if (!level.isClientSide()) {
      event.addCapability(TiltifyBuns.RESOURCE_LOCATION, new TiltifyBunsCapabilityProvider());
    }
  }

  @SubscribeEvent
  public static void registerCommands(RegisterCommandsEvent event){
    TiltifyBunsCommand.register(event.getDispatcher());
  }

}
