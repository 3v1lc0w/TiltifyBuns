package com.buncord.tiltifybuns.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ScalableRabbit extends Rabbit {

  private static final EntityDataAccessor<Float> DATA_DONO_SCALE =
      SynchedEntityData.defineId(ScalableRabbit.class, EntityDataSerializers.FLOAT);

  public ScalableRabbit(
      EntityType<? extends Rabbit> rabbit,
      Level level
  )
  {
    super(rabbit, level);
  }

  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_DONO_SCALE, 0.6F);
  }

  public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> entityDataAccessor) {
    if (DATA_DONO_SCALE.equals(entityDataAccessor)) {
      super.getDimensions(this.getPose()).scale(this.entityData.get(DATA_DONO_SCALE));
      this.refreshDimensions();
    }
  }

  public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
    super.addAdditionalSaveData(compoundTag);
    compoundTag.putFloat("DonoScale", this.getScale());
  }

  public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
    super.readAdditionalSaveData(compoundTag);
    this.setScale(compoundTag.getFloat("DonoScale"));
  }

  public float getScale() {
//    return 30.0F;
    return this.entityData.get(DATA_DONO_SCALE);
  }

  public void setScale(float scale) {
    this.entityData.set(DATA_DONO_SCALE, scale);
  }

  public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
    return EntityDimensions.scalable(0.4f, 0.5f).scale(this.getScale() / 0.6F);
  }

}
