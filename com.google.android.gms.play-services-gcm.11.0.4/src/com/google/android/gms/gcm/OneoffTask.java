package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class OneoffTask extends Task {
   private final long zzbfV;
   private final long zzbfW;
   public static final Creator CREATOR = new zzf();

   private OneoffTask(OneoffTask.Builder var1) {
      super((Task.Builder)var1);
      this.zzbfV = var1.zzbfX;
      this.zzbfW = var1.zzbfY;
   }

   /** @deprecated */
   @Deprecated
   private OneoffTask(Parcel var1) {
      super(var1);
      this.zzbfV = var1.readLong();
      this.zzbfW = var1.readLong();
   }

   public void toBundle(Bundle var1) {
      super.toBundle(var1);
      var1.putLong("window_start", this.zzbfV);
      var1.putLong("window_end", this.zzbfW);
   }

   public long getWindowStart() {
      return this.zzbfV;
   }

   public long getWindowEnd() {
      return this.zzbfW;
   }

   public void writeToParcel(Parcel var1, int var2) {
      super.writeToParcel(var1, var2);
      var1.writeLong(this.zzbfV);
      var1.writeLong(this.zzbfW);
   }

   public String toString() {
      String var1 = String.valueOf(super.toString());
      long var2 = this.getWindowStart();
      long var4 = this.getWindowEnd();
      return (new StringBuilder(64 + String.valueOf(var1).length())).append(var1).append(" windowStart=").append(var2).append(" windowEnd=").append(var4).toString();
   }

   // $FF: synthetic method
   OneoffTask(Parcel var1, zzf var2) {
      this(var1);
   }

   // $FF: synthetic method
   OneoffTask(OneoffTask.Builder var1, zzf var2) {
      this(var1);
   }

   public static class Builder extends Task.Builder {
      private long zzbfX = -1L;
      private long zzbfY = -1L;

      public Builder() {
         this.isPersisted = false;
      }

      public OneoffTask.Builder setExecutionWindow(long var1, long var3) {
         this.zzbfX = var1;
         this.zzbfY = var3;
         return this;
      }

      public OneoffTask.Builder setService(Class var1) {
         this.gcmTaskService = var1.getName();
         return this;
      }

      public OneoffTask.Builder setRequiredNetwork(int var1) {
         this.requiredNetworkState = var1;
         return this;
      }

      public OneoffTask.Builder setRequiresCharging(boolean var1) {
         this.requiresCharging = var1;
         return this;
      }

      public OneoffTask.Builder setTag(String var1) {
         this.tag = var1;
         return this;
      }

      public OneoffTask.Builder setPersisted(boolean var1) {
         this.isPersisted = var1;
         return this;
      }

      public OneoffTask.Builder setUpdateCurrent(boolean var1) {
         this.updateCurrent = var1;
         return this;
      }

      public OneoffTask.Builder setExtras(Bundle var1) {
         this.extras = var1;
         return this;
      }

      protected void checkConditions() {
         super.checkConditions();
         if (this.zzbfX != -1L && this.zzbfY != -1L) {
            if (this.zzbfX >= this.zzbfY) {
               throw new IllegalArgumentException("Window start must be shorter than window end.");
            }
         } else {
            throw new IllegalArgumentException("Must specify an execution window using setExecutionWindow.");
         }
      }

      public OneoffTask build() {
         this.checkConditions();
         return new OneoffTask(this, (zzf)null);
      }
   }
}
