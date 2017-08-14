package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class PeriodicTask extends Task {
   protected long mIntervalInSeconds;
   protected long mFlexInSeconds;
   public static final Creator CREATOR = new zzh();

   private PeriodicTask(PeriodicTask.Builder var1) {
      super((Task.Builder)var1);
      this.mIntervalInSeconds = -1L;
      this.mFlexInSeconds = -1L;
      this.mIntervalInSeconds = var1.zzbfZ;
      this.mFlexInSeconds = Math.min(var1.zzbga, this.mIntervalInSeconds);
   }

   /** @deprecated */
   @Deprecated
   private PeriodicTask(Parcel var1) {
      super(var1);
      this.mIntervalInSeconds = -1L;
      this.mFlexInSeconds = -1L;
      this.mIntervalInSeconds = var1.readLong();
      this.mFlexInSeconds = Math.min(var1.readLong(), this.mIntervalInSeconds);
   }

   public void toBundle(Bundle var1) {
      super.toBundle(var1);
      var1.putLong("period", this.mIntervalInSeconds);
      var1.putLong("period_flex", this.mFlexInSeconds);
   }

   public void writeToParcel(Parcel var1, int var2) {
      super.writeToParcel(var1, var2);
      var1.writeLong(this.mIntervalInSeconds);
      var1.writeLong(this.mFlexInSeconds);
   }

   public long getPeriod() {
      return this.mIntervalInSeconds;
   }

   public long getFlex() {
      return this.mFlexInSeconds;
   }

   public String toString() {
      String var1 = String.valueOf(super.toString());
      long var2 = this.getPeriod();
      long var4 = this.getFlex();
      return (new StringBuilder(54 + String.valueOf(var1).length())).append(var1).append(" period=").append(var2).append(" flex=").append(var4).toString();
   }

   // $FF: synthetic method
   PeriodicTask(Parcel var1, zzh var2) {
      this(var1);
   }

   // $FF: synthetic method
   PeriodicTask(PeriodicTask.Builder var1, zzh var2) {
      this(var1);
   }

   public static class Builder extends Task.Builder {
      private long zzbfZ = -1L;
      private long zzbga = -1L;

      public Builder() {
         this.isPersisted = true;
      }

      public PeriodicTask.Builder setPeriod(long var1) {
         this.zzbfZ = var1;
         return this;
      }

      public PeriodicTask.Builder setFlex(long var1) {
         this.zzbga = var1;
         return this;
      }

      public PeriodicTask.Builder setService(Class var1) {
         this.gcmTaskService = var1.getName();
         return this;
      }

      public PeriodicTask.Builder setRequiredNetwork(int var1) {
         this.requiredNetworkState = var1;
         return this;
      }

      public PeriodicTask.Builder setRequiresCharging(boolean var1) {
         this.requiresCharging = var1;
         return this;
      }

      public PeriodicTask.Builder setTag(String var1) {
         this.tag = var1;
         return this;
      }

      public PeriodicTask.Builder setPersisted(boolean var1) {
         this.isPersisted = var1;
         return this;
      }

      public PeriodicTask.Builder setUpdateCurrent(boolean var1) {
         this.updateCurrent = var1;
         return this;
      }

      public PeriodicTask.Builder setExtras(Bundle var1) {
         this.extras = var1;
         return this;
      }

      public PeriodicTask build() {
         this.checkConditions();
         return new PeriodicTask(this, (zzh)null);
      }

      protected void checkConditions() {
         super.checkConditions();
         if (this.zzbfZ == -1L) {
            throw new IllegalArgumentException("Must call setPeriod(long) to establish an execution interval for this periodic task.");
         } else if (this.zzbfZ <= 0L) {
            long var1 = this.zzbfZ;
            throw new IllegalArgumentException((new StringBuilder(66)).append("Period set cannot be less than or equal to 0: ").append(var1).toString());
         } else if (this.zzbga == -1L) {
            this.zzbga = (long)((float)this.zzbfZ * 0.1F);
         } else {
            if (this.zzbga > this.zzbfZ) {
               this.zzbga = this.zzbfZ;
            }

         }
      }
   }
}
