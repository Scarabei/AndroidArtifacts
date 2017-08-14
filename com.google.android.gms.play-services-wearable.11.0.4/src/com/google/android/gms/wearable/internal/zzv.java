package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;

final class zzv implements CapabilityApi.CapabilityListener {
   private CapabilityApi.CapabilityListener zzbRY;
   private String zzbRZ;

   zzv(CapabilityApi.CapabilityListener var1, String var2) {
      this.zzbRY = var1;
      this.zzbRZ = var2;
   }

   public final void onCapabilityChanged(CapabilityInfo var1) {
      this.zzbRY.onCapabilityChanged(var1);
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         zzv var2 = (zzv)var1;
         return !this.zzbRY.equals(var2.zzbRY) ? false : this.zzbRZ.equals(var2.zzbRZ);
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return this.zzbRY.hashCode() * 31 + this.zzbRZ.hashCode();
   }
}
