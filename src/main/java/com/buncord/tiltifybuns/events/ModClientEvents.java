package com.buncord.tiltifybuns.events;

import com.buncord.tiltifybuns.TiltifyBuns;
import com.buncord.tiltifybuns.init.EntityInit;
import com.buncord.tiltifybuns.models.ScalableRabbitModel;
import com.buncord.tiltifybuns.renderers.ScalableRabbitRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TiltifyBuns.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

  @SubscribeEvent
  public static void entityRenderers(RegisterRenderers event) {
    event.registerEntityRenderer(EntityInit.SCALABUN.get(), ScalableRabbitRenderer::new);
  }

  @SubscribeEvent
  public static void registerLayerDefinitions(RegisterLayerDefinitions event) {
    event.registerLayerDefinition(ScalableRabbitModel.LAYER_LOCATION, ScalableRabbitModel::createBodyLayer);
  }

}
