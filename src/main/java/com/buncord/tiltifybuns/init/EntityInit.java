package com.buncord.tiltifybuns.init;

import com.buncord.tiltifybuns.TiltifyBuns;
import com.buncord.tiltifybuns.entities.ScalableRabbit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

  public static final DeferredRegister<EntityType<?>> ENTITIES =
      DeferredRegister.create(ForgeRegistries.ENTITIES, TiltifyBuns.MODID);

  public static final RegistryObject<EntityType<ScalableRabbit>> SCALABUN = ENTITIES.register(
      "scalabun",
      () -> EntityType.Builder.of(ScalableRabbit::new, MobCategory.CREATURE)
                              .sized(0.4F, 0.5F)
                              .clientTrackingRange(8)
                              .build(TiltifyBuns.MODID + ":scalabun")
  );

}
