package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.nearby.messages.BleSignal;
import java.util.Arrays;

public final class zza extends com.google.android.gms.common.internal.safeparcel.zza implements BleSignal {
   public static final Creator CREATOR = new zzb();
   private int zzaku;
   private int zzbyS;
   private int zzbyT;

   zza(int var1, int var2, int var3) {
      this.zzaku = var1;
      this.zzbyS = var2;
      this.zzbyT = -169 < var3 && var3 < 87 ? var3 : Integer.MIN_VALUE;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbyS);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbyT);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int getRssi() {
      return this.zzbyS;
   }

   public final int getTxPower() {
      return this.zzbyT;
   }

   public final String toString() {
      int var1 = this.zzbyS;
      int var2 = this.zzbyT;
      return (new StringBuilder(48)).append("BleSignal{rssi=").append(var1).append(", txPower=").append(var2).append("}").toString();
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof BleSignal)) {
         return false;
      } else {
         BleSignal var2 = (BleSignal)var1;
         return this.zzbyS == var2.getRssi() && this.zzbyT == var2.getTxPower();
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbyS, this.zzbyT});
   }
}
