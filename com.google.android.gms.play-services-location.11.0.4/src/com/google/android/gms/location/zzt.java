package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzt extends com.google.android.gms.common.internal.safeparcel.zza {
   private final String zzbie;
   private final String zzbif;
   private final int zzbig;
   private final boolean zzbih;
   public static final Creator CREATOR = new zzu();

   zzt(String var1, String var2, int var3, boolean var4) {
      this.zzbie = var1;
      this.zzbif = var2;
      this.zzbig = var3;
      this.zzbih = var4;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbie, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbif, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbig);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbih);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
