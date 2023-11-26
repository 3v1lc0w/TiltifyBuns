package com.buncord.tiltifybuns.capabilities;

import com.google.gson.Gson;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TiltifyBunsCapabilityProvider implements ICapabilityProvider,
                                                     ICapabilitySerializable<CompoundTag>
{
  public static final Capability<ITiltifyBunsCapability> TILTIFYBUNS_CAPABILITY =
      CapabilityManager.get(new CapabilityToken<>() {});

  private TiltifyBunsCapability tiltifyBunsCapability = new TiltifyBunsCapability();

  @NotNull
  @Override
  public <T> LazyOptional<T> getCapability(
      @NotNull Capability<T> capability,
      @Nullable Direction direction
  )
  {
    return (LazyOptional<T>) LazyOptional.of(() -> this.tiltifyBunsCapability);
  }

  @NotNull
  public <T> LazyOptional<T> getCapability(
      @NotNull Capability<T> capability
  ) {
    return (LazyOptional<T>) LazyOptional.of(() -> this.tiltifyBunsCapability);
  }

  @Override
  public CompoundTag serializeNBT() {
    CompoundTag compoundTag = new CompoundTag();

    compoundTag.putString(
        "tiltifyBunsCapability",
        new Gson().toJson(this.tiltifyBunsCapability)
    );

    return compoundTag;
  }

  @Override
  public void deserializeNBT(CompoundTag compoundTag) {
    this.tiltifyBunsCapability = new Gson().fromJson(
        compoundTag.getString("tiltifyBunsCapability"),
        TiltifyBunsCapability.class
    );
  }

}
