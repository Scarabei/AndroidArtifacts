package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzd extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zze();
   private zzdk zzbRJ;
   private IntentFilter[] zzbRK;
   private String zzbRL;
   private String zzbRM;

   zzd(IBinder var1, IntentFilter[] var2, String var3, String var4) {
      if (var1 != null) {
         IInterface var6;
         this.zzbRJ = (zzdk)(var1 == null ? null : ((var6 = var1.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener")) instanceof zzdk ? (zzdk)var6 : new zzdm(var1)));
      } else {
         this.zzbRJ = null;
      }

      this.zzbRK = var2;
      this.zzbRL = var3;
      this.zzbRM = var4;
   }

   public zzd(zzga var1) {
      this.zzbRJ = var1;
      this.zzbRK = var1.zzDY();
      this.zzbRL = var1.zzDZ();
      this.zzbRM = null;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbRJ == null ? null : this.zzbRJ.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbRK, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbRL, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbRM, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
