package com.buncord.tiltifybuns.models;

import com.buncord.tiltifybuns.TiltifyBuns;
import com.buncord.tiltifybuns.entities.ScalableRabbit;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ScalableRabbitModel<T extends ScalableRabbit> extends EntityModel<T> {
  public static final ModelLayerLocation LAYER_LOCATION =
      new ModelLayerLocation(new ResourceLocation(TiltifyBuns.MODID, "scalabun"), "main");

  private final ModelPart leftRearFoot;
  private final ModelPart rightRearFoot;
  private final ModelPart leftHaunch;
  private final ModelPart rightHaunch;
  private final ModelPart body;
  private final ModelPart leftFrontLeg;
  private final ModelPart rightFrontLeg;
  private final ModelPart head;
  private final ModelPart rightEar;
  private final ModelPart leftEar;
  private final ModelPart tail;
  private final ModelPart nose;

  private float jumpRotation;
  private float scale = 0.6F;

  public ScalableRabbitModel(ModelPart p_170881_) {
    this.leftRearFoot = p_170881_.getChild("left_hind_foot");
    this.rightRearFoot = p_170881_.getChild("right_hind_foot");
    this.leftHaunch = p_170881_.getChild("left_haunch");
    this.rightHaunch = p_170881_.getChild("right_haunch");
    this.body = p_170881_.getChild("body");
    this.leftFrontLeg = p_170881_.getChild("left_front_leg");
    this.rightFrontLeg = p_170881_.getChild("right_front_leg");
    this.head = p_170881_.getChild("head");
    this.rightEar = p_170881_.getChild("right_ear");
    this.leftEar = p_170881_.getChild("left_ear");
    this.tail = p_170881_.getChild("tail");
    this.nose = p_170881_.getChild("nose");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();
    partdefinition.addOrReplaceChild("left_hind_foot", CubeListBuilder.create().texOffs(26, 24).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F), PartPose.offset(3.0F, 17.5F, 3.7F));
    partdefinition.addOrReplaceChild("right_hind_foot", CubeListBuilder.create().texOffs(8, 24).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F), PartPose.offset(-3.0F, 17.5F, 3.7F));
    partdefinition.addOrReplaceChild("left_haunch", CubeListBuilder.create().texOffs(30, 15).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F), PartPose.offsetAndRotation(3.0F, 17.5F, 3.7F, -0.34906584F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("right_haunch", CubeListBuilder.create().texOffs(16, 15).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F), PartPose.offsetAndRotation(-3.0F, 17.5F, 3.7F, -0.34906584F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -10.0F, 6.0F, 5.0F, 10.0F), PartPose.offsetAndRotation(0.0F, 19.0F, 8.0F, -0.34906584F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(8, 15).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F), PartPose.offsetAndRotation(3.0F, 17.0F, -1.0F, -0.17453292F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F), PartPose.offsetAndRotation(-3.0F, 17.0F, -1.0F, -0.17453292F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -4.0F, -5.0F, 5.0F, 4.0F, 5.0F), PartPose.offset(0.0F, 16.0F, -1.0F));
    partdefinition.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(52, 0).addBox(-2.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 16.0F, -1.0F, 0.0F, -0.2617994F, 0.0F));
    partdefinition.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(58, 0).addBox(0.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 16.0F, -1.0F, 0.0F, 0.2617994F, 0.0F));
    partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(52, 6).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 20.0F, 7.0F, -0.3490659F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(32, 9).addBox(-0.5F, -2.5F, -5.5F, 1.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 16.0F, -1.0F));
    return LayerDefinition.create(meshdefinition, 64, 32);
  }

  public void renderToBuffer(
      PoseStack poseStack,
      @NotNull VertexConsumer vertexConsumer,
      int uv2,
      int overlayCoords,
      float r,
      float g,
      float b,
      float a
  ) {
    poseStack.pushPose();

    poseStack.scale(scale, scale, scale);

    // scale - translation
    // 0.6   - +1.0
    // 1.2   - -0.3
    // 1.8   - -0.7
    // 2.4   - -0.9
    // 3.0   - -1.0F
    // 30.0  - -1.45F

    poseStack.translate(0.0F, 1.47F * Math.pow(scale, -1.0F) - 1.5F, 0.0F);

    ImmutableList.of(
        this.leftRearFoot,
        this.rightRearFoot,
        this.leftHaunch,
        this.rightHaunch,
        this.body,
        this.leftFrontLeg,
        this.rightFrontLeg,
        this.head,
        this.rightEar,
        this.leftEar,
        this.tail,
        this.nose
    ).forEach((modelPart) ->
        modelPart.render(poseStack, vertexConsumer, uv2, overlayCoords, r, g, b, a)
    );
    poseStack.popPose();
  }

  public void setupAnim(
      T entity,
      float animationPosition,
      float animationSpeed,
      float partialTick,
      float headYRot,
      float headXRot
  ) {
    float f = partialTick - (float)entity.tickCount;
    this.nose.xRot = headXRot * ((float)Math.PI / 180F);
    this.head.xRot = headXRot * ((float)Math.PI / 180F);
    this.rightEar.xRot = headXRot * ((float)Math.PI / 180F);
    this.leftEar.xRot = headXRot * ((float)Math.PI / 180F);
    this.nose.yRot = headYRot * ((float)Math.PI / 180F);
    this.head.yRot = headYRot * ((float)Math.PI / 180F);
    this.rightEar.yRot = this.nose.yRot - 0.2617994F;
    this.leftEar.yRot = this.nose.yRot + 0.2617994F;
    this.jumpRotation = Mth.sin(entity.getJumpCompletion(f) * (float)Math.PI);
    this.leftHaunch.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
    this.rightHaunch.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
    this.leftRearFoot.xRot = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
    this.rightRearFoot.xRot = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
    this.leftFrontLeg.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
    this.rightFrontLeg.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
  }

  public void prepareMobModel(
      @NotNull T entity,
      float animationPosition,
      float animationSpeed,
      float partialTick
  ) {
    super.prepareMobModel(entity, animationPosition, animationSpeed, partialTick);
    this.jumpRotation = Mth.sin(entity.getJumpCompletion(partialTick) * (float)Math.PI);
  }

  public float getScale() {
    return scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
  }

}
