package com.buncord.tiltifybuns;

import com.buncord.tiltifybuns.capabilities.ITiltifyBunsCapability;
import com.buncord.tiltifybuns.capabilities.TiltifyBunsCapability;
import com.buncord.tiltifybuns.capabilities.TiltifyBunsCapabilityProvider;
import com.buncord.tiltifybuns.init.EntityInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TiltifyBuns.MODID)
public class TiltifyBuns
{
    public static final String MODID = "tiltifybuns";
    public static final ResourceLocation RESOURCE_LOCATION =
        new ResourceLocation(MODID, "tiltifybuns_data");

    public static ITiltifyBunsCapability getCapability(Level level) {
        return level.getCapability(TiltifyBunsCapabilityProvider.TILTIFYBUNS_CAPABILITY)
                    .orElse(new TiltifyBunsCapability());
    }

    public TiltifyBuns() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityInit.ENTITIES.register(bus);
    }
}
