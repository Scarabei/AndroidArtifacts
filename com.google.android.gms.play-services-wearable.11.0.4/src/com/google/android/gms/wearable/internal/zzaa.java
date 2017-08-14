package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.wearable.CapabilityInfo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class zzaa extends com.google.android.gms.common.internal.safeparcel.zza implements CapabilityInfo {
   public static final Creator CREATOR = new zzab();
   private final Object mLock = new Object();
   private final String mName;
   private final List zzbSd;
   private Set zzbSa;

   public zzaa(String var1, List var2) {
      this.mName = var1;
      this.zzbSd = var2;
      this.zzbSa = null;
      com.google.android.gms.common.internal.zzbo.zzu(this.mName);
      com.google.android.gms.common.internal.zzbo.zzu(this.zzbSd);
   }

   public final String getName() {
      return this.mName;
   }

   public final Set getNodes() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzbSa == null) {
            this.zzbSa = new HashSet(this.zzbSd);
         }

         return this.zzbSa;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbSd, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      String var1 = this.mName;
      String var2 = String.valueOf(this.zzbSd);
      return (new StringBuilder(18 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("CapabilityInfo{").append(var1).append(", ").append(var2).append("}").toString();
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         zzaa var2 = (zzaa)var1;
         if (this.mName != null) {
            if (!this.mName.equals(var2.mName)) {
               return false;
            }
         } else if (var2.mName != null) {
            return false;
         }

         if (this.zzbSd != null) {
            if (this.zzbSd.equals(var2.zzbSd)) {
               return true;
            }
         } else if (var2.zzbSd == null) {
            return true;
         }

         return false;
      } else {
         return false;
      }
   }

   public final int hashCode() {
      int var1;
      return (var1 = 31 + (this.mName != null ? this.mName.hashCode() : 0)) * 31 + (this.zzbSd != null ? this.zzbSd.hashCode() : 0);
   }
}
