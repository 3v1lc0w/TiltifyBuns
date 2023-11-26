package com.buncord.tiltifybuns.renderers;

import com.buncord.tiltifybuns.entities.ScalableRabbit;
import com.buncord.tiltifybuns.models.ScalableRabbitModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ScalableRabbitRenderer extends MobRenderer<ScalableRabbit, ScalableRabbitModel<ScalableRabbit>> {
  private static final ResourceLocation RABBIT_BROWN_LOCATION = new ResourceLocation("textures/entity/rabbit/brown.png");
  private static final ResourceLocation RABBIT_WHITE_LOCATION = new ResourceLocation("textures/entity/rabbit/white.png");
  private static final ResourceLocation RABBIT_BLACK_LOCATION = new ResourceLocation("textures/entity/rabbit/black.png");
  private static final ResourceLocation RABBIT_GOLD_LOCATION = new ResourceLocation("textures/entity/rabbit/gold.png");
  private static final ResourceLocation RABBIT_SALT_LOCATION = new ResourceLocation("textures/entity/rabbit/salt.png");
  private static final ResourceLocation RABBIT_WHITE_SPLOTCHED_LOCATION = new ResourceLocation("textures/entity/rabbit/white_splotched.png");
  private static final ResourceLocation RABBIT_TOAST_LOCATION = new ResourceLocation("textures/entity/rabbit/toast.png");
  private static final ResourceLocation RABBIT_EVIL_LOCATION = new ResourceLocation("textures/entity/rabbit/caerbannog.png");

  public ScalableRabbitRenderer(EntityRendererProvider.Context context) {
    super(context, new ScalableRabbitModel<>(context.bakeLayer(ScalableRabbitModel.LAYER_LOCATION)), 0.3F);
  }

  public ResourceLocation getTextureLocation(ScalableRabbit p_115803_) {
    String s = ChatFormatting.stripFormatting(p_115803_.getName().getString());
    if ("Toast".equals(s)) {
      return RABBIT_TOAST_LOCATION;
    } else {
      return switch (p_115803_.getRabbitType()) {
        default -> RABBIT_BROWN_LOCATION;
        case 1 -> RABBIT_WHITE_LOCATION;
        case 2 -> RABBIT_BLACK_LOCATION;
        case 3 -> RABBIT_WHITE_SPLOTCHED_LOCATION;
        case 4 -> RABBIT_GOLD_LOCATION;
        case 5 -> RABBIT_SALT_LOCATION;
        case 99 -> RABBIT_EVIL_LOCATION;
      };
    }
  }

  public void render(
      @NotNull ScalableRabbit rabbit,
      float p_115309_,
      float partialRenderTick,
      @NotNull PoseStack matrixStack,
      @NotNull MultiBufferSource buffers,
      int light
  ) {
    this.model.setScale(rabbit.getScale());

    super.render(
        rabbit,
        p_115309_,
        partialRenderTick,
        matrixStack,
        buffers,
        light
    );
  }

}
