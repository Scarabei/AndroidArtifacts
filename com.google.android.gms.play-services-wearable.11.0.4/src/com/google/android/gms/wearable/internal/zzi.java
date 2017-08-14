package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzi extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzj();
   private byte zzbRN;
   private final byte zzbRO;
   private final String mValue;

   public zzi(byte var1, byte var2, String var3) {
      this.zzbRN = var1;
      this.zzbRO = var2;
      this.mValue = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbRN);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbRO);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.mValue, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      byte var1 = this.zzbRN;
      byte var2 = this.zzbRO;
      String var3 = this.mValue;
      return (new StringBuilder(73 + String.valueOf(var3).length())).append("AmsEntityUpdateParcelable{, mEntityId=").append(var1).append(", mAttributeId=").append(var2).append(", mValue='").append(var3).append("'}").toString();
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         zzi var2 = (zzi)var1;
         if (this.zzbRN != var2.zzbRN) {
            return false;
         } else if (this.zzbRO != var2.zzbRO) {
            return false;
         } else {
            return this.mValue.equals(var2.mValue);
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      int var1;
      return ((var1 = 31 + this.zzbRN) * 31 + this.zzbRO) * 31 + this.mValue.hashCode();
   }
}
