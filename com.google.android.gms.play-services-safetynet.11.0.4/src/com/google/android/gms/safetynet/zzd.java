package com.google.android.gms.safetynet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzd extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zze();
   public final long zzbBG;
   public final HarmfulAppsData[] zzbBH;

   public zzd(long var1, HarmfulAppsData[] var3) {
      this.zzbBG = var1;
      this.zzbBH = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbBG);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbBH, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
